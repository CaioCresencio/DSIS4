/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.xml;

import dsis4.util.AlgoritmoLeituraXML;
import dsis4.entidades.CategoriaObra;
import dsis4.entidades.ListaObra;
import dsis4.entidades.ObraLiteraria;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author caio
 */
public class LeitorDOM implements AlgoritmoLeituraXML  {
    
    private String arquivo;
    
    public LeitorDOM(String arquivo){
        this.arquivo = arquivo;
    }
    
    @Override
    public ListaObra ler() {
        ListaObra o = new ListaObra(new ArrayList<ObraLiteraria>());
        Document document = carregarDocumento();
        //Element raiz = document.getDocumentElement();
        NodeList nodeList = document.getElementsByTagName("obra");
        for(int i = 0; i < nodeList.getLength(); i++) {
            ObraLiteraria obra = new ObraLiteraria();
            Element obraElement = (Element)nodeList.item(i);
            String isbn = obraElement.getElementsByTagName("isbn").item(0).getTextContent();
            String titulo = obraElement.getElementsByTagName("titulo").item(0).getTextContent();
            String descricao = obraElement.getElementsByTagName("categoria").item(0).getTextContent();
            
            NodeList autorLista = obraElement.getElementsByTagName("autor");
            List<String> autores = new ArrayList<>();
            
            for(int j = 0; j < autorLista.getLength(); j++){
                Element autorElement = (Element)autorLista.item(j);
                autores.add(autorElement.getTextContent());
            }
            
            NodeList palavraLista = obraElement.getElementsByTagName("palavra-chave");
            List<String> palavras = new ArrayList<>();
            for(int j = 0; j < palavraLista.getLength(); j++){
                Element palavraElement = (Element)palavraLista.item(j);
                palavras.add(palavraElement.getTextContent());
            }
            
            Element dataElement = (Element)obraElement.getElementsByTagName("data").item(0);
            LocalDate date = null;
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                date = LocalDate.parse(dataElement.getTextContent(), formatter);              
            }
            catch(DateTimeParseException ex){
                ex.printStackTrace();
            }
            
            int edicao = Integer.parseInt(obraElement.getElementsByTagName("edicao").item(0).getTextContent());
            
            String editora = obraElement.getElementsByTagName("editora").item(0).getTextContent();
            
            obra.setIsbn(isbn);
            obra.setTitulo(titulo);
            obra.setCategoria(new CategoriaObra(descricao));
            obra.setAutores(autores);
            obra.setDataPublicacao(date);
            obra.setNrmEdicao(edicao);
            obra.setPalavraChave(palavras);
            obra.setEditora(editora);
            
            
            o.addObra(obra);
        }
        
       
        return o;
    }
     
    private Document carregarDocumento() {
        Document document = null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new TratadorErro());
            document = builder.parse(arquivo);
            
        }catch(ParserConfigurationException | SAXException | IOException erro){
            erro.printStackTrace();
        
        }
        
        return document;
    }


     
    
}  

