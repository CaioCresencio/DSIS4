/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author caio
 */
public class ManipuladorJackson{
    private String arquivo;
    
    
    public ManipuladorJackson(String arquivo){
        this.arquivo = arquivo;
    }
    
    public void gravar(Object o){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            //objectMapper.enable(SerializationFeature.INDENT_OUTPUT); 
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(arquivo), o);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    
    
    
     
    
}
