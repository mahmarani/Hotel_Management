import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
public class BrowseRooms extends JFrame {
    int baseWidth = 1000;
    int baseHeight = 600;

    JPanel Panel;
    Map<Component, Rectangle> originalBounds = new HashMap<>();
    Map<Component, Font> originalFonts = new HashMap<>();

    public BrowseRooms() {
        setTitle("Rooms");
        setSize(baseWidth, baseHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon(LogIn.class.getResource("/resources/ChooseYourRoom.jpg"));
        Image img = image.getImage();

        Panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        Panel.setLayout(null);

        // ========== BUTTONS ==========
        JButton back = createButton("BACK", 0, 0, 80, 20);
        JButton search = createButton("Search", 370, 490, 150, 35);
        JButton home = createButton("Home", 620, 47, 90, 30);
        JButton aboutUs = createButton("About", 745, 47, 90, 30);
        JButton contactUs = createButton("Contact", 850, 47, 90, 30);

        // ========== COMBO BOXES ==========
        JComboBox<String> roomTypeBox = createComboBox(new String[]{"Standard", "Deluxe", "Suite"}, 230, 200, 130, 35);
        JComboBox<String> bedTypeBox = createComboBox(new String[]{"Single", "Double"}, 230, 260, 130, 35);
        JComboBox<String> acBox = createComboBox(new String[]{"Yes", "No"}, 230, 320, 130, 35);
        JComboBox<String> viewBox = createComboBox(new String[]{"City", "Sea", "Garden"}, 230, 370, 130, 35);


        // ========== LABELS ==========
        JLabel bedTypeLabel = createLabel("Select Bed Type:", 85, 240, 200, 65);
        JLabel roomTypeLabel = createLabel("Select Room Type", 85, 200, 200, 35);
        JLabel acLabel = createLabel("AC?", 85, 310, 100, 35);
        JLabel viewLabel = createLabel("Select View", 85, 367, 100, 35);
        JLabel checkInLabel = createLabel("Check-in Date:", 85, 420, 120, 35);
        JLabel checkOutLabel = createLabel("Check-out Date:", 85, 450, 120, 35);

        // ===== DATE PICKERS =====

        JDateChooser checkInDate = new JDateChooser();
        checkInDate.setBounds( 230, 420, 120, 25);
        checkInDate.setDateFormatString("yyyy-MM-dd");
        JDateChooser checkOutDate = new JDateChooser();
        checkOutDate.setBounds(230, 450, 120, 25);
        checkOutDate.setDateFormatString("yyyy-MM-dd");

        // ========== ADD TO PANEL ==========
        Component[] allComponents = {
                back, search, home, aboutUs, contactUs,
                roomTypeBox, bedTypeBox, acBox, viewBox,
                bedTypeLabel, roomTypeLabel, acLabel, viewLabel,
                checkInLabel, checkInDate, checkOutLabel, checkOutDate
        };

        for (Component comp : allComponents) {
            Panel.add(comp);
            originalBounds.put(comp, comp.getBounds());
            originalFonts.put(comp, comp.getFont());
        }

        // Resize handler
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });

        add(Panel);
        setVisible(true);

        // Actions
        //Back
        back.addActionListener(e -> dispose());
        //home
        home.addActionListener(e -> {
           dispose();
        });
        //Contact
        contactUs.addActionListener(e -> {
            new ContactUs();
        });
        //About
        aboutUs.addActionListener(e -> {
            new AboutUs();
        });
        search.addActionListener(e -> {
            String roomTypeString = roomTypeBox.getSelectedItem().toString();
            String bedTypeString = bedTypeBox.getSelectedItem().toString();
            String acString = acBox.getSelectedItem().toString();
            boolean ac = acString.equalsIgnoreCase("Yes");
            String viewStr = viewBox.getSelectedItem().toString();
            Date checkIn = checkInDate.getDate();
            Date checkOut = checkOutDate.getDate();


            if (checkIn == null || checkOut == null) {
                JOptionPane.showMessageDialog(this, "Please select both check-in and check-out dates.");
                return;
            }
            if(!checkOut.after(checkIn)){
                JOptionPane.showMessageDialog(this, "Check-out date must be after check-in date.");
                return;
            }

            new AvailableRooms(roomTypeString,bedTypeString,ac,viewStr);

        });
    }

    private JButton createButton(String text, int x, int y, int w, int h) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, w, h);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Georgia", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JComboBox<String> createComboBox(String[] items, int x, int y, int w, int h) {
        JComboBox<String> box = new JComboBox<>(items);
        box.setBounds(x, y, w, h);
        box.setBackground(Color.BLACK);
        box.setForeground(Color.WHITE);
        box.setFont(new Font("Georgia", Font.BOLD, 14));
        return box;
    }



    private JLabel createLabel(String text, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, w, h);
        lbl.setFont(new Font("Georgia", Font.BOLD, 14));
        lbl.setForeground(Color.black);
        return lbl;
    }

    private void resizeComponents() {
        double xRatio = (double) getWidth() / baseWidth;
        double yRatio = (double) getHeight() / baseHeight;

        for (Component comp : Panel.getComponents()) {
            Rectangle original = originalBounds.get(comp);
            if (original != null) {
                int newX = (int) (original.x * xRatio);
                int newY = (int) (original.y * yRatio);
                int newW = (int) (original.width * xRatio);
                int newH = (int) (original.height * yRatio);
                comp.setBounds(newX, newY, newW, newH);
            }

            Font originalFont = originalFonts.get(comp);
            if (originalFont != null) {
                float newSize = (float) (originalFont.getSize() * yRatio);
                comp.setFont(originalFont.deriveFont(Math.max(newSize, 10f))); // min font size 10
            }
        }





    }

    public static void main(String[] args) {
        new BrowseRooms();
    }
}
