package master.iitn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import master.iitn.model.Classe;
import master.iitn.model.Gender;
import master.iitn.model.Matiere;
import master.iitn.model.NoteInfo;
import master.iitn.model.Professeur;

public class ProfesseurDao {

    private final ConnectionFactory connectionFactory;

    public ProfesseurDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    public Professeur getProfesseurById(int id) {
        String sql = "SELECT u.IMAGE, u.NOM, u.PRENOM, u.GENRE, u.DATE_NAISSANCE, u.CIN, u.PHONE, u.EMAIL"
                + "FROM USER u, PROFESSEUR p" +
                "WHERE u.ID_USER = p.ID_USER AND u.ID_USER =" + id;

        Professeur professeur = new Professeur();
        try (Connection conn = connectionFactory.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) {
                // professeur.setImage(rs.getString("IMAGE"));
                professeur.setNom(rs.getString("NOM"));
                professeur.setPrenom(rs.getString("PRENOM"));
                professeur.setGender(rs.getString("GENRE").equals("Homme") ? Gender.Homme : Gender.Femme);
                professeur.setDate_naissance(rs.getDate("DATE_NAISSANCE"));
                professeur.setCin(rs.getString("CIN"));
                professeur.setPhone(rs.getString("PHONE"));
                professeur.setEmail(rs.getString("EMAIL"));

            } else {
                throw new SQLException("Error: Cannot fetch professeur data");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return professeur;
    }

    public ObservableList<String> getClasses(int id) {
        ComboBox<String> classes = new ComboBox<>();
        String sql = "SELECT c.ID_CLASS, c.NOM_CLASS FROM USER u, PROFESSEUR p, CLASS c, ASSOCIER a WHERE u.ID_USER=p.ID_USER AND p.ID_PROFESSEUR=a.ID_PROFESSEUR AND a.ID_CLASS=c.ID_CLASS AND u.ID_USER = "
                + id;
        try (Connection conn = connectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Classe classe = new Classe();
                classe.setID_CLASS(rs.getInt("ID_CLASS"));
                classe.setNOM_CLASSE(rs.getString("NOM_CLASS"));
                classes.getItems().add(classe.toString());
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return classes.getItems();
    }

    public ObservableList<String> getMatiereByIdProf(int id) {
        String sql = "SELECT m.ID_MATIERE, m.NOM_MATIERE FROM USER u, PROFESSEUR p, MATIERES m, ENSEIGNER e WHERE u.ID_USER=p.ID_USER AND p.ID_PROFESSEUR=e.ID_PROFESSEUR AND e.ID_MATIERE=m.ID_MATIERE AND u.ID_USER = "
                + id;
        ComboBox<String> matieres = new ComboBox<>();
        try (Connection conn = connectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Matiere matiere = new Matiere();
                matiere.setId(rs.getInt("ID_MATIERE"));
                matiere.setNomMatiere(rs.getString("NOM_MATIERE"));
                System.out.println(matiere.toString());
                matieres.getItems().add(matiere.toString());

            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matieres.getItems();
    }

    public ObservableList<NoteInfo> getNotesByClasses(int idClasse, int idMatiere) {
        ObservableList<NoteInfo> notes = FXCollections.observableArrayList();
        String sql = "SELECT e.ID_ETUDIANT, u.NOM, u.PRENOM, c.NOM_CLASS, ma.NOM_MATIERE, e.CNE, n.NOTE_NORMALE, n.NOTE_RATTRAPAGE FROM USER u, ETUDIANT e, CLASS c, NOTES n, MATIERES ma, ETAT eta WHERE u.ID_USER=e.ID_USER AND eta.ID_CLASS=c.ID_CLASS AND e.ID_ETUDIANT=n.ID_ETUDIANT AND n.ID_MATIERE = ma.ID_MATIERE AND eta.ID_ETUDIANT=e.ID_ETUDIANT AND c.ID_CLASS = "
                + idClasse + " AND ma.ID_MATIERE = " + idMatiere;

        try (Connection conn = connectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String ID_ETUDIANT = rs.getString("ID_ETUDIANT");
                String nomkaml = rs.getString("NOM") + " " + rs.getString("PRENOM");
                String NOM_CLASS = rs.getString("NOM_CLASS");
                String CNE = rs.getString("CNE");
                String NOTE_NORMALE = rs.getString("NOTE_NORMALE");
                String NOTE_RATTRAPAGE = rs.getString("NOTE_RATTRAPAGE");
                NoteInfo noteInfo = new NoteInfo(ID_ETUDIANT, nomkaml, NOM_CLASS, CNE, NOTE_NORMALE, NOTE_RATTRAPAGE);
                notes.add(noteInfo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(notes.toString());
        return notes;
    }

    public void addNoteByEtudiant(){
        
    }
}
