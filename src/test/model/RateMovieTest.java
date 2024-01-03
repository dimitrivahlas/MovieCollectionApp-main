package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RateMovieTest {

    private RateMovie rateMovieTest;

    @BeforeEach
    void runBefore(){
        rateMovieTest = new RateMovie("Avengers",4);
    }

    @Test
    void testConstructor(){
        assertEquals("Avengers",rateMovieTest.getTitle());
        assertEquals(4,rateMovieTest.getRating());
    }




}
