package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Represents a list of the users favourite movies
public class FavouritesList implements Writable {
    private ArrayList<RateMovie> favouritesList; //list of favourite movies

    //MODIFIES:this
    //EFFECTS: Initializes favouritesList with an empty arrayList
    public FavouritesList() {
        favouritesList = new ArrayList<>();
    }

    //REQUIRES: movie parameter can not be null
    //MODIFIES: favouritesList
    //EFFECTS: Adds the movie to list of favourite movies
    public void addMovieFavourites(RateMovie movie) {
        favouritesList.add(movie);
    }

    //EFFECTS: prints the list of favourite movies
    public void printFavouritesList() {
        if (favouritesList.isEmpty()) {
            System.out.println("List is empty");
        } else {
            System.out.println("Your Favourites are:");
            for (RateMovie movie : favouritesList) {
                System.out.println("Title: " + movie.getTitle() + " Rating: " + movie.getRating());
            }
        }
    }

    public List<RateMovie> getFavouritesList() {
        return favouritesList;
    }

    //EFFECTS: Converts the Movies in MoviesSeenList into JSON and returns it as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        JSONArray favouritesListArray = new JSONArray();
        for (RateMovie movie : favouritesList) {
            JSONObject movieJson = new JSONObject();
            movieJson.put("title", movie.getTitle());
            movieJson.put("rating", movie.getRating());
            favouritesListArray.put(movieJson);
        }
        json.put("FavouritesList", favouritesListArray);
        return json;
    }
}
