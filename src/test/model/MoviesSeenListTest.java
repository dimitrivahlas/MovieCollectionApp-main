package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MoviesSeenListTest {
    private MoviesSeenList testList;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeEach
    void runBefore(){
        testList = new MoviesSeenList();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void addOneMovieToMovieSeen(){
        RateMovie movie = new RateMovie("300",5);
        testList.addMovieSeen(movie);
       assertEquals(1,testList.getMovieSeen().size());
    }

    @Test
    void addManyMoviesToMovieSeen(){
        RateMovie movie1 = new RateMovie("Up",4);
        RateMovie movie2 = new RateMovie("Good Boys",1);
        RateMovie movie3 = new RateMovie("Good Fellas",5);
        testList.addMovieSeen(movie1);
        testList.addMovieSeen(movie2);
        testList.addMovieSeen(movie3);
        assertEquals(3,testList.getMovieSeen().size());
    }

    @Test
    void printMoviesSeen(){
        RateMovie movie1 = new RateMovie("Transformers 2",2);
        RateMovie movie2 = new RateMovie("A Bees Life",3);

        testList.addMovieSeen(movie1);
        testList.addMovieSeen(movie2);

        System.out.println("Rated Movies:");
        testList.printMovieSeenList();
        String printedOutput = outContent.toString();
        assertTrue(printedOutput.contains("Transformers 2"));
        assertTrue(printedOutput.contains("A Bees Life"));
        assertTrue(printedOutput.contains("Rating: 2"));
        assertTrue(printedOutput.contains("Rating: 3"));
    }

    @Test
    void printEmptyMovieSeenList(){
        assertEquals(0,testList.getMovieSeen().size());
        testList.printMovieSeenList();
        String printedOutput = outContent.toString().trim();
        assertEquals("List is empty", printedOutput);
    }

    @Test
    void testToJsonString(){
        assertEquals("{\"MovieSeenList\":[]}",
                testList.toJson().toString());

    }

    @Test
    void testToJson(){
        RateMovie movie1 = new RateMovie("Batman Begins", 5);
        RateMovie movie2 = new RateMovie("Amazing Spiderman 2", 3);
        testList.addMovieSeen(movie1);
        testList.addMovieSeen(movie2);
        testList.toJson();

        JSONObject expected = new JSONObject();
        JSONArray arrayList = new JSONArray();
        JSONObject movie1Json = new JSONObject();
        JSONObject movie2Json = new JSONObject();

        movie1Json.put("title","Batman Begins");
        movie1Json.put("rating",5);
        movie2Json.put("title", "Amazing Spiderman 2");
        movie2Json.put("rating",3);

        arrayList.put(movie1Json);
        arrayList.put(movie2Json);
        expected.put("MovieSeenList",arrayList);


        assertEquals(expected.toString(),testList.toJson().toString());

    }
}
