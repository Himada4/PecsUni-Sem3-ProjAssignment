import BusinessObjects.Employee;
import BusinessObjects.EmployeeData;
import Runtime.Populate;
import Runtime.DailyWorkDataFunctions;
import Runtime.Printer;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {


        //Make sure to run the program once to allow for Database Folder to generate!!


        Scanner scanner = new Scanner(System.in);
        int numOfEmployees = 20;

        while (true) {
            System.out.print("Please Input the Number Of Employees\n");

            if (scanner.hasNextInt()) {
                numOfEmployees = scanner.nextInt();
                break;
            } else {
                System.out.println("The input is invalid. Please enter a valid integer.\n");
                scanner.next();
            }
        }

        Populate populate = new Populate(numOfEmployees);

        //TASK 1
        //Generate - Calibrate the Catalog File for use, matching with the number of Employees.
        populate.setEmployeeData();
        //Populate the Employee Collection with data extracted from the Catalog File.
        EmployeeData<Employee> catalogEmployeeData = populate.getEmployeeData();


        int daysPassed = 26;
        while (true) {
            System.out.print("Please Input the Number Of Days Passed\n");

            if (scanner.hasNextInt()) {
                daysPassed = scanner.nextInt();
                break;
            } else {
                System.out.println("The input is invalid. Please enter a valid integer.\n");
                scanner.next();
            }
        }
        scanner.close();

        DailyWorkDataFunctions dailyWorkData = new DailyWorkDataFunctions(catalogEmployeeData, daysPassed);

        //TASK 2
        //Generate - Calibrate the Daily Work Data Files for use, matching with the number of days passed.
        dailyWorkData.generateDailyWorkRawData();
        //Populate the ArrayList collection with ALL data extracted from the DailyWorkDataFiles Folder.
        dailyWorkData.setDailyWorkRawData();
        //Incorporates the worked hours to the existing Employee collection.
        dailyWorkData.incorporateDailyWorkData();


        Printer printer = new Printer(catalogEmployeeData);
        //TASK 3
        printer.printNameAndWage();
        //TASK 4
        printer.printNameAndMissedHours();
        //TASK 5
        printer.printFinancialReport();
    }
}