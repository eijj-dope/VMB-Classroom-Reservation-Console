import java.util.Scanner;

public class ConsoleUI {
    private Scanner sc = new Scanner(System.in);
    private ConsoleAuth auth = new ConsoleAuth();
    private RoomManager roomManager = new RoomManager();

    public void start() {
        System.out.println("====================================");
        System.out.println("     CLASSROOM SCHEDULE HEB BUILDING   ");
        System.out.println("====================================");

        while (true) {
            System.out.println("\nLogin as:");
            System.out.println("1. Admin");
            System.out.println("2. User");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> adminMenu(auth.adminLogin());
                case 2 -> userMenu(auth.userLogin());
                case 3 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void adminMenu(Admin admin) {
        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. View All Rooms");
            System.out.println("2. View All Reservations");
            System.out.println("3. Exit to Main Menu");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> roomManager.viewAllRooms();
                case 2 -> roomManager.viewAllReservations();
                case 3 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private void userMenu(User user) {
        while (true) {
            System.out.println("\n=== USER MENU ===");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Reserve a Room");
            System.out.println("3. View My Reservations");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. Report Schedule Conflicts");
            System.out.println("6. Exit to Main Menu");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> roomManager.viewAvailableRooms();
                case 2 -> {
                    System.out.println("=== ROOM RESERVATION ===");
                    System.out.print("Enter Room Number (e.g., VMB 101): ");
                    String roomNum = sc.nextLine();
                    System.out.print("Enter Start Time (e.g., 7am): ");
                    String start = sc.nextLine();
                    System.out.print("Enter End Time (e.g., 10am): ");
                    String end = sc.nextLine();
                    roomManager.reserveRoom(roomNum, start, end, user.getName());
                }
                case 3 -> roomManager.viewUserReservations(user.getName());
                case 4 -> {
                    System.out.print("Enter Room Number to Cancel: ");
                    String room = sc.nextLine();
                    roomManager.cancelReservation(room, user.getName());
                }
                case 5 -> roomManager.reportConflicts();
                case 6 -> { return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
