import java.util.ArrayList;

public class Storage {
    private ArrayList<Room> rooms = new ArrayList<>();

    public Storage() {
        for (int floor = 1; floor <= 5; floor++) {
            for (int num = 1; num <= 5; num++) {
                String id = "VMB " + floor + "0" + num;
                boolean available = (floor != 2); // 2nd floor unavailable
                rooms.add(new Room(id, available));
            }
        }
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
