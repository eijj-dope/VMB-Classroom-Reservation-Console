import java.util.Scanner;

public class ConsoleUI {
    private final Storage storage;
    private final ConsoleAuth auth;
    private final Scanner sc = new Scanner(System.in);

    public ConsoleUI(Storage storage) {
        this.storage = storage;
        this.auth = new ConsoleAuth(storage, sc);
    }

    public void start() {
        while (true) {
            System.out.println("\n===== CLASSROOM RESERVATION SYSTEM =====");
            System.out.println("1. Login as Admin");
            System.out.println("2. Continue as User");
            System.out.println("3. Register User");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> adminMenu();
                case "2" -> userMenu();
                case "3" -> auth.register();
                case "4" -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void adminMenu() {
        System.out.print("\nEnter Admin Username: ");
        String user = sc.nextLine();
        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        Admin admin = storage.getAdmin();
        if (admin.login(user, pass)) {
            while (true) {
                System.out.println("\n===== ADMIN MENU =====");
                System.out.println("1. View All Rooms");
                System.out.println("2. Add New Room");
                System.out.println("3. View All Reservations");
                System.out.println("4. Logout");
                System.out.print("Choose: ");
                String choice = sc.nextLine();

                switch (choice) {
                    case "1" -> storage.getRoomManager().viewRooms();
                    case "2" -> {
                        System.out.print("Enter new room name: ");
                        String name = sc.nextLine();
                        storage.getRoomManager().addRoom(name);
                        System.out.println("Room added successfully!");
                    }
                    case "3" -> storage.getRoomManager().viewAllReservations();
                    case "4" -> {
                        System.out.println("Logged out!");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }
        } else {
            System.out.println("Incorrect admin credentials!");
        }
    }

    private void userMenu() {
        User user = auth.login();
        if (user == null) return;

        while (true) {
            System.out.println("\n===== USER MENU =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Reserve a Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View My Reservations");
            System.out.println("5. Logout");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            RoomManager manager = storage.getRoomManager();
            switch (choice) {
                case "1" -> manager.viewRooms();
                case "2" -> {
                    System.out.print("Enter room name to reserve: ");
                    String roomName = sc.nextLine();
                    if (manager.reserveRoom(roomName, user.getUsername())) {
                        System.out.println("Room reserved successfully!");
                    } else {
                        System.out.println("Room not available or not found.");
                    }
                }
                case "3" -> {
                    System.out.print("Enter room name to cancel: ");
                    String roomName = sc.nextLine();
                    if (manager.cancelReservation(roomName, user.getUsername())) {
                        System.out.println("Reservation cancelled!");
                    } else {
                        System.out.println("No such reservation found.");
                    }
                }
                case "4" -> manager.viewUserReservations(user.getUsername());
                case "5" -> {
                    System.out.println("Logged out!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
