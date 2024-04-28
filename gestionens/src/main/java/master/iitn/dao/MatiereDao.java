package master.iitn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import master.iitn.model.Matiere;

public class MatiereDao {
    ConnectionFactory connection;

    public MatiereDao(){
        this.connection = new ConnectionFactory();
    }

    public void addMatiere(Matiere mat){
        String sql = "INSERT INTO MATIERES(NOM_MATIERE) VALUES ('"+ mat.getNomMatiere() +"')";
        
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

    public ObservableList<Matiere> getAllMatieres(){
    ObservableList<Matiere> matieres = FXCollections.observableArrayList();
    String sql = "SELECT * FROM MATIERES";
    try {
        Connection conn = connection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            Matiere mat = new Matiere();
            mat.setId(rs.getInt("ID_MATIERE"));
            mat.setNomMatiere(rs.getString("NOM_MATIERE"));
            matieres.add(mat);
        }

        stmt.close();
    }catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return matieres;
}

    
}
