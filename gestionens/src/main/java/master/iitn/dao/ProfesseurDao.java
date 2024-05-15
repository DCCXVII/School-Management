package master.iitn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import master.iitn.model.Classe;
import master.iitn.model.Gender;
import master.iitn.model.Matiere;
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

    public ObservableList<Classe> getClasses(int id) {
        ObservableList<Classe> classes = FXCollections.observableArrayList();
        String sql = "SELECT c.ID_CLASS, c.NOM_CLASS FROM USER u, PROFESSEUR p, CLASS c, ASSOCIER a WHERE u.ID_USER=p.ID_USER AND p.ID_PROFESSEUR=a.ID_PROFESSEUR AND a.ID_CLASS=c.ID_CLASS AND u.ID_USER = "
                + id;
        try (Connection conn = connectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Classe classe = new Classe();
                classe.setID_CLASS(rs.getInt("ID_CLASS"));
                classe.setNOM_CLASSE(rs.getString("NOM_CLASS"));
                classes.add(classe);
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return classes;
    }

    public ObservableList<Matiere> getMatiereByIdProf(int id) {
        String sql = "SELECT m.ID_MATIERE, m.NOM_MATIERE FROM USER u, PROFESSEUR p, MATIERE m, ENSEIGNER e WHERE u.ID_USER=p.ID_USER AND p.ID_PROFESSEUR=e.ID_PROFESSEUR AND e.ID_MATIERE=m.ID_MATIERE AND u.ID_USER = "
                + id;
        ObservableList<Matiere> matieres = FXCollections.observableArrayList();
        try (Connection conn = connectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Matiere matiere = new Matiere();
                matiere.setId(rs.getInt("ID_MATIERE"));
                matiere.setNomMatiere(rs.getString("NOM_MATIERE"));
                matieres.add(matiere);
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return matieres;
    }
}
