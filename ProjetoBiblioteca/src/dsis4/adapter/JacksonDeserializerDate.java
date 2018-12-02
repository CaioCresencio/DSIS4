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
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author caio
 */
public class JacksonDeserializerDate extends StdDeserializer<LocalDate> {
     DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public JacksonDeserializerDate(Class<?> c){
        super(c);
    }

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
            LocalDate retorno = LocalDate.parse(jp.getValueAsString(), formatter);
           return retorno;
    }
}
