import java.util.ArrayList;
import java.util.Random;

public class Stock {
    // Changes the time it takes for the stock to crash
    public static final int MAX_ROLLAGE_MIN = 0;
    public static final int MAX_ROLLAGE_MAX = 365;
    // The amount the MAX_ROLLAGE can differ by
    public static final int MAX_ROLLAGE_DIF = MAX_ROLLAGE_MAX-MAX_ROLLAGE_MIN;

    public static final double MIN_IPO = 0.00;
    public static final double MAX_IPO = 3.00;
    public static final double DIF_IPO = MAX_IPO-MIN_IPO;

    // Volatility is measured in percentages, not dollar amounts it goes up or down
    public static final double MIN_VOLATILITY = 0.01;
    public static final double MAX_VOLATILITY = 0.15;
    public static final double DIF_VOLATILITY = MAX_VOLATILITY-MIN_VOLATILITY;

    // Trajectory minimums and maximums to determine when stock should climb or go down
    public static final int MIN_UPWARDS_TRAJECTORY = 3;
    public static final int MAX_UPWARDS_TRAJECTORY = 6;
    public static final double CHANCE_CHANGING_IN_UPWARDS = 0.1;
    public static final int MIN_DOWNWARDS_TRAJECTORY = 5;
    public static final int MAX_DOWNWARDS_TRAJECTORY = 8;
    public static final double CHANCE_CHANGING_IN_DOWNWARDS = 0.7;
    private int upwardsTrajectory;
    private int downwardsTrajectory;
    private boolean goUp = true;

    private double ipo;
    private double[] history;
    private boolean crashed = false;
    private int dayCrashed = -1;

    public Stock() {
        init();
    }

    public void init() {
        // maximum time until the stock crashes
        // Can last anywhere from 0 days to MAX_ROLLAGE_DIF days
        dayCrashed = (int) (MAX_ROLLAGE_MIN + MAX_ROLLAGE_DIF * new Random().nextDouble());
        history = new double[dayCrashed+1];

        upwardsTrajectory = 0;
        downwardsTrajectory = 0;

        ipo = MIN_IPO + DIF_IPO * new Random().nextDouble();
        history[0] = ipo;

        // Every index of the history of the stock is one day
        // The stock will "roll" for a maximum of MAX_ROLLAGE days before crashing
        int today = 1;
        while (!crashed) {
            if (today-1 == dayCrashed) {
                history[today-1] = 0.0;
                break;
            }
            history[today] = calculatePrice(today-1);
            today++;
        }
    }

    private double calculatePrice(int yesterday) {
        double prevPrice = getPrice(yesterday);
        double volatility = (MIN_VOLATILITY + DIF_VOLATILITY * new Random().nextDouble());
        // The stock is more likely to follow the trajectory it was following the day before
        int sign = 1;
//        if (goUp) {
//            sign = 1;
//            upwardsTrajectory++;
//            if (upwardsTrajectory >= MIN_UPWARDS_TRAJECTORY && upwardsTrajectory <= MAX_UPWARDS_TRAJECTORY && Math.random() < CHANCE_CHANGING_IN_UPWARDS) {
//                upwardsTrajectory = 0;
//                downwardsTrajectory++;
//                goUp = false;
//            }
//            else if (upwardsTrajectory > MAX_UPWARDS_TRAJECTORY) {
//                upwardsTrajectory = 0;
//                downwardsTrajectory++;
//                goUp = false;
//            }
//        }
//        else {
//            sign = -1;
//            downwardsTrajectory++;
//            if (downwardsTrajectory >= MIN_DOWNWARDS_TRAJECTORY && downwardsTrajectory <= MAX_DOWNWARDS_TRAJECTORY && Math.random() < CHANCE_CHANGING_IN_DOWNWARDS) {
//                downwardsTrajectory = 0;
//                upwardsTrajectory++;
//                goUp = true;
//            }
//            else if (downwardsTrajectory > MAX_DOWNWARDS_TRAJECTORY) {
//                downwardsTrajectory = 0;
//                upwardsTrajectory++;
//                goUp = true;
//            }
//        }
        double price = prevPrice + (prevPrice * sign * volatility);
        if (price < 0) {
            crashed = true;
            return 0.0;
        }
        return price;
    }


    public void prettyPrintHistory() {
        System.out.println("IPO     = $" + getPrice(0));
        for (int i = 1; i < history.length; i++)
            System.out.println("DAY:{" + i + "} = $" + getPrice(i));
    }






    public double getIPO() {
        return ipo;
    }

    public double getMaxPrice() {
        return getPrice(history.length-1);
    }

    public int getDayCrashed() {
        return history.length-1;
    }

    public double getPrice(int day) {
        return history[day];
    }

    public double getMultiplier(int day) {
        if (day <= 0)
            return 1;
        else if (day >= history.length)
            return 0;
        return getPrice(day)/getPrice(0);
    }
}
