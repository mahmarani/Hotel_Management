import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

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

        ImageIcon image = new ImageIcon(LogIn.class.getResource("/resources/choosing_room.jpg"));
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

        // ========== TEXT FIELDS ==========
        JTextField checkInField = createTextField("2025-08-01", 85, 455, 100, 35);
        checkInField.setBackground(Color.WHITE);
        checkInField.setForeground(Color.BLACK);
        JTextField checkOutField = createTextField("2025-08-05", 200, 455, 100, 35);
        checkOutField.setBackground(Color.WHITE);
        checkOutField.setForeground(Color.BLACK);

        // ========== LABELS ==========
        JLabel bedTypeLabel = createLabel("Select Bed Type:", 85, 240, 200, 65);
        JLabel roomTypeLabel = createLabel("Select Room Type", 85, 200, 200, 35);
        JLabel acLabel = createLabel("AC?", 85, 310, 100, 35);
        JLabel viewLabel = createLabel("Select View", 85, 367, 100, 35);
        JLabel checkInLabel = createLabel("Check In date", 85, 420, 120, 35);
        JLabel checkOutLabel = createLabel("Check out date", 200, 420, 150, 35);

        // ========== ADD TO PANEL ==========
        Component[] allComponents = {
                back, search, home, aboutUs, contactUs,
                roomTypeBox, bedTypeBox, acBox, viewBox,
                checkInField, checkOutField,
                bedTypeLabel, roomTypeLabel, acLabel, viewLabel, checkInLabel, checkOutLabel
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

        // Action
        back.addActionListener(e -> dispose());
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

    private JTextField createTextField(String text, int x, int y, int w, int h) {
        JTextField tf = new JTextField(text);
        tf.setBounds(x, y, w, h);
        tf.setBackground(Color.BLACK);
        tf.setForeground(Color.WHITE);
        tf.setFont(new Font("Georgia", Font.BOLD, 14));
        return tf;
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
