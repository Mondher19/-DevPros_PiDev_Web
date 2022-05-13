/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import services.CommandeService;

/**
 *
 * @author moham
 */
public class Pdf {
     public void GeneratePdf(String filename) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document() {};
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
      
       CommandeService m=new CommandeService();
        List<Commande > list=m.afficherCommande ();    
        document.add(new Paragraph("La liste des commandes :"));
        document.add(new Paragraph("     "));
         for(Commande u:list)
        {
           
        document.add(new Paragraph("Nom  :"+u.getNom()));
        document.add(new Paragraph("Prenom:"+u.getPrenom()));
        document.add(new Paragraph("Adresse :"+u.getEmail()));
                document.add(new Paragraph("Email :"+u.getAdresse()));
                 document.add(new Paragraph("Numero Telphone :"+u.getNumtelephone()));
                 document.add(new Paragraph("id  :"+u.getId()));


        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        }
        document.close();
        Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+filename+".pdf");
    }
    
       
    
}
