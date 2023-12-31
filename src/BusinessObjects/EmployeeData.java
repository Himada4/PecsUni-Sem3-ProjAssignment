package BusinessObjects;

import java.util.ArrayList;

public class EmployeeData<T extends Employee>{

    protected ArrayList<T> EmployeeList = new ArrayList<>();

    public void add(T newEmployee){

        EmployeeList.add(newEmployee);

    }

    public T get(int index){
        return EmployeeList.get(index);
    }

    public ArrayList<T> getEmployeeList() {
        return EmployeeList;
    }

    public int size() {return EmployeeList.size();}

    public void sortByName() {
        EmployeeList.sort(
                (employee1, employee2) -> employee1.getName().compareToIgnoreCase(employee2.getName())
        );
    }

    public void sortByHoursMissed() {
        EmployeeList.sort(
                (employee1, employee2) -> employee2.getSumOfMissedHours() - employee1.getSumOfMissedHours()
        );
    }


    @SuppressWarnings("all")
    public EmployeeData<T> clone() {
        EmployeeData<T> clonedData = new EmployeeData<T>();
        for (T employee : EmployeeList) {
            T clonedEmployee = (T) employee.clone();
            clonedData.add(clonedEmployee);
        }
        return clonedData;
    }


}
