import java.util.Date;

public class Reservation {
    private String user;
    private Room room;
    private Date date;

    public Reservation(String user, Room room) {
        this.user = user;
        this.room = room;
        this.date = new Date();
    }

    public String getUser() {
        return user;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Room: " + room.getName() + " | Reserved by: " + user + " | Date: " + date;
    }
}
