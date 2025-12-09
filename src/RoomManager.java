import java.util.ArrayList;

public class RoomManager {
    private ArrayList<Room> rooms;

    public RoomManager(Storage storage) {
        this.rooms = storage.getRooms(); // Use shared Storage
    }

    public void viewAvailableRooms() {
        System.out.println("\n=== AVAILABLE ROOMS ===");
        for (Room r : rooms) {
            if (!r.isAvailable() || !r.getReservations().isEmpty()) {
                System.out.println(r.getRoomNumber() + " - UNAVAILABLE");
            } else {
                System.out.println(r.getRoomNumber());
            }
        }
    }


    public void viewAllRooms() {
        System.out.println("\n=== ALL ROOMS ===");
        for (Room r : rooms) {
            if (!r.isAvailable())
                System.out.println(r.getRoomNumber() + " - UNAVAILABLE (Faculty)");
            else if (!r.getReservations().isEmpty()) {
                for (Reservation res : r.getReservations()) {
                    System.out.println(r.getRoomNumber() + " - RESERVED by " +
                            res.getReservedBy() + " (" + res.getTimeRange() + ")");
                }
            } else {
                System.out.println(r.getRoomNumber() + " - AVAILABLE");
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

                // Check for time conflicts
                for (Reservation existing : r.getReservations()) {
                    if (timeOverlap(start, end, existing.getStartTime(), existing.getEndTime())) {
                        System.out.println("Time conflict with " + existing.getReservedBy() +
                                " (" + existing.getTimeRange() + ")");
                        return;
                    }
                }

                // No conflict → reserve the room
                r.addReservation(new Reservation(roomNum, user, start, end));
                System.out.println("Room " + roomNum + " reserved by " + user +
                        " (" + start + " - " + end + ")");
                return;
            }
        }
        System.out.println("Room not found.");
    }



    public void viewUserReservations(String user) {
        boolean found = false;
        System.out.println("\n=== " + user.toUpperCase() + " RESERVATIONS ===");
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

                // If no reservations remain, mark as available
                if (r.getReservations().isEmpty() && r.getRoomNumber().startsWith("VMB") && r.getRoomNumber().charAt(4) != '2') {
                    r.setAvailable(true);
                }

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
        boolean found = false;
        System.out.println("\n=== ALL RESERVATIONS ===");
        for (Room r : rooms) {
            for (Reservation res : r.getReservations()) {
                System.out.println(r.getRoomNumber() + " - " +
                        res.getReservedBy() + " (" + res.getTimeRange() + ")");
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
                    if (a != b &&
                            a.getReservedBy().equalsIgnoreCase(b.getReservedBy()) &&
                            (a.getStartTime().equalsIgnoreCase(b.getStartTime()) ||
                                    a.getEndTime().equalsIgnoreCase(b.getEndTime()))) {
                        System.out.println("Conflict: " + a.getReservedBy() +
                                " has overlapping reservations in " + r.getRoomNumber());
                        conflict = true;
                    }
                }
            }
        }
        if (!conflict) System.out.println("No conflicts found.");
    }

    private boolean timeOverlap(String start1, String end1, String start2, String end2) {
        // Convert to integers for 24-hour comparison
        int s1 = convertTimeToHour(start1);
        int e1 = convertTimeToHour(end1);
        int s2 = convertTimeToHour(start2);
        int e2 = convertTimeToHour(end2);

        return s1 < e2 && s2 < e1; // overlap if times intersect
    }

    // Converts "7am"/"2pm" to integer 24-hour value
    private int convertTimeToHour(String t) {
        t = t.toLowerCase().trim();
        int hour = Integer.parseInt(t.replaceAll("[^0-9]", ""));
        if (t.endsWith("pm") && hour != 12) hour += 12;
        if (t.endsWith("am") && hour == 12) hour = 0;
        return hour;
    }

}
