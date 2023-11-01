package Runtime;

import BusinessObjects.Employee;
import BusinessObjects.EmployeeData;

import java.util.Objects;
import java.util.Scanner;

public class Options{
    public static void beginPrompt(EmployeeData<Employee> catalogEmployeeData, Scanner scanner){

        while(true) {
            System.out.printf("%n%n%nPlease Select:%n1)Add Employee Manually.%n2)Add A Daily Work Log Manually.%n" +
                    "3)Search Employee By ID.%n4)Print Employee List Sorted By Name. (Showing Name, Position, and Wage)" +
                    "%n5)Print Employee List Sorted By Missed Hours. (Showing Name, Position and Missed Hours)" +
                    "%n6)Print The Financial Report.%n7)Terminate Program.%n");
            String input;
            while (true) {
                input = scanner.next();
                if (!Utility.isInteger(input)) {
                    System.out.println("The input is invalid. Please enter a valid integer.");
                    continue;
                }
                if (!(Objects.equals(input, "1") || Objects.equals(input, "2")
                        || Objects.equals(input, "3") || Objects.equals(input, "4")
                        || Objects.equals(input, "5") || Objects.equals(input, "6")
                        || Objects.equals(input, "7"))
                ) {
                    System.out.println("The input is invalid. Please enter a valid integer, from 1 to 6.");
                    continue;
                }
                break;
            }

            switch (input) {
                case "1": // Input new employee
                    addManualEmployee();
                    break;
                case "2": // Input new Log
                    addManualLog();
                    break;
                case "3": // Print Employee (Search based on ID)
                    searchEmployeeByID();
                    break;
                case "4": // Print Employee list Sorted by name (Showing Name, Position, and Wage)
                    sortByNameAndPrint(catalogEmployeeData);
                    break;
                case "5": // Print Employee list Sorted by missed hours (Showing Name, Position, Missed Hours)
                    sortByMissedHoursAndPrint(catalogEmployeeData);
                    break;
                case "6": // Print the financial Report
                    printFinancialReport(catalogEmployeeData);
                    break;
                case "7": // End
                    System.out.println("Terminating program...");
                    return;

            }
        }
    }

    private static void addManualEmployee(){

    }
    private static void addManualLog(){

    }
    private static void searchEmployeeByID(){

    }
    private static void sortByNameAndPrint(EmployeeData<Employee> catalogEmployeeData){

        Printer printer = new Printer(catalogEmployeeData);
        //TASK 3
        printer.printNameAndWage();

    }
    private static void sortByMissedHoursAndPrint(EmployeeData<Employee> catalogEmployeeData){

        Printer printer = new Printer(catalogEmployeeData);
        //TASK 4
        printer.printNameAndMissedHours();
    }
    private static void printFinancialReport(EmployeeData<Employee> catalogEmployeeData){

        Printer printer = new Printer(catalogEmployeeData);
        //TASK 5
        printer.printFinancialReport();
    }
}
