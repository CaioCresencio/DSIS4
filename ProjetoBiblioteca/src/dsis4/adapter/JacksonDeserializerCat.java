/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.adapter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dsis4.entidades.CategoriaObra;
import java.io.IOException;

/**
 *
 * @author caio
 */
public class JacksonDeserializerCat extends StdDeserializer<CategoriaObra> {
    
    public JacksonDeserializerCat(Class<?> c){
        super(c);
    }

    @Override
    public CategoriaObra deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        return new CategoriaObra(0,jp.getValueAsString());
    }
    
    
}
