package cinema.model.response;

public class StatsResponse {
    private final int currentIncome;
    private final int numberOfAvailable_seats;
    private final int numberOfPurchasedTickets;

    public StatsResponse(int currentIncome, int numberOfAvailable_seats, int numberOfPurchasedTickets) {
        this.currentIncome = currentIncome;
        this.numberOfAvailable_seats = numberOfAvailable_seats;
        this.numberOfPurchasedTickets = numberOfPurchasedTickets;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public int getNumberOfAvailable_seats() {
        return numberOfAvailable_seats;
    }

    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }
}
