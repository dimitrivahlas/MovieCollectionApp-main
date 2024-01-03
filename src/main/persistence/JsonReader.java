package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

//Represents a JsonReader which will read the contents of a loaded file
//Based on JsonSerializationDemo
public class JsonReader {
    private String source;

    //EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads from file and return is
    //throws IOException if an error occurs reading data from file
    public MovieListCollection read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMovieListCollection(jsonObject);
    }

    // REQUIRES: The source parameter must be a valid file path
    // EFFECTS: Reads the contents of a file located at the given source and returns it as a single string
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // REQUIRES: The jsonObject must be a valid JSON representation of a MovieListCollection
    // MODIFIES: Creates and populates a new MovieListCollection object
    // EFFECTS: Parses the provided JSON object and constructs a MovieListCollection with its data
    private MovieListCollection parseMovieListCollection(JSONObject jsonObject) {
        MovieListCollection movieListCollection = new MovieListCollection();
        JSONObject movieListCollectionObj = jsonObject.getJSONObject("MovieListCollection");
        parseToWatchList(movieListCollection, movieListCollectionObj);
        parseMovieSeenList(movieListCollection, movieListCollectionObj);
        parseFavouritesList(movieListCollection, movieListCollectionObj);
        return movieListCollection;
    }


    // REQUIRES: movieListCollection must be a valid MovieListCollection object
    // MODIFIES: movieListCollection
    // EFFECTS: Parses the ToWatchList JSON array from the MovieListCollection object and adds movies to the ToWatchList
    private void parseToWatchList(MovieListCollection movieListCollection, JSONObject movieListCollectionObj) {
        JSONArray toWatchListMovies = movieListCollectionObj.getJSONArray("ToWatchList");
        for (Object json : toWatchListMovies) {
            JSONObject movie = (JSONObject) json;
            addMovieToToWatchList(movieListCollection.getToWatchList(), movie);
        }
    }

    // REQUIRES: movieListCollection must be a valid MovieListCollection object
    // MODIFIES: movieListCollection
    // EFFECTS: Parses the MovieSeenList JSON array from the MovieListCollection object and adds movies to MovieSeenList
    private void parseMovieSeenList(MovieListCollection movieListCollection, JSONObject movieListCollectionObj) {
        JSONArray movieSeenList = movieListCollectionObj.getJSONArray("MovieSeenList");
        for (Object json : movieSeenList) {
            JSONObject movie = (JSONObject) json;
            addMovieToMovieSeenList(movieListCollection.getMovieSeen(), movie);
        }
    }

    // REQUIRES: movieListCollection must be a valid MovieListCollection object
    // MODIFIES: movieListCollection
    // EFFECTS: Parses FavouritesList JSON array from the MovieListCollection object and adds movies to FavouritesList
    private void parseFavouritesList(MovieListCollection movieListCollection, JSONObject movieListCollectionObj) {
        JSONArray favouritesList = movieListCollectionObj.getJSONArray("FavouritesList");
        for (Object json : favouritesList) {
            JSONObject movie = (JSONObject) json;
            addMovieToFavouritesList(movieListCollection.getFavouritesList(), movie);
        }
    }


    // REQUIRES: toWatchList must be a valid ToWatchList object,
    // and the jsonObject must contain a valid JSON representation of a movie to be added
    // MODIFIES: toWatchList
    // EFFECTS: Parses a JSON object representing a movie, creates a Movie object, and adds it to the toWatchList
    private void addMovieToToWatchList(ToWatchList toWatchList, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        Movie movie = new Movie(title);
        toWatchList.addMovieToWatchList(movie);
    }

    // REQUIRES: The moviesSeenList parameter must be a valid MoviesSeenList object,
    // and the jsonObject must contain a valid JSON representation of a movie to be added
    // MODIFIES: moviesSeenList
    // EFFECTS: Parses a JSON object representing a movie, creates a RateMovie object, and adds it to the moviesSeenList
    private void addMovieToMovieSeenList(MoviesSeenList moviesSeenList, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        int rating = jsonObject.getInt("rating");
        RateMovie movie = new RateMovie(title, rating);
        moviesSeenList.addMovieSeen(movie);
    }

    // REQUIRES: The favouritesList parameter must be a valid FavouritesList object,
    // and the jsonObject must contain a valid JSON representation of a movie to be added
    // MODIFIES: favouritesList
    // EFFECTS: Parses a JSON object representing a movie, creates a RateMovie object, and adds it to the favouritesList
    private void addMovieToFavouritesList(FavouritesList favouritesList, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        int rating = jsonObject.getInt("rating");
        RateMovie movie = new RateMovie(title, rating);
        favouritesList.addMovieFavourites(movie);
    }
}
