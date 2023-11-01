package Runtime;

import BusinessIO.FileProcessor;
import BusinessObjects.Employee;
import BusinessObjects.EmployeeData;
import BusinessObjects.Manager;
import BusinessObjects.Worker;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Options{

    private  EmployeeData<Employee> catalogEmployeeData;

    private final Scanner scanner;

    private final Printer printer;

    public Options(EmployeeData<Employee> catalogEmployeeData, Scanner scanner){
        this.catalogEmployeeData = catalogEmployeeData;
        this.scanner = scanner;
        this.printer = new Printer(catalogEmployeeData);
    }
    public void beginPrompt(){

        while(true) {
            System.out.printf("%nPlease Select:%n1)Add Employee Manually.%n2)Add A Daily Work Log Manually.%n" +
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
                    sortByNameAndPrint();
                    break;
                case "5": // Print Employee list Sorted by missed hours (Showing Name, Position, Missed Hours)
                    sortByMissedHoursAndPrint();
                    break;
                case "6": // Print the financial Report
                    printFinancialReport();
                    break;
                case "7": // End
                    System.out.println("Terminating program...");
                    return;

            }
        }
    }

    private void addManualEmployee(){

        String name;
        String position;
        String requiredWorkHours;
        String baseWage;
        String overtimeWage;


        System.out.printf("%n%n%nPlease Type the Name of the Employee:%n");
        name = scanner.next();

        System.out.printf("%nPlease Type the Position of the Employee:%n");
        while (true) {
            position = scanner.next();

            if (!(Objects.equals("Manager", position) || (Objects.equals("Worker", position)))
            ) {
                System.out.println("The input is invalid. Please enter a valid position, \"Worker\" or \"Manager\".");
                continue;
            }
            break;
        }

        System.out.printf("%nPlease Type the Required Work Hours Per Day of the Employee:%n");
        while (true) {
            requiredWorkHours = scanner.next();
            if (!Utility.isInteger(requiredWorkHours)) {
                System.out.println("The input is invalid. Please enter a valid integer.");
                continue;
            }
            break;
        }

        System.out.printf("%nPlease Type the Base Wage (Hourly Wage for Worker, Monthly Wage for Manager) of the Employee:%n");
        while (true) {
            baseWage = scanner.next();
            if (!Utility.isInteger(baseWage)) {
                System.out.println("The input is invalid. Please enter a valid integer.");
                continue;
            }
            break;
        }

        System.out.printf("%nPlease Type the Overtime wage (Overtime percentile for Worker, Overtime wage for Manager) of the Employee:%n");
        while (true) {
            overtimeWage = scanner.next();
            if (!Utility.isDouble(overtimeWage)) {
                System.out.println("The input is invalid. Please enter a valid decimal from 0.1 to 0.9.");
                continue;
            }
            break;
        }
        String employeeID = "ID" + String.format("%03d", catalogEmployeeData.size() + 1);

        Employee employee = (position.equals("Manager"))
                ? new Manager(employeeID, name, Integer.parseInt(requiredWorkHours), Integer.parseInt(baseWage), Integer.parseInt(overtimeWage))
                : new Worker(employeeID, name, Integer.parseInt(requiredWorkHours), Integer.parseInt(baseWage), Double.parseDouble(overtimeWage));

        Populate populate = new Populate();
        populate.insertEmployeeData(employee);
        catalogEmployeeData = populate.getEmployeeData();

        Utility.updateDatabase(catalogEmployeeData, Utility.numOfDaysData());

    }
    private void addManualLog(){

        String identifier;
        String hoursWorkedOnDay;
        String dayNumber;

        System.out.printf("%n%n%nPlease enter which day to log:%n");
        while (true) {
            dayNumber = scanner.next();
            if (!Utility.isInteger(dayNumber)) {
                System.out.println("The input is invalid. Please enter a valid integer.");
                continue;
            }
            if(Integer.parseInt(dayNumber) > Utility.numOfDaysData() + 1){
                System.out.printf("The day number you are trying to create a log for exceeds the allowed limit.%n" +
                        "Please enter a day number from 1 to %s.%n", Utility.numOfDaysData() + 1);
                continue;
            }
            break;
        }

        System.out.printf("%nPlease enter the ID to search for::%n");

        while (true) {
            identifier = scanner.next();

            Pattern format = Pattern.compile("^ID\\d{3}$");
            Matcher matcher = format.matcher(identifier);

            if (!(matcher.matches())) {
                System.out.println("The input is invalid. Please enter a valid format. (e.g \"ID024\", \"ID241\")");
                continue;
            }
            if(!(Utility.searchIfExist(identifier, catalogEmployeeData))){
                System.out.println("The Identifier was Not Found. Please enter a Valid Identifier.");
                continue;
            }
            break;
        }

        System.out.printf("%nPlease Enter the Hours the Employee Worked on the Day:%n");

        while (true) {
            hoursWorkedOnDay = scanner.next();

            if (!(Utility.isInteger(hoursWorkedOnDay))) {
                System.out.println("The input is invalid. Please enter a valid integer.");
                continue;
            }
            break;
        }

        if(Integer.parseInt(dayNumber) == Utility.numOfDaysData() + 1){
            //create new log
            FileProcessor.writeFile("Database/DailyWorkDataFiles/Day_" + dayNumber + ".txt", Integer.parseInt(dayNumber), true, identifier, Integer.parseInt(hoursWorkedOnDay));
        }else{
            //append selected log
            FileProcessor.writeFile("Database/DailyWorkDataFiles/Day_" + dayNumber + ".txt", Integer.parseInt(dayNumber), false, identifier, Integer.parseInt(hoursWorkedOnDay));
        }


        int overtime = 0;
        int missedHours = 0;
        int totalHours = 0;
        Employee currEmp = catalogEmployeeData.get(Utility.searchIndex(identifier, catalogEmployeeData));

        ArrayList<String> temp = FileProcessor.readFile("Database/DailyWorkDataFiles/Day_" + dayNumber + ".txt");

        for(var t:temp){
            int hoursOnDay = Integer.parseInt(t.split(";")[1]);

            totalHours += hoursOnDay;

            if(hoursOnDay > currEmp.getRequiredDailyWorkHours())
                overtime += (hoursOnDay - currEmp.getRequiredDailyWorkHours());
                //If employee worked LESS than the required work hours (Missed Hours)
            else if (hoursOnDay < currEmp.getRequiredDailyWorkHours())
                missedHours += (currEmp.getRequiredDailyWorkHours() - hoursOnDay);

        }
        currEmp.setSumOfWorkHours(totalHours);
        currEmp.setSumOfMissedHours(missedHours);
        currEmp.setSumOfOvertimeHours(overtime);
        currEmp.setWage();


    }
    private void searchEmployeeByID(){
        System.out.printf("%nPlease enter the ID to search for::%n");
        String searchTarget;
        while (true) {
            searchTarget = scanner.next();

            Pattern format = Pattern.compile("^ID\\d{3}$");
            Matcher matcher = format.matcher(searchTarget);

            if (!(matcher.matches())) {
                System.out.println("The input is invalid. Please enter a valid format. (e.g \"ID024\", \"ID241\")");
                continue;
            }
            if(!(Utility.searchIfExist(searchTarget, catalogEmployeeData))){
                System.out.println("The Identifier was Not Found. Please enter a Valid Identifier.");
                continue;
            }
            break;
        }

        catalogEmployeeData.get(Utility.searchIndex(searchTarget, catalogEmployeeData))
        .print();
    }
    private void sortByNameAndPrint(){
        //TASK 3
        printer.printNameAndWage();

    }
    private void sortByMissedHoursAndPrint(){
        //TASK 4
        printer.printNameAndMissedHours();
    }
    private void printFinancialReport(){
        //TASK 5
        printer.printFinancialReport();
    }
}
