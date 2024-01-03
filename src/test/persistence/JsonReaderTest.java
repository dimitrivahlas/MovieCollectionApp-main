package persistence;

import model.FavouritesList;
import model.MovieListCollection;
import model.MoviesSeenList;
import model.ToWatchList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//Tests in this class are designed using the example provided
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MovieListCollection list = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralMovieListCollection() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralMovieListCollection.json");
        try {
            MovieListCollection movieListCollection = reader.read();

            ToWatchList toWatchList = movieListCollection.getToWatchList();
            MoviesSeenList moviesSeenList = movieListCollection.getMovieSeen();
            FavouritesList favouritesList = movieListCollection.getFavouritesList();

            assertEquals(1,movieListCollection.getFavouritesList().getFavouritesList().size());
            assertEquals(1,movieListCollection.getMovieSeen().getMovieSeen().size());
            assertEquals(1,movieListCollection.getToWatchList().getToWatchList().size());

            checkMovie("Up",toWatchList.getToWatchList().get(0));
            checkRateMovie("Spiderman",2,moviesSeenList.getMovieSeen().get(0));
            checkRateMovie("Godfather",5,favouritesList.getFavouritesList().get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
