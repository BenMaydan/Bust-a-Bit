public class Client {
    private Server server;
    private Profile profile;
    private boolean betMade = false;

    public Client(Server server, Profile profile) {
        this.server = server;
        this.profile = profile;
        this.betMade = false;
    }

    public void makeBet(int autoCashOutMultiplier) {
        if (server.isAcceptingBets()) {
            server.takeBet(profile, new Bet(profile.getWallet(), autoCashOutMultiplier));
            betMade = true;
        }
    }
}
