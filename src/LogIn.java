import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn extends JFrame {
    //authentication
    boolean authenticateUser(String username, String email,String password){
        try{
            Connection conn = DataBaseConnection.getConnection();
            String sql = "Select * from users where username = ? AND email = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs =stmt.executeQuery();
            return rs.next();
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

   public LogIn(){
setTitle("LogIn");
setSize(1000,600);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLocationRelativeTo(null);
    ImageIcon image = new ImageIcon(LogIn.class.getResource("/resources/login.png"));
    Image i = image.getImage();

    JPanel Panel = new JPanel() {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
g.drawImage(i,0,0,getWidth(),getHeight(),this);

        }
    };
Panel.setLayout(null);

//labels:

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(60,250,100,25);
        userLabel.setForeground(new Color(8,41,150));
        JTextField userField = new JTextField(30);
        userField.setBounds(150,250,200,25);
       userLabel.setFont(new Font("Georgia", Font.BOLD, 16));


       JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(60,300,100,25);
        emailLabel.setForeground(new Color(8,41,150));
        JTextField emailField = new JTextField(40);
        emailField.setBounds(150,300,200,25);
       emailLabel.setFont(new Font("Georgia", Font.BOLD, 16));


        JLabel passLabel = new JLabel("Password");
        passLabel.setBounds(60,350,100,25);
        passLabel.setForeground(new Color(8,41,150));
        JPasswordField passField = new JPasswordField(20);
        passField.setBounds(150,350,200,25);
       passLabel.setFont(new Font("Georgia", Font.BOLD, 16));


       JButton login = new JButton("Log In");
       login.setBounds(150,400,200,30);
       login.setBackground(new Color(8, 42, 150));
       login.setForeground(Color.WHITE);
       login.setFocusPainted(false);
       login.setFont(new Font("SansSerif", Font.BOLD, 14));

JLabel new_to_us = new JLabel("New to us?-");
new_to_us.setBounds(150,450,90,20);
       new_to_us.setForeground(new Color(8,41,150));
      new_to_us.setFont(new Font("SansSerif", Font.ITALIC, 13));

        JButton signup = new JButton("SignUp");
        signup.setBounds(225,450,80,25);
       signup.setBackground(new Color(70, 70, 70));
       signup.setForeground(Color.WHITE);
       signup.setFocusPainted(false);
       signup.setFont(new Font("SansSerif", Font.PLAIN, 10));


       Panel.add(userLabel);
       Panel.add(userField);
       Panel.add(passLabel);
       Panel.add(passField);
       Panel.add(emailLabel);
       Panel.add(emailField);
       Panel.add(login);
       Panel.add(signup);
       Panel.add(new_to_us);
       add(Panel);

       setVisible(true);

       login.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               String username = userField.getText();
               String email = emailField.getText();
               String password= new String(passField.getPassword());
           }
       });

}

    public static void main(String[] args) {
        new LogIn();
    }
}