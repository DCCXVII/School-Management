package master.iitn.dao;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import master.iitn.model.Etudiant;
import master.iitn.services.Utils;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.element.Table;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.JComboBox;





public class ImprimerDao {
    private final ConnectionFactory connectionFactory;
    // private final Utils utils;

    public ImprimerDao() {
        this.connectionFactory = new ConnectionFactory();

    }


    public ObservableList<Etudiant> searchEtudiant(String cne) throws IOException {
        String sql = "SELECT DISTINCT(e.CNE), u.NOM, u.PRENOM, u.CIN, u.DATE_NAISSANCE, et.ANNEE_UNIVERSITAIRE, c.NOM_CLASS, u.EMAIL  FROM USER u, ETUDIANT e, ETAT et, CLASS c "
                    + "WHERE u.ID_USER=e.ID_USER "
                    + "AND e.ID_ETUDIANT=et.ID_ETUDIANT "
                    + "AND et.ID_CLASS=c.ID_CLASS "
                    + "AND e.CNE='" + cne + "' LIMIT 1;";

        ObservableList<Etudiant> overlist = FXCollections.observableArrayList();

        try (Connection conn = connectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Etudiant et = new Etudiant();
                et.setCne(rs.getString("CNE"));
                et.setNom(rs.getString("NOM"));
                et.setPrenom(rs.getString("PRENOM"));
                et.setCin(rs.getString("CIN"));
                et.setClasse(rs.getString("NOM_CLASS"));
                et.setEmail(rs.getString("EMAIL"));
                et.setDate_naissance(rs.getDate("DATE_NAISSANCE"));
                et.setAnneeUniversitaire(rs.getString("ANNEE_UNIVERSITAIRE"));
                overlist.add(et);
            }

            System.out.println(overlist.toString());

            return overlist;

        } catch (SQLException e) {
            logError("Error searching for student: ", e);
            throw new RuntimeException("Error searching for student", e);
        }
    }

    public void ImprimerAttestation(String cne) throws IOException{

        String desktopPath = "C:\\Users\\admin\\Desktop";
        String path = desktopPath +"\\"+ cne +".pdf";

        ObservableList<Etudiant> etud = searchEtudiant(cne);


        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        Document document = new Document(pdfDocument);
// FIXED TEXT IN THE RESULT 

        document.add(new Image(ImageDataFactory.create("attestationHeader.jpg")).setHorizontalAlignment(HorizontalAlignment.CENTER).setMarginBottom(20));
        String text = "ATTESTATION DE SCOLAIRE";

        document.add(new Paragraph(text).setBold().setFontSize(12f).setTextAlignment(TextAlignment.CENTER).setMarginBottom(40));

// STUDENT INFO IN THE RESULT
        String nom_phrase = "Le Directeur atteste que l'étudiant : \t"+ etud.get(0).getNom()+" "+ etud.get(0).getPrenom();
        String cin_phrase = "Numéro de la carte d'identité nationale : \t"+ etud.get(0).getCin();
        String cne_phrase = "Code national de l'étudiant : \t"+ etud.get(0).getCne();
        String dateNaissance_phrase = "Né le : "+ etud.get(0).getDate_naissance() +" à Kénitra";
        String anneUniversitaire_phrase  = etud.get(0).getAnneeUniversitaire();
        String par = "est régulièrement inscrit à l'Ecole Nationale des Sciences Appliquées de Kénitra pour l'année universitaire " + anneUniversitaire_phrase ;
        String diplome_phrase  = "Diplome : "+etud.get(0).getClasse();
        String annee_phrase  = "Année : "+etud.get(0).getClasse() ;

        document.add(new Paragraph(nom_phrase).setFontSize(10f).setMarginBottom(30));
        document.add(new Paragraph(cin_phrase).setFontSize(10f).setMarginBottom(30));
        document.add(new Paragraph(cne_phrase).setFontSize(10f).setMarginBottom(30));
        document.add(new Paragraph(dateNaissance_phrase).setFontSize(10f).setMarginBottom(30));
        document.add(new Paragraph(par).setFontSize(10f).setMarginBottom(30));
        document.add(new Paragraph(diplome_phrase).setFontSize(10f).setMarginBottom(30));
        document.add(new Paragraph(annee_phrase).setFontSize(10f).setMarginBottom(80));
        
// LA DATE ET LE SIGNATURE
        LocalDate currentDate = LocalDate.now();
        String dayName = currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRENCH);
        int dayNumber = currentDate.getDayOfMonth();
        String monthName = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.FRENCH);
        
        // Get the year
        int year = currentDate.getYear();
        String date = dayName + " " + dayNumber + " " + monthName + " " + year;
        String signature = "Fait à Kénitra , le " + date + " \nLe Directeur";

        document.add(new Paragraph(signature).setFontSize(10f).setTextAlignment(TextAlignment.CENTER)).setHorizontalAlignment(HorizontalAlignment.RIGHT);

