import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUp extends JFrame {

    private JTextField nameField, userField, emailField;
    private JPasswordField passField;
    private JCheckBox showhide;
    private JButton signup, login;
    private JLabel nameLabel, userLabel, emailLabel, passLabel, already;

    boolean registerUser(String FullName, String userName, String email, String password) {
        try {
            Connection conn = DataBaseConnection.getConnection();
            String sql = "INSERT INTO users(full_name, username, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, FullName);
            stmt.setString(2, userName);
            stmt.setString(3, email);
            stmt.setString(4, password);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Sign Up Error: " + e.getMessage());
            return false;
        }
    }

    public SignUp() {
        setTitle("SignUp");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Background Image
        ImageIcon image = new ImageIcon(getClass().getResource("/resources/signup2.jpg"));
        Image bgImage = image.getImage();

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(null);
        setContentPane(panel);

        // Components
        nameLabel = new JLabel("Full Name");
        nameLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        nameLabel.setForeground(Color.BLACK);
        nameField = new JTextField(30);

        userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        userLabel.setForeground(Color.BLACK);
        userField = new JTextField(30);

        emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        emailLabel.setForeground(Color.BLACK);
        emailField = new JTextField(40);

        passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Georgia", Font.BOLD, 16));
        passLabel.setForeground(Color.BLACK);
        passField = new JPasswordField(20);

        showhide = new JCheckBox("Show Password");
        showhide.setFont(new Font("SansSerif", Font.PLAIN, 11));
        showhide.setBackground(new Color(0, 0, 0, 0));
        showhide.setForeground(Color.black);
        showhide.addActionListener(e -> {
            if (showhide.isSelected()) {
                passField.setEchoChar((char) 0);
            } else {
                passField.setEchoChar('\u2022');
            }
        });

        signup = new JButton("Register");
        signup.setFont(new Font("SansSerif", Font.BOLD, 14));
        signup.setBackground(Color.black);
        signup.setForeground(Color.WHITE);
        signup.setFocusPainted(false);

        already = new JLabel("Already Registered?");
        already.setFont(new Font("SansSerif", Font.ITALIC, 13));
        already.setForeground(Color.BLACK);

        login = new JButton("LogIn");
        login.setFont(new Font("SansSerif", Font.PLAIN, 10));
        login.setBackground(new Color(70, 70, 70));
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);

        // Add components to panel
        panel.add(nameLabel); panel.add(nameField);
        panel.add(userLabel); panel.add(userField);
        panel.add(emailLabel); panel.add(emailField);
        panel.add(passLabel); panel.add(passField);
        panel.add(showhide);
        panel.add(signup);
        panel.add(already); panel.add(login);

        // Initial positioning
        setComponentPositions(getWidth(), getHeight());

        // Resize handling
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                setComponentPositions(getWidth(), getHeight());
            }
        });

        // Button Actions
        signup.addActionListener(e -> {
            String fullName = nameField.getText();
            String userName = userField.getText();
            String email = emailField.getText();
            String password = new String(passField.getPassword());

            // Validation
            if (fullName.isEmpty() || userName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter all fields.");
                return;
            }
            if (!email.contains("@") || !email.contains(".")) {
                JOptionPane.showMessageDialog(this, "Enter a valid email address.");
                return;
            }
            if (password.length() < 8) {
                JOptionPane.showMessageDialog(this, "Password must be at least 8 characters.");
                return;
            }

            if (registerUser(fullName, userName, email, password)) {
                JOptionPane.showMessageDialog(this, "Signup successful! You can now log in.");
                new LogIn();
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Signup failed. Username might already exist.");
            }
        });

        login.addActionListener(e -> {
            new LogIn();
            dispose();
        });

        setVisible(true);
    }

    private void setComponentPositions(int frameWidth, int frameHeight) {
        int centerX = frameWidth / 2;
        int baseY = frameHeight / 2 - 150;

        nameLabel.setBounds(centerX - 400, baseY, 100, 25);
        nameField.setBounds(centerX - 270, baseY, 200, 25);

        userLabel.setBounds(centerX - 400, baseY + 50, 100, 25);
        userField.setBounds(centerX - 270, baseY + 50, 200, 25);

        emailLabel.setBounds(centerX - 400, baseY + 100, 100, 25);
        emailField.setBounds(centerX - 270, baseY + 100, 200, 25);

        passLabel.setBounds(centerX - 400, baseY + 150, 100, 25);
        passField.setBounds(centerX - 270, baseY + 150, 200, 25);

        showhide.setBounds(centerX -160, baseY + 176, 120, 20);

        signup.setBounds(centerX - 270, baseY + 210, 200, 30);
        already.setBounds(centerX - 270, baseY + 250, 140, 20);
        login.setBounds(centerX -140, baseY + 250, 80, 25);
    }

    public static void main(String[] args) {
        new SignUp();
    }
}
