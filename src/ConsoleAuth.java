import java.util.Scanner;

public class ConsoleAuth {
    private Scanner sc;
    private Admin admin = new Admin();

    public ConsoleAuth(Scanner sc) {
        this.sc = sc;
    }

    public Admin adminLogin() {
        System.out.print("Enter admin username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        if (admin.login(username, password)) {
            System.out.println("Admin login successful!");
            return admin;
        } else {
            System.out.println("Invalid ADMIN credentials! Access denied.");
            return null;
        }
    }

    public User userLogin() {
        System.out.print("Enter username: ");
        String name = sc.nextLine();
        System.out.print("Enter password: ");
        sc.nextLine(); // password placeholder (not verified)
        System.out.println("Login successful as USER: " + name);
        return new User(name);
    }
}
