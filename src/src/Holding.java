public class Holding {

    private Stock stock;
    private int quantity;
    private double totalInvested;

    public Holding(Stock stock, int quantity, double totalInvested) {
        this.stock = stock;
        this.quantity = quantity;
        this.totalInvested = totalInvested;
    }

    public Stock getStock() {
        return stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalInvested() {
        return totalInvested;
    }

    public void buy(int quantity, double amount) {
        this.quantity += quantity;
        this.totalInvested += amount;
    }

    public double getMarketValue() {
        return quantity * stock.getPrice();
    }

    public double getProfitLoss() {
        return getMarketValue() - totalInvested;
    }

    public void sell(int quantity, double amount) {

        if (quantity > this.quantity) {
            throw new IllegalArgumentException(
                    "Not enough shares."
            );
        }

        double averageCost =
                totalInvested / this.quantity;

        this.quantity -= quantity;

        this.totalInvested -=
                averageCost * quantity;

        if (this.quantity == 0) {
            totalInvested = 0;
        }
    }
}