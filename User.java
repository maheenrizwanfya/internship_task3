import java.util.ArrayList;
import java.util.List;

public class User {
    String userId;
    String name;
    double balance;
    List<Payment> paymentHistory;

    public User(String userId, String name, double balance){
        this.userId = userId;
        this.name = name;
        this.balance = balance;
        paymentHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Payment> getPaymentHistory() {
        return paymentHistory;
    }
}
