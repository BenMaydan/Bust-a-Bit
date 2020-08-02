import java.util.ArrayList;

public class Server {
    private static Stock stock;
    private static boolean acceptingBets = true;
    private static ArrayList<Bet> bets = new ArrayList<>();

    public static void setupForBets() {
        stock = new Stock();
    }

    public void takeBet(Profile p, Bet b) {
        if (acceptingBets && p.isValid())
            bets.add(b);
    }

    public void checkAutoCashOutBets() {
        // for ()
    }

    public void evaluateBets() {
        for (Bet b : bets) {

        }
        bets.clear();
    }





    public boolean isAcceptingBets() { return acceptingBets; }
    public Stock getStock() { return stock; }
}
