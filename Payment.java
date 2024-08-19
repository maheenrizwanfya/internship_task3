public class Payment {
    User user;
    double amount;

    public Payment(User user, double amount){
        this.user = user;
        this.amount = amount;
    }

    public boolean processPayment(){
        if (user.getBalance() < amount){
            System.out.println("Insufficient balance for the transaction");
            return false;
        }
        else{
            user.setBalance(user.getBalance() - amount);
            System.out.println("Transaction successful");
            return true;
        }
    }

    public User getUser() {
        return user;
    }

    public double getAmount() {
        return amount;
    }
}
