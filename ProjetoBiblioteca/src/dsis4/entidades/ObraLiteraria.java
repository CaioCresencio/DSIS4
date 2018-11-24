/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.entidades;

import dsis4.xml.DataAdapter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author caio
 */

@XmlAccessorType(XmlAccessType.FIELD)

//@XmlType(propOrder = {"isbn","titulo","categoria","autores","palavras-chave","data","edicao","editora"})

public class ObraLiteraria {
    @XmlTransient
    private int idObra;
    
    @XmlElement(name = "isbn")
    private String isbn;
    
    @XmlTransient
    private int qtdExemplares;
    
    @XmlElement(name = "edicao")
    private int nrmEdicao;
    
    @XmlElement(name = "data")
    @XmlJavaTypeAdapter(DataAdapter.class)
    private LocalDate dataPublicacao;
    
    @XmlElement(name = "editora")
    private String editora;
    
    @XmlElement(name = "titulo")
    private String titulo;

    @XmlElement(name = "categoria")
    private CategoriaObra categoria;
    
    @XmlElementWrapper(name = "autores")
    @XmlElement(name = "autor")

    private List<String> autores;
    
    @XmlElementWrapper(name = "palavras-chave")
    @XmlElement(name = "palavra-chave")
    private List<String> palavraChave;
    
    



    public ObraLiteraria(List<String> autores, List<String> palavrasChaves){
        this.autores = autores;
        this.palavraChave = palavrasChaves;
    }
    
    public ObraLiteraria(){
    }
    
    public ObraLiteraria(String isbn,int qtdExemplares, int nrmEdicao,LocalDate dataPublicacao,String editora,String titulo,CategoriaObra categoria,List<String> autores, List<String> palavrasChave){
        this.isbn = isbn;
        this.qtdExemplares = qtdExemplares;
        this.dataPublicacao = dataPublicacao;
        this.editora = editora;
        this.titulo = titulo;
        this.categoria = categoria;
        this.autores = autores;
        this.palavraChave = palavrasChave;
    }
    
    public List<String> getAutores(){
        return autores;
    }

    public List<String> getPalavras(){
        return palavraChave;
    }
    
    public void addAutor(String autor){
        autores.add(autor);

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

    public List<String> getPalavraChave() {
        return palavraChave;
    }

    public void addPalavraChave(String palavra) {
        palavraChave.add(palavra);
    }

    @Override
    public String toString() {
        return categoria.toString(); //String.format("Isbn: %s Titulo: %s Autores: %s Palavras-Chave %s Categoria: ", isbn, titulo, autores.toString(), palavraChave.toString());
    }
    
}