// RESULT FOOTER
        String schoolInfo = "Ecole National des Sciences Appliquées | Adresse: Campus universitaire Maamora BP:2010 Kénitra Maroc | Site: ensa.uit.ac.ma | www.uit.ac.ma";
        String footerText = "Le présent document n'est délivré qu'en un seul exemplaire. Il appartient à l'étudiant d'en faire des photocopies certifiées conformes";
        
        float paragraphHeight = 10f; 
        float position = paragraphHeight * 2; 
        
        Paragraph schoolInfoParagraph = new Paragraph(schoolInfo).setFontSize(8f).setTextAlignment(TextAlignment.CENTER);
        Paragraph footerTextParagraph = new Paragraph(footerText).setFontSize(8f).setTextAlignment(TextAlignment.CENTER);
        
        // Set the position of the paragraphs
        schoolInfoParagraph.setFixedPosition(0, position, PageSize.A4.getWidth());
        footerTextParagraph.setFixedPosition(0, position - paragraphHeight, PageSize.A4.getWidth());
        
        document.add(schoolInfoParagraph);
        document.add(footerTextParagraph);

        document.close();
    }
    
    public void ImprimerBultin(String cne, String semestre) throws MalformedURLException, FileNotFoundException{
        String path = "bultin.pdf";

        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        Document document = new Document(pdfDocument);
// FIXED TEXT IN THE RESULT 

        document.add(new Image(ImageDataFactory.create("bultinHeader.jpg")).setHorizontalAlignment(HorizontalAlignment.CENTER).setMarginBottom(20));
        String text = "RELEVE DE NOTES ET RESULTATS\n 2022/2023\n SESSION 1";

        document.add(new Paragraph(text).setBold().setFontSize(12f).setTextAlignment(TextAlignment.CENTER).setMarginBottom(40));

// STUDENT INFO IN THE RESULT
        String nom = "Nom & Prenom: ";
        String cne_phrase = "CNE: ";
        String cin = "CIN: ";
        String dateNaissance = "Né le ";

        document.add(new Paragraph(nom).setFontSize(10f));
        document.add(new Paragraph(cne).setFontSize(10f));
        document.add(new Paragraph(cin).setFontSize(10f));
        document.add(new Paragraph(dateNaissance).setFontSize(10f).setMarginBottom(30));
        // le nom , CNE, CIN, datenaissance

        String semestreFiliere = "Semestre 1 Industrie Intillegente et Technologie Numerique";
        document.add(new Paragraph(semestreFiliere).setFontSize(10f));
        document.add(new Paragraph("a obtenu les notes suivantes").setFontSize(8.5f));
        
// THE RESULT TABLE
        float moduleCol = 300f;
        Table resultTable = new Table(new float[] {moduleCol, 11f, 11f, 11f, 11f });

        // Add headers to the table
        resultTable.addHeaderCell(new Paragraph("Modules").setTextAlignment(TextAlignment.CENTER));
        resultTable.addHeaderCell(new Paragraph("Note/barème").setTextAlignment(TextAlignment.CENTER));
        resultTable.addHeaderCell(new Paragraph("Résultat").setTextAlignment(TextAlignment.CENTER));
        resultTable.addHeaderCell(new Paragraph("Session").setTextAlignment(TextAlignment.CENTER));
        resultTable.addHeaderCell(new Paragraph("Année").setTextAlignment(TextAlignment.CENTER));

        // Add data to the table (replace with your actual data)
        for(int i=0; i<6; i++){
                resultTable.addCell(new Paragraph("AI & Big DATA "));
                resultTable.addCell(new Paragraph("16").setTextAlignment(TextAlignment.CENTER));
                resultTable.addCell(new Paragraph("V").setTextAlignment(TextAlignment.CENTER));
                resultTable.addCell(new Paragraph("1").setTextAlignment(TextAlignment.CENTER));
                resultTable.addCell(new Paragraph("2022/2023").setTextAlignment(TextAlignment.CENTER));   
        }
        
        document.add(resultTable);

        double noteGeneral = 13.55;
        document.add(new Paragraph("Resultat d'admission session Session1: " + noteGeneral).setFontSize(10f).setMarginBottom(80));

// LA DATE ET LE SIGNATURE
        LocalDate currentDate = LocalDate.now();
        String dayName = currentDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRENCH);
        int dayNumber = currentDate.getDayOfMonth();
        String monthName = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.FRENCH);
        
        // Get the year
        int year = currentDate.getYear();
        String date = dayName + " " + dayNumber + " " + monthName + " " + year;
        String signature = "Fait à Kénitra , le " + date + " \nLe Directeur";

        document.add(new Paragraph(signature).setFontSize(10f).setTextAlignment(TextAlignment.CENTER));

// RESULT FOOTER
        String schoolInfo = "Ecole National des Sciences Appliquées | Adresse: Campus universitaire Maamora BP:2010 Kénitra Maroc | Site: ensa.uit.ac.ma | www.uit.ac.ma";
        String footerText = "Le présent document n'est délivré qu'en un seul exemplaire. Il appartient à l'étudiant d'en faire des photocopies certifiées conformes";
        
        float paragraphHeight = 10f; 
        float position = paragraphHeight * 2; 
        
        Paragraph schoolInfoParagraph = new Paragraph(schoolInfo).setFontSize(8f).setTextAlignment(TextAlignment.CENTER);
        Paragraph footerTextParagraph = new Paragraph(footerText).setFontSize(8f).setTextAlignment(TextAlignment.CENTER);
        
        // Set the position of the paragraphs
        schoolInfoParagraph.setFixedPosition(0, position, PageSize.A4.getWidth());
        footerTextParagraph.setFixedPosition(0, position - paragraphHeight, PageSize.A4.getWidth());
        
        document.add(schoolInfoParagraph);
        document.add(footerTextParagraph);

        document.close();
    }

    private void logError(String string, SQLException e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'logError'");
    }
}

