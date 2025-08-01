import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RoomGenerator {

    public static void main(String[] args) {
        generateRooms();
    }

    public static void generateRooms() {
        String[] roomTypes = {"Standard", "Deluxe", "Suite"};
        String[] bedTypes = {"Single", "Double"};
        boolean[] acOptions = {true, false};
        String[] views = {"City", "Sea", "Garden"};

        int roomCounter = 100;

        try {
            // Replace with your DB info
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Hotel_management", "root", "root");

            String sql = "INSERT INTO rooms (room_id, room_type, bed_type, ac, view_type, price_per_night,status) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (String roomType : roomTypes) {
                for (String bed : bedTypes) {
                    for (boolean ac : acOptions) {
                        for (String view : views) {
                            for (int i = 0; i < 10; i++) {
                                String room_id = roomType.charAt(0) + bed.substring(0, 1) + (ac ? "A" : "N") + view.charAt(0) + roomCounter++;

                                double basePrice = 1000.0;
                                if (roomType.equals("Deluxe")) basePrice += 500;
                                if (roomType.equals("Suite")) basePrice += 1000;
                                if (bed.equals("Double")) basePrice += 300;
                                if (ac) basePrice += 200;

                                stmt.setString(1, room_id);
                                stmt.setString(2, roomType);
                                stmt.setString(3, bed);
                                stmt.setBoolean(4, ac);
                                stmt.setString(5, view);
                                stmt.setDouble(6, basePrice);
                                stmt.setBoolean(7, true);

                                stmt.addBatch();
                            }
                        }
                    }
                }
            }

            stmt.executeBatch();
            stmt.close();
            conn.close();

            System.out.println("Rooms generated and inserted successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
