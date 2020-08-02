import java.awt.*;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;




public class Control {
    private Display display;
    private Game game;
    private Timer gameTickerTimer;

    public Control(Display display, Game game) {
        this.display = display;
        this.game = game;

        Timer gameTickerTimer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (getDay() == getDayCrashed())
                    gameTickerTimer.cancel();
                else
                    game.advanceDay();
                display.repaint();
            }
        };
        gameTickerTimer.schedule(task, new Date(), 100);
    }

    public double getStockIPO() {
        return game.getStock().getIPO();
    }

    public double getStockMaxPrice() {
        return game.getStock().getMaxPrice();
    }

    public double getPrice(int day) {
        return game.getStockPrice(day);
    }

    public double getPrice() {
        return game.getStockPrice();
    }

    public void gameTick() { game.advanceDay(); }

    public int getDay() {
        return game.getCurrentDay();
    }

    public int getDayCrashed() {
        return game.getStock().getDayCrashed();
    }
}
