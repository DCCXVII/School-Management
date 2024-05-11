package master.iitn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import master.iitn.model.Gender;
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
                professeur.setImage(rs.getString("IMAGE"));
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
}
