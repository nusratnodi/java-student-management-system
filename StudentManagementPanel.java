import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;  

public class StudentManagementPanel extends JPanel {
    private JTextField nameField, idField, gradeField;
    private JButton addButton, clearButton;
    private JTable table;
    private DefaultTableModel tableModel;

    public StudentManagementPanel() {
        setLayout(new BorderLayout());
        
        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        
        inputPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        inputPanel.add(gradeField);
        
        addButton = new JButton("Add Student");
        clearButton = new JButton("Clear");
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(clearButton);
        
        inputPanel.add(buttonPanel);
        
        // Table
        String[] columns = {"Name", "ID", "Grade"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        // Event listeners
        addButton.addActionListener(e -> addStudent());
        clearButton.addActionListener(e -> clearFields());
    }
    
    private void addStudent() {
        String name = nameField.getText();
        String id = idField.getText();
        String grade = gradeField.getText();
        
        if (name.isEmpty() || id.isEmpty() || grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields");
            return;
        }
        
        tableModel.addRow(new Object[]{name, id, grade});
        clearFields();
    }
    
    private void clearFields() {
        nameField.setText("");
        idField.setText("");
        gradeField.setText("");
    }
}