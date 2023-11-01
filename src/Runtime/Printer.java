package Runtime;

import BusinessObjects.*;

public class Printer {
    private final EmployeeData<Employee> Employees;
    public Printer(EmployeeData<Employee> Employees){
        this.Employees = Employees;
    }

    public void printNameAndWage(){

        Employees.sortByName();

        System.out.println("-----------------------------------------------------------");
        System.out.println("Employees sorted based their name:");

        int counter = 1;
        for (Employee employee: Employees.getEmployeeList()) {
            String position = null;
            if(employee instanceof Worker) position = "Worker";
            if(employee instanceof Manager) position = "Manager";
            System.out.printf("Employee %d(%s):%n   Name: %s%n   Wage: %s%n", counter, position, employee.getName(), (int)employee.getWage());
            counter++;
        }
        System.out.println("-----------------------------------------------------------");
    }

    //Task 4: Print list of employees in order of missed hours (most in front)
    public void printNameAndMissedHours(){

        Employees.sortByHoursMissed();
        System.out.println("-----------------------------------------------------------");
        System.out.println("Employees sorted based on missed hours in descending order:");

        int counter = 1;
        for (Employee employee: Employees.getEmployeeList()) {
            System.out.printf("Employee %d:%n   Name: %s%n   Missed Hours: %s%n", counter, employee.getName(), employee.getSumOfMissedHours());
            counter++;
        }
        System.out.println("-----------------------------------------------------------");
    }

    //DEBUG
    public void printWageList(){


        System.out.println("-----------------------------------------------------------");
        System.out.println("Employees Wage Information:");

        int counter = 1;
        int overtimeWage = 0;
        int normalWage = 0;
        int baseWage = 0;
        int loss = 0;

        for (Employee employee: Employees.getEmployeeList()) {
            if (employee instanceof Worker worker){
                normalWage = (worker.getSumOfWorkHours() - worker.getSumOfOvertimeHours()) * worker.getHourlyWage();
                overtimeWage = (int)(worker.getSumOfOvertimeHours() * worker.getHourlyWage() * (1 + worker.getOvertimeWagePercentile()));
                loss = worker.getSumOfMissedHours() * worker.getHourlyWage();
                baseWage = worker.getHourlyWage();
            }
            //Assuming there are 26 working days in a month
            else if (employee instanceof Manager manager){
                normalWage = (int) (double) Math.round((((double)(manager.getSumOfWorkHours() - manager.getSumOfOvertimeHours())/(26 * manager.getRequiredDailyWorkHours())) * manager.getMonthlyWage()) * 100) / 100;
                overtimeWage = manager.getOvertimeWage() * manager.getSumOfOvertimeHours();
                loss = (int) ((double)manager.getSumOfMissedHours() /(26 * manager.getRequiredDailyWorkHours()) * manager.getMonthlyWage());
                baseWage = manager.getMonthlyWage();
            }
            System.out.println("-----------------------------------------------------------");
            System.out.printf("Employee %d:%n   Name: %s%n   Base Wage: %s%n   Current Wage: %s%n   Total Overtime Wage: %s%n   Total Loss: %s%n   Wage: %s%n   Missed Hours: %s%n   Overtime Hours: %s%n", counter, employee.getName(), baseWage,normalWage, overtimeWage, loss, (int)employee.getWage(), employee.getSumOfMissedHours(), employee.getSumOfOvertimeHours());
            System.out.println("-----------------------------------------------------------");

            counter++;
        }
    }

    //Task 5: Print the total amount the company has to pay for normal wage,
    // overtime wage and the loss resulted by hours employees did not work (calculated from basic wage).
    public void printFinancialReport(){
        int overtimeWage = 0;
        int normalWage = 0;
        int loss = 0;

        for (Employee employee: Employees.getEmployeeList()) {
            if (employee instanceof Worker worker){
                normalWage += (worker.getSumOfWorkHours() - worker.getSumOfOvertimeHours()) * worker.getHourlyWage();
                overtimeWage += (int)(worker.getSumOfOvertimeHours() * worker.getHourlyWage() * (1 + worker.getOvertimeWagePercentile()));
                loss += worker.getSumOfMissedHours() * worker.getHourlyWage();
            }
            //Assuming there are 26 working days in a month
            else if (employee instanceof Manager manager){
                normalWage += (int)(double) Math.round((((double)(manager.getSumOfWorkHours() - manager.getSumOfOvertimeHours())/(26 * manager.getRequiredDailyWorkHours())) * manager.getMonthlyWage()) * 100) / 100;
                overtimeWage += manager.getOvertimeWage() * manager.getSumOfOvertimeHours();
                loss += (int) ((double)manager.getSumOfMissedHours() /(26 * manager.getRequiredDailyWorkHours()) * manager.getMonthlyWage());
            }
        }
        System.out.println("-----------------------------------------------------------");
        System.out.printf("Current Company's Financial Report:%n   Amount of Basic Wages to pay: %s%n   Amount of Overtime Wages to pay: %s%n   Loss resulted from missing hours: %s%n",normalWage, overtimeWage, loss);
        System.out.println("-----------------------------------------------------------");
    }
}
