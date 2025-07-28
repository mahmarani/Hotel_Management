import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ContactUs extends JFrame {
    public ContactUs() {
        setTitle("Contact Us");
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon(LogIn.class.getResource("/resources/contact.jpg"));
        Image i = image.getImage();

        JPanel Panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(i,0,0,getWidth(),getHeight(),this);

            }
        };
        Panel.setLayout(null);
        JButton back = new JButton("BACK");
        back.setBounds(0,0,80,20);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("georgia",Font.ITALIC,14));
        back.setFocusPainted(false);


        Panel.add(back);
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setBounds(520, 200, 80, 30);

        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setBounds(520, 250, 80, 30);

        JLabel messageLabel = new JLabel("Write Message");
        messageLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        messageLabel.setForeground(Color.BLACK);
        messageLabel.setBounds(480, 300, 120, 30);

        Panel.add(nameLabel);
        Panel.add(emailLabel);
        Panel.add(messageLabel);



        // Name
        JTextField nameField = new JTextField();
        nameField.setBounds(600, 200, 300, 30);
        nameField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        Panel.add(nameField);

        // Email
        JTextField emailField = new JTextField();
        emailField.setBounds(600, 250, 300, 30);
        emailField.setForeground(Color.BLACK);
        emailField.setBackground(Color.WHITE);
        emailField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        Panel.add(emailField);



        // Message box
        JTextArea messageArea = new JTextArea();
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        JScrollPane messageScroll = new JScrollPane(messageArea);
        messageScroll.setBounds(600, 300, 300, 100);
        Panel.add(messageScroll);


        // Send Message Button
        JButton sendButton = new JButton("Send Message");
        sendButton.setBounds(470, 440, 300, 30);
        sendButton.setBackground(Color.BLACK);
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setFont(new Font("georgia", Font.BOLD, 12));
        Panel.add(sendButton);

        // Handle send
        sendButton.addActionListener((ActionEvent e) -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String message = messageArea.getText().trim();


            if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.");
                return;
            }



            JOptionPane.showMessageDialog(this, "Your message has been sent. Thank you!");
            nameField.setText("");
            emailField.setText("");
            messageArea.setText("");

        });




        add(Panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ContactUs();
    }
}
