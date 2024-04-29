package master.iitn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Utils.Utils;
import master.iitn.model.Etudiant;
// import master.iitn.model.etudiant;

public class AdministateurDao {
    ConnectionFactory connection;

    public AdministateurDao() {
        this.connection = new ConnectionFactory();
    }

    // adding etudiant to the database
    // errors in the code 
    public void Addetudiant(Etudiant etudiant) {
        String sql = "INSERT INTO USER (NOM, PRENOM, GENRE, DATE_NAISSANCE, CIN, PHONE, USERNAME, EMAIL, PASSWORD, ROLE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String getUserIdSql = "SELECT LAST_INSERT_ID() INTO @user_id";
        String insertIntoEtudiantSql = "INSERT INTO ETUDIANT (USER_ID,ID_CLASS,CIN,LEVEL) VALUES (@user_id,1,1,1)";


        Utils utils = new Utils();

        try {
            Connection conn = connection.getConnection();
            System.out.println(etudiant.toString());

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setString(3, etudiant.getGender().toString());
            stmt.setString(4, etudiant.getDate_naissance().toString());
            stmt.setString(5, etudiant.getCin());
            stmt.setString(6, etudiant.getPhone());
            stmt.setString(7, "etudianttest");
            stmt.setString(8, utils.generateEmail(etudiant.getNom(), etudiant.getPrenom()));
            stmt.setString(9, utils.generateHash(etudiant.getPassword()));
            stmt.setString(10, etudiant.getRole().toString());

            stmt.execute();
            stmt.close();

            // Execute the second statement to get the user ID
            stmt = conn.prepareStatement(getUserIdSql);
            stmt.execute();
            stmt.close();

            // Execute the third statement to insert the user ID into the ETUDIANT table
            stmt = conn.prepareStatement(insertIntoEtudiantSql);
            stmt.execute();
            stmt.close();

            System.out.println("Insertion successfully !!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
