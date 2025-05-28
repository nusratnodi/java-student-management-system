import javax.swing.*;
import java.awt.*;

public class StudentManagementSystem {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Management System");
        frame.setSize(1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Student Management", new StudentManagementPanel().createPanel());
        tabs.addTab("CGPA Calculator", new CGPACalculatorPanel().createPanel());
        
        frame.add(tabs);
        frame.setVisible(true);
    }
}