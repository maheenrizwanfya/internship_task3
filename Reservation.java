import java.util.Date;

public class Reservation {
    String reservationId;
    Room room;
    User user;
    Date checkInDate;
    Date checkOutDate;
    double total;
    boolean isPaid;

    public Reservation(String reservationId, Room room, User user, Date checkInDate, Date checkOutDate){
        this.reservationId = reservationId;
        this.room = room;
        this.user = user;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        total = calculateTotalAmount();
        this.isPaid = false;
        room.setAvailable(false);
    }

    public double calculateTotalAmount() {
        long diffInMills = Math.abs(checkOutDate.getTime() - checkInDate.getTime());
        long nights = diffInMills / 86400000; // manually convert milliseconds to days (1000*60*60*24)
        return nights * room.getPricePerNight();
    }

    public boolean confirmReservation() {
        Payment payment = new Payment(user, total, this); // "this" passes the current Reservation object as the third arguement for the Payment object
        if (payment.processPayment()) {
            this.isPaid = true;
            room.setAvailable(false);
            return true;
        } else {
            return false;
        }
    }

    public String getReservationId() {
        return reservationId;
    }

    public Room getRoom() {
        return room;
    }

    public User getUser() {
        return user;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void markAsPaid() {
        this.isPaid = true;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public double getTotal() {
        return calculateTotalAmount();
    }

}
