import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LogIn extends JFrame {

    // Components
    private JTextField emailField;
    private JPasswordField passField;
    private JButton login, signup;
    private JLabel emailLabel, passLabel, newToUs;

    // Authentication logic
    boolean authenticateUser(String email, String password) {
        try {
            Connection conn = DataBaseConnection.getConnection();
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    static String getUserName(String email, String password) {
        try {
            Connection conn = DataBaseConnection.getConnection();
            String sql = "SELECT full_name FROM users WHERE email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("full_name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public LogIn() {
        setTitle("LogIn");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Background image
        ImageIcon bgIcon = new ImageIcon(getClass().getResource("/resources/login2.png"));
        Image bgImage = bgIcon.getImage();

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);
        setContentPane(panel);

        // Create components
        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        emailLabel.setForeground(Color.BLACK);

        emailField = new JTextField(30);

        passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        passLabel.setForeground(Color.black);

        passField = new JPasswordField(30);

        login = new JButton("Log In");
        login.setBackground(Color.black);
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setFont(new Font("SansSerif", Font.BOLD, 14));

        newToUs = new JLabel("New to us?-");
        newToUs.setFont(new Font("SansSerif", Font.ITALIC, 13));
        newToUs.setForeground(Color.BLACK);

        signup = new JButton("SignUp");
        signup.setFont(new Font("SansSerif", Font.PLAIN, 10));
        signup.setBackground(new Color(70, 70, 70));
        signup.setForeground(Color.WHITE);
        signup.setFocusPainted(false);

        // Add to panel
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(passLabel);
        panel.add(passField);
        panel.add(login);
        panel.add(newToUs);
        panel.add(signup);

        // Set initial positions
        setComponentPositions(getWidth(), getHeight());

        // Reposition on resize
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                setComponentPositions(getWidth(), getHeight());
            }
        });

        // Action listeners
        login.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passField.getPassword());
            if (authenticateUser(email, password)) {
                String name = getUserName(email, password);
                JOptionPane.showMessageDialog(null, "LogIn Successfully");
                new HomePage(name);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Not Yet Registered");
            }
        });

        signup.addActionListener(e -> {
            new SignUp();
        });

        setVisible(true);
    }

    private void setComponentPositions(int frameWidth, int frameHeight) {
        int centerX = frameWidth / 2;
        int baseY = frameHeight / 2 - 100;

        emailLabel.setBounds(centerX - 400, baseY+10, 100, 25);
        emailField.setBounds(centerX - 300, baseY+10, 200, 25);

        passLabel.setBounds(centerX - 400, baseY + 50, 100, 25);
        passField.setBounds(centerX - 300, baseY + 50, 200, 25);

        login.setBounds(centerX - 300, baseY + 100, 200, 30);

        newToUs.setBounds(centerX - 300, baseY + 200-65, 90, 20);
        signup.setBounds(centerX - 220, baseY + 150-17, 100, 25);
    }

    public static void main(String[] args) {
        new LogIn();
    }
}
