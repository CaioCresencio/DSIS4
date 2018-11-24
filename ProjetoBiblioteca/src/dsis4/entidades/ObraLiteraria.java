/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.entidades;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author caio
 */
public class ObraLiteraria {
    private int idObra;
    private String isbn;
    private int qtdExemplares;
    private int nrmEdicao;
    private LocalDate dataPublicacao;
    private String editora;
    private String titulo;
    private CategoriaObra categoria;
    private List<Autor> autores;
    private List<PalavraChave> palavraChave;
    
    
    public ObraLiteraria(String isbn,int qtdExemplares, int nrmEdicao,LocalDate dataPublicacao,String editora,String titulo,CategoriaObra categoria,List<Autor> autores){
        this.isbn = isbn;
        this.qtdExemplares = qtdExemplares;
        this.dataPublicacao = dataPublicacao;
        this.editora = editora;
        this.titulo = titulo;
        this.categoria = categoria;
        this.autores = autores;
    }
    
    public List<Autor> getAutores(){
        return autores;
    }
    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQtdExemplares() {
        return qtdExemplares;
    }

    public void setQtdExemplares(int qtdExemplares) {
        this.qtdExemplares = qtdExemplares;
    }

    public int getNrmEdicao() {
        return nrmEdicao;
    }

    public void setNrmEdicao(int nrmEdicao) {
        this.nrmEdicao = nrmEdicao;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public CategoriaObra getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaObra categoria) {
        this.categoria = categoria;
    }
    
}
