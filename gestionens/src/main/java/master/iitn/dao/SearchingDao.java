package master.iitn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.ObservableList;
import master.iitn.model.Etudiant;
import master.iitn.model.Gender;

public class SearchingDao {
    ConnectionFactory connection;

    public SearchingDao() {
        this.connection = new ConnectionFactory();
    }

    public ObservableList<Etudiant> SearchEtudiant(String searchIndex, String searchValue) {
        ObservableList<Etudiant> etudiants = javafx.collections.FXCollections.observableArrayList();
        String sql;
        switch (searchIndex) {
            case "CIN":
                sql = "SELECT e.ID_ETUDIANT, u.NOM, u.PRENOM, u.CIN, e.CNE, u.GENRE, u.EMAIL, u.PHONE, c.NOM_CLASS " +
                        "FROM USER u, ETUDIANT e, CLASS c " +
                        "WHERE u.USER_ID = e.USER_ID AND c.ID_CLASS = e.ID_CLASS AND u.CIN = '" + searchValue + "'";
                break;
            case "CNE":
                sql = "SELECT e.ID_ETUDIANT, u.NOM, u.PRENOM, u.CIN, e.CNE, u.GENRE, u.EMAIL, u.PHONE, c.NOM_CLASS " +
                        "FROM USER u, ETUDIANT e, CLASS c " +
                        "WHERE u.USER_ID = e.USER_ID AND c.ID_CLASS = e.ID_CLASS AND e.CNE = '" + searchValue + "'";
                break;
            case "Classe":
                sql = "SELECT e.ID_ETUDIANT, u.NOM, u.PRENOM, u.CIN, e.CNE, u.GENRE, u.EMAIL, u.PHONE, c.NOM_CLASS " +
                        "FROM USER u, ETUDIANT e, CLASS c " +
                        "WHERE u.USER_ID = e.USER_ID AND c.ID_CLASS = e.ID_CLASS AND c.NOM_CLASS = '" + searchValue
                        + "'";
                break;
            default:
                sql = "";
                break;
        }

        try {
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(rs.getInt("ID_ETUDIANT"));
                etudiant.setNom(rs.getString("NOM"));
                etudiant.setPrenom(rs.getString("PRENOM"));
                etudiant.setGender(rs.getString("GENRE").equals("Homme") ? Gender.Homme : Gender.Femme);
                etudiant.setCne(rs.getString("CNE"));
                etudiant.setCin(rs.getString("CIN"));
                etudiant.setPhone(rs.getString("PHONE"));
                etudiant.setEmail(rs.getString("EMAIL"));
                etudiant.setClasse(rs.getString("NOM_CLASS"));
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return etudiants;
    }

}