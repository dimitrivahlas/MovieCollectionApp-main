package ui;

import model.Movie;
import model.MovieListCollection;
import model.RateMovie;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Represents a Movie Collection Application
/*
The following methods have been taken from the example JsonSerializationDemo:saveMovieCollection,loadMovieListCollection
 */
public class MyMoviesApp {
    private final Scanner in = new Scanner(System.in);
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private MovieListCollection movieListCollection;
    private static final String JSON_STORE = "./data/myFile.json";


    //MODIFIES: this
    //EFFECTS: Initializes movie lists
    public MyMoviesApp() {
        movieListCollection = new MovieListCollection();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMyMovies();
    }

    //MODIFIES: this
    //EFFECTS: Manages the application, allowing users to add, rate, and view their movie collections
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void runMyMovies() {
        boolean keepGoing = true;
        int userChoice;

        while (keepGoing) {
            menu();
            userChoice = in.nextInt();
            in.nextLine();
            if (userChoice == 1) {
                addMovieToWatchlist();
            } else if (userChoice == 2) {
                addMovieToWatchedList();
            } else if (userChoice == 3) {
                printToWatchlist();
            } else if (userChoice == 4) {
                printWatchedList();
            } else if (userChoice == 5) {
                printFavourites();
            } else if (userChoice == 6) {
                saveMovieCollection();
            } else if (userChoice == 7) {
                loadMovieListCollection();
            } else {
                keepGoing = false;
                System.out.println("Thanks for using!");
            }

        }

    }


    //EFFECTS: prints menu
    private void menu() {
        System.out.println("1 to add a new Movie to your To Watchlist");
        System.out.println("2 to rate a Movie you've seen and add to your Watched list");
        System.out.println("3 to View your To Watchlist");
        System.out.println("4 to view your Watched list");
        System.out.println("5 to view your Favourites");
        System.out.println("6 to save your MovieCollection");
        System.out.println("7 to load MovieCollection");
    }

    //MODIFIES:toWatchList
    //EFFECTS: adds specified movie to toWatchList
    private void addMovieToWatchlist() {
        Movie movie;
        String title;
        System.out.println("Enter movie title:");
        title = in.nextLine();
        movie = new Movie(title);
        movieListCollection.getToWatchList().addMovieToWatchList(movie);
    }

    //MODIFIES: movieSeen, favouritesList
    //EFFECTS: adds movie to movieSeen, and adds to favouritesList based on user input
    private void addMovieToWatchedList() {
        RateMovie movie;
        String title;
        int rating;
        int fav;
        System.out.println("Enter movie title:");
        title = in.nextLine();
        System.out.println("Enter movie rating: 1-5");
        rating = in.nextInt();
        in.nextLine();
        movie = new RateMovie(title, rating);
        movieListCollection.getMovieSeen().addMovieSeen(movie);
        System.out.println("Is this a favorite? 1 if yes");
        fav = in.nextInt();
        in.nextLine();
        if (fav == 1) {
            movieListCollection.getFavouritesList().addMovieFavourites(movie);
        }
    }

    //EFFECTS: prints toWatchList list
    private void printToWatchlist() {
        movieListCollection.getToWatchList().printToWatchList();
    }

    //EFFECTS: prints movieSeen list
    private void printWatchedList() {
        movieListCollection.getMovieSeen().printMovieSeenList();
    }

    //EFFECTS: prints favouritesList
    private void printFavourites() {
        movieListCollection.getFavouritesList().printFavouritesList();
    }


    //EFFECTS: saves movie collection to file
    private void saveMovieCollection() {
        try {
            jsonWriter.open();
            jsonWriter.write(movieListCollection);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file:");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads movieListCollection from file
    private void loadMovieListCollection() {
        try {
            movieListCollection = jsonReader.read();
            String jsonString = jsonReader.readFile(JSON_STORE);
            JSONObject json = new JSONObject(jsonString);
            String neatJson = json.toString(4);
            System.out.println("Loaded " + neatJson + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println(" Unable to read from file: " + JSON_STORE);
        }
    }


}


