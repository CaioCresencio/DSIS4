package dsis4adapter;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import dsis4.entidades.CategoriaObra;
import java.lang.reflect.Type;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author caio
 */
public class GsonCategoriaObraAdapter implements JsonDeserializer<CategoriaObra>, JsonSerializer<CategoriaObra>  {

    @Override
    public CategoriaObra deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        CategoriaObra catObra = new CategoriaObra(je.getAsString());
        
        return catObra;
    }

    @Override
    public JsonElement serialize(CategoriaObra t, Type type, JsonSerializationContext jsc) {
        String retorno = t.getDescricao();
        
        return new JsonPrimitive(retorno);
    }
    
}
