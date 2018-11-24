package dsis4.xml;

import dsis4.entidades.CategoriaObra;
import dsis4.entidades.ListaObra;
import dsis4.entidades.ObraLiteraria;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriel
 */
public class SAXHandler extends DefaultHandler {
    
    private ListaObra lista;
    private ObraLiteraria obra;
    private String valor;
       
    public ListaObra getLista() {
        return lista;
    }
    
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Início do parsing...");
        lista = new ListaObra(new ArrayList<>());
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("obra")) {
            obra = new ObraLiteraria(new ArrayList<>(), new ArrayList<>());
            lista.addObra(obra);
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        valor = String.valueOf(ch, start, length);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(qName.equals("isbn")) {
            obra.setIsbn(valor);
        }else if(qName.equals("titulo")) {
            obra.setTitulo(valor);
        }else if(qName.equals("categoria")) {
            CategoriaObra cat = new CategoriaObra(valor);
            obra.setCategoria(cat);
        }else if(qName.equals("autor")) {
            obra.addAutor(valor);
        }else if(qName.equals("palavra-chave")) {
           obra.addPalavraChave(valor);
        }else if(qName.equals("data")) {
            DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            obra.setDataPublicacao(LocalDate.parse(valor, formatter));
        }else if(qName.equals("edicao")) {
            obra.setNrmEdicao(Integer.parseInt(valor));
        }else if(qName.equals("editora")) {
            obra.setEditora(valor);
        }
        
    }
    
    @Override
    public void endDocument() throws SAXException {
        System.out.println("Fim do parsing...");
    }
}
