package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ToWatchListTest {
    private ToWatchList testList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    @BeforeEach
    void runBefore(){
        testList = new ToWatchList();
        System.setOut(new PrintStream(outContent));


    }

    @Test
    void testOneAddMovieToWatchList(){
        Movie movie = new Movie("Avengers:End Game");
        testList.addMovieToWatchList(movie);
        assertEquals("Avengers:End Game",movie.getTitle());
        assertEquals(1,testList.getToWatchList().size());
    }

    @Test
    void testAddMultipleMoviesToWatchList(){
        Movie movie1 = new Movie("Indiana Jones");
        Movie movie2 = new Movie("The Empire Strikes Back");
        testList.addMovieToWatchList(movie1);
        testList.addMovieToWatchList(movie2);
        assertEquals(2,testList.getToWatchList().size());
    }

    @Test
    void printEmptyToWatchList(){
        assertEquals(0,testList.getToWatchList().size());
        testList.printToWatchList();
        String printedOutput = outContent.toString().trim();
                assertEquals("No movies in to watchlist", printedOutput);

    }

    @Test
    void printWatchList(){
    Movie movie1 = new Movie("Godzilla");
    Movie movie2 = new Movie("King Kong");
    testList.addMovieToWatchList(movie1);
    testList.addMovieToWatchList(movie2);

        System.out.println("To Watch List:");
        testList.printToWatchList();
        String printedOutput = outContent.toString();
        assertTrue(printedOutput.contains("Godzilla"));
        assertTrue(printedOutput.contains("King Kong"));

    }

    @Test
    void testToJsonString(){
        assertEquals("{\"ToWatchList\":[]}",
                testList.toJson().toString());
    }

    @Test
    void testToJson(){
        Movie movie1 = new Movie("Transformers");
        Movie movie2 = new Movie("Oppenheimer");
        testList.addMovieToWatchList(movie1);
        testList.addMovieToWatchList(movie2);
        testList.toJson();

        JSONObject expected = new JSONObject();
        JSONArray arrayList = new JSONArray();
        JSONObject movie1Json = new JSONObject();
        JSONObject movie2Json = new JSONObject();

        movie1Json.put("title","Transformers");
        movie2Json.put("title","Oppenheimer");

        arrayList.put(movie1Json);
        arrayList.put(movie2Json);
        expected.put("ToWatchList",arrayList);


        assertEquals(expected.toString(),testList.toJson().toString());

    }



}
