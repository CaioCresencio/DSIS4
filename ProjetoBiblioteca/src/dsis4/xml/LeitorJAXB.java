package dsis4.xml;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
public class LeitorJAXB implements AgoritmoLeituraXML{
    
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
}
