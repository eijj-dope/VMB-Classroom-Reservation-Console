public class Reservation {
    private String roomNumber;
    private String reservedBy;
    private String startTime;
    private String endTime;

    public Reservation(String roomNumber, String reservedBy, String startTime, String endTime) {
        this.roomNumber = roomNumber;
        this.reservedBy = reservedBy;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getRoomNumber() { return roomNumber; }
    public String getReservedBy() { return reservedBy; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getTimeRange() { return startTime + " - " + endTime; }
}
