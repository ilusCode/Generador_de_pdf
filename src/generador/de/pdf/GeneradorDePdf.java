/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generador.de.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

/**
 *
 * @author iluscode
 */
public class GeneradorDePdf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            String plantilla = "./formulario.pdf";
            File file = new File(plantilla);
            PDDocument doc = PDDocument.load(file);
            
            PDDocumentCatalog docCatalog = doc.getDocumentCatalog();
            PDAcroForm acroForm = docCatalog.getAcroForm();
            Date d = new Date();
            
            rellenarCampos(acroForm, "nombre", "prueba");
            
            doc.save("./formulario1.pdf");
        doc.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex);           
        }
    }

    private static void rellenarCampos(PDAcroForm acroForm, String nombreCampo, String valor) throws IOException {
        PDField field = acroForm.getField(nombreCampo);
        if (field != null) {
            field.setValue(valor);
        } else {
            System.err.println("No se ha encontrado el campo " + nombreCampo + "!");
        }
    }
}
