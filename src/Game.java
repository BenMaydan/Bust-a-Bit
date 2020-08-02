public class Game {
    private Stock stock;
    private int currentDay;

    public Game() {
        stock = new Stock();
        currentDay = 0;
    }

    public void resetStock() {
        stock.init();
        currentDay = 0;
    }

    public void advanceDay() {
        currentDay++;
    }

    public double getStockPrice() {
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
