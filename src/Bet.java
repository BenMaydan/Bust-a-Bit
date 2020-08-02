public class Bet {
    private Wallet wallet;
    private boolean autoCashOut = false;
    private int autoCashOutMultiplier = 1;

    public Bet(Wallet wallet, Integer autoCashOutMultiplier) {
        this.wallet = wallet;
        if (autoCashOutMultiplier != null) {
            this.autoCashOut = true;
            this.autoCashOutMultiplier = autoCashOutMultiplier;
        }
    }

    public void removeAutoCashOut() {
        autoCashOut = false;
    }

//    public void addAutoCashOut(Server s, int multiplier) {
//        if (s.getStock().getMultiplier() < multiplier) {
//            autoCashOut = true;
//            autoCashOutMultiplier = multiplier;
//        }
//    }

    public Wallet getWallet() { return wallet; }
    public boolean isAutoCashOut() { return autoCashOut; }
    public int getAutoCashOutMultiplier() { return autoCashOutMultiplier; }
}
