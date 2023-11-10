import BusinessObjects.Employee;
import BusinessObjects.EmployeeData;
import Runtime.QueryCatalog;
import Runtime.QueryDailyWork;
import Runtime.Calibration;
import Runtime.Options;

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
        int numOfEmployees;
        int daysPassed;

        //Skip Pre-Simulation Prompts if No overriding is needed.
        if (new Calibration().preCheck(scanner)){
            //If The Existing Database Does not need to be touched, get information(employee data, and days passed) via file reading.
            EmployeeData<Employee> employeeData = new QueryCatalog().getEmployeeDataFromCatalogFile();

            QueryDailyWork query = new QueryDailyWork(employeeData);
            query.getNumOfDaysFromFileCount();
            query.getDailyWorkRawData();
            EmployeeData<Employee> incorporatedEmployeeData = query.incorporateDailyWorkData();


            new Options(incorporatedEmployeeData, employeeData, scanner).beginPrompt();

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
        QueryCatalog queryCatalog = new QueryCatalog();
        //Generate - Calibrate the Catalog File for use, matching with the number of Employees.
        queryCatalog.setEmployeeData(numOfEmployees);
        //Populate the Employee Collection with data extracted from the Catalog File.
        EmployeeData<Employee> employeeData = queryCatalog.getEmployeeDataFromCatalogFile();

        //TASK 2
        QueryDailyWork query = new QueryDailyWork(employeeData.clone());
        //Generate - Calibrate the Daily Work Data Folder for use, each day having logs equal to the number of Employees.

        query.setDailyWorkRawData(daysPassed);
        query.getNumOfDaysFromFileCount();
        query.getDailyWorkRawData();
        EmployeeData<Employee> incorporatedEmployeeData = query.incorporateDailyWorkData();

        new Options(incorporatedEmployeeData, employeeData, scanner)
        .beginPrompt();


        scanner.close();
    }
}