package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

//MovieListCollection of movieLists
public class MovieListCollection implements Writable {
    private MoviesSeenList movieSeen;
    private ToWatchList toWatchList;
    private FavouritesList favouritesList;

    //MODIFIES: this
    //EFFECTS: Initializes a new MovieListCollection by creating new
    // instances of MoviesSeenList, ToWatchList, and FavouritesList.
    public MovieListCollection() {
        movieSeen = new MoviesSeenList();
        toWatchList = new ToWatchList();
        favouritesList = new FavouritesList();
    }

    public MoviesSeenList getMovieSeen() {
        return movieSeen;
    }

    public ToWatchList getToWatchList() {
        return toWatchList;
    }

    public FavouritesList getFavouritesList() {
        return favouritesList;
    }


    //EFFECTS: Converts the lists to JSON and returns it as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        JSONArray toWatchArray = toWatchList.toJson().getJSONArray("ToWatchList");
        JSONArray seenArray = movieSeen.toJson().getJSONArray("MovieSeenList");
        JSONArray favouritesArray = favouritesList.toJson().getJSONArray("FavouritesList");

        return createMovieListCollectionJson(toWatchArray, seenArray, favouritesArray);
    }

    //MODIFIES: this
    //EFFECTS: puts arrays, in index's of the MovieListCollection Json object and then returns it as a JSONObject
    private JSONObject createMovieListCollectionJson(JSONArray toWatchArray, JSONArray seenArray,
                                                     JSONArray favouritesArray) {
        JSONObject movieListCollectionJson = new JSONObject();
        movieListCollectionJson.put("ToWatchList", toWatchArray);
        movieListCollectionJson.put("MovieSeenList", seenArray);
        movieListCollectionJson.put("FavouritesList", favouritesArray);

        JSONObject finalJson = new JSONObject();
        finalJson.put("MovieListCollection", movieListCollectionJson);

        return finalJson;
    }

}

