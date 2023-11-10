package Runtime;

import BusinessObjects.Employee;
import BusinessObjects.Manager;
import BusinessObjects.Worker;

public class Formatter {
    public Employee formatIntoNewEmployee(String CatalogRawData) {

        String[] splitCatalogRawData = CatalogRawData.split(";");


        Employee newEmployee = null;

        //Identifier;Name;Position;Required Work Hours;Wage;Overtime Wage

        //Creates an instance of Worker/Manager based on the catalog data's *position* field
        switch (splitCatalogRawData[2]){
            case "Worker":
                newEmployee = new Worker(
                        splitCatalogRawData[0], // Identifier
                        splitCatalogRawData[1], // Name
                        Integer.parseInt(splitCatalogRawData[3]), // Required Work hours
                        Integer.parseInt(splitCatalogRawData[4]), // Basic Wage
                        Double.parseDouble(splitCatalogRawData[5]) // Overtime Wage
                );
                break;
            case "Manager":
                newEmployee = new Manager(
                        splitCatalogRawData[0], // Identifier
                        splitCatalogRawData[1], // Name
                        Integer.parseInt(splitCatalogRawData[3]), // Required Work hours
                        Integer.parseInt(splitCatalogRawData[4]), // Basic Wage
                        Integer.parseInt(splitCatalogRawData[5]) // Overtime Wage
                );
                break;
            default:
                break;
        }

        return newEmployee;
    }
}
