/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.entidades;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author caio
 */

public class CategoriaObra {
    @XmlTransient
    private int codigoCategoria;
    
    private String descricao;
    
    public CategoriaObra(int codigoCategoria, String descricao){
        this.codigoCategoria = codigoCategoria;
        this.descricao = descricao;
    }
    public CategoriaObra(String descricao){
        this.descricao = descricao;
    }
    
    public CategoriaObra(){};

    public int getCodigoCategoria() {
        return codigoCategoria;
    }


    public void setCodigoCategoria(int codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }


    public String getDescricao() {
        return descricao;
    }

   
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
    
    
}
