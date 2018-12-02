/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.entidades;



import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "obras")

public class ListaObra {
    
    
    @XmlElement(name = "obra")
    @Expose
    private List<ObraLiteraria> obras;
    
    public ListaObra(List<ObraLiteraria> obras){
        this.obras = obras;
    }
    
    public ListaObra(){};
    
    public void addObra(ObraLiteraria obra){
        obras.add(obra);
    }
    
    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       obras.forEach(o -> {
           sb.append(o.toString());
           sb.append("\n");
           
       });
       return sb.toString();
    }
    @JsonProperty("obras")
    public List<ObraLiteraria> getLista(){
        return obras;
    }
}
