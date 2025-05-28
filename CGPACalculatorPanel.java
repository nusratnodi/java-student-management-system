import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CGPACalculatorPanel implements ActionListener {
    private JPanel panel;
    private JTextField[] courseMarksFields;
    private JTextField[] courseCreditFields;
    private JLabel[] courseGradeLabels; // Changed to JLabel since grades are display-only
    private JButton calculateCGPA, resetCGPA;
    private JLabel cgpaResult;
    private static final int MAX_COURSES = 6;

    public JPanel createPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(245, 245, 245));

        // Gradient title
        JLabel title = new JLabel("CGPA CALCULATOR") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                GradientPaint gp = new GradientPaint(0, 0, new Color(52, 152, 219), getWidth(), 0, new Color(46, 204, 113));
                g2d.setPaint(gp);
                g2d.setFont(getFont());
                g2d.drawString(getText(), 0, getHeight() - 10);
            }
        };
        title.setBounds(300, 30, 400, 50);
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setOpaque(false);

        courseMarksFields = new JTextField[MAX_COURSES];
        courseCreditFields = new JTextField[MAX_COURSES];
        courseGradeLabels = new JLabel[MAX_COURSES];

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        Font fieldFont = new Font("Arial", Font.PLAIN, 14);
        int yOffset = 100;
        for (int i = 0; i < MAX_COURSES; i++) {
            JLabel courseLabel = new JLabel("Course " + (i+1));
            courseLabel.setBounds(50, yOffset, 100, 30);
            courseLabel.setFont(labelFont);

            courseMarksFields[i] = new JTextField();
            courseMarksFields[i].setBounds(150, yOffset, 100, 30);
            courseMarksFields[i].setFont(fieldFont);
            courseMarksFields[i].setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            // Add KeyListener to update grade label when marks are entered
            final int index = i;
            courseMarksFields[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyReleased(KeyEvent e) {
                    updateGradeLabel(index);
                }
            });

            JLabel creditsLabel = new JLabel("Credits");
            creditsLabel.setBounds(270, yOffset, 60, 30);
            creditsLabel.setFont(labelFont);

            courseCreditFields[i] = new JTextField();
            courseCreditFields[i].setBounds(330, yOffset, 80, 30);
            courseCreditFields[i].setFont(fieldFont);
            courseCreditFields[i].setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

            JLabel gradeLabel = new JLabel("Grade");
            gradeLabel.setBounds(430, yOffset, 60, 30);
            gradeLabel.setFont(labelFont);

            courseGradeLabels[i] = new JLabel("");
            courseGradeLabels[i].setBounds(490, yOffset, 60, 30);
            courseGradeLabels[i].setFont(fieldFont);
            courseGradeLabels[i].setForeground(new Color(52, 152, 219));

            panel.add(courseLabel);
            panel.add(courseMarksFields[i]);
            panel.add(creditsLabel);
            panel.add(courseCreditFields[i]);
            panel.add(gradeLabel);
            panel.add(courseGradeLabels[i]);

            yOffset += 40;
        }

        calculateCGPA = StyledButton.createStyledButton("Calculate CGPA", new Color(46, 204, 113));
        calculateCGPA.setBounds(200, yOffset + 20, 150, 35);
        calculateCGPA.addActionListener(this);

        resetCGPA = StyledButton.createStyledButton("Reset", new Color(241, 196, 15));
        resetCGPA.setBounds(360, yOffset + 20, 150, 35);
        resetCGPA.addActionListener(this);

        cgpaResult = new JLabel("CGPA: ");
        cgpaResult.setBounds(200, yOffset + 60, 200, 30);
        cgpaResult.setFont(new Font("Arial", Font.BOLD, 16));
        cgpaResult.setForeground(new Color(52, 152, 219));

        panel.add(title);
        panel.add(calculateCGPA);
        panel.add(resetCGPA);
        panel.add(cgpaResult);

        return panel;
    }

    // Method to update grade label based on marks
    private void updateGradeLabel(int index) {
        String marksStr = courseMarksFields[index].getText().trim();
        if (!marksStr.isEmpty()) {
            try {
                double marks = Double.parseDouble(marksStr);
                if (marks >= 0 && marks <= 100) {
                    courseGradeLabels[index].setText(getLetterGrade(marks));
                } else {
                    courseGradeLabels[index].setText("");
                }
            } catch (NumberFormatException e) {
                courseGradeLabels[index].setText("");
            }
        } else {
            courseGradeLabels[index].setText("");
        }
    }

    // Method to convert marks to letter grade
    private String getLetterGrade(double marks) {
        if (marks >= 80) return "A+";
        else if (marks >= 75) return "A";
        else if (marks >= 70) return "A-";
        else if (marks >= 65) return "B+";
        else if (marks >= 60) return "B";
        else if (marks >= 55) return "B-";
        else if (marks >= 50) return "C+";
        else if (marks >= 45) return "C";
        else if (marks >= 40) return "D";
        else return "F";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateCGPA) {
            double totalGradePoints = 0;
            double totalCredits = 0;

            for (int i = 0; i < MAX_COURSES; i++) {
                String gradeStr = courseMarksFields[i].getText().trim();
                String creditStr = courseCreditFields[i].getText().trim();

                if (!gradeStr.isEmpty() && !creditStr.isEmpty()) {
                    try {
                        double grade = Double.parseDouble(gradeStr);
                        double credits = Double.parseDouble(creditStr);

                        if (grade < 0 || grade > 100) {
                            JOptionPane.showMessageDialog(panel, "Grade must be between 0 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (credits <= 0) {
                            JOptionPane.showMessageDialog(panel, "Credits must be positive.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        double gradePoint = Utility.convertToGradePoint(grade);
                        totalGradePoints += gradePoint * credits;
                        totalCredits += credits;

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(panel, "Please enter valid numbers for grade and credits.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            if (totalCredits > 0) {
                double cgpa = totalGradePoints / totalCredits;
                cgpaResult.setText(String.format("CGPA: %.2f", cgpa));
            } else {
                cgpaResult.setText("CGPA: 0.00");
                JOptionPane.showMessageDialog(panel, "Please enter at least one course.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == resetCGPA) {
            for (int i = 0; i < MAX_COURSES; i++) {
                courseMarksFields[i].setText("");
                courseCreditFields[i].setText("");
                courseGradeLabels[i].setText("");
            }
            cgpaResult.setText("CGPA: ");
        }
    }
}