import javax.lang.model.element.Name;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;


public class HomePage extends JFrame {

     JPanel backgroundPanel;
     Image backgroundImage;
     List<JButton> buttonList = new ArrayList<>();
     List<Point> originalPositions = new ArrayList<>();

    // Label
     JLabel nameLabel;
     Point originalLabelPosition;
     int originalLabelWidth = 240;
    int originalLabelHeight = 100;
     int originalFontSize = 16;

    // Base resolution for proportional scaling
    private final int baseWidth = 1000;
    private final int baseHeight = 600;



    public HomePage(String Name) {
        setTitle("Home");
        setSize(baseWidth, baseHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load background image
        ImageIcon imageIcon = new ImageIcon(LogIn.class.getResource("/resources/home.png"));
        backgroundImage = imageIcon.getImage();

        // Custom background panel with image
        backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        // Create label
        nameLabel = new JLabel(Name);
        nameLabel.setForeground(Color.BLACK);
        nameLabel.setFont(new Font("Georgia", Font.BOLD, originalFontSize));
        originalLabelPosition = new Point(160, 140);
        nameLabel.setBounds(originalLabelPosition.x, originalLabelPosition.y, originalLabelWidth, originalLabelHeight);
        backgroundPanel.add(nameLabel);

        // Add buttons at custom (manual) positions
        addCustomButton("Browse Rooms", 510, 85);
        addCustomButton("My Bookings", 510, 160);
        addCustomButton("About Us", 510, 230);
        addCustomButton("Help", 510, 295);
        addCustomButton("Contact Us", 740, 85);
        addCustomButton("LogOut", 740, 160);

        // Add resize listener to reposition buttons and label
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                repositionComponents();
                backgroundPanel.repaint();
            }
        });

        setVisible(true);
    }



    // Add button and store original position
    private void addCustomButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 180, 25);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Georgia", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backgroundPanel.add(button);
        buttonList.add(button);
        originalPositions.add(new Point(x, y)); // save base position


        button.addActionListener(e -> {
            if(text.equals("Browse Rooms")) {
              new BrowseRooms();
            } else if(text.equals("My Bookings")){
                //bookings page
            } else if (text.equals("About Us")) {
               new AboutUs();
            } else if (text.equals("Help")) {
                new help();
            } else if (text.equals("Contact Us")) {
              new ContactUs();
            } else if (text.equals("LogOut")) {
                int choice = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to log out?",
                        "Confirm Logout",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (choice == JOptionPane.YES_OPTION) {
                    dispose(); // Close current window
                    new LogIn();
                }
            }
        });
    }

    // Reposition all components based on window size
    private void repositionComponents() {
        double xRatio = (double) getWidth() / baseWidth;
        double yRatio = (double) getHeight() / baseHeight;

        // Reposition and resize buttons
        for (int i = 0; i < buttonList.size(); i++) {
            JButton btn = buttonList.get(i);
            Point original = originalPositions.get(i);
            int newX = (int) (original.x * xRatio);
            int newY = (int) (original.y * yRatio);
            btn.setBounds(newX, newY, (int)(180 * xRatio), (int)(25 * yRatio));
        }

        // Reposition and resize label
        int newLabelX = (int)(originalLabelPosition.x * xRatio);
        int newLabelY = (int)(originalLabelPosition.y * yRatio);
        int newLabelWidth = (int)(originalLabelWidth * xRatio);
        int newLabelHeight = (int)(originalLabelHeight * yRatio);
        int newFontSize = Math.max((int)(originalFontSize * yRatio), 12); // minimum font size
        nameLabel.setBounds(newLabelX, newLabelY, newLabelWidth, newLabelHeight);
        nameLabel.setFont(new Font("Georgia", Font.BOLD, newFontSize));
    }



    public static void main(String[] args) {
        new HomePage("Mahima Rani");
    }
}
