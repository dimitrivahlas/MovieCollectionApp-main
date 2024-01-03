package persistence;

import model.Movie;
import model.RateMovie;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Based on JsonSerializationDemo example
public class JsonTest {

    protected void checkRateMovie(String title, int rating, RateMovie rateMovie){
        assertEquals(title,rateMovie.getTitle());
        assertEquals(rating, rateMovie.getRating());
    }

    protected void checkMovie(String title, Movie movie){
        assertEquals(title,movie.getTitle());
    }

}
