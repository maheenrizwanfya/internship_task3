public class Payment {
    User user;
    double amount;
    Reservation reservation;

    public Payment(User user, double amount, Reservation reservation){
        this.user = user;
        this.amount = amount;
        this.reservation = reservation;
    }

    public boolean processPayment(){
        if (user.getBalance() < amount){
            System.out.println("Insufficient balance for the transaction");
            return false;
        }
        else{
            user.setBalance(user.getBalance() - amount);
            reservation.markAsPaid();
            return true;
        }
    }
}
