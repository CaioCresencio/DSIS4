/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.entidades;

/**
 *
 * @author Windows
 */
public class Exemplar {
    
    private int id;
    private boolean disponivel;
    private int numero;
    
    public Exemplar(int id, boolean disponivel,int numero){
        this.id = id;
        this.disponivel = disponivel;
        this.numero = numero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    
    @Override
    public String toString(){
        return String.format("ID: %d, %s",id, disponivel ? "disponivel":"nao disponivel");
    }
}

