/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.json;

import dsis4adapter.GsonCategoriaObraAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dsis4.entidades.CategoriaObra;
import dsis4adapter.GsonDataAdapter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.time.LocalDate;

/**
 *
 * @author caio
 */
public class ManipuladorGson {
    private String arquivo;
    
    public ManipuladorGson (String arquivo){
        this.arquivo = arquivo;
    }

    
    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
    
        
    public <T extends Object> T ler(Class<T> clazz){
        GsonBuilder builder = new GsonBuilder();
        
        builder.registerTypeAdapter(LocalDate.class,new GsonDataAdapter());
        builder.registerTypeAdapter(CategoriaObra.class, new GsonCategoriaObraAdapter());
        Gson gson  = builder.create();
        
        T t = null;
       
        
        try(Reader reader = new FileReader(arquivo)){
            t =  gson.fromJson(reader, clazz);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return t;
    }
    
      
    public void gravar(Object object){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
         
        builder.registerTypeAdapter(LocalDate.class,new GsonDataAdapter());
        builder.registerTypeAdapter(CategoriaObra.class, new GsonCategoriaObraAdapter());
        
        Gson gson = builder.create();
    
        try(Writer writer = new FileWriter(arquivo)){
            
            gson.toJson(object, writer);
            System.out.println(gson.toJson(object));
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}