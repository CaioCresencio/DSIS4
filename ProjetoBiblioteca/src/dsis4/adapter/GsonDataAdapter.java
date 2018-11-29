/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author caio
 */
public class GsonDataAdapter implements JsonDeserializer<LocalDate>, JsonSerializer<LocalDate> {
    DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    @Override
    public LocalDate deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        String data = je.getAsString();
          
        return LocalDate.parse(data, formatter);  
    }

    @Override
    public JsonElement serialize(LocalDate t, Type type, JsonSerializationContext jsc) {
        String data = t.format(formatter);
        
        return new JsonPrimitive(data);
    }
    
}
