import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class SignUp extends JFrame{
    boolean registerUser(String FullName, String userName, String email, String password){
        try{
            Connection conn = DataBaseConnection.getConnection();
            String sql = "Insert into users(full_name, username, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,FullName);
            stmt.setString(2, userName);
            stmt.setString(3,email);
            stmt.setString(4,password);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Sign Up" + e.getMessage());
            return  false;
        }




    }
    public SignUp() {



        setTitle("SignUp");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon(LogIn.class.getResource("/resources/signup.png"));
        Image i = image.getImage();

        JPanel Panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(i, 0, 0, getWidth(), getHeight(), this);

            }
        };
        Panel.setLayout(null);
        JLabel nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(60,220,100,25);
        nameLabel.setForeground(new Color(8,41,150));
        JTextField nameField = new JTextField(30);
        nameField.setBounds(150,220,200,25);
        nameLabel.setFont(new Font("Georgia", Font.BOLD, 16));

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(60,270,100,25);
        userLabel.setForeground(new Color(8,41,150));
        JTextField userField = new JTextField(30);
        userField.setBounds(150,270,200,25);
        userLabel.setFont(new Font("Georgia", Font.BOLD, 16));



        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(60,320,100,25);
        emailLabel.setForeground(new Color(8,41,150));
        JTextField emailField = new JTextField(40);
        emailField.setBounds(150,320,200,25);
        emailLabel.setFont(new Font("Georgia", Font.BOLD, 16));


        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(60,370,100,25);
        passLabel.setForeground(new Color(8,41,150));
        JPasswordField passField = new JPasswordField(20);
        passField.setBounds(150,370,200,25);
        passLabel.setFont(new Font("Georgia", Font.BOLD, 16));

        JCheckBox showhide = new JCheckBox("Show Password");
        showhide.setBounds(250,396,110,20);
        passLabel.setForeground(new Color(8,41,150));
        showhide.setFont(new Font("SansSerif", Font.PLAIN, 11));
        showhide.addActionListener(e -> {
            if (showhide.isSelected()) {
                passField.setEchoChar((char) 0); // show password
            } else {
                passField.setEchoChar('\u2022'); // • (hide password)
            }
        });




        JButton signup = new JButton("Register");
        signup.setBounds(150,430,200,30);
        signup.setBackground(new Color(8, 42, 150));
        signup.setForeground(Color.WHITE);
        signup.setFocusPainted(false);
        signup.setFont(new Font("SansSerif", Font.BOLD, 14));



        JLabel already = new JLabel("Already Registered?");
        already.setBounds(127,470,140,20);
        already.setForeground(new Color(8,41,150));
        already.setFont(new Font("SansSerif", Font.ITALIC, 13));

        JButton login = new JButton("LogIn");
        login.setBounds(250,470,80,25);
        login.setBackground(new Color(70, 70, 70));
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setFont(new Font("SansSerif", Font.PLAIN, 10));

Panel.add(nameField);
Panel.add(nameLabel);
Panel.add(userField);
Panel.add(userLabel);
        Panel.add(passLabel);
        Panel.add(passField);
        Panel.add(emailLabel);
        Panel.add(emailField);
        Panel.add(already);
   Panel.add(login);
  Panel.add(signup);
Panel.add(showhide);
        add(Panel);

        setVisible(true);

        signup.addActionListener(e -> {
            String FullName = nameField.getText();
            String userName = userField.getText();
            String email = emailField.getText();
            String password = new String(passField.getPassword());

            //InputValidation:
            if(FullName.isEmpty() || userName.isEmpty() || email.isEmpty() || password.isEmpty()){
                JOptionPane.showMessageDialog(this,"Please Enter All Fields");
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
            if (registerUser(FullName, userName, email, password)) {
                JOptionPane.showMessageDialog(this, "Signup successful! You can now log in.");
               new LogIn();
            } else {
                JOptionPane.showMessageDialog(this, "Signup failed. Username might already exist.");
            }



        });






    }

    public static void main(String[] args) {
        new SignUp();
    }
}
