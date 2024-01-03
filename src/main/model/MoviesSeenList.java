package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a movie list with movies the user has already seen and wants to rate
public class MoviesSeenList implements Writable {
    private ArrayList<RateMovie> movieSeen;//list of movies the user has seen

    //MODIFIES:this
    //EFFECTS: Initializes toWatch with an empty arrayList for movies
    public MoviesSeenList() {
        movieSeen = new ArrayList<>();
    }

    //REQUIRES: movie can not be null
    //MODIFIES: movieSeen
    //EFFECTS: adds the specified movie to movieSeen list
    public void addMovieSeen(RateMovie movie) {
        movieSeen.add(movie);
    }

    //EFFECTS: prints the list of seen movies
    public void printMovieSeenList() {
        if (movieSeen.isEmpty()) {
            System.out.println("List is empty");
        } else {
            System.out.println("Movies Seen List:");
            for (RateMovie movie : movieSeen) {
                System.out.println("Title: " + movie.getTitle() + " Rating: " + movie.getRating());
            }
        }
    }

    public List<RateMovie> getMovieSeen() {
        return movieSeen;
    }

    //EFFECTS: Converts the Movies in MoviesSeenList into JSON and returns it as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray movieSeenArray = new JSONArray();
        for (RateMovie movie : movieSeen) {
            JSONObject movieJson = new JSONObject();
            movieJson.put("title", movie.getTitle());
            movieJson.put("rating", movie.getRating());
            movieSeenArray.put(movieJson);
        }
        json.put("MovieSeenList", movieSeenArray);
        return json;
    }
}
