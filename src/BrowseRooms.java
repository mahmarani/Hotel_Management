
import javax.swing.*;
import java.awt.*;

public class BrowseRooms extends JFrame{
    int baseWidth= 1000;
    int baseHeight= 600;
    int originalLabelWidth = 240;
    int originalLabelHeight = 100;
    int originalFontSize = 16;
    Point originalLabelPosition;

public BrowseRooms(){
    setTitle("Rooms");
    setSize(1000,600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    ImageIcon image = new ImageIcon(LogIn.class.getResource("/resources/choosing_room.jpg"));
    Image img = image.getImage();

    JPanel Panel = new JPanel() {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(img,0,0,getWidth(),getHeight(),this);

        }
    };
    Panel.setLayout(null);
    //back
    JButton back = new JButton("BACK");
    back.setBounds(0,0,80,20);
    back.setBackground(Color.BLACK);
    back.setForeground(Color.WHITE);
    back.setFont(new Font("georgia",Font.ITALIC,14));
    back.setFocusPainted(false);
//search
    JButton search = new JButton("Search");
    search.setBounds(370,490, 150, 35);
    search.setBackground(Color.BLACK);
    search.setForeground(Color.WHITE);
    search.setFont(new Font("Georgia", Font.BOLD, 14));
    search.setFocusPainted(false);
    search.setCursor(new Cursor(Cursor.HAND_CURSOR));
    Panel.add(search);
    //rooms to home
    JButton home = new JButton("home");
    home.setBounds(370,40, 150, 35);
    home.setBackground(Color.BLACK);
    home.setForeground(Color.WHITE);
    home.setFont(new Font("Georgia", Font.BOLD, 14));
    home.setFocusPainted(false);
    home.setCursor(new Cursor(Cursor.HAND_CURSOR));
    Panel.add(home);
    //rooms to about us
     JButton About_us = new JButton("About Us");
    About_us.setBounds(370,30, 150, 35);
    About_us.setBackground(Color.BLACK);
    About_us.setForeground(Color.WHITE);
    About_us.setFont(new Font("Georgia", Font.BOLD, 14));
    About_us.setFocusPainted(false);
    About_us.setCursor(new Cursor(Cursor.HAND_CURSOR));
    Panel.add(About_us);
//Contact
JButton Contact_Us = new JButton("Contact");
    Contact_Us.setBounds(850,49, 100, 35);
    Contact_Us.setBackground(Color.BLACK);
    Contact_Us.setForeground(Color.WHITE);
    Contact_Us.setFont(new Font("Georgia", Font.BOLD, 14));
    Contact_Us.setFocusPainted(false);
    Contact_Us.setCursor(new Cursor(Cursor.HAND_CURSOR));
    Panel.add(Contact_Us);

    JComboBox<String> roomTypeBox, bedTypeBox, acBox, viewBox;
    JTextField checkInField, checkOutField;
    JButton searchButton, backButton;
    JPanel resultPanel;

    roomTypeBox = new JComboBox<>(new String[]{"Standard", "Deluxe", "Suite"});
    bedTypeBox = new JComboBox<>(new String[]{"Single", "Double"});
    acBox = new JComboBox<>(new String[]{"Yes", "No"});
    viewBox = new JComboBox<>(new String[]{"City", "Sea", "Garden"});
    checkInField = new JTextField("2025-08-01"); // Replace with JDatePicker later
    checkOutField = new JTextField("2025-08-05");


















    // Reposition all components based on window size



    Panel.add(back);
    add(Panel);
    setVisible(true);









    //ACTIONLISTENERS:
    back.addActionListener(e -> {
        dispose();
    });


}
    public void repositionComponents() {
        double xRatio = (double) getWidth() / baseWidth;
        double yRatio = (double) getHeight() / baseHeight;

        // Reposition and resize buttons
//        for (int i = 0; i < buttonList.size(); i++) {
//            JButton btn = buttonList.get(i);
//            Point original = originalPositions.get(i);
//            int newX = (int) (original.x * xRatio);
//            int newY = (int) (original.y * yRatio);
//            btn.setBounds(newX, newY, (int)(180 * xRatio), (int)(25 * yRatio));
//        }

        // Reposition and resize label
        int newLabelX = (int)(originalLabelPosition.x * xRatio);
        int newLabelY = (int)(originalLabelPosition.y * yRatio);
        int newLabelWidth = (int)(originalLabelWidth * xRatio);
        int newLabelHeight = (int)(originalLabelHeight * yRatio);
        int newFontSize = Math.max((int)(originalFontSize * yRatio), 12); // minimum font size
//        nameLabel.setBounds(newLabelX, newLabelY, newLabelWidth, newLabelHeight);
//        nameLabel.setFont(new Font("Georgia", Font.BOLD, newFontSize));
    }
    public static void main(String[] args) {
new  BrowseRooms();
    }
}