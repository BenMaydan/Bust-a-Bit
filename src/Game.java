public class Game {
    private Stock stock;
    private boolean crashed;
    private int currentDay;

    public Game() {
        stock = new Stock();
        crashed = false;
        currentDay = 0;
    }

    public void resetStock() {
        stock.init();
        crashed = false;
        currentDay = 0;
    }

    public void advanceDay() {
        if (currentDay == stock.getDayCrashed())
            crashed = true;
        currentDay++;
    }
    public void reverseDays(int days) {
        currentDay -= days;
    }

    public double getStockPrice() {
        if (crashed)
            return 0;
        return stock.getPrice(currentDay);
    }
    public double getStockPrice(int day) {
        return stock.getPrice(day);
    }






    public Stock getStock() {
        return stock;
    }

    public int getCurrentDay() {
        return currentDay;
    }
}
