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

        //Pre Simulation Checks:
        //Check if Database exist, if not, go straight to Options
        //If Database exists, ask if restarting with another set of random data, note that this is to create test pool, not input itself
        //Update the Database

        //Options:
        //1)Input new employee
        //2)Input new Log
        //3)Print Employee (Search based on ID)
        //4)Print Employee list Sorted by name (Showing Name, Position, and Wage)
        //5)Print Employee list Sorted by missed hours (Showing Name, Position, Missed Hours)
        //6)End

        System.out.printf("_______________________________________________________________________________%n");
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        int numOfEmployees = 0;
        int daysPassed = 0;

        //Skip Pre-Simulation Prompts if No overriding is needed.
        if (Calibration.preCheck(scanner)){
            //If The Existing Database Does not need to be touched, get information(employee data, and days passed) via file reading.
            new Options(Utility.updateDatabase(new Populate().getEmployeeData(), Utility.numOfDaysData()), scanner).beginPrompt();

            return;
        }

        //Pre Simulation Random data generation
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

        new Options(Utility.updateDatabase(catalogEmployeeData, daysPassed), scanner)
        .beginPrompt();


        scanner.close();
    }
}