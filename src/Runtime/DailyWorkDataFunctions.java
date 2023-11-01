package Runtime;

import BusinessIO.FileProcessor;
import BusinessObjects.Employee;
import BusinessObjects.EmployeeData;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

public class DailyWorkDataFunctions {

    private static final String folderRelativePath = "Database/DailyWorkDataFiles";

    private final EmployeeData<Employee> EmployeeData;

    private final int numberOfDaysPassed;

    private ArrayList<ArrayList<String>> logsOfNDaysPassed;

    public DailyWorkDataFunctions(EmployeeData<Employee> EmployeeData, int numberOfDaysPassed){
        this.EmployeeData = EmployeeData;
        this.numberOfDaysPassed = numberOfDaysPassed;
    }

    public void setDailyWorkRawData(){

        File[] files = new File(folderRelativePath).listFiles();
        ArrayList<String> paths = new ArrayList<>();

        assert files != null;
        for(File file: files){
            if(file.isFile()){
                paths.add(file.getPath());
            }
        }

        ArrayList<ArrayList<String>> output = new ArrayList<>();

        for (String path:paths) {
            ArrayList<String> RawData = FileProcessor.readFile(path);

            output.add(RawData);
        }

        logsOfNDaysPassed = output;
    }

    public void generateDailyWorkRawData(){

        File folder = new File(folderRelativePath);
        if (!folder.exists()) folder.mkdirs();

        File[] files = new File(folderRelativePath).listFiles();
        assert files != null;
        if(numberOfDaysPassed == files.length) return;

        //Requires more logs to be generated
        if(numberOfDaysPassed > files.length){
            int numberOfFilesToBeGenerated = numberOfDaysPassed - files.length;
            int startingIndex = files.length + 1;

            for(int i = startingIndex; i < startingIndex + numberOfFilesToBeGenerated; i ++){
                FileProcessor.writeFile(folderRelativePath + "/Day_" + i + ".txt", EmployeeData.size(), i);
            }
        }
        //Requires deletion of logs
        else {
            int numberOfFilesToBeDeleted = files.length - numberOfDaysPassed;
            int startingIndex = numberOfDaysPassed + 1;

            for(int i = startingIndex; i < startingIndex + numberOfFilesToBeDeleted; i ++){
                FileProcessor.deleteFile(folderRelativePath + "/Day_" + i + ".txt");
            }
        }
    }

    public EmployeeData<Employee> incorporateDailyWorkData(){

        //setSumOfWorkHours // Daily Work data
        //setSumOfMissedHours//Req hours - Daily work > 0
        //setSumOfOvertimeHours // Req hours - daily work < 0
        //setWage

        for (ArrayList<String> dailyWorkRawDatum : logsOfNDaysPassed) {
            for (String line : dailyWorkRawDatum) {
                String[] splitLine = line.split(";");

                String identifier = splitLine[0];
                int hoursWorkedOnDay = Integer.parseInt(splitLine[1]);

                if (searchIfExist(identifier, EmployeeData)) {

                    int index = searchIndex(identifier, EmployeeData);
                    Employee currEmp = EmployeeData.get(index);

                    //ADDS the total Worked hours
                    currEmp.setSumOfWorkHours(currEmp.getSumOfWorkHours() + hoursWorkedOnDay);

                    //If employee worked MORE than the required work hours (Overtime)
                    if(hoursWorkedOnDay > currEmp.getRequiredDailyWorkHours())
                        currEmp.setSumOfOvertimeHours(currEmp.getSumOfOvertimeHours() + (hoursWorkedOnDay - currEmp.getRequiredDailyWorkHours()));
                    //If employee worked LESS than the required work hours (Missed Hours)
                    else if (hoursWorkedOnDay < currEmp.getRequiredDailyWorkHours())
                        currEmp.setSumOfMissedHours(currEmp.getSumOfMissedHours() + (currEmp.getRequiredDailyWorkHours() - hoursWorkedOnDay));

                    currEmp.setWage();
                }
            }
        }

        return EmployeeData;
    }

    private static boolean searchIfExist(String searchTarget, EmployeeData<Employee> searchPool){

        boolean found = false;
        for(Employee employee : searchPool.getEmployeeList()){

            if (Objects.equals(searchTarget, employee.getIdentifier())) {
                found = true;
                break;
            }
        }

        return found;
    }

    private static int searchIndex(String searchTarget, EmployeeData<Employee> searchPool){

        int index = 0;

        for(int i  = 0; i < searchPool.size(); i ++){
            if(Objects.equals(searchTarget, searchPool.get(i).getIdentifier())) {
                index = i;
                break;
            }
        }

        return index;
    }
}