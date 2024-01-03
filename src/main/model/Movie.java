package model;



//Represents a movie with a title.
public class Movie {

    String title; //Movie title

    //REQUIRES: title parameter can not be null
    //MODIFIES: this
    //EFFECTS: Initializes new Movie object with the title
    public Movie(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
