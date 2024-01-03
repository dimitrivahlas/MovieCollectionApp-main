package ui;

import model.Movie;
import model.MovieListCollection;
import persistence.JsonReader;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

//Creates and defines the LoadToWatchListAction which is instantiated when the Load To Watch List button is clicked
public class LoadToWatchListAction implements ActionListener {
    private MovieCollectionMainFrame frame;
    private JsonReader jsonReader;
    private static final String JSONPATH = "./data/GUI.json";

    //REQUIRES: Frame to not be null
    //EFFECTS: Initializes LoadToWatchListAction with the frame and sets up JsonReader
    public LoadToWatchListAction(MovieCollectionMainFrame frame) {
        this.frame = frame;
        this.jsonReader = new JsonReader(JSONPATH);
    }

    //EFFECTS: Reads the JSON file and updates the to watchList with it, if null catch IOException
    //MODIFIES: toWatchList
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            MovieListCollection loadedCollection = jsonReader.read();
            frame.updateMovieListCollection(loadedCollection);
            JOptionPane.showMessageDialog(null, "To Watch List loaded from " + JSONPATH);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSONPATH);
        }
    }

}

