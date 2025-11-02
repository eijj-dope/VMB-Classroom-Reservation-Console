import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final List<User> users = new ArrayList<>();
    private final RoomManager roomManager = new RoomManager();
    private final Admin admin = new Admin("admin", "admin123");

    public Storage() {
        // Initial rooms
        roomManager.addRoom("Room A");
        roomManager.addRoom("Room B");
        roomManager.addRoom("Room C");
    }

    public Admin getAdmin() {
        return admin;
    }

    public RoomManager getRoomManager() {
        return roomManager;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User findUser(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;
    }
}
