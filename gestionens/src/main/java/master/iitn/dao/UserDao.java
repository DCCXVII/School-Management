package master.iitn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import master.iitn.model.Roles;
import master.iitn.model.User;
import Utils.Utils;

public class UserDao {
    ConnectionFactory connection;
    Utils utils = new Utils();

    public UserDao() {
        this.connection = new ConnectionFactory();
    }

    public User LoginUser(String email,String password) {
        String sql = "SELECT * FROM USER WHERE EMAIL='" + email + "' AND PASSWORD='" + password + "'";
        User user = new User();
        try (Connection conn = connection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                user.setId(rs.getInt("USER_ID"));
                user.setNom(rs.getString("NOM"));
                user.setEmail(rs.getString("EMAIL"));
                user.setPhone(rs.getString("PHONE"));
                user.setCin(rs.getString("CIN"));
                user.setRole(rs.getString("ROLE").equals("Admin") ? Roles.Administrateur : Roles.Etudiant);
            } else {
                throw new SQLException("Invalid email or password");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}