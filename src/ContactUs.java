import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setFont(new Font("georgia",Font.ITALIC,14));
        back.setFocusPainted(false);



        Panel.add(back);
        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        nameLabel.setForeground(Color.BLACK);
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        emailLabel.setForeground(Color.BLACK);


        JLabel messageLabel = new JLabel("Write Message");
        messageLabel.setFont(new Font("Georgia", Font.BOLD, 14));
        messageLabel.setForeground(Color.BLACK);


        Panel.add(nameLabel);
        Panel.add(emailLabel);
        Panel.add(messageLabel);



        // Name
        JTextField nameField = new JTextField();

        nameField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        Panel.add(nameField);

        // Email
        JTextField emailField = new JTextField();

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

        Panel.add(messageScroll);


        // Send Message Button
        JButton sendButton = new JButton("Send Message");

        sendButton.setBackground(Color.BLACK);
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.setFont(new Font("georgia", Font.BOLD, 12));
        Panel.add(sendButton);


        Panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int w = Panel.getWidth();
                int h = Panel.getHeight();

                back.setBounds(10, 10, 80, 25);

                int formX = (int)(w * 0.55); // custom offset
                int labelWidth = 100;
                int fieldWidth = (int)(w * 0.3);
                int fieldHeight = 30;
                int yStart = (int)(h * 0.35);
                int spacing = 50;
                nameLabel.setBounds(formX - labelWidth+52, yStart, labelWidth, fieldHeight);
                nameField.setBounds(formX+36, yStart, fieldWidth, fieldHeight);

                emailLabel.setBounds(formX - labelWidth+52, yStart + spacing, labelWidth, fieldHeight);
                emailField.setBounds(formX+36, yStart + spacing, fieldWidth, fieldHeight);

                messageLabel.setBounds(formX - labelWidth+30, yStart + spacing * 2, labelWidth+26, fieldHeight);
                messageScroll.setBounds(formX+36, yStart + spacing * 2, fieldWidth, 100);

                sendButton.setBounds(formX+36, yStart + spacing * 2 + 120, fieldWidth, 35);
            }
        });


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

back.addActionListener(e -> {
    dispose();
});


        add(Panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ContactUs();
    }
}
