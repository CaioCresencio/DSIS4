/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.entidades;

/**
 *
 * @author caio
 */
public class Autor {
    private int id_autor;
    private String nome;
    
    public Autor(String nome){
        this.nome = nome;
    }

    
    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
