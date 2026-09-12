import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class StockTradingPlatform {

    private static final Scanner scanner = new Scanner(System.in);

    private static final Map<String, Stock> market =
            new LinkedHashMap<>();

    private static final Portfolio portfolio =
            new Portfolio(100000.00);

    public static void main(String[] args) {

        initializeMarket();

        boolean running = true;

        System.out.println("======================================");
        System.out.println("      CODEALPHA STOCK TRADING PLATFORM");
        System.out.println("======================================");

        while (running) {

            printMenu();

            int choice = readInt(
                    "Choose an option: "
            );

            try {

                switch (choice) {

                    case 1:
                        displayMarket();
                        break;

                    case 2:
                        buyStock();
                        break;

                    case 3:
                        sellStock();
                        break;

                    case 4:
                        portfolio.displayPortfolio();
                        break;

                    case 5:
                        portfolio.recordSnapshot();
                        break;

                    case 6:
                        portfolio.displayPerformance();
                        break;

                    case 7:
                        portfolio.saveTransactions(
                                "transactions.txt"
                        );

                        System.out.println(
                                "Transactions saved successfully."
                        );
                        break;

                    case 8:
                        running = false;
                        break;

                    default:
                        System.out.println(
                                "Invalid option."
                        );
                }

            } catch (IllegalArgumentException e) {

                System.out.println(
                        "Operation failed: "
                                + e.getMessage()
                );
            }
        }

        portfolio.saveTransactions(
                "transactions.txt"
        );

        System.out.println(
                "Thank you for using the Stock Trading Platform!"
        );

        scanner.close();
    }

    private static void initializeMarket() {

        market.put(
                "TCS",
                new Stock(
                        "TCS",
                        "Tata Consultancy Services",
                        3900
                )
        );

        market.put(
                "INFY",
                new Stock(
                        "INFY",
                        "Infosys",
                        1800
                )
        );

        market.put(
                "RELIANCE",
                new Stock(
                        "RELIANCE",
                        "Reliance Industries",
                        2900
                )
        );

        market.put(
                "HDFCBANK",
                new Stock(
                        "HDFCBANK",
                        "HDFC Bank",
                        1700
                )
        );

        market.put(
                "WIPRO",
                new Stock(
                        "WIPRO",
                        "Wipro",
                        550
                )
        );
    }

    private static void printMenu() {

        System.out.println("\n1. Display Market Data");
        System.out.println("2. Buy Stock");
        System.out.println("3. Sell Stock");
        System.out.println("4. View Portfolio");
        System.out.println("5. Record Performance Snapshot");
        System.out.println("6. View Performance");
        System.out.println("7. Save Transactions");
        System.out.println("8. Exit");
    }

    private static void displayMarket() {

        System.out.println(
                "\n========== MARKET DATA =========="
        );

        System.out.printf(
                "%-12s %-32s %-12s%n",
                "Symbol",
                "Company",
                "Price"
        );

        for (Stock stock : market.values()) {

            System.out.printf(
                    "%-12s %-32s ₹%-10.2f%n",
                    stock.getSymbol(),
                    stock.getCompanyName(),
                    stock.getPrice()
            );
        }
    }

    private static void buyStock() {

        Stock stock = findStock();

        int quantity = readInt(
                "Quantity to buy: "
        );

        portfolio.buy(
                stock,
                quantity
        );

        System.out.println(
                "Buy order completed successfully."
        );
    }

    private static void sellStock() {

        Stock stock = findStock();

        int quantity = readInt(
                "Quantity to sell: "
        );

        portfolio.sell(
                stock,
                quantity
        );

        System.out.println(
                "Sell order completed successfully."
        );
    }

    private static Stock findStock() {

        String symbol = readNonEmpty(
                "Enter stock symbol: "
        ).toUpperCase();

        Stock stock = market.get(symbol);

        if (stock == null) {

            throw new IllegalArgumentException(
                    "Stock symbol not found."
            );
        }

        return stock;
    }

    private static int readInt(String prompt) {

        while (true) {

            try {

                System.out.print(prompt);

                return Integer.parseInt(
                        scanner.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid integer."
                );
            }
        }
    }

    private static String readNonEmpty(
            String prompt) {

        while (true) {

            System.out.print(prompt);

            String value =
                    scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println(
                    "Value cannot be empty."
            );
        }
    }
}