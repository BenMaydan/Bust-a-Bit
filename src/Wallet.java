public class Wallet {
    public static final int STARTING_BALANCE = 500;
    public int balance = STARTING_BALANCE;

    public Wallet() {
    }
    public Wallet(int cents) {
        if (cents > 0)
            balance = cents;
    }

    public int combine(Wallet ma) {
        balance += ma.balance;
        ma.balance = 0;
        return balance;
    }

    public Wallet withdraw(int amount) {
        if (balance <= amount)
            balance -= amount;
        return new Wallet(amount);
    }

    public void transferTo(Wallet w) {
        w.combine(this);
    }




    public int getBalance() {
        return balance;
    }
}
