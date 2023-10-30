package Runtime;

import BusinessIO.FileProcessor;
import BusinessObjects.Employee;
import BusinessObjects.EmployeeData;

import java.io.File;
import java.util.ArrayList;

public class Populate {
    private final int numOfEmployees;
    private static final String folderRelativePath = "Database/Catalog.txt";
    public Populate(int numOfEmployees){
        this.numOfEmployees = numOfEmployees;
    }
    public void setEmployeeData(){

        File folder = new File("Database");
        if (!folder.exists()) folder.mkdirs();

        //Create a new catalog file if file does not exist.
        if(!FileProcessor.doesFileExist(folderRelativePath)) {
            FileProcessor.createFile(folderRelativePath, numOfEmployees);
            return;
        }

        ArrayList<String> Buffer = FileProcessor.readFile(folderRelativePath);

        //Check if current employee number is equal to the requested amount, is so, no action is needed.
        if(Buffer.size() == numOfEmployees) return;

        //Appends Text File with more Employees, if not Delete necessary lines
        FileProcessor.writeFile(folderRelativePath, numOfEmployees, Buffer.size(), Buffer.size() < numOfEmployees);

    }

    public EmployeeData<Employee> getEmployeeData(){
        EmployeeData<Employee> employeeData = new EmployeeData<>();

        //Reads the Catalog file and Stores each line into an array list.
        ArrayList<String> RawData = FileProcessor.readFile(folderRelativePath);

        for (String line : RawData) {
            employeeData.add(Formatter.formatIntoNewEmployee(line));
        }

        return employeeData;
    }
}
