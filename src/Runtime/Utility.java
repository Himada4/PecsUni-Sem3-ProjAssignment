package Runtime;

import java.io.File;

public class Utility {
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static int numOfDaysData(){
        File[] files = new File("Database/DailyWorkDataFiles").listFiles();
        assert files != null;
        return files.length;
    }
}
