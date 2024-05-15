package master.iitn.controller.Prof;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import master.iitn.controller.SessionController;
import master.iitn.dao.ProfesseurDao;
import master.iitn.model.NoteInfo;
import master.iitn.utils.Utils;

public class ProfController implements Initializable {

    ProfesseurDao professeurDao;
    Utils utils;

    private int idClasse;
    private int idMatiere;

    @FXML
    ComboBox comboClasse;
    @FXML
    ComboBox comboSession;
    @FXML
    ComboBox comboMatiere;

    @FXML
    TextField Cne;

    @FXML
    TextField Note;

    @FXML
    TableView TableauNotes;

    @FXML
    TableColumn idEtudiant;

    @FXML
    TableColumn NomPrenom;
    @FXML
    TableColumn Classe;
    @FXML
    TableColumn CNE;
    @FXML
    TableColumn sessionNormale;

    @FXML
    TableColumn NoteN;

    @FXML
    TableColumn sessionRattrapage;

    @FXML
    TableColumn NoteR;

    public ProfController() {
        this.professeurDao = new ProfesseurDao();
        this.utils = new Utils();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboClasse.getItems().addAll(professeurDao.getClasses(SessionController.getInstance().getUser().getId()));
        comboSession.getItems().addAll("Session normale", "Session de rattrapage");
        comboMatiere.getItems()
                .addAll(professeurDao.getMatiereByIdProf(SessionController.getInstance().getUser().getId()));

    }

    @FXML
    public void setNoteByClasse(ActionEvent event) {
        this.idClasse = Integer.parseInt(comboClasse.getValue().toString().split(" ")[0]);
        this.idMatiere = Integer.parseInt(comboMatiere.getValue().toString().split(" ")[0]);
        System.out.println(idClasse + " " + idMatiere);
        ObservableList<NoteInfo> notes = professeurDao.getNotesByClasses(idClasse, idMatiere);
        System.out.println(notes.toString());

        // Set the cell value factories for each TableColumn
        idEtudiant.setCellValueFactory(new PropertyValueFactory<NoteInfo, String>("ID_ETUDIANT"));
        NomPrenom.setCellValueFactory(new PropertyValueFactory<NoteInfo, String>("nomkaml"));
        Classe.setCellValueFactory(new PropertyValueFactory<NoteInfo, String>("NOM_CLASS"));
        CNE.setCellValueFactory(new PropertyValueFactory<NoteInfo, String>("CNE"));
        NoteN.setCellValueFactory(new PropertyValueFactory<NoteInfo, String>("NOTE_NORMALE"));
        NoteR.setCellValueFactory(new PropertyValueFactory<NoteInfo, String>("NOTE_RATTRAPAGE"));

        TableauNotes.setItems(notes);
    }

}