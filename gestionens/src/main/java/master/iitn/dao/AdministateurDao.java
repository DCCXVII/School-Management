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

        
        String sql = "INSERT INTO user (NOM, PRENOM, CIN, PHONE, USERNAME, EMAIL, PASSWORD,  ROLE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        
        try{
            // USER_ID	IMAGE	NOM	PRENOM	CIN	PHONE	USERNAME	EMAIL	PASSWORD	ROLE
            Connection conn = connection.getConnection();


            System.out.println(user.toString());

            PreparedStatement stmt = conn.prepareStatement(sql);
            // stmt.setInt(1, user.getId());
            stmt.setString(1, user.getNom());
            stmt.setString(2, user.getPrenom());
            stmt.setString(3, user.getCin());
            stmt.setString(4,user.getPhone());
            stmt.setString(5, "usertest");
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getPassword());
            stmt.setString(8, user.getRole().toString());
            // stmt.setString(9, user.getEmail());
            stmt.execute();
            stmt.close();

            System.out.println("Insertion successfully !!!");

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

}
