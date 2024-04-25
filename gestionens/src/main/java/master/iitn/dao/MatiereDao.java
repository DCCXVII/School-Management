package master.iitn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import master.iitn.model.Matiere;

public class MatiereDao {
    ConnectionFactory connection;

    public MatiereDao(){
        this.connection = new ConnectionFactory();
    }

    public void addMatiere(Matiere mat){
        String sql = "INSERT INTO Matieres (NOM_MATIERE) VALUES ('"+ mat.getNomMatiere() +"')";
        
        System.out.println(sql);
        try {
            Connection conn = connection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.execute();
            stmt.close();

            System.out.println("Matiere insertion successfully!!");

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
