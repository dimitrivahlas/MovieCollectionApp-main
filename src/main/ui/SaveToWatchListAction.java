package ui;

import model.MovieListCollection;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

//Creates and defines the SaveToWatchListAction which is instantiated when the Save To Watch List button is clicked
public class SaveToWatchListAction implements ActionListener {
    private MovieListCollection movieListCollection;
    private JsonWriter jsonWriter;
    private static final String JSONPATH = "./data/GUI.json";

    //REQUIRES: movieListCollection can't be null
    //EFFECTS: Initializes SaveToWatchListAction with the parameter and sets up the JSON writer
    public SaveToWatchListAction(MovieListCollection movieListCollection) {
        this.movieListCollection = movieListCollection;
        this.jsonWriter = new JsonWriter(JSONPATH);
    }

    //EFFECTS: Opens JSON writer and writes the movieListCollection to JSONPATH, if no file found displays error message
    //MODIFIES: JSONPATH file
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            jsonWriter.open();
            jsonWriter.write(movieListCollection);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "To Watch List saved to " + JSONPATH);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSONPATH);
        }
    }
}
