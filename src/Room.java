public class Room {
    private String name;
    private boolean isReserved;
    private String reservedBy;

    public Room(String name) {
        this.name = name;
        this.isReserved = false;
        this.reservedBy = null;
    }

    public String getName() {
        return name;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public String getReservedBy() {
        return reservedBy;
    }

    public boolean reserve(String userName) {
        if (isReserved) return false;
        isReserved = true;
        reservedBy = userName;
        return true;
    }

    public boolean cancelReservation(String userName) {
        if (isReserved && reservedBy.equals(userName)) {
            isReserved = false;
            reservedBy = null;
            return true;
        }
        return false;
    }
}
