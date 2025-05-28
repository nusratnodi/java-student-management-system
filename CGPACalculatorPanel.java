import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CGPACalculatorPanel {
    private JPanel panel;
    private JTextField[] marksFields;
    private JTextField[] creditFields;
    private JLabel resultLabel;
    private static final int NUM_COURSES = 5;

    public JPanel createPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create title
        JLabel title = new JLabel("CGPA Calculator", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(title, BorderLayout.NORTH);

        // Create input panel
        JPanel inputPanel = new JPanel(new GridLayout(NUM_COURSES + 2, 4, 5, 5));
        
        // Add column headers
        inputPanel.add(new JLabel("Course"));
        inputPanel.add(new JLabel("Marks (0-100)"));
        inputPanel.add(new JLabel("Credits"));
        inputPanel.add(new JLabel("Grade"));

        marksFields = new JTextField[NUM_COURSES];
        creditFields = new JTextField[NUM_COURSES];

        for (int i = 0; i < NUM_COURSES; i++) {
            // Course number
            inputPanel.add(new JLabel("Course " + (i + 1)));

            // Marks field
            marksFields[i] = new JTextField();
            inputPanel.add(marksFields[i]);

            // Credits field
            creditFields[i] = new JTextField();
            inputPanel.add(creditFields[i]);

            // Grade label (will be filled automatically)
            JLabel gradeLabel = new JLabel("");
            gradeLabel.setForeground(Color.BLUE);
            inputPanel.add(gradeLabel);
        }

        // Create button panel
        JPanel buttonPanel = new JPanel();
        JButton calculateButton = new JButton("Calculate CGPA");
        JButton resetButton = new JButton("Reset");

        calculateButton.addActionListener(e -> calculateCGPA());
        resetButton.addActionListener(e -> resetForm());

        buttonPanel.add(calculateButton);
        buttonPanel.add(resetButton);

        // Result label
        resultLabel = new JLabel("CGPA: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 18));
        resultLabel.setForeground(new Color(0, 100, 0));

        // Add components to main panel
        panel.add(inputPanel, BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(resultLabel, BorderLayout.NORTH);

        return panel;
    }

    private void calculateCGPA() {
        double totalGradePoints = 0;
        double totalCredits = 0;
        boolean hasError = false;

        for (int i = 0; i < NUM_COURSES; i++) {
            String marksText = marksFields[i].getText().trim();
            String creditsText = creditFields[i].getText().trim();

            if (marksText.isEmpty() && creditsText.isEmpty()) {
                continue; // Skip empty rows
            }

            try {
                double marks = Double.parseDouble(marksText);
                double credits = Double.parseDouble(creditsText);

                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(panel, 
                        "Marks must be between 0 and 100 for Course " + (i + 1), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    hasError = true;
                    break;
                }

                if (credits <= 0) {
                    JOptionPane.showMessageDialog(panel, 
                        "Credits must be positive for Course " + (i + 1), 
                        "Error", JOptionPane.ERROR_MESSAGE);
                    hasError = true;
                    break;
                }

                double gradePoint = convertToGradePoint(marks);
                totalGradePoints += gradePoint * credits;
                totalCredits += credits;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(panel, 
                    "Please enter valid numbers for Course " + (i + 1), 
                    "Error", JOptionPane.ERROR_MESSAGE);
                hasError = true;
                break;
            }
        }

        if (!hasError) {
            if (totalCredits > 0) {
                double cgpa = totalGradePoints / totalCredits;
                resultLabel.setText(String.format("CGPA: %.2f", cgpa));
            } else {
                JOptionPane.showMessageDialog(panel, 
                    "Please enter at least one course", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
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

    private void resetForm() {
        for (int i = 0; i < NUM_COURSES; i++) {
            marksFields[i].setText("");
            creditFields[i].setText("");
        }
        resultLabel.setText("CGPA: ");
    }
}