/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.adapter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dsis4.entidades.CategoriaObra;
import java.io.IOException;


/**
 *
 * @author caio
 */
public class JacksonCategoriaAdapter  extends JsonSerializer<CategoriaObra> {

    @Override
    public void serialize(CategoriaObra t, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
        jg.writeString(t.getDescricao());
    }
    
}
