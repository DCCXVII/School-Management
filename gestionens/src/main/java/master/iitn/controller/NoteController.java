package master.iitn.controller;

import java.sql.SQLException;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import master.iitn.dao.NoteDao;

import master.iitn.model.Note;

public class NoteController {
    private NoteDao noteDao;

    public NoteController() {
        this.noteDao = new NoteDao();
    }

    @FXML
    private TableView<Note> notesTable;

    @FXML
    private TableColumn<Note, String> Annee;

    @FXML
    private TableColumn<Note, String> Matiere;

    @FXML
    private TableColumn<Note, String> Session1;

    @FXML
    private TableColumn<Note, Double> NoteS1;

    @FXML
    private TableColumn<Note, String> ResultatS1;

    @FXML
    private TableColumn<Note, String> Session2;

    @FXML
    private TableColumn<Note, Double> NoteS2;

    @FXML
    private TableColumn<Note, String> ResultatS2;

    public void initialize() throws SQLException {
        // Create static data for the table
        ObservableList<Note> data = noteDao.getNotesById(
                SessionController.getInstance().getUser().getId());

        System.out.println(data.toString());
        // Set the data to the table
        notesTable.setItems(data);

        // Bind the columns to the data properties
        Annee.setCellValueFactory(new PropertyValueFactory<>("annee"));
        Matiere.setCellValueFactory(new PropertyValueFactory<>("matiere"));
        Session1.setCellValueFactory(new PropertyValueFactory<>("session1"));
        NoteS1.setCellValueFactory(new PropertyValueFactory<>("noteS1"));
        ResultatS1.setCellValueFactory(new PropertyValueFactory<>("resultatS1"));
        Session2.setCellValueFactory(new PropertyValueFactory<>("session2"));
        NoteS2.setCellValueFactory(new PropertyValueFactory<>("noteS2"));
        ResultatS2.setCellValueFactory(new PropertyValueFactory<>("resultatS2"));
    }


    // @FXML
    // public void goTo
}