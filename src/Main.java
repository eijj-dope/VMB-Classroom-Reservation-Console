import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage();              // shared storage
        RoomManager manager = new RoomManager(storage);
        ConsoleAuth auth = new ConsoleAuth(sc);

        ConsoleUI ui = new ConsoleUI(sc, manager, auth);
        ui.start();
    }
}
