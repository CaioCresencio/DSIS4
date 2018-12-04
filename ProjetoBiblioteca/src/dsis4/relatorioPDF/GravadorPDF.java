/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.relatorioPDF;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import dsis4.entidades.ListaObra;
import dsis4.entidades.ObraLiteraria;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GravadorPDF {
    private String arquivo;
    private PdfPTable tabela;
    
    public GravadorPDF(String arquivo){
        this.arquivo = arquivo;
    }
   
    public void criaPDF(ListaObra obras) {
            
        try{
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(arquivo));
            
            document.open();
            
            Paragraph paragraph = new Paragraph("RELATÓRIO\n\n");
            document.add(paragraph);
            paragraph = new Paragraph("Obras Literárias:\n\n");
            document.add(paragraph);
            
//            tabela = new PdfPTable(6);
//            headerTabela();
// 
//            preencheTabela(obras);
            
//            document.add(tabela);
            int i = 1;
            for(ObraLiteraria o: obras.getLista()){     
                document.add(new Paragraph(String.format("\n------ Obra: %d ------\n",i++)));
                document.add(new Paragraph(o.toString()));
                
            }
            document.close();
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GravadorPDF.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(GravadorPDF.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }
    
//    
//    private void headerTabela(){
//            PdfPCell c = new PdfPCell(new Phrase("Título"));
//            tabela.addCell(c);
//            c = new PdfPCell(new Phrase("ISBN"));
//            tabela.addCell(c);
//            c = new PdfPCell(new Phrase("Edição"));
//            tabela.addCell(c);
//            c = new PdfPCell(new Phrase("Categoria"));
//            tabela.addCell(c);
//            c = new PdfPCell(new Phrase("Acervo"));
//            tabela.addCell(c);
//            c = new PdfPCell(new Phrase("Disponiveis"));
//            tabela.addCell(c);
//            c = new PdfPCell(new Phrase("Autor(es)"));
//            tabela.addCell(c);            
//            tabela.setHeaderRows(1);
//    }
//    
//    private void preencheTabela(ListaObra obras){
//        for(ObraLiteraria o: obras.getLista()){                
//            tabela.addCell(o.getTitulo());
//            tabela.addCell(o.getIsbn());
//            tabela.addCell(String.valueOf(o.getNrmEdicao()));
//            tabela.addCell(o.getCategoria().getDescricao());
//            tabela.addCell(String.valueOf(o.getQtdExemplares()));
//            tabela.addCell(String.valueOf(o.getQtdDisponiveis()));
//            tabela.addCell(String.valueOf(o.getAutores().toString()));             
//        }
//        
//    }
}
