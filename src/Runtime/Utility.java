package Runtime;

import BusinessObjects.Employee;
import BusinessObjects.EmployeeData;

import java.util.Objects;

public class Utility {
    public boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isDouble(String str){
        try{
            Double.parseDouble(str);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public boolean searchIfExist(String searchTarget, EmployeeData<Employee> searchPool){

        boolean found = false;
        for(Employee employee : searchPool.getEmployeeList()){

            if (Objects.equals(searchTarget, employee.getIdentifier())) {
                found = true;
                break;
            }
        }

        return found;
    }

    public int searchIndex(String searchTarget, EmployeeData<Employee> searchPool){

        int index = 0;

        for(int i  = 0; i < searchPool.size(); i ++){
            if(Objects.equals(searchTarget, searchPool.get(i).getIdentifier())) {
                index = i;
                break;
            }
        }

        return index;
    }
}
