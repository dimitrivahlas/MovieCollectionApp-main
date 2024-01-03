package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTest {

    private Movie movieTest;

    @BeforeEach
    void runBefore(){
        movieTest = new Movie("Avengers");
    }

    @Test
    void testConstructor(){
        assertEquals("Avengers", movieTest.getTitle());
    }
}
