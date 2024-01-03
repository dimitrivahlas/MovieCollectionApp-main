package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a watchlist for movies that the user would like to watch
public class ToWatchList implements Writable {
    private List<Movie> toWatch; //list of movies to watch

    //MODIFIES:this
    //EFFECTS: Initializes toWatch with an empty arrayList for movies
    public ToWatchList() {
        toWatch = new ArrayList<>();
    }

    //REQUIRES: movie can not be null
    //MODIFIES: toWatch
    //EFFECTS: adds the specified movie to the watchlist
    public void addMovieToWatchList(Movie movie) {
        EventLog.getInstance().logEvent(new Event(movie.getTitle() + " added to watchList"));
        toWatch.add(movie);
    }

    //REQUIRES: movie can not be null
    //MODIFIES: toWatch
    //EFFECTS: removes the specified movie from the watchlist
    public void removeMovieToWatchList(Movie movie) {
        if (toWatch.contains(movie)) {
            EventLog.getInstance().logEvent(new Event(movie.getTitle() + " was removed from to Watchlist"));
            toWatch.remove(movie);
        }
    }

    //EFFECTS: prints the movies in the watchlist
    public void printToWatchList() {
        if (toWatch.isEmpty()) {
            System.out.println("No movies in to watchlist");

        } else {
            System.out.println("Your movies in your to Watchlist are");
            for (Movie movie : toWatch) {
                System.out.println("Title: " + movie.getTitle());
            }
        }
    }

    public List<Movie> getToWatchList() {
        return toWatch;
    }

    //EFFECTS: Converts the Movies in toWatchList into JSON and returns it as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray toWatchArray = new JSONArray();
        for (Movie movie : toWatch) {
            JSONObject movieJson = new JSONObject();
            movieJson.put("title", movie.getTitle());
            toWatchArray.put(movieJson);
        }
        json.put("ToWatchList", toWatchArray);
        return json;
    }

}
