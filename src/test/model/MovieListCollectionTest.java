package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import static org.junit.jupiter.api.Assertions.*;

public class MovieListCollectionTest {
    private MovieListCollection testCollection;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void runBefore(){
        testCollection = new MovieListCollection();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testEmptyLists(){
        assertEquals(0,testCollection.getToWatchList().getToWatchList().size());
        assertEquals(0,testCollection.getMovieSeen().getMovieSeen().size());
        assertEquals(0,testCollection.getFavouritesList().getFavouritesList().size());
        assertTrue(testCollection.getToWatchList().getToWatchList().isEmpty());
        assertTrue(testCollection.getMovieSeen().getMovieSeen().isEmpty());
        assertTrue(testCollection.getFavouritesList().getFavouritesList().isEmpty());
    }

    @Test
    void testToJson() {
        assertEquals("{\"MovieListCollection\":{\"MovieSeenList\":[],\"ToWatchList\":[],\"FavouritesList\":[]}}",
                testCollection.toJson().toString());
    }
}
