import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

public class StudentManagementPanel {
    private JPanel panel;
    private JTextField nameField, idField, gradeField, dobField, contactField, emailField;
    private JRadioButton maleRadio, femaleRadio;
    private JTable table;
    private DefaultTableModel tableModel;

    public JPanel createPanel() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        formPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);
        
        formPanel.add(new JLabel("Student ID:"));
        idField = new JTextField();
        formPanel.add(idField);
        
        formPanel.add(new JLabel("Student Grade:"));
        gradeField = new JTextField();
        formPanel.add(gradeField);
        
        formPanel.add(new JLabel("Date of Birth:"));
        dobField = new JTextField();
        formPanel.add(dobField);
        
        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel();
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        formPanel.add(genderPanel);
        
        formPanel.add(new JLabel("Contact:"));
        contactField = new JTextField();
        formPanel.add(contactField);
        
        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Student");
        JButton resetButton = new JButton("Reset");
        JButton deleteButton = new JButton("Delete");
        
        addButton.addActionListener(e -> addStudent());
        resetButton.addActionListener(e -> resetForm());
        deleteButton.addActionListener(e -> deleteStudent());
        
        buttonPanel.add(addButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(deleteButton);
        
        // Table
        String[] columns = {"Name", "ID", "Grade", "DOB", "Gender", "Contact", "Email"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        
        // Add components to main panel
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.CENTER);
        panel.add(scrollPane, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private void addStudent() {
        String name = nameField.getText();
        String id = idField.getText();
        String grade = gradeField.getText();
        String dob = dobField.getText();
        String contact = contactField.getText();
        String email = emailField.getText();
        String gender = maleRadio.isSelected() ? "Male" : "Female";
        
        if (name.isEmpty() || id.isEmpty() || grade.isEmpty() || dob.isEmpty() || 
            contact.isEmpty() || email.isEmpty() || (!maleRadio.isSelected() && !femaleRadio.isSelected())) {
            JOptionPane.showMessageDialog(panel, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        Object[] row = {name, id, grade, dob, gender, contact, email};
        tableModel.addRow(row);
        resetForm();
    }
    
    private void resetForm() {
        nameField.setText("");
        idField.setText("");
        gradeField.setText("");
        dobField.setText("");
        contactField.setText("");
        emailField.setText("");
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
    }
    
    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(panel, "Please select a student to delete", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}