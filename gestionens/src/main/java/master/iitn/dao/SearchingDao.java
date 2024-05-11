package master.iitn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import master.iitn.model.Etudiant;
import master.iitn.model.Gender;

public class SearchingDao {
    private final ConnectionFactory connectionFactory;

    public SearchingDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    public ObservableList<Etudiant> searchEtudiant(String searchIndex, String searchValue) {
        ObservableList<Etudiant> etudiants = FXCollections.observableArrayList();
        String sql = getSearchQuery(searchIndex);

        try (Connection conn = connectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, searchValue);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Etudiant etudiant = createEtudiantFromResultSet(rs);
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            logError("Error searching for students: ", e);
            throw new RuntimeException("Error searching for students", e);
        }

        return etudiants;
    }

    private String getSearchQuery(String searchIndex) {
        String sql = "SELECT e.ID_ETUDIANT, u.NOM, u.PRENOM, u.CIN, e.CNE, u.GENRE, u.EMAIL, u.PHONE, c.NOM_CLASS "
                + "FROM USER u, ETUDIANT e, CLASS c "
                + "WHERE u.ID_USER = e.ID_USER AND c.ID_CLASS = e.ID_CLASS AND %s = ?";

        switch (searchIndex) {
            case "CIN":
                return String.format(sql, "u.CIN");
            case "CNE":
                return String.format(sql, "e.CNE");
            case "Classe":
                return String.format(sql, "c.NOM_CLASS");
            default:
                return "";
        }
    }

    private Etudiant createEtudiantFromResultSet(ResultSet rs) throws SQLException {
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
        return etudiant;
    }

    private void logError(String message, Exception e) {
        System.err.println(message + e.getMessage());
    }
}