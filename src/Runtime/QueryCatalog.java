package Runtime;

import BusinessIO.FileProcessor;
import BusinessObjects.Employee;
import BusinessObjects.EmployeeData;

import java.io.File;
import java.util.ArrayList;

public class QueryCatalog {

    private static final String folderRelativePath = "Database/Catalog.txt";

    // FOR MANUAL GENERATION
    public void insertEmployeeData(Employee employee){
        new FileProcessor().writeFile(folderRelativePath, employee);
    }

    //FOR RANDOM GENERATION (Pre-simulation generation)
    @SuppressWarnings("all")
    public void setEmployeeData(int numOfEmployees){

        File folder = new File("Database");
        if (!folder.exists()) folder.mkdirs();

        FileProcessor fp = new FileProcessor();

        //Create a new catalog file if file does not exist.
        if(!fp.doesFileExist(folderRelativePath)) {
            fp.createRandomFile(folderRelativePath, numOfEmployees);
            return;
        }

        ArrayList<String> Buffer = fp.readFile(folderRelativePath);

        //Check if current employee number is equal to the requested amount, is so, no action is needed.
        if(Buffer.size() == numOfEmployees) return;

        //Appends Text File with more Employees, if not Delete necessary lines
        fp.writeRandomFile(folderRelativePath, numOfEmployees, Buffer.size(), Buffer.size() < numOfEmployees);

    }

    //READING
    public EmployeeData<Employee> getEmployeeDataFromCatalogFile(){

        EmployeeData<Employee> employeeData = new EmployeeData<>();

        //Reads the Catalog file and Stores each line into an array list.
        ArrayList<String> RawData = new FileProcessor().readFile(folderRelativePath);

        for (String line : RawData) {
            employeeData.add(new Formatter().formatIntoNewEmployee(line));
        }

        return employeeData;
    }
}
