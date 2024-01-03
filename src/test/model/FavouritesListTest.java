package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FavouritesListTest {
    private FavouritesList testList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void runBefore(){
        testList = new FavouritesList();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void addFavourite() {
        RateMovie movie = new RateMovie("The Italian Job", 5);
        testList.addMovieFavourites(movie);
        assertEquals(1, testList.getFavouritesList().size());
    }

    @Test
    void addManyMoviesToFavourites(){
        RateMovie movie1 = new RateMovie("Fight club", 5);
        RateMovie movie2 = new RateMovie("StarWars Episode 3", 5);
        RateMovie movie3 = new RateMovie("The Godfather", 5);
        testList.addMovieFavourites(movie1);
        testList.addMovieFavourites(movie2);
        testList.addMovieFavourites(movie3);
        assertEquals(3,testList.getFavouritesList().size());
    }

    @Test
    void printFavourites() {
        RateMovie favouriteMovie = new RateMovie("Fight Club", 5);

        testList.addMovieFavourites(favouriteMovie);

        System.out.println("Favorites:");
        testList.printFavouritesList();

        String printedOutput = outContent.toString();
        assertTrue(printedOutput.contains("Fight Club"));
        assertTrue(printedOutput.contains(" Rating: 5"));
    }

    @Test
    void printEmptyFavouritesList() {
        assertEquals(0, testList.getFavouritesList().size());
        testList.printFavouritesList();
        String printedOutput = outContent.toString().trim();
        assertEquals("List is empty", printedOutput);

    }

    @Test
    void testToJsonString(){
        assertEquals("{\"FavouritesList\":[]}",
                testList.toJson().toString());
    }

    @Test
    void testToJson(){
        RateMovie movie1 = new RateMovie("Interstellar", 5);
        RateMovie movie2 = new RateMovie("Inception", 5);
        testList.addMovieFavourites(movie1);
        testList.addMovieFavourites(movie2);
        testList.toJson();

        JSONObject expected = new JSONObject();
        JSONArray arrayList = new JSONArray();
        JSONObject movie1Json = new JSONObject();
        JSONObject movie2Json = new JSONObject();

        movie1Json.put("title","Interstellar");
        movie1Json.put("rating",5);
        movie2Json.put("title", "Inception");
        movie2Json.put("rating",5);

        arrayList.put(movie1Json);
        arrayList.put(movie2Json);
        expected.put("FavouritesList",arrayList);


        assertEquals(expected.toString(),testList.toJson().toString());

    }


}

