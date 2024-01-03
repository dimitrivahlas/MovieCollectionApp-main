package ui;

import model.Movie;
import model.MovieListCollection;
import model.ToWatchList;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

//Creates and defines the Remove Movie Action which is instantiated when the RemoveMovieAction button is clicked
public class RemoveMovieAction implements ActionListener {
    private MovieListCollection movieListCollection;

    //REQUIRES: MovieListCollection object can't be null
    //EFFECTS:  Initializes RemoveMovieAction with the movieListCollection
    //MODIFIES: movieListCollection
    public RemoveMovieAction(MovieListCollection movieListCollection) {
        this.movieListCollection = movieListCollection;
    }

    //EFFECTS: removes movie to list that the user types in
    //MODIFIES: toWatchList
    @Override
    public void actionPerformed(ActionEvent e) {
        String title = JOptionPane.showInputDialog(null, "Enter the title of the movie to remove:");
        if (title != null && !title.trim().isEmpty()) {
            if (removeMovieByTitle(title)) {
                JOptionPane.showMessageDialog(null, "Movie removed successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Movie not found.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No movie title entered.");
        }
    }

    //REQUIRES: String title can't be null
    //EFFECTS: Searches toWatchList by title and removes if found, returns true if movie is removed, false otherwise
    //MODIFIES: toWatchList
    private boolean removeMovieByTitle(String title) {
        ToWatchList toWatchList = movieListCollection.getToWatchList();
        List<Movie> movies = toWatchList.getToWatchList();
        Optional<Movie> movieToRemove = movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .findFirst();

        if (movieToRemove.isPresent()) {
            toWatchList.removeMovieToWatchList(movieToRemove.get()); // Using the method from ToWatchList
            return true;
        } else {
            return false;
        }
    }
}

