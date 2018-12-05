/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.util;

/**
 *
 * @author denis
 */

//Código pronto
public enum TipoArquivo {
    
    JAXB("XML"),
    GSON("JSON"),
    SAX("XML"),
    DOM("XML"),
    JACKSON("JSON");
    
    //Poderia haver outros gravadores na aplicação
    
    private String formato;
    
    private TipoArquivo(String formato) {
        this.formato = formato;
    }
    
    public String getFormato() {
        return formato;
    }
    
    public static Object[] getValues() {
        return values();
    }
}
