import java.util.ArrayList;

public class RoomManager {
    private ArrayList<Room> rooms = new ArrayList<>();

    public RoomManager() {
        generateRooms();
    }

    private void generateRooms() {
        for (int floor = 1; floor <= 5; floor++) {
            for (int num = 1; num <= 5; num++) {
                String id = "VMB " + floor + "0" + num;
                boolean available = (floor != 2); // 2nd floor unavailable
                rooms.add(new Room(id, available));
            }
        }
    }

    public void viewAvailableRooms() {
        System.out.println("\n=== AVAILABLE ROOMS ===");
        for (Room r : rooms) {
            if (!r.isAvailable()) {
                System.out.println(r.getRoomNumber() + " - Unavailable");
            } else {
                System.out.println(r.getRoomNumber());
            }
        }
    }


    public void viewAllRooms() {
        System.out.println("\n=== ROOM LIST ===");
        for (Room r : rooms) {
            if (!r.isAvailable())
                System.out.println(r.getRoomNumber() + " - UNAVAILABLE (Faculty)");
            else if (r.getReservations().isEmpty())
                System.out.println(r.getRoomNumber() + " - AVAILABLE");
            else {
                for (Reservation res : r.getReservations()) {
                    System.out.println(r.getRoomNumber() + " - RESERVED by " +
                            res.getReservedBy() + " (" + res.getTimeRange() + ")");
                }
            }
        }
    }

    public void reserveRoom(String roomNum, String start, String end, String user) {
        for (Room r : rooms) {
            if (r.getRoomNumber().equalsIgnoreCase(roomNum)) {
                if (!r.isAvailable()) {
                    System.out.println("Room unavailable (Faculty room).");
                    return;
                }

                // Check if within 7am–7pm roughly
                if (!isValidTime(start) || !isValidTime(end)) {
                    System.out.println("Invalid time! Please enter between 7AM and 7PM.");
                    return;
                }

                for (Reservation existing : r.getReservations()) {
                    if (timeOverlap(start, end, existing.getStartTime(), existing.getEndTime())) {
                        System.out.println("Time conflict with " +
                                existing.getReservedBy() + " (" + existing.getTimeRange() + ")");
                        return;
                    }
                }

                r.addReservation(new Reservation(roomNum, user, start, end));
                System.out.println("Room " + roomNum + " reserved by " + user +
                        " (" + start + " - " + end + ")");
                return;
            }
        }
        System.out.println("Room not found.");
    }

    public void viewUserReservations(String user) {
        System.out.println("\n=== " + user.toUpperCase() + " RESERVATIONS ===");
        boolean found = false;
        for (Room r : rooms) {
            for (Reservation res : r.getReservations()) {
                if (res.getReservedBy().equalsIgnoreCase(user)) {
                    System.out.println(r.getRoomNumber() + " - " + res.getTimeRange());
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No reservations found.");
    }

    public void cancelReservation(String roomNum, String user) {
        for (Room r : rooms) {
            if (r.getRoomNumber().equalsIgnoreCase(roomNum)) {
                int before = r.getReservations().size();
                r.removeReservation(user);
                if (r.getReservations().size() < before)
                    System.out.println("Reservation canceled for " + roomNum);
                else
                    System.out.println("No reservation found for " + roomNum);
                return;
            }
        }
        System.out.println("Room not found.");
    }

    public void viewAllReservations() {
        System.out.println("\n=== ALL ROOM RESERVATIONS ===");
        boolean found = false;
        for (Room r : rooms) {
            for (Reservation res : r.getReservations()) {
                System.out.println(r.getRoomNumber() + " - " + res.getReservedBy() + " (" + res.getTimeRange() + ")");
                found = true;
            }
        }
        if (!found) System.out.println("No reservations yet.");
    }

    public void reportConflicts() {
        System.out.println("\n=== CONFLICT REPORT ===");
        boolean conflict = false;
        for (Room r : rooms) {
            for (Reservation a : r.getReservations()) {
                for (Reservation b : r.getReservations()) {
                    if (a != b && a.getReservedBy().equalsIgnoreCase(b.getReservedBy()) &&
                            timeOverlap(a.getStartTime(), a.getEndTime(), b.getStartTime(), b.getEndTime())) {
                        System.out.println("Conflict: " + a.getReservedBy() +
                                " has overlapping reservations in " + r.getRoomNumber());
                        conflict = true;
                    }
                }
            }
        }
        if (!conflict) System.out.println("No conflicts found.");
    }

    private boolean isValidTime(String t) {
        t = t.toLowerCase();
        return (t.endsWith("am") || t.endsWith("pm"));
    }

    private boolean timeOverlap(String s1, String e1, String s2, String e2) {
        // Simplified overlap check based on text (no actual conversion)
        return s1.equalsIgnoreCase(s2) || e1.equalsIgnoreCase(e2);
    }
}
