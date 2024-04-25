package master.iitn;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import master.iitn.model.Matiere;
import master.iitn.dao.MatiereDao;

public class MatiereController {

    @FXML
    private TextField TextField_AddMatiere;

    MatiereDao matiereDao = new MatiereDao();
    
    @FXML
    private void addNewMatiere(ActionEvent event) throws IOException{
        try{
            String nom_matiere = TextField_AddMatiere.getText();

            Matiere mat = new Matiere(nom_matiere);

            System.out.println(mat.getNomMatiere());
            
            matiereDao.addMatiere(mat);
        }
        catch (Exception e){
            e.printStackTrace();

        }

    }
}
