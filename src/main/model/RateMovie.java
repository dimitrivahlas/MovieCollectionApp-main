package model;



//Represents a rated Movie with a title and rating
public class RateMovie extends Movie {
    int rating; // rating of the movie

    //REQUIRES: Title can not be null, rating can not be negative
    //MODIFIES: this
    //EFFECTS: Initializes a new RateMovie with a title and rating
    public RateMovie(String title, int rating) {
        super(title);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }
}
