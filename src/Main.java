import BusinessObjects.Employee;
import BusinessObjects.EmployeeData;
import Runtime.Populate;
import Runtime.DailyWorkDataFunctions;
import Runtime.Calibration;
import Runtime.Options;
import Runtime.Utility;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        //!!!!!Make sure to run the program once to allow for Database Folder to generate!!!!!!

        //Check if Database exist, if not, then go straight to prompts
        //if exists, ask if overriding or appending/editing , note that this is to create test pool, not input itself
        //Update the Database
        //1)Input new employee
        //2)Input new Log
            //What day
            //ID
            //hours worked
            //Update
        //3)Print Employee (Search based on ID)
        //4)Print Employee list Sorted by name (Showing Name, Position, and Wage)
        //5)Print Employee list Sorted by missed hours (Showing Name, Position, Missed Hours)
        //6)End

        System.out.printf("_______________________________________________________________________________%n%n%n");
        Scanner scanner = new Scanner(System.in);
        int numOfEmployees = 0;
        int daysPassed = 0;

        //Skip Inputs if unTouch = True
        if (Calibration.preCheck(scanner)){
            //If The Existing Database Does not need to be touched, get information(employee data, and days passed) via file reading.
            Options.beginPrompt(updateDatabase(new Populate().getEmployeeData(), Utility.numOfDaysData()), scanner);

            return;
        }

        while (true) {
            System.out.print("Please Input the Number Of Employees:\n");

            if (scanner.hasNextInt()) {
                numOfEmployees = scanner.nextInt();
                break;
            } else {
                System.out.println("The input is invalid. Please enter a valid integer.\n");
                scanner.next();
            }
        }

        while (true) {
            System.out.print("Please Input the Number Of Days Passed:\n");

            if (scanner.hasNextInt()) {
                daysPassed = scanner.nextInt();
                break;
            } else {
                System.out.println("The input is invalid. Please enter a valid integer.\n");
                scanner.next();
            }
        }

        //TASK 1
        Populate populate = new Populate();
        //Generate - Calibrate the Catalog File for use, matching with the number of Employees.
        populate.setEmployeeData(numOfEmployees);
        //Populate the Employee Collection with data extracted from the Catalog File.
        EmployeeData<Employee> catalogEmployeeData = populate.getEmployeeData();

        Options.beginPrompt(updateDatabase(catalogEmployeeData, daysPassed), scanner);



        scanner.close();
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
}