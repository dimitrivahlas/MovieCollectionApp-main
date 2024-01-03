package ui;

import model.Movie;
import model.MovieListCollection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

//Creates and defines the ViewMoviesAction which is instantiated when the View to Watch List button is clicked
public class ViewMoviesAction implements ActionListener {
    private MovieCollectionMainFrame movieListCollection;

    //REQUIRES: movieListCollection can not be null
    //EFFECTS: Initializes ViewMoviesAction with the MovieCollectionMainFrame movieListCollection
    public ViewMoviesAction(MovieCollectionMainFrame movieListCollection) {
        this.movieListCollection = movieListCollection;
    }

    //EFFECTS: Creates displays new frame with the list displayed
    //MODIFIES: Creates new GUI JFrame
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame listFrame = new JFrame("To Watch List");
        listFrame.setSize(300, 200);
        listFrame.setLocationRelativeTo(null);
        listFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        listFrame.add(panel);

        List<Movie> movies = movieListCollection.getToWatchlist();
        if (movies.isEmpty()) {
            panel.add(new JLabel("No movies in to watchlist"));
        } else {
            for (Movie movie : movies) {
                panel.add(new JLabel(movie.getTitle()));
            }
        }

        listFrame.setVisible(true);
    }
}
