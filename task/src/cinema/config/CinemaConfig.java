package cinema.config;

import cinema.model.CinemaRoom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CinemaConfig {
    @Bean
    CinemaRoom cinemaRoom(@Value("${cinema.room.rows}")int rows,
                          @Value("${cinema.room.cols}")int cols){
        return new CinemaRoom(rows,cols);
    }

}
