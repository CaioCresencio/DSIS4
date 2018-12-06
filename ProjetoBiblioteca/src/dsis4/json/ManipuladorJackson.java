/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import dsis4.entidades.ListaObra;
import dsis4.entidades.ObraLiteraria;
import java.io.FileOutputStream;
import java.io.IOException;
import dsis4.util.AlgoritmoGravacaoJson;
import dsis4.util.AlgoritmoLeituraJson;

/**
 *
 * @author caio
 */
public class ManipuladorJackson implements AlgoritmoGravacaoJson, AlgoritmoLeituraJson{
    private String arquivo;
    private Class clazz;
    
    
    public ManipuladorJackson(String arquivo){
        this.arquivo = arquivo;
    }
    @Override
    public void gravar(Object o){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            //objectMapper.enable(SerializationFeature.INDENT_OUTPUT); 
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new FileOutputStream(arquivo), o);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    public Object ler(){
        ListaObra lista;
        ObjectMapper mapper = new ObjectMapper();
        try{
             mapper.readValue(arquivo,clazz);
             
            //System.out.println(lista.toString());
        }catch(IOException e){
            throw new RuntimeException(e);
        }
        return new Object();
    }
    
    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

}
