/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.entidades;

import java.time.LocalDate;

/**
 *
 * @author caio
 */
public class CategoriaLeitor {
    
    private int codigo;
    private String descricao;
    private LocalDate tempo_emprestimo;
    
    public CategoriaLeitor(int codigo, String descricao, LocalDate tempo_emprestimo){
        this.codigo = codigo;
        this.descricao = descricao;
        this.tempo_emprestimo = tempo_emprestimo;
        
    }
      public CategoriaLeitor(String descricao, LocalDate tempo_emprestimo){
        this.descricao = descricao;
        this.tempo_emprestimo = tempo_emprestimo;
        
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

  
    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public LocalDate getTempo_emprestimo() {
        return tempo_emprestimo;
    }

   
    public void setTempo_emprestimo(LocalDate tempo_emprestimo) {
        this.tempo_emprestimo = tempo_emprestimo;
    }
    
    
    
}
