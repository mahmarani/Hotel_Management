import javax.swing.*;
import java.awt.*;

public class help extends JFrame {
    public help(){
        setTitle("Help");
        setSize(1000,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon(LogIn.class.getResource("/resources/Help.jpg"));
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
        add(Panel);
        setVisible(true);


        back.addActionListener(e -> {
            dispose();

        });
    }

    public static void main(String[] args) {
        new help();
    }

}
