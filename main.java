import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Hotel myHotel = new Hotel();

        // adding rooms to the hotel
        myHotel.addRoom(new Room(101, "Single", 1000));
        myHotel.addRoom(new Room(201, "Double", 2500));
        myHotel.addRoom(new Room(301, "Suite", 5000));
        myHotel.addRoom(new Room(102, "Single", 1000));
        myHotel.addRoom(new Room(202, "Double", 2500));
        myHotel.addRoom(new Room(302, "Suite", 5000));
        myHotel.addRoom(new Room(103, "Single", 1000));
        myHotel.addRoom(new Room(203, "Double", 2500));
        myHotel.addRoom(new Room(303, "Suite", 5000));

        System.out.println("---WELCOME TO THE HOTEL RESERVATION SYSTEM---");

        System.out.println("Enter your User ID: ");
        String userID = input.nextLine();
        System.out.println("Enter your name: ");
        String userName = input.nextLine();
        System.out.println("Enter your balance: ");
        double balance = input.nextDouble();
        User user = new User(userID, userName, balance);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // necessary for the checkin and checkout dates

        boolean running = true;
        while(running){
            System.out.println("\n1. Search Available Rooms");
            System.out.println("2. Make Reservations");
            System.out.println("3. View Reservations");
            System.out.println("4. Process Payment");
            System.out.println("5. Exit");
            int choice = input.nextInt();
            input.nextLine(); // consuming the enter thats pressed by the user so that later when the user is prompted, it can take the input

            switch(choice){
                case 1:
                    System.out.println("Enter your required category (Single/Double/Suite): ");
                    String categoryRequired = input.nextLine();
                    List<Room> availableRooms = myHotel.searchAvailableRooms(categoryRequired);
                    if (availableRooms.isEmpty())
                        System.out.println("No rooms found in the category");
                    else{
                        System.out.println("Available Rooms: ");
                        for (Room room : availableRooms){
                            System.out.println("Room number: " + room.getRoomNumber() + "\nPrice per Night: " + room.getPricePerNight());
                        }
                    }
                    break;

                case 2:
                    System.out.println("Enter Reservation ID: ");
                    String reservationID = input.nextLine();
                    System.out.println("Enter Room Number: ");
                    int roomNumber = input.nextInt();
                    input.nextLine(); // same purpose as before
                    System.out.println("Enter Check-In Date (yyyy-MM-dd)");
                    Date checkInDate;
                    try{
                        checkInDate = sdf.parse(input.nextLine());
                    }
                    catch(ParseException e ){
                        System.out.println("Invalid date format.");
                        continue;
                    }
                    System.out.println("Enter Check-Out Date (yyyy-MM-dd)");
                    Date checkOutDate;
                    try{
                        checkOutDate = sdf.parse(input.nextLine());
                    }
                    catch(ParseException e ){
                        System.out.println("Invalid date format.");
                        continue;
                    }
                    Room room = myHotel.getRooms().stream().filter(r -> r.getRoomNumber() == roomNumber).findFirst().orElse(null); // converts the list into a stream amd filters through it to find the room withe the required room number and find the first room u see or else present null
                    if (room != null && room.isAvailable()) {
                        Reservation reservation = myHotel.makeReservation(reservationID, room, user, checkInDate, checkOutDate);
                        if (reservation != null)
                            System.out.println("Reservation made successfully. Total amount: " + reservation.getTotal());
                    } else
                        System.out.println("Room not available or invalid room number.");
                    break;

                case 3:
                    List<Reservation> userReservations = myHotel.getReservationsByUser(user);
                    if (userReservations.isEmpty()) {
                        System.out.println("No reservations found.");
                    } else {
                        System.out.println("Reservations:");
                        for (Reservation res : userReservations) {
                            System.out.println("Reservation ID: " + res.getReservationId() +
                                    ", Room Number: " + res.getRoom().getRoomNumber() +
                                    ", Check-In: " + res.getCheckInDate() +
                                    ", Check-Out: " + res.getCheckOutDate() +
                                    ", Total: " + res.getTotal());
                        }
                    }
                    break;

                case 4:
                    System.out.println("Enter Payment Amount: ");
                    double amount = input.nextDouble();
                    Payment payment = new Payment(user, amount);
                    if (payment.processPayment()) {
                        System.out.println("Payment processed successfully.");
                    } else {
                        System.out.println("Payment failed.");
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
