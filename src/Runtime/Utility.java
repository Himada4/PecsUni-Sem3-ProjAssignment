package Runtime;

import BusinessObjects.Employee;
import BusinessObjects.EmployeeData;

import java.io.File;
import java.util.Objects;

public class Utility {
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str){
        try{
            Double.parseDouble(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static int numOfDaysData(){
        File[] files = new File("Database/DailyWorkDataFiles").listFiles();
        assert files != null;
        return files.length;
    }

    public static EmployeeData<Employee> updateDatabase(EmployeeData<Employee> catalogEmployeeData, int daysPassed){

        //TASK 2
        DailyWorkDataFunctions dailyWorkData = new DailyWorkDataFunctions(catalogEmployeeData, daysPassed);
        //Generate - Calibrate the Daily Work Data Files for use, matching with the number of days passed.
        dailyWorkData.generateDailyWorkRawData();
        //Populate the ArrayList collection with ALL data extracted from the DailyWorkDataFiles Folder.
        dailyWorkData.setDailyWorkRawData();
        //Incorporates the worked hours to the existing Employee collection.
        return dailyWorkData.incorporateDailyWorkData();

    }

    public static boolean searchIfExist(String searchTarget, EmployeeData<Employee> searchPool){

        boolean found = false;
        for(Employee employee : searchPool.getEmployeeList()){

            if (Objects.equals(searchTarget, employee.getIdentifier())) {
                found = true;
                break;
            }
        }

        return found;
    }

    public static int searchIndex(String searchTarget, EmployeeData<Employee> searchPool){

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
