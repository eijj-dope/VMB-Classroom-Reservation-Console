import java.util.ArrayList;
import java.util.List;

public class RoomManager {
    private List<Room> rooms = new ArrayList<>();

    public void addRoom(String name) {
        rooms.add(new Room(name));
    }

    public void viewRooms() {
        System.out.println("\n===== AVAILABLE ROOMS =====");
        for (Room room : rooms) {
            String status = room.isReserved() ? "Reserved by " + room.getReservedBy() : "Available";
            System.out.println("- " + room.getName() + " (" + status + ")");
        }
    }

    public boolean reserveRoom(String roomName, String userName) {
        for (Room room : rooms) {
            if (room.getName().equalsIgnoreCase(roomName)) {
                return room.reserve(userName);
            }
        }
        return false;
    }

    public boolean cancelReservation(String roomName, String userName) {
        for (Room room : rooms) {
            if (room.getName().equalsIgnoreCase(roomName)) {
                return room.cancelReservation(userName);
            }
        }
        return false;
    }

    public void viewAllReservations() {
        System.out.println("\n===== ALL RESERVATIONS =====");
        boolean hasAny = false;
        for (Room room : rooms) {
            if (room.isReserved()) {
                System.out.println(room.getName() + " → Reserved by " + room.getReservedBy());
                hasAny = true;
            }
        }
        if (!hasAny) System.out.println("No current reservations.");
    }

    public void viewUserReservations(String userName) {
        System.out.println("\n===== YOUR RESERVATIONS =====");
        boolean hasAny = false;
        for (Room room : rooms) {
            if (room.isReserved() && room.getReservedBy().equals(userName)) {
                System.out.println("- " + room.getName());
                hasAny = true;
            }
        }
        if (!hasAny) System.out.println("You have no reservations.");
    }
}
