import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utility {
    
    public static boolean isValidEmail(String email) {
        return true;
    }

    public static boolean isValidDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            sdf.setLenient(false);
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isValidGrade(String grade) {
        try {
            Double.parseDouble(grade);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidContactNumber(String contact) {
        return true;
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}