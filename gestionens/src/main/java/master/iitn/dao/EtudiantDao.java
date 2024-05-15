package master.iitn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import master.iitn.model.Etudiant;

public class EtudiantDao {

    private final ConnectionFactory connectionFactory;

    public EtudiantDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    public Etudiant getEtudiantById(int id) {
        String sql = "SELECT e.ID_ETUDIANT, e.CNE, c.NOM_CLASS, eta.SEMESTRE, eta.ANNEE_UNIVERSITAIRE FROM USER u, ETUDIANT e, CLASS c, ETAT eta WHERE u.ID_USER = e.ID_USER AND e.ID_ETUDIANT = eta.ID_ETUDIANT AND eta.ID_CLASS=c.ID_CLASS AND u.ID_USER = "
                + id;
        Etudiant etudiant = new Etudiant();

        try (Connection conn = connectionFactory.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                etudiant.setID_ETUDIANT(rs.getInt("ID_ETUDIANT"));
                etudiant.setCne(rs.getString("CNE"));
                etudiant.setClasse(rs.getString("NOM_CLASS"));
                etudiant.setNiveau(rs.getString("SEMESTRE"));
                etudiant.setAnneeUniversitaire(rs.getString("ANNEE_UNIVERSITAIRE"));
            } else {
                // Handle cases where no results are found
                // For example, return a default or null value
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return etudiant;
    }

}
