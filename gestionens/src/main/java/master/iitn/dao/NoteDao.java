package master.iitn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import master.iitn.model.Note;
import master.iitn.utils.Utils;

public class NoteDao {
    ConnectionFactory connectionFactory;
    Utils utils = new Utils();

    public NoteDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    public ObservableList<Note> getNotesById(int id) throws SQLException {
        String sql = "SELECT etu.ID_ETUDIANT, eta.SEMESTRE, eta.ANNEE_UNIVERSITAIRE, c.NOM_CLASS, n.NOTE_NORMALE, n.MENTION_NORMALE, n.NOTE_RATTRAPAGE, n.MENTION_RATTRAPAGE, m.NOM_MATIERE FROM USER u, ETUDIANT etu, ETAT eta, CLASS c, NOTES n, MATIERES m WHERE u.ID_USER = etu.ID_USER AND etu.ID_ETUDIANT=eta.ID_ETUDIANT AND etu.ID_ETUDIANT=n.ID_ETUDIANT AND n.ID_MATIERE=m.ID_MATIERE AND eta.ID_CLASS=c.ID_CLASS AND u.ID_USER = "
                + id;

        ObservableList<Note> notes = FXCollections.observableArrayList();

        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery()) {
            System.out.println(sql);
            while (rs.next()) {
                Note note = new Note();
                note.setAnnee(rs.getString("ANNEE_UNIVERSITAIRE"));
                note.setMatiere(rs.getString("NOM_MATIERE"));
                note.setNoteS1(rs.getDouble("NOTE_NORMALE"));
                note.setResultatS1(rs.getString("MENTION_NORMALE"));
                note.setNoteS2(rs.getDouble("NOTE_RATTRAPAGE"));
                note.setResultatS2(rs.getString("MENTION_RATTRAPAGE"));
                notes.add(note);

            }

            statement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(notes.toString());
        return notes;
    }
}

// NoteDao : DONE - 13/04/2024
