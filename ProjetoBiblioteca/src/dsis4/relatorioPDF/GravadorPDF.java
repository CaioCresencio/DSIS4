/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.relatorioPDF;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import dsis4.entidades.ListaObra;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GravadorPDF {
    String arquivo;
    
    public GravadorPDF(String arquivo){
        this.arquivo = arquivo;
    }
   
    public void criaPDF(ListaObra obras) {
        
        
        try{
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
            
            document.open();
            
            Paragraph paragraph = new Paragraph("Obras Literarias");
            
            
            
            
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GravadorPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GravadorPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
