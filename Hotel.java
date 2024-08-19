import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Hotel {
    List<Room> rooms;
    List<Reservation> reservations;

    public Hotel(){
        rooms = new ArrayList<>(); // here ArrayList instead of List cus then id have to implement a bunch of methods that arent necessary
        reservations = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public Reservation makeReservation(String reservationId, Room room, User user, Date checkInDate, Date checkOutDate){
        Reservation newReservation = new Reservation(reservationId, room, user, checkInDate, checkOutDate);
        if (newReservation.confirmReservation()) {
            reservations.add(newReservation);
            return newReservation;
        } else {
            System.out.println("Reservation could not be completed due to insufficient balance.");
            return null;
        }
    }

    public List<Room> searchAvailableRooms(String category){ // this method is for the hotel management to look for available rooms
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms){
            if(room.getCategory().equalsIgnoreCase(category) && room.isAvailable()) // check if the category matches
                availableRooms.add(room);
        }
        return availableRooms;
    }

    public List<Reservation> getReservationsByUser(User user){ // this method is for the user to see their reservations
        List<Reservation> userReservations = new ArrayList<>();
        for (Reservation reservation : reservations){
            if (reservation.getUser().equals(user))
                userReservations.add(reservation);
        }
        return userReservations;
    }
}
