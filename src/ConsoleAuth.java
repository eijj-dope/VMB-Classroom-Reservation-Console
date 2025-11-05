import java.util.Scanner;

public class ConsoleAuth {
    private Scanner sc = new Scanner(System.in);

    public Admin adminLogin() {
        System.out.print("Enter admin name: ");
        String name = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        System.out.println("Login successful as ADMIN: " + name);
        return new Admin(name);
    }

    public User userLogin() {
        System.out.print("Enter username: ");
        String name = sc.nextLine();
        System.out.print("Enter password: ");
        String pass = sc.nextLine();
        System.out.println("Login successful as USER: " + name);
        return new User(name);
    }
}
