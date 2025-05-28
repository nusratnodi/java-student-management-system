import javax.swing.*;
import java.awt.*;

public class StudentManagementSystem extends JFrame {
    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Add panels as tabs
        tabbedPane.addTab("Student Management", new StudentManagementPanel());
        tabbedPane.addTab("CGPA Calculator", new CGPACalculatorPanel());
        
        add(tabbedPane);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagementSystem());
    }
}