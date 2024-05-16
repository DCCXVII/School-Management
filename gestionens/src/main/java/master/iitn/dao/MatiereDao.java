package master.iitn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import master.iitn.model.Matiere;

public class MatiereDao {
    private final ConnectionFactory connectionFactory;

    public MatiereDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    public void addMatiere(Matiere mat, int idModule, int idClass, int idProfesseur) {
        String sql = "INSERT INTO MATIERES(NOM_MATIERE) VALUES (?)";
        String contenirSql = "INSERT INTO CONTENIR (ID_MATIERE, ID_MODULE) VALUES (?, ?)";
        String enseignerSql = "INSERT INTO ENSEIGNER (ID_PROFESSEUR, ID_MATIERE) VALUES (?, ?)";
        String associerSql = "INSERT INTO ASSOCIER (ID_PROFESSEUR, ID_CLASS) VALUES (?, ?)";
        String moduleClassSql = "INSERT INTO MODULE_CLASS (ID_MODULE, ID_CLASS) VALUES (?, ?)";

        try {
            Connection conn = connectionFactory.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement contenirStmt = conn.prepareStatement(contenirSql);
            PreparedStatement enseignerStmt = conn.prepareStatement(enseignerSql);
            PreparedStatement associerStmt = conn.prepareStatement(associerSql);
            PreparedStatement moduleClassStmt = conn.prepareStatement(moduleClassSql);

            stmt.setString(1, mat.getNomMatiere());
            stmt.executeUpdate();

            int idMatiere;
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    idMatiere = rs.getInt(1);
                } else {
                    throw new SQLException("Failed to get generated Matiere ID");
                }
            }

            contenirStmt.setInt(1, idMatiere);
            contenirStmt.setInt(2, idModule);
            contenirStmt.executeUpdate();

            enseignerStmt.setInt(1, idProfesseur);
            enseignerStmt.setInt(2, idMatiere);
            enseignerStmt.executeUpdate();

            // Check if the module-class relationship already exists to avoid duplicates
            moduleClassStmt.setInt(1, idModule);
            moduleClassStmt.setInt(2, idClass);
            try {
                moduleClassStmt.executeUpdate();
            } catch (SQLException ex) {
            }

            // Check if the professeur-class relationship already exists to avoid duplicates
            associerStmt.setInt(1, idProfesseur);
            associerStmt.setInt(2, idClass);
            try {
                associerStmt.executeUpdate();
            } catch (SQLException ex) {
            }

            conn.commit();

            System.out.println("Matiere insertion successfully!!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Matiere> getAllMatieres() {
        ObservableList<Matiere> matieres = FXCollections.observableArrayList();
        String sql = "SELECT DISTINCT cl.NOM_CLASS, mo.NOM_MODULE, ma.NOM_MATIERE " +
                "FROM MATIERES ma " +
                "JOIN CONTENIR co ON ma.ID_MATIERE = co.ID_MATIERE " +
                "JOIN MODULES mo ON co.ID_MODULE = mo.ID_MODULE " +
                "JOIN ENSEIGNER en ON ma.ID_MATIERE = en.ID_MATIERE " +
                "JOIN PROFESSEUR p ON en.ID_PROFESSEUR = p.ID_PROFESSEUR " +
                "JOIN MODULE_CLASS mc ON mo.ID_MODULE = mc.ID_MODULE " +
                "JOIN CLASS cl ON mc.ID_CLASS = cl.ID_CLASS " +
                "JOIN ASSOCIER a ON p.ID_PROFESSEUR = a.ID_PROFESSEUR AND cl.ID_CLASS = a.ID_CLASS " +
                "WHERE a.ID_CLASS = cl.ID_CLASS;";
        try {
            Connection conn = connectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Matiere mat = new Matiere();
                mat.setNomClass(rs.getString("NOM_CLASS"));
                mat.setNomModule(rs.getString("NOM_MODULE"));
                mat.setNomMatiere(rs.getString("NOM_MATIERE"));
                matieres.add(mat);
            }

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return matieres;
    }

    public ObservableList<String> getClassNames() {
        ComboBox<String> classeComboBox = new ComboBox<>();
        String sql = "SELECT ID_CLASS, NOM_CLASS FROM CLASS";
        String classInfo;
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement getClassStmt = conn.prepareStatement(sql);
            ResultSet resultClassName = getClassStmt.executeQuery();

            while (resultClassName.next()) {
                classInfo = resultClassName.getInt("ID_CLASS") + " " + resultClassName.getString("NOM_CLASS");
                classeComboBox.getItems().add(classInfo);
            }

        } catch (SQLException e) {
            // logError("Error getting class names: ", e);
            throw new RuntimeException("Error getting class names", e);
        }
        System.err.println(classeComboBox.getItems().toString());
        return classeComboBox.getItems();
    }

    public ObservableList<String> getModulesNames() {
        ComboBox<String> modulesComboBox = new ComboBox<>();
        String sql = "SELECT ID_MODULE, NOM_MODULE FROM MODULES";
        String moduleInfo;
        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement getModuleStmt = conn.prepareStatement(sql);
            ResultSet resultModuleName = getModuleStmt.executeQuery();

            while (resultModuleName.next()) {
                moduleInfo = resultModuleName.getInt("ID_MODULE") + " "
                        + resultModuleName.getString("NOM_MODULE");
                modulesComboBox.getItems().add(moduleInfo);
            }

        } catch (SQLException e) {
            // logError("Error getting class names: ", e);
            throw new RuntimeException("Error getting class names", e);
        }
        System.err.println(modulesComboBox.getItems().toString());
        return modulesComboBox.getItems();
    }

    public ObservableList<String> getProfesseurNames() {
        ComboBox<String> professeurComboBox = new ComboBox<>();
        String sql = "SELECT p.ID_PROFESSEUR, u.NOM, u.PRENOM FROM PROFESSEUR p, USER u WHERE u.ID_USER = p.ID_USER;";
        String professeurInfo;

        try (Connection conn = connectionFactory.getConnection()) {
            PreparedStatement getProfesseurStmt = conn.prepareStatement(sql);
            ResultSet resultProfesseurName = getProfesseurStmt.executeQuery();

            while (resultProfesseurName.next()) {
                professeurInfo = resultProfesseurName.getInt("ID_PROFESSEUR") + " "
                        + resultProfesseurName.getString("NOM") + " "
                        + resultProfesseurName.getString("PRENOM");
                professeurComboBox.getItems().add(professeurInfo);
            }

        } catch (SQLException e) {
            // logError("Error getting professeur names: ", e);
            throw new RuntimeException("Error getting professeur names", e);
        }
        System.err.println(professeurComboBox.getItems().toString());
        return professeurComboBox.getItems();
    }

    // private void logError(String string, SQLException e) {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'logError'");
    // }

}
