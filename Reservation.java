import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    String reservationId;
    Room room;
    User user;
    Date checkInDate;
    Date checkOutDate;
    double total;

    public Reservation(String reservationId, Room room, User user, Date checkInDate, Date checkOutDate){
        this.reservationId = reservationId;
        this.room = room;
        this.user = user;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        total = calculateTotalAmount();
        room.setAvailable(false);
    }

    public double calculateTotalAmount(){
        long diffInMills = Math.abs(checkOutDate.getTime() - checkInDate.getTime()); // using .getTime(), we get the number of milliseconds between the checkin and checkout time
        long nights = TimeUnit.DAYS.convert(diffInMills, TimeUnit.MILLISECONDS); // here using TimeUnit, we convert those milliseconds to days
        return nights * room.getPricePerNight();
    }

    public boolean confirmReservation() {
        Payment payment = new Payment(user, total);
        if (payment.processPayment()) {
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

    public double getTotal() {
        return calculateTotalAmount();
    }

}
