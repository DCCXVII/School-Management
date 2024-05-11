package master.iitn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import master.iitn.model.Etudiant;

public class EtudiantDao {

    private final ConnectionFactory connectionFactory;

    public EtudiantDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    public Etudiant getEtudiantById(int id) {
        String sql = "SELECT e.CNE, c.NOM_CLASS, c.NIVEAU, c.ANNEE_UNIVERSITAIRE "
                + "FROM USER u, ETUDIANT e, CLASS c " +
                "WHERE u.ID_USER = e.ID_USER AND e.ID_CLASS = c.ID_CLASS AND u.ID_USER = " + id;
        Etudiant etudiant = new Etudiant();

        try (Connection conn = connectionFactory.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                etudiant.setCne(rs.getString("CNE"));
                etudiant.setClasse(rs.getString("NOM_CLASS"));
                etudiant.setNiveau(rs.getString("NIVEAU"));
                etudiant.setAnneeUniversitaire(rs.getString("ANNEE_UNIVERSITAIRE"));
            } else {
                throw new SQLException("Error: Cannot fetch etudiant data");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return etudiant;
    }
}
