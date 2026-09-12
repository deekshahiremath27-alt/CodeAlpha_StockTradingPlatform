import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private String symbol;
    private int quantity;
    private double price;
    private LocalDateTime time;

    public Transaction(String type, String symbol, int quantity, double price) {
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.time = LocalDateTime.now();
    }

    public String toFileString() {
        return String.format(
                "%s | %s | %d | %.2f | %s",
                type,
                symbol,
                quantity,
                price,
                time.format(
                        DateTimeFormatter.ofPattern(
                                "yyyy-MM-dd HH:mm:ss"
                        )
                )
        );
    }

    @Override
    public String toString() {
        return toFileString();
    }
}