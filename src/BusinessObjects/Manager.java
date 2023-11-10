package BusinessObjects;

import java.text.DecimalFormat;

public class Manager extends Employee{
    private final int overtimeWage;
    private final int monthlyWage;

    public Manager(String identifier, String name, int requiredDailyWorkHours, int monthlyWage, int overtimeWage) {
        super(identifier, name, requiredDailyWorkHours);

        this.overtimeWage = overtimeWage;
        this.monthlyWage = monthlyWage;
        setWage();
    }

    @Override public void setWage() {
        Wage = getMonthlyWage() + (overtimeWage * getSumOfOvertimeHours());
    }

    public int getOvertimeWage() {
        return overtimeWage;
    }
    public int getMonthlyWage(){
        return monthlyWage;
    }

    @Override public void print(){
        System.out.println("\n-----------------------------------------");
        System.out.println("INFORMATION ON " + getName() + ", the Manager");
        System.out.println("Monthly Wage: " + getMonthlyWage());
        System.out.println("Overtime Wage: " + overtimeWage);
        System.out.println("Sum of Total Hours: " + getSumOfWorkHours());
        System.out.println("Sum of Overtime Hours: " + getSumOfOvertimeHours());
        System.out.println("Sum of Missed Hours: " + getSumOfMissedHours());
        System.out.println("Total Wage: " + Double.parseDouble(new DecimalFormat("#.##").format(Wage)));
        System.out.println("-----------------------------------------");
    }

    @Override public Employee clone(){

        Manager clonedManager = new Manager(getIdentifier(), getName(), getRequiredDailyWorkHours(), getMonthlyWage(), getOvertimeWage());
        clonedManager.setSumOfMissedHours(getSumOfMissedHours());
        clonedManager.setSumOfWorkHours(getSumOfWorkHours());
        clonedManager.setSumOfOvertimeHours(getSumOfOvertimeHours());
        return clonedManager;

    }
}
