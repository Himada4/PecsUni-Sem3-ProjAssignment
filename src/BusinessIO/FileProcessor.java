package BusinessIO;

import BusinessObjects.Employee;
import BusinessObjects.Manager;
import BusinessObjects.Names;
import BusinessObjects.Worker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;


public class FileProcessor {

    public ArrayList<String> readFile(String Path){

        ArrayList<String> rawText = new ArrayList<>();

        try {

            File file = new File(Path);
            Scanner fileReader = new Scanner(file);

            //Skips the first line
            if (fileReader.hasNextLine()) fileReader.nextLine();

            //Reads the Text file, skipping any empty lines
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                if (!data.trim().isEmpty()) rawText.add(data);
            }
            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        return rawText;
    }
    public boolean doesFileExist(String Path){
        File file = new File(Path);
        return file.exists() && file.isFile();
    }
    public boolean doesFolderExist(String Path){
        File folder = new File(Path);
        return folder.exists() && folder.isDirectory();
    }
    public ArrayList<String> updateDailyWorkArrayList(ArrayList<String> originalArrayList, String searchTargetID, int hoursWorked){

        ArrayList<String> updatedArrayList = new ArrayList<>();

        for (var t : originalArrayList){
            String[] split = t.split(";");
            if(Objects.equals(searchTargetID, split[0])){
                int addedVal = Integer.parseInt(split[1]) + hoursWorked;
                updatedArrayList.add(searchTargetID + ";" + addedVal);
            }else{
                updatedArrayList.add(t);
            }
        }

        return updatedArrayList;

    }


    //FOR MANUAL INPUTS (OPTION 1 and 2)
    public String createEmployeeLine(Employee employee){
        String position = null;
        int wage = 0;
        String overtimeWage = null;
        if(employee instanceof Worker worker) {
            position = "Worker";
            wage = worker.getHourlyWage();
            DecimalFormat df = new DecimalFormat("#.##");
            overtimeWage = String.valueOf(Double.parseDouble(df.format(worker.getOvertimeWagePercentile())));
        }
        else if (employee instanceof Manager manager){
            position = "Manager";
            wage = manager.getMonthlyWage();
            overtimeWage = String.valueOf(manager.getOvertimeWage());
        }

        String employeeID = employee.getIdentifier();
        String name = employee.getName();

        int requiredHours = employee.getRequiredDailyWorkHours();

        return employeeID + ";" + name + ";" + position + ";" + requiredHours + ";" + wage + ";" + overtimeWage;
    }
    public void writeFile(String Path, Employee employee){


        try (FileWriter fileWriter = new FileWriter(Path, true)) {

            fileWriter.write("\n" + createEmployeeLine(employee));

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    public void writeFile(String Path, int dayNum, boolean newLog, String employeeID, int hoursWorked){

        try {
            if(newLog){
                FileWriter fileWriter = new FileWriter(Path);
                fileWriter.write("Identifier;HoursWorked On the day");
                fileWriter.write("\n" + employeeID + ";" + hoursWorked);
                fileWriter.close();
                System.out.printf("%nDaily Work Data File Day %d created successfully.", dayNum);
            }else{
                ArrayList<String> updatedDailyData = updateDailyWorkArrayList(readFile(Path), employeeID, hoursWorked);
                FileWriter fileWriter = new FileWriter(Path);
                fileWriter.write("Identifier;HoursWorked On the day");
                for(String line : updatedDailyData){
                    fileWriter.write("\n" + line);
                }
                fileWriter.close();
                System.out.printf("%nDaily Work Data File Day %d updated successfully.", dayNum);
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }



    //FOR WRITING RANDOM CATALOG FILES
    public void writeRandomFile(String Path, int newNumOfEmployees, int currNumOfEmployees, boolean Append){

        ArrayList<String> temp = readFile(Path);

        try (FileWriter fileWriter = new FileWriter(Path, Append)) {
            if(Append){
                for(int i = currNumOfEmployees + 1; i <= newNumOfEmployees; i ++){
                    fileWriter.write(createRandomEmployeeLine(i));
                }
            }else{
                fileWriter.write("Identifier;Name;Position;Required Work Hours;Base Wage;Overtime Wage");
                for(int i = 0; i < newNumOfEmployees; i ++){
                    fileWriter.write("\n" + temp.get(i));
                }
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    public void createRandomFile(String Path, int numOfEmployees){

        try {
            FileWriter fileWriter = new FileWriter(Path);

            fileWriter.write("Identifier;Name;Position;Required Work Hours;Base Wage;Overtime Wage");

            for (int i = 1; i <= numOfEmployees; i++) fileWriter.write(createRandomEmployeeLine(i));

            fileWriter.close();

            System.out.println("Catalog table generated successfully.");

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    public String createRandomEmployeeLine(int i) {

        Random random = new Random();
        String employeeID = "ID" + String.format("%03d", i);
        String name = new Names().getRandomName();
        String position = (random.nextInt(2) == 0) ? "Worker" : "Manager";

        int requiredHours = (position.equals("Worker")) ? 6 + random.nextInt(2) : 7 + random.nextInt(2);
        int wage = (position.equals("Worker")) ? 7 + random.nextInt(19) : 3000 + random.nextInt(4000);
        DecimalFormat df = new DecimalFormat("#.##");

        String overtimeWage = (position.equals("Worker"))
                ? String.valueOf(Double.parseDouble(df.format(0.2 + (0.4 - 0.2) * random.nextDouble())))
                : String.valueOf(25 + random.nextInt(16));

        return "\n" + employeeID + ";" + name + ";" + position + ";" + requiredHours + ";" + wage + ";" + overtimeWage;
    }


    //FOR WRITING RANDOM DAILY WORK DATA FILES
    public void writeRandomFile(String Path, int numOfEmployees, int dayNum){

        try {
            FileWriter fileWriter = new FileWriter(Path);
            Random random = new Random();

            fileWriter.write("Identifier;HoursWorked On the day");

            for (int i = 1; i <= numOfEmployees; i++) {
                String employeeID = "ID" + String.format("%03d", i);
                int randomHours = 7 + random.nextInt(3);

                String line = "\n" + employeeID + ";" + randomHours;

                fileWriter.write(line);
            }
            fileWriter.close();
            System.out.printf("%nDaily Work Data File Day %d created successfully.", dayNum);

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }


    //FOR REMOVING UNNECESSARY FILES AND FOLDERS IN THE CURRENT ITERATION
    public void deleteFile(String Path){
        File fileToDelete = new File(Path);

        if(!(fileToDelete.exists() && fileToDelete.isFile())){
            System.err.println("File not found or is not a regular file.");
            return;
        }

        if (fileToDelete.exists() && fileToDelete.isFile()) {

            if (!fileToDelete.delete()) System.err.println("Unable to delete the file.");

            System.out.println("File deleted successfully.");
        }
    }

    @SuppressWarnings("all")
    public void deleteFolder(String folderPath) {
        try {
            Path directory = Paths.get(folderPath);
            Files.walk(directory)
                    .sorted((p1, p2) -> -p1.compareTo(p2))
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            System.out.println("Database has been reset. Regenerating...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}