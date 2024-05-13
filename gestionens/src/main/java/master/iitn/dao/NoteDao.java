package master.iitn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import master.iitn.model.Mentions;
import master.iitn.model.Note;
import master.iitn.services.Utils;

public class NoteDao {
    ConnectionFactory connectionFactory;
    Utils utils = new Utils();

    public NoteDao() {
        this.connectionFactory = new ConnectionFactory();
    }

    public List<Note> getNotesById(int id) throws SQLException {
        String sql = "SELECT * FROM NOTES WHERE ID_ETUDIANT = '" + id + "'";

        try (Connection connection = connectionFactory.getConnection();
                java.sql.Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql)) {

            List<Note> notes = new ArrayList<Note>();
            while (rs.next()) {
                Note note = new Note();
                note.setID_ETUDIANT(rs.getInt("ID_ETUDIANT"));
                note.setNoteSemestre(rs.getFloat("NOTE"));
                note.setAnnnee(2023);
                note.setMention(rs.getString("MENTION").equals("Validé") ? Mentions.Validé : Mentions.NValidé);
                note.setSemestre(rs.getString("SEMESTRE"));
                notes.add(note);
            }
            return notes;

        }
    }
}
