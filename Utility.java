import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utility {
    public static double convertToGradePoint(double percentage) {
        if (percentage >= 80) return 4.00;
        else if (percentage >= 75) return 3.75;
        else if (percentage >= 70) return 3.50;
        else if (percentage >= 65) return 3.25;
        else if (percentage >= 60) return 3.00;
        else if (percentage >= 55) return 2.75;
        else if (percentage >= 50) return 2.50;
        else if (percentage >= 45) return 2.25;
        else if (percentage >= 40) return 2.00;
        else return 0.0;
    }

    public static boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
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
        return contact.matches("^[0-9]+$");
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