package cinema.controller;

import cinema.exeption.AlreadySoldException;
import cinema.exeption.AuthException;
import cinema.exeption.BusinessLogicException;
import cinema.exeption.OutOfBoundsException;
import cinema.model.CinemaRoom;
import cinema.model.Seat;
import cinema.model.SoldSeat;
import cinema.model.response.StatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class CinemaController {

    @Autowired
    CinemaRoom cinemaRoom;

    @Value("${cinema.room.password}")
    String correctPassword;

    @GetMapping("/seats")
    public CinemaRoom seats(){
        return cinemaRoom;
    }

    @PostMapping(path = "/purchase")
    public SoldSeat purchase(@RequestBody Seat seat) {
            return cinemaRoom.sell(seat);
    }

    @PostMapping("/return")
    public Map<String, Seat> returnTicket(@RequestBody Map<String, String> parameters){
        Seat seat = cinemaRoom.returnTicket(parameters.get("token"));
        return Map.of("returned_ticket", seat);
    }

    @PostMapping("/stats")
    public StatsResponse stats(@RequestParam(required = false) String password){
        if(!"super_secret".equals(password)){
            throw new AuthException();
        }
        return new StatsResponse(
                cinemaRoom.getCurrentIncome(),
                cinemaRoom.getNumberOfAvailable_seats(),
                cinemaRoom.getNumberOfPurchasedTickets()
        );
    }

    @ExceptionHandler(BusinessLogicException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    Map<String, String> errorHandler(Exception e){
        return Map.of("error", e.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    Map<String, String> errorHandlerAuth(Exception e){
        return Map.of("error", e.getMessage());
    }
}
