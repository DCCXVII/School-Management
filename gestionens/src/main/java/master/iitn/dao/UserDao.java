package master.iitn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import master.iitn.model.Gender;
import master.iitn.model.User;
import master.iitn.utils.*;

public class UserDao {
    ConnectionFactory connection;
    Utils utils = new Utils();

    public UserDao() {
        this.connection = new ConnectionFactory();
    }

    public User LoginUser(String email, String password) {
        String sql = "SELECT * FROM USER WHERE EMAIL='" + email + "' AND PASSWORD='" + password + "'";

        // ---------- khdmha ila kan l hashage dyal password ---- //

        // String hashedpaString = utils.generateHash(password);
        // String sql = "SELECT * FROM USER WHERE EMAIL='" + email + "' AND PASSWORD='"
        // + hashedpaString + "'";
        User user = new User();
        System.out.println(sql);
        try (Connection conn = connection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                user.setId(rs.getInt("ID_USER"));
                user.setNom(rs.getString("NOM"));
                user.setPrenom(rs.getString("PRENOM"));
                user.setGender(rs.getString("GENRE").equals("Homme") ? Gender.Homme : Gender.Femme);
                user.setDate_naissance(rs.getDate("DATE_NAISSANCE"));
                user.setCin(rs.getString("CIN"));
                user.setPhone(rs.getString("PHONE"));
                user.setEmail(rs.getString("EMAIL"));
                user.setRole(utils.setRole(rs.getString("ROLE")));
            } else {
                throw new SQLException("Invalid email or password");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}