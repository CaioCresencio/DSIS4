/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 *
 * @author Gabriel
 */
public class LeitorSAX implements AlgoritmoLeituraXML{
    
    private String arquivo;
    
    public LeitorSAX(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public Object ler() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        Object object = null;
        try(InputStream input = new FileInputStream(arquivo)) 
        {
            SAXParser parser = factory.newSAXParser();
            SAXHandler handler = new SAXHandler();
            parser.parse(input, handler);
            object = handler.getLista();
        }
        catch(ParserConfigurationException | SAXException | IOException erro) {
            System.out.println(erro.getMessage());
        }
        return object;
    }
}
