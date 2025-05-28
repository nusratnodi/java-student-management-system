import java.awt.*;
import javax.swing.*;

public class StudentManagementSystem extends JFrame {
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JPanel studentManagementPanel;

    public StudentManagementSystem() {
        setTitle("Student Management System by Nusrat Jahan");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create sidebar with gradient background
        JPanel sidebar = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(66, 135, 245), 0, getHeight(), new Color(33, 67, 122));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(200, getHeight()));

        // Style sidebar buttons
        JButton studentManagementBtn = StyledButton.createStyledButton("Student Management", new Color(52, 152, 219));


        studentManagementBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        studentManagementBtn.addActionListener(e -> cardLayout.show(mainPanel, "StudentManagement"));


        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(studentManagementBtn);
        sidebar.add(Box.createVerticalStrut(10));

        // Create main panel with CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(new Color(245, 245, 245));

        // Initialize panels
        studentManagementPanel = new StudentManagementPanel().createPanel();

        mainPanel.add(studentManagementPanel, "StudentManagement");


        // Set up layout
        setLayout(new BorderLayout());
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StudentManagementSystem();
        });
    }
}