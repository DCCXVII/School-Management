package master.iitn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Utils.Utils;
import master.iitn.model.User;

public class AdministateurDao {
    ConnectionFactory connection;

    public AdministateurDao() {
        this.connection = new ConnectionFactory();
    }

    // adding user to the database
    public void AddUser(User user) {
        String sql = "INSERT INTO USER (NOM, PRENOM, CIN, PHONE, USERNAME, EMAIL, PASSWORD,  ROLE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String getUserIdSql = "SELECT LAST_INSERT_ID() INTO @user_id";
        String insertIntoEtudiantSql = "INSERT INTO ETUDIANT (USER_ID,ID_CLASS,CIN,LEVEL) VALUES (@user_id,1,1,1)";

        Utils utils = new Utils();
        String HashedPassword = utils.generateHash(user.getPassword());
        String email = utils.generateEmail(user.getNom(), user.getPrenom(), 3);

        try {
            Connection conn = connection.getConnection();
            System.out.println(user.toString());

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getPrenom());
            stmt.setString(3, user.getCin());
            stmt.setString(4, user.getPhone());
            stmt.setString(5, "usertest");
            stmt.setString(6, email);
            stmt.setString(7, HashedPassword);
            stmt.setString(8, user.getRole().toString());
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
