public class Main {
    public static void main(String[] args) {
        Storage storage = new Storage();
        ConsoleUI ui = new ConsoleUI(storage);
        ui.start();
    }
}
