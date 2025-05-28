import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CGPACalculatorPanel extends JPanel {
    private JTextField[] marksFields, creditFields;
    private JButton calculateButton, clearButton;
    private JLabel resultLabel;

    public CGPACalculatorPanel() {
        setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(0, 4, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add headers
        inputPanel.add(new JLabel("Course"));
        inputPanel.add(new JLabel("Marks"));
        inputPanel.add(new JLabel("Credits"));
        inputPanel.add(new JLabel("Grade"));
        
        marksFields = new JTextField[5];
        creditFields = new JTextField[5];
        
        for (int i = 0; i < 5; i++) {
            inputPanel.add(new JLabel("Course " + (i+1)));
            
            marksFields[i] = new JTextField();
            inputPanel.add(marksFields[i]);
            
            creditFields[i] = new JTextField();
            inputPanel.add(creditFields[i]);
            
            JLabel gradeLabel = new JLabel();
            inputPanel.add(gradeLabel);
        }
        
        // Buttons and result
        JPanel buttonPanel = new JPanel();
        calculateButton = new JButton("Calculate CGPA");
        clearButton = new JButton("Clear");
        buttonPanel.add(calculateButton);
        buttonPanel.add(clearButton);
        
        resultLabel = new JLabel("CGPA: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(resultLabel, BorderLayout.NORTH);
        
        // Event listeners
        calculateButton.addActionListener(e -> calculateCGPA());
        clearButton.addActionListener(e -> clearFields());
    }
    
    private void calculateCGPA() {
        double totalPoints = 0;
        double totalCredits = 0;
        
        for (int i = 0; i < 5; i++) {
            try {
                double marks = Double.parseDouble(marksFields[i].getText());
                double credits = Double.parseDouble(creditFields[i].getText());
                
                double gradePoint = convertToGradePoint(marks);
                totalPoints += gradePoint * credits;
                totalCredits += credits;
            } catch (NumberFormatException e) {
                // Ignore empty fields
            }
        }
        
        if (totalCredits > 0) {
            double cgpa = totalPoints / totalCredits;
            resultLabel.setText(String.format("CGPA: %.2f", cgpa));
        } else {
            resultLabel.setText("CGPA: 0.00");
        }
    }
    
    private double convertToGradePoint(double percentage) {
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
    
    private void clearFields() {
        for (int i = 0; i < 5; i++) {
            marksFields[i].setText("");
            creditFields[i].setText("");
        }
        resultLabel.setText("CGPA: ");
    }
}