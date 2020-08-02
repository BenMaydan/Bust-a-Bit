import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Profile {
    private String name = "";
    private Wallet wallet = new Wallet();
    private String dateCreated;

    public Profile(String name, int startingBalance) {
        this.name = name;
        wallet.combine(new Wallet(startingBalance));
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(new Date().toString().getBytes());
            dateCreated = md.digest().toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }






    public boolean isValid() {
        // Check hashed date created against the given date created
        return true;
    }
    public Wallet getWallet() { return wallet;              }
    public String getName()   { return name;                }
    public int getBalance()   { return wallet.getBalance(); }
}
