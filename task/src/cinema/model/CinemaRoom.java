package cinema.model;

import cinema.exeption.AlreadySoldException;
import cinema.exeption.OutOfBoundsException;
import cinema.exeption.UnauthorizedException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.lang.reflect.Array;
import java.util.*;

public class CinemaRoom {
    private int totalRows;
    private int totalColumns;
    private List<Seat> availableSeats;
    private Map<String, Seat> soldSeats = new HashMap<>();

    public CinemaRoom(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        availableSeats = new ArrayList<>();
        for(int row = 1; row <= totalRows; row++){
            for(int col = 1; col <= totalColumns; col++){
                availableSeats.add(new Seat(row,col));
            }
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    public Collection<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }

    public SoldSeat sell(Seat seat) {
        if(seat.getRow() < 1 || totalRows < seat.getRow() ||
                seat.getColumn() < 1 || totalColumns < seat.getColumn()
        ){
            throw new OutOfBoundsException();
        }
        synchronized (this) {
            System.out.println(seat.getColumn());
            if(!availableSeats.contains(seat)){
                throw new AlreadySoldException();
            }
            var soldSeat = new SoldSeat(UUID.randomUUID().toString(), seat);
            soldSeats.put(soldSeat.getToken(), seat);
            availableSeats.remove(seat);
            return soldSeat;
        }
    }

    public Seat returnTicket(String token) {
        synchronized (this) {
            if (!soldSeats.containsKey(token)) {
                throw new UnauthorizedException();
            }
            var seat = soldSeats.remove(token);
            availableSeats.add(seat);
            return seat;
        }
    }
    @JsonIgnore
    public int getCurrentIncome() {
        return soldSeats.values().stream().
                mapToInt(Seat::getPrice)
                .sum();
    }
    @JsonIgnore
    public int getNumberOfAvailable_seats() {
        return availableSeats.size();
    }
    @JsonIgnore
    public int getNumberOfPurchasedTickets() {
        return soldSeats.size();
    }
}
