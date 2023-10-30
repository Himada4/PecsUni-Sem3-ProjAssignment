package BusinessObjects;

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

    public void print(){
        //DEBUG

        System.out.println("INFORMATION ON " + getName() + ", the Manager");
        System.out.println("Basic Wage: " + getMonthlyWage());
        System.out.println("Overtime Wage: " + overtimeWage);
        System.out.println("Sum of Overtime Hours: " + getSumOfOvertimeHours());
        System.out.println("Sum of Total Hours: " + getSumOfWorkHours());
        System.out.println("Sum of Missed Hours: " + getSumOfMissedHours());
        System.out.println("Total Wage: " + Wage);
        System.out.println("-----------------------------------------");
    }
}
