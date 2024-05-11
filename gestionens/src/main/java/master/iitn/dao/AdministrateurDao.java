package master.iitn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import master.iitn.model.Etudiant;
import master.iitn.model.Roles;
import master.iitn.services.Utils;

public class AdministrateurDao {
    private final ConnectionFactory connectionFactory;
    private final Utils utils;

    public AdministrateurDao() {
        this.connectionFactory = new ConnectionFactory();
        this.utils = new Utils();
    }


    // public Etudiant getAdminById(int id) {
    //     String sql = "SELECT u.IMAGE, u.NOM, u.PRENOM, u.GENRE, u.DATE_NAISSANCE, u.CIN, u.PHONE, u.EMAIL, e.CNE, c.NOM_CLASS, c.NIVEAU, c.ANNEE_UNIVERSITAIRE"
    //             + "FROM USER u, ETUDIANT e, CLASS c" +
    //             "WHERE u.ID_USER = e.ID_USER AND e.ID_CLASS = c.ID_CLASS AND u.ID_USER = " + id;
    //     Etudiant etudiant = new Etudiant();
    //     Administrateur admin = new Administrateur();


    //     try (Connection conn = connectionFactory.getConnection();
    //             Statement stmt = conn.createStatement();
    //             ResultSet rs = stmt.executeQuery(sql)) {

    //         if (rs.next()) {
    //             etudiant.setImage(rs.getString("IMAGE"));
    //             etudiant.setNom(rs.getString("NOM"));
    //             etudiant.setPrenom(rs.getString("PRENOM"));
    //             etudiant.setGender(rs.getString("GENRE").equals("Homme") ? Gender.Homme : Gender.Femme);
    //             etudiant.setDate_naissance(rs.getDate("DATE_NAISSANCE"));
    //             etudiant.setCin(rs.getString("CIN"));
    //             etudiant.setPhone(rs.getString("PHONE"));
    //             etudiant.setEmail(rs.getString("EMAIL"));
    //             etudiant.setCne(rs.getString("CNE"));
    //             etudiant.setClasse(rs.getString("NOM_CLASS"));
    //             etudiant.setNiveau(rs.getString("NIVEAU"));
    //             etudiant.setAnneeUniversitaire(rs.getString("ANNEE_UNIVERSITAIRE"));
    //         } else {
    //             throw new SQLException("Error: Cannot fetch etudiant data");
    //         }

    //     } catch (SQLException e) {
    //         throw new RuntimeException(e);
    //     }

    //     return etudiant;
    // }

    public void addEtudiant(Etudiant etudiant) {
        String insertUserSql = "INSERT INTO USER (NOM, PRENOM, GENRE, DATE_NAISSANCE, CIN, PHONE, USERNAME, EMAIL, PASSWORD, ROLE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // String getUserIdSql = "SELECT LAST_INSERT_ID()";
        String insertEtudiantSql = "INSERT INTO ETUDIANT (ID_USER, ID_CLASS, CNE, LEVEL) VALUES (?, 1, ?, ?)";

        try (Connection conn = connectionFactory.getConnection();
                PreparedStatement userStmt = conn.prepareStatement(insertUserSql, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement etudiantStmt = conn.prepareStatement(insertEtudiantSql)) {
            conn.setAutoCommit(false);

            // Insert user data
            userStmt.setString(1, etudiant.getNom());
            userStmt.setString(2, etudiant.getPrenom());
            userStmt.setString(3, etudiant.getGender().toString());
            userStmt.setString(4, etudiant.getDate_naissance().toString());
            userStmt.setString(5, etudiant.getCin());
            userStmt.setString(6, etudiant.getPhone());
            userStmt.setString(7, "etudianttest");
            userStmt.setString(8, utils.generateEmail(etudiant.getNom(), etudiant.getPrenom()));
            userStmt.setString(9, utils.generateHash(etudiant.getPassword()));
            userStmt.setString(10, Roles.Etudiant.toString());
            userStmt.executeUpdate();

            // Get the generated user ID
            int userId;
            try (ResultSet rs = userStmt.getGeneratedKeys()) {
                if (rs.next()) {
                    userId = rs.getInt(1);
                } else {
                    throw new SQLException("Failed to get generated user ID");
                }
            }

            // Insert etudiant data
            etudiantStmt.setInt(1, userId);
            etudiantStmt.setString(2, etudiant.getCne());
            etudiantStmt.setString(3, etudiant.getLevel());
            etudiantStmt.executeUpdate();

            conn.commit();
            System.out.println("Insertion successful!");
        } catch (SQLException e) {
            logError("Error adding student: ", e);
            throw new RuntimeException("Error adding student", e);
        }
    }

    private void logError(String message, Exception e) {
        // Implement logging mechanism
        System.err.println(message + e.getMessage());
    }
}
