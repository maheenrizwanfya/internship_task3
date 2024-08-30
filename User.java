public class User {
    String userId;
    String name;
    double balance;

    public User(String userId, String name, double balance){
        this.userId = userId;
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
