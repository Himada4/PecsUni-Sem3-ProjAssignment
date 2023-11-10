package BusinessObjects;

public abstract class Employee {
    private final String identifier;
    private final String name;
    private final int requiredDailyWorkHours;
    private int sumOfWorkHours;
    private int sumOfMissedHours;
    private int sumOfOvertimeHours;
    protected double Wage;


    public Employee(String identifier, String name, int requiredDailyWorkHours) {
        this.identifier = identifier;
        this.name = name;
        this.requiredDailyWorkHours = requiredDailyWorkHours;
    }


    //GETTERS

    public String getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public int getRequiredDailyWorkHours() {
        return requiredDailyWorkHours;
    }

    public int getSumOfWorkHours() {
        return sumOfWorkHours;
    }

    public int getSumOfMissedHours() {
        return sumOfMissedHours;
    }

    public int getSumOfOvertimeHours() {
        return sumOfOvertimeHours;
    }

    public double getWage() {
        return Wage;
    }

    //SETTERS
    public abstract void setWage();

    public abstract void print();

    public void setSumOfWorkHours(int sumOfWorkHours) { // Daily Work data
        this.sumOfWorkHours = sumOfWorkHours;
    }

    public void setSumOfMissedHours(int sumOfMissedHours) { //Req hours - Daily work > 0
        this.sumOfMissedHours = sumOfMissedHours;
    }

    public void setSumOfOvertimeHours(int sumOfOvertimeHours) { // Req hours - daily work < 0
        this.sumOfOvertimeHours = sumOfOvertimeHours;
    }


    public abstract Employee clone();
}
