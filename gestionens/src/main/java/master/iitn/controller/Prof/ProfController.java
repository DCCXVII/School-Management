package master.iitn.controller.Prof;

import master.iitn.dao.ProfesseurDao;
import master.iitn.services.Utils;

public class ProfController {
    
    ProfesseurDao professeurDao;
    Utils utils;

    public ProfController(){
        this.professeurDao = new ProfesseurDao();
        this.utils = new Utils();
    }

    public void ProfGestionEtudiant(){
        
    }
}
