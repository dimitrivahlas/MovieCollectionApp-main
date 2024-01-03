package ui;

import model.EventLog;
import model.Movie;
import model.MovieListCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

//Creates the MainFrame for the application
public class MovieCollectionMainFrame extends JFrame implements WindowListener {
    private JButton addButton;
    private JButton viewButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton removeButton;
    private JButton imageButton;
    private MovieListCollection movieListCollection;


    //EFFECTS: Initializes movie list collection and sets up ui
    //MODIFIES: movieListCollection and ui components
    public MovieCollectionMainFrame() {
        movieListCollection = new MovieListCollection();
        initializeUI();
        createButtons();
        addListeners();
        addWindowListener(this);
    }


    //EFFECTS: Sets the title, size, default close operation and alignment of the frame
    private void initializeUI() {
        setTitle("My Movies App");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    //EFFECTS: Creates and adds buttons for each action and adds panel to mainframe
    //MODIFIES: Layout and button components
    private void createButtons() {
        addButton = new JButton("Add Movie");
        viewButton = new JButton("View To Watch List");
        saveButton = new JButton("Save To Watch List");
        loadButton = new JButton("Load To Watch List");
        removeButton = new JButton("Remove Movie");
        imageButton = new JButton("Image");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(imageButton);

        add(buttonPanel, BorderLayout.CENTER);
    }


    //EFFECTS: Adds action listeners to each button for Add Movie, Remove Movie, View to Watch List, Save to Watch List
    //Load To Watch List and Image
    //MODIFIES: Behavior of each button
    private void addListeners() {
        addButton.addActionListener(new AddMovieAction(movieListCollection));
        removeButton.addActionListener(new RemoveMovieAction(movieListCollection));
        viewButton.addActionListener(new ViewMoviesAction(this));
        saveButton.addActionListener(new SaveToWatchListAction(movieListCollection));
        loadButton.addActionListener(new LoadToWatchListAction(this));
        imageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame imageFrame = new JFrame("Image Display");
                imageFrame.setSize(500, 500);
                imageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                ImageIcon imageIcon = new ImageIcon("/Users/dimitrivahlas/Desktop/LOGO.png");
                JLabel imageLabel = new JLabel(imageIcon);
                imageFrame.add(imageLabel);

                imageFrame.pack();
                imageFrame.setLocationRelativeTo(null);
                imageFrame.setVisible(true);
            }
        });
    }


    //EFFECTS: runs the application, makes frame visible
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                MovieCollectionMainFrame mainFrame = new MovieCollectionMainFrame();
                mainFrame.setVisible(true);
            }
        });
    }

    //EFFECTS: returns toWatchList list
    public List<Movie> getToWatchlist() {

        return movieListCollection.getToWatchList().getToWatchList();
    }

    //EFFECTS: assigns movieListCollection to the parameter
    //MODIFIES: movieListCollection
    public void updateMovieListCollection(MovieListCollection loadedCollection) {
        this.movieListCollection = loadedCollection;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("MyMoviesApp closing: Events logged");
        for (model.Event event : model.EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

}
