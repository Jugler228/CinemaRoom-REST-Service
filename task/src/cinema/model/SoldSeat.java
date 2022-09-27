package cinema.model;

public class SoldSeat {
    private String token;
    private Seat ticket;

    public SoldSeat(String token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    public String getToken() {
        return token;
    }

    public Seat getTicket() {
        return ticket;
    }
}
