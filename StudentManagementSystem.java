import java.awt.*;
import javax.swing.*;

public class StudentManagementSystem extends JFrame {
    public StudentManagementSystem() {
        setTitle("Student Management System");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));
        sidebar.setBackground(new Color(66, 135, 245)); 

        JButton studentManagementBtn = new JButton("Student Management");
        studentManagementBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        studentManagementBtn.setBackground(new Color(52, 152, 219)); 
        studentManagementBtn.setForeground(Color.WHITE);

        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Color.WHITE);

        mainPanel.add(new StudentManagementPanel().createPanel(), "StudentManagement");

        setLayout(new BorderLayout());
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        
        studentManagementBtn.addActionListener(e -> cardLayout.show(mainPanel, "StudentManagement"));

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(studentManagementBtn);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentManagementSystem());
    }
}