package Runtime;

import BusinessIO.FileProcessor;
import BusinessObjects.Employee;
import BusinessObjects.EmployeeData;

import java.io.File;
import java.util.ArrayList;

public class Populate {
    private static final String folderRelativePath = "Database/Catalog.txt";

    // FOR MANUAL GENERATION
    public void insertEmployeeData(Employee employee){
        FileProcessor.writeFile(folderRelativePath, employee);
    }

    //FOR RANDOM GENERATION (Pre-simulation generation)
    public void setEmployeeData(int numOfEmployees){

        File folder = new File("Database");
        if (!folder.exists()) folder.mkdirs();

        //Create a new catalog file if file does not exist.
        if(!FileProcessor.doesFileExist(folderRelativePath)) {
            FileProcessor.createRandomFile(folderRelativePath, numOfEmployees);
            return;
        }

        ArrayList<String> Buffer = FileProcessor.readFile(folderRelativePath);

        //Check if current employee number is equal to the requested amount, is so, no action is needed.
        if(Buffer.size() == numOfEmployees) return;

        //Appends Text File with more Employees, if not Delete necessary lines
        FileProcessor.writeRandomFile(folderRelativePath, numOfEmployees, Buffer.size(), Buffer.size() < numOfEmployees);

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
