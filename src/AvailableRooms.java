import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AvailableRooms extends JFrame {

    public AvailableRooms(String roomType, String bedType, boolean ac, String view) {
        setTitle("Available Rooms");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        ImageIcon image = new ImageIcon(LogIn.class.getResource("/resources/available2.jpg"));
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

JPanel resultPanel = new JPanel();
resultPanel.setLayout(new BoxLayout(resultPanel,BoxLayout.Y_AXIS));
JScrollPane scrollPane = new JScrollPane(resultPanel);
scrollPane.setBounds(5,80,480,350);
Panel.add(scrollPane);

try{
    Connection conn = DataBaseConnection.getConnection();
    String sql = "SELECT * FROM rooms WHERE room_type=? AND bed_type=? AND ac=? AND view_type=? AND status=1 ";
    PreparedStatement p= conn.prepareStatement(sql);
    p.setString(1,roomType);
    p.setString(2,bedType);
    p.setBoolean(3,ac);
    p.setString(4,view);

    ResultSet rs = p.executeQuery();
    boolean found = false;
    while(rs.next()){
        found = true;
        String roomNo = rs.getString("room_id");
        double price = rs.getDouble("price_per_night");

        JPanel roomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        roomPanel.setOpaque(false);
        JLabel roomInfo = new JLabel("Room No: " + roomNo + " | ₹" + price);
        roomInfo.setFont(new Font("Georgia", Font.PLAIN, 16));
        roomInfo.setForeground(Color.WHITE);
        Panel.add(roomPanel);
        Panel.add(roomInfo);


    }
    if(!found) {
        JLabel noRoom = new JLabel("No rooms found matching your preferences.");
        noRoom.setFont(new Font("Georgia", Font.BOLD, 16));
        noRoom.setForeground(Color.RED);
        Panel.add(noRoom);
    }

} catch (Exception e) {
    e.printStackTrace();
}























        setVisible(true);
        //action
        back.addActionListener(e -> {
            dispose();

        });
setVisible(true);
    }

    public static void main(String[] args) {
        new AvailableRooms("Suite","single",true,"garden");
    }
}
