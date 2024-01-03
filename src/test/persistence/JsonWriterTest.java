package persistence;

import model.*;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Tests in this class are designed using the example provided
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile(){
        try {
            MovieListCollection list = new MovieListCollection();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        }catch (IOException e){
            // pass
        }
    }

    @Test
    void testWriterGeneralMovieListCollection() {
        try {
            MovieListCollection movieListCollection = new MovieListCollection();
            RateMovie rm1 = new RateMovie("Spiderman", 2);
            RateMovie rm2 = new RateMovie("Godfather", 5);
            Movie m = new Movie("Up");

            movieListCollection.getToWatchList().addMovieToWatchList(m);
            movieListCollection.getMovieSeen().addMovieSeen(rm1);
            movieListCollection.getFavouritesList().addMovieFavourites(rm2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMovieListCollection.json");
            writer.open();
            writer.write(movieListCollection);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMovieListCollection.json");
            movieListCollection = reader.read();
            ToWatchList toWatchList = movieListCollection.getToWatchList();
            MoviesSeenList moviesSeenList = movieListCollection.getMovieSeen();
            FavouritesList favouritesList = movieListCollection.getFavouritesList();

           assertEquals(1,movieListCollection.getFavouritesList().getFavouritesList().size());
           assertEquals(1,movieListCollection.getMovieSeen().getMovieSeen().size());
           assertEquals(1,movieListCollection.getToWatchList().getToWatchList().size());

           checkMovie("Up",toWatchList.getToWatchList().get(0));
           checkRateMovie("Spiderman",2,moviesSeenList.getMovieSeen().get(0));
           checkRateMovie("Godfather",5,favouritesList.getFavouritesList().get(0));
        }catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
