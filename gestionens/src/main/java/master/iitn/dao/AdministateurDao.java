package master.iitn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.scene.control.TextField;
import master.iitn.model.User;

public class AdministateurDao {
    ConnectDB connection;

    public AdministateurDao() {
        this.connection = new ConnectDB();
    }

    // adding user to the database
    public void AddUser(User user) {

        
        String sql = "INSERT INTO user (USER_ID, NOM, PRENOM, EMAIL, PASSWORD, CIN, PHONE, ROLE) VALUES (?,?,?,?,?,?,?)";

        Connection conn = connection.getConnection();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getNom());
            stmt.setString(3, user.getPrenom());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword());
            stmt.setString(6, user.getCin());
            stmt.setString(7,user.getPhone());
            stmt.setString(8, user.getRole().toString());
            // stmt.setString(9, user.getEmail());

            stmt.execute();
            stmt.close();


        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

}
