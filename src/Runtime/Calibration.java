package Runtime;

import BusinessIO.FileProcessor;

import java.util.Objects;
import java.util.Scanner;

public class Calibration{
    public static boolean preCheck(Scanner scanner){

        boolean unTouchData = false;
        if(!FileProcessor.doesFolderExist("Database")) return unTouchData;

        System.out.printf("This is a pre-simulation check to calibrate the existing database.%nPlease select:" +
                "%n1) I Am Restarting From Scratch.                    (Every Data Will Be Erased And Another Set Of Data Will Be Generated.)" +
                "%n2) I Am Appending / Reducing The Existing Database. (It Will Take The Existing Data, And Either Append, Or Delete Partially.)" +
                "%n3) I Would Like To Continue With The Existing Data. (No Action Will Be Taken.)%n");
        String input;
        while (true) {
            input = scanner.next();
            if (!Utility.isInteger(input)) {
                System.out.println("The input is invalid. Please enter a valid integer.");
                continue;
            }
            if(!(Objects.equals(input, "1") || Objects.equals(input, "2") || Objects.equals(input, "3"))){
                System.out.println("The input is invalid. Please enter a valid integer, from 1 to 3.");
                continue;
            }
            break;
        }

        switch (input){
            case "1": // Populate the Database from scratch
                FileProcessor.deleteFolder("Database");
                break;
            case "2":
                System.out.println("Please enter the adjusted values for the following:");
                break;
            case "3":
                unTouchData = true;
                break;
        }

        System.out.printf("_______________________________________________________________________________%n");
        return unTouchData;
    }


}
