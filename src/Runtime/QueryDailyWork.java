package Runtime;

import BusinessIO.FileProcessor;
import BusinessObjects.Employee;
import BusinessObjects.EmployeeData;

import java.io.File;
import java.util.ArrayList;

public class QueryDailyWork {

    private static final String folderRelativePath = "Database/DailyWorkDataFiles";
    private int numberOfDaysPassed;
    private final EmployeeData<Employee> employeeData;

    private ArrayList<ArrayList<String>> logsOfNDaysPassed;

    public QueryDailyWork(EmployeeData<Employee> employeeData){
        this.employeeData = employeeData;
    }

    //READING
    public void getDailyWorkRawData(){

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
            ArrayList<String> RawData = new FileProcessor().readFile(path);

            output.add(RawData);
        }

        this.logsOfNDaysPassed = output;
    }

    //RANDOM GENERATION
    @SuppressWarnings("all")
    public void setDailyWorkRawData(int numberOfDaysPassed){

        this.numberOfDaysPassed = numberOfDaysPassed;
        File folder = new File(folderRelativePath);
        if (!folder.exists()) folder.mkdirs();

        File[] files = new File(folderRelativePath).listFiles();
        assert files != null;
        if(numberOfDaysPassed == files.length) return;

        FileProcessor fp = new FileProcessor();

        //Requires more logs to be generated
        if(numberOfDaysPassed > files.length){
            int numberOfFilesToBeGenerated = numberOfDaysPassed - files.length;
            int startingIndex = files.length + 1;

            for(int i = startingIndex; i < startingIndex + numberOfFilesToBeGenerated; i ++){
                fp.writeRandomFile(folderRelativePath + "/Day_" + i + ".txt", employeeData.size(), i);
            }
            System.out.println("\n");
        }
        //Requires deletion of logs
        else {
            int numberOfFilesToBeDeleted = files.length - numberOfDaysPassed;
            int startingIndex = numberOfDaysPassed + 1;

            for(int i = startingIndex; i < startingIndex + numberOfFilesToBeDeleted; i ++){
                fp.deleteFile(folderRelativePath + "/Day_" + i + ".txt");
            }
        }
    }


    //Updates the Employee Collection's Sum of worked hours, Sum of Missed hours, and Sum of Overtime Hours
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
                Utility util = new Utility();
                if (util.searchIfExist(identifier, employeeData)) {

                    int index = util.searchIndex(identifier, employeeData);
                    Employee currEmp = employeeData.get(index);


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

        return employeeData;
    }

    public int getNumOfDaysFromFileCount(){
        File[] files = new File("Database/DailyWorkDataFiles").listFiles();
        assert files != null;
        this.numberOfDaysPassed = files.length;
        return files.length;
    }
}