package ui;

import model.Movie;
import model.MovieListCollection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Creates and defines the Add Movie Action which is instantiated when the AddMovieAction button is clicked
public class AddMovieAction implements ActionListener {
    private MovieListCollection movieListCollection;

    //REQUIRES: movieListCollection is not null
    //EFFECTS: Initializes AddMovieAction with the movieListCollection
    //MODIFIES: movieListCollection
    public AddMovieAction(MovieListCollection movieListCollection) {
        this.movieListCollection = movieListCollection;
    }

    //EFFECTS: adds movie to list that the user types in
    //MODIFIES: toWatchList
    @Override
    public void actionPerformed(ActionEvent e) {
        String title = JOptionPane.showInputDialog(null, "Enter the title of the movie to add:");
        if (title != null && !title.trim().isEmpty()) {
            addMovieToWatchlist(title);
            JOptionPane.showMessageDialog(null, "Movie '" + title + "' has been added to the watchlist.");
        } else {
            JOptionPane.showMessageDialog(null, "No movie title entered.");
        }
    }

    //REQUIRES: string that is not null
    //MODIFIES:toWatchlist
    //EFFECTS: adds a Movie to toWatchList
    private void addMovieToWatchlist(String title) {
        Movie movie = new Movie(title);
        movieListCollection.getToWatchList().addMovieToWatchList(movie);
    }
}
