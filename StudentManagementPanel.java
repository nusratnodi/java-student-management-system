import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentManagementPanel implements ActionListener {
     private JPanel panel;
    private JLabel jtitle;
    private JLabel studentName, studentID, studentGrade, dobLabel, genderLabel, contactLabel, emailLabel;
    private JTextField jstudentName, jstudentID, jstudentGrade, dobField, contactField, emailField, searchField;
    private JRadioButton maleRadio, femaleRadio;
    private ButtonGroup genderGroup;
    private JButton addStudent, reset, deleteRecord, searchButton;
    private JTable studentTable;
    private DefaultTableModel tableModel;

    public JPanel createPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 240, 240)); 

        jtitle = new JLabel("STUDENT MANAGEMENT SYSTEM");
        jtitle.setBounds(250, 10, 700, 50);
        jtitle.setFont(new Font("Arial", Font.BOLD, 32));
        jtitle.setForeground(new Color(0, 102, 204)); // Blue color

        Font labelFont = new Font("Arial", Font.PLAIN, 14);
        studentName = new JLabel("Student Name");
        studentName.setBounds(50, 80, 150, 30);
        studentName.setFont(labelFont);

        studentID = new JLabel("Student ID");
        studentID.setBounds(50, 120, 150, 30);
        studentID.setFont(labelFont);

        studentGrade = new JLabel("Student Grade");
        studentGrade.setBounds(50, 160, 150, 30);
        studentGrade.setFont(labelFont);

        dobLabel = new JLabel("Date of Birth");
        dobLabel.setBounds(50, 200, 150, 30);
        dobLabel.setFont(labelFont);

        genderLabel = new JLabel("Gender");
        genderLabel.setBounds(50, 240, 150, 30);
        genderLabel.setFont(labelFont);

        contactLabel = new JLabel("Contact Name");
        contactLabel.setBounds(50, 280, 150, 30);
        contactLabel.setFont(labelFont);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(50, 320, 150, 30);
        emailLabel.setFont(labelFont);


        jstudentName = new JTextField();
        jstudentName.setBounds(200, 80, 200, 30);
        jstudentName.setBackground(Color.WHITE);

        jstudentID = new JTextField();
        jstudentID.setBounds(200, 120, 200, 30);
        jstudentID.setBackground(Color.WHITE);

        jstudentGrade = new JTextField();
        jstudentGrade.setBounds(200, 160, 200, 30);
        jstudentGrade.setBackground(Color.WHITE);

        dobField = new JTextField();
        dobField.setBounds(200, 200, 200, 30);
        dobField.setBackground(Color.WHITE);

        maleRadio = new JRadioButton("Male");
        maleRadio.setBounds(200, 240, 80, 30);
        maleRadio.setBackground(new Color(240, 240, 240));

        femaleRadio = new JRadioButton("Female");
        femaleRadio.setBounds(290, 240, 100, 30);
        femaleRadio.setBackground(new Color(240, 240, 240));

        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        contactField = new JTextField();
        contactField.setBounds(200, 280, 200, 30);
        contactField.setBackground(Color.WHITE);

        emailField = new JTextField();
        emailField.setBounds(200, 320, 200, 30);
        emailField.setBackground(Color.WHITE);


        addStudent = new JButton("Add Student");
        addStudent.setBounds(650, 150, 150, 35);
        addStudent.setBackground(new Color(76, 175, 80)); // Green
        addStudent.setForeground(Color.WHITE);

        reset = new JButton("Reset");
        reset.setBounds(650, 195, 150, 35);
        reset.setBackground(new Color(255, 193, 7)); // Amber
        reset.setForeground(Color.BLACK);

        deleteRecord = new JButton("Delete Record");
        deleteRecord.setBounds(650, 240, 150, 35);
        deleteRecord.setBackground(new Color(244, 67, 54)); // Red
        deleteRecord.setForeground(Color.WHITE);

        searchField = new JTextField();
        searchField.setBounds(50, 360, 300, 30);
        searchField.setBackground(Color.WHITE);

        searchButton = new JButton("Search by ID");
        searchButton.setBounds(360, 360, 150, 35);
        searchButton.setBackground(new Color(33, 150, 243)); // Blue
        searchButton.setForeground(Color.WHITE);

        panel.add(jtitle);
        panel.add(studentName);
        panel.add(studentID);
        panel.add(studentGrade);
        panel.add(dobLabel);
        panel.add(genderLabel);
        panel.add(contactLabel);
        panel.add(emailLabel);
        panel.add(jstudentName);
        panel.add(jstudentID);
        panel.add(jstudentGrade);
        panel.add(dobField);
        panel.add(maleRadio);
        panel.add(femaleRadio);
        panel.add(contactField);
        panel.add(emailField);
        panel.add(addStudent);
        panel.add(reset);
        panel.add(deleteRecord);
        panel.add(searchField);
        panel.add(searchButton);


        String[] columnNames = {"Student Name", "Student ID", "Student Grade", "Date of Birth", "Gender", "Contact Name", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);

        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(25);
        studentTable.getTableHeader().setBackground(new Color(33, 150, 243)); 
        studentTable.getTableHeader().setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(50, 400, 860, 150);
        panel.add(scrollPane);


        addStudent.addActionListener(this);
        reset.addActionListener(this);
        deleteRecord.addActionListener(this);
        searchButton.addActionListener(this);

        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addStudent) {
            String name = jstudentName.getText();
            String id = jstudentID.getText();
            String grade = jstudentGrade.getText();
            String dob = dobField.getText();
            String contact = contactField.getText();
            String email = emailField.getText();
            String gender = maleRadio.isSelected() ? "Male" : femaleRadio.isSelected() ? "Female" : "";

            if (name.isEmpty() || id.isEmpty() || grade.isEmpty() || dob.isEmpty() || contact.isEmpty() || email.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!Utility.isValidEmail(email)) {
                JOptionPane.showMessageDialog(panel, "Invalid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!Utility.isValidDate(dob)) {
                JOptionPane.showMessageDialog(panel, "Invalid date of birth. Use the format 'dd-MM-yyyy'.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!Utility.isValidGrade(grade)) {
                JOptionPane.showMessageDialog(panel, "Invalid student grade. It should be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!Utility.isNumeric(id)) {
                JOptionPane.showMessageDialog(panel, "Invalid student ID. It should be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!Utility.isValidContactNumber(contact)) {
                JOptionPane.showMessageDialog(panel, "Invalid contact number. It should be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean idExists = false;
                for (int row = 0; row < tableModel.getRowCount(); row++) {
                    if (tableModel.getValueAt(row, 1).equals(id)) {
                        idExists = true;
                        break;
                    }
                }

                if (idExists) {
                    JOptionPane.showMessageDialog(panel, "Student ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    String[] data = {name, id, grade, dob, gender, contact, email};
                    tableModel.addRow(data);
                    JOptionPane.showMessageDialog(panel, "Student data added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    resetFields();
                }
            }
        } else if (e.getSource() == reset) {
            resetFields();
        } else if (e.getSource() == deleteRecord) {
            int selectedRow = studentTable.getSelectedRow();
            if (selectedRow >= 0) {
                tableModel.removeRow(selectedRow);
                JOptionPane.showMessageDialog(panel, "Student data deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(panel, "Please select a record to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == searchButton) {
            String searchId = searchField.getText();
            if (searchId.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Please enter a Student ID to search.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean found = false;
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                if (tableModel.getValueAt(row, 1).equals(searchId)) {
                    studentTable.setRowSelectionInterval(row, row);
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(panel, "Student ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void resetFields() {
        jstudentName.setText("");
        jstudentID.setText("");
        jstudentGrade.setText("");
        dobField.setText("");
        genderGroup.clearSelection();
        contactField.setText("");
        emailField.setText("");
    }
}