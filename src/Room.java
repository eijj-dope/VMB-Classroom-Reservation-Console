import java.util.ArrayList;

public class Room {
    private String roomNumber;
    private boolean available;
    private ArrayList<Reservation> reservations = new ArrayList<>();

    public Room(String roomNumber, boolean available) {
        this.roomNumber = roomNumber;
        this.available = available;
    }

    public String getRoomNumber() { return roomNumber; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    public ArrayList<Reservation> getReservations() { return reservations; }

    public void addReservation(Reservation r) { reservations.add(r); }

    public void removeReservation(String user) {
        reservations.removeIf(r -> r.getReservedBy().equalsIgnoreCase(user));
    }
}
