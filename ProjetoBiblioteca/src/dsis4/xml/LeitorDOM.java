/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.xml;

import dsis4.entidades.ObraLiteraria;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
    public Object ler() {
        Object o = null;
        Document document = carregarDocumento();
//        NodeList nodeList = document.getElementsByTagName("obras");
//        List<ObraLiteraria> obras = new ArrayList<>();
//        for(int i = 0; i < nodeList.getLength(); i++) {
//            Node node = nodeList.item(i);
//            Element pessoaElement = (Element)node;
//            String isbn = pessoaElement.getElementsByTagName("isbn").item(0).getTextContent();
//            int idade = Integer.parseInt(pessoaElement.getElementsByTagName("idade").item(0).getTextContent());
//            //ObraLiteraria obra = new ObraLiteraria(nome, idade, idade, LocalDate.MIN, nome, arquivo, categoria, autores, palavrasChave)
//        }
//        o = obras;
       
        return o;
    }
     
    private Document carregarDocumento() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new TratadorErro());
            Document document = builder.parse(new File(arquivo));
            return document;
        }
        catch(IOException | ParserConfigurationException | SAXException erro) {
            throw new RuntimeException(erro);
        }
    }
     
    
}
