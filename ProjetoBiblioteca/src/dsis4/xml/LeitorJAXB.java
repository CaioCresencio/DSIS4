package dsis4.xml;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gabriel
 */
public class LeitorJAXB implements AlgoritmoLeituraXML{
    
    private String arquivo;
    private Class[] classes;
    
    public LeitorJAXB(String arquivo) {
        this.arquivo = arquivo;
    }

    @Override
    public Object ler() {
        Object object = null;
        try {
            JAXBContext context = JAXBContext.newInstance(classes);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            object = unmarshaller.unmarshal(new File(arquivo));            
        }
        catch(JAXBException erro) {
            erro.printStackTrace();
        }
        return object;
    }

    /**
     * @param classes the classes to set
     */
    public void setClasses(Class... classes) {
        this.classes = classes;
    }
    
    
    public void gravarXML(Object p){
        
        try {
            JAXBContext contex = JAXBContext.newInstance(classes);
            
            Marshaller marshaller = contex.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(p, new File(arquivo));
            marshaller.marshal(p, System.out);
            
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        
        
    }
    
    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}
