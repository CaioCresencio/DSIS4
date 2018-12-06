/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.util;

/**
 *
 * @author caio
 */
public enum TipoGravador {
    
    JAXB("XML"),
    GSON("JSON"),
    JACKSON("JSON");
    
    private String formato;
    
    private TipoGravador(String formato) {
        this.formato = formato;
    }
    
    public String getFormato() {
        return formato;
    }
    
    public static Object[] getValues() {
        return values();
    }

}
