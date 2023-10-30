package BusinessObjects;

public class Worker extends Employee{
    private final double overtimeWagePercentile;
    private final int hourlyWage;

    public Worker(String identifier, String name, int requiredDailyWorkHours, int hourlyWage, double overtimeWagePercentile) {
        super(identifier, name, requiredDailyWorkHours);

        this.overtimeWagePercentile = overtimeWagePercentile;
        this.hourlyWage = hourlyWage;
        setWage();
    }

    @Override public void setWage() {
        Wage = (getRequiredDailyWorkHours() * getHourlyWage()) + (getSumOfOvertimeHours() * getHourlyWage() * (1 + overtimeWagePercentile));
    }

    public double getOvertimeWagePercentile() {
        return overtimeWagePercentile;
    }

    public int getHourlyWage(){
        return hourlyWage;
    }

    public void print(){
        //DEBUG

        System.out.println("INFORMATION ON " + getName() + ", the Worker");
        System.out.println("Required Daily Work Hours: " + getRequiredDailyWorkHours());
        System.out.println("basic Wage: " + getHourlyWage());
        System.out.println("Sum of Overtime Hours: " + getSumOfOvertimeHours());
        System.out.println("Sum of Total Hours: " + getSumOfWorkHours());
        System.out.println("Sum of Missed Hours: " + getSumOfMissedHours());
        System.out.println("Overtime Wage: 1 + " + overtimeWagePercentile);
        System.out.println("Total Wage: " + Wage);
        System.out.println("-----------------------------------------");
    }
}
