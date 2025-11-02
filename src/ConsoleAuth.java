import java.util.Scanner;

public class ConsoleAuth {
    private final Storage storage;
    private final Scanner sc;

    public ConsoleAuth(Storage storage, Scanner sc) {
        this.storage = storage;
        this.sc = sc;
    }

    public User login() {
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = storage.findUser(username);
        if (user == null) {
            System.out.println("User not found!");
            return null;
        }
        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password!");
            return null;
        }

        System.out.println("Login successful! Welcome, " + user.getUsername());
        return user;
    }

    public void register() {
        System.out.print("Enter new username: ");
        String username = sc.nextLine();

        if (storage.findUser(username) != null) {
            System.out.println("Username already exists!");
            return;
        }

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User newUser = new User(username, password);
        storage.addUser(newUser);
        System.out.println("Registration successful! You can now log in.");
    }
}
