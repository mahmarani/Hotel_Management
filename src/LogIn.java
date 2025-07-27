import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogIn extends JFrame {

   public LogIn(){
JFrame frame = new JFrame("Login");

    ImageIcon image = new ImageIcon("D:Hotel/src/resourses/login.png");
    Image i = image.getImage();

    JPanel Panel = new JPanel() {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
g.drawImage(i,0,0,getWidth(),getHeight(),this);

        }
    };
Panel.setLayout(null);

//labels:

        JLabel userLabel = new JLabel();
        JTextField userField = new JTextField(30);
        JLabel emailLabel = new JLabel();
        JTextField emailField = new JTextField(40);
        JLabel passLabel = new JLabel();
        JPasswordField passField = new JPasswordField(20);
JButton login = new JButton("LogIn");
JButton signup = new JButton("Go to SignUp");


       Panel.add(userLabel);
       Panel.add(userField);
       Panel.add(passLabel);
       Panel.add(passField);
       Panel.add(login);
       Panel.add(signup);


       setVisible(true);
}
}