/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
import dsis4.adapter.JAXBCategoriaObraAdapter;
import dsis4.adapter.DataAdapter;
import dsis4.adapter.JacksonCategoriaAdapter;
import dsis4.adapter.JacksonDateAdapter;
import dsis4.adapter.JacksonDeserializerCat;
import dsis4.adapter.JacksonDeserializerDate;
import java.time.LocalDate;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author caio
 */

@XmlAccessorType(XmlAccessType.FIELD)

//@XmlType(propOrder = {"isbn","titulo","categoria","autores","palavras-chave","data","edicao","editora"})
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@JsonTypeName("obra")
public class ObraLiteraria {
    //@XmlTransient
    private transient int idObra;
    
    @XmlElement(name = "isbn")
    
    private String isbn;
    
    //@XmlTransient
    private transient int qtdExemplares;
    
    @SerializedName("edicao")
    @XmlElement(name = "edicao")
    private int nrmEdicao;
    
    @XmlElement(name = "data")
    @SerializedName("data")
    @XmlJavaTypeAdapter(DataAdapter.class)
    @JsonSerialize(using = JacksonDateAdapter.class)
    @JsonDeserialize(using = JacksonDeserializerDate.class)
    private LocalDate dataPublicacao;
    
    @XmlElement(name = "editora")
    private String editora;
    
    @XmlElement(name = "titulo")
    private String titulo;

    @XmlElement(name = "categoria")
    @XmlJavaTypeAdapter(JAXBCategoriaObraAdapter.class)
    @JsonSerialize(using = JacksonCategoriaAdapter.class)
    @JsonDeserialize(using = JacksonDeserializerCat.class)
    private CategoriaObra categoria;
    
    
    @XmlElement(name = "autor")
    @XmlElementWrapper(name = "autores")
    private List<String> autores;
    
    @JsonIgnore
    @SerializedName("palavras-chave")
    @XmlElement(name = "palavra-chave")
    @XmlElementWrapper(name = "palavras-chave")
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
    @JsonProperty("palavras-chave")
    public List<String> getPalavras(){
        return palavraChave;
    }
    
    public void addAutor(String autor){
        autores.add(autor);

    }
    @JsonIgnore
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
    @JsonIgnore
    public int getQtdExemplares() {
        return qtdExemplares;
    }

    public void setQtdExemplares(int qtdExemplares) {
        this.qtdExemplares = qtdExemplares;
    }
    @JsonProperty("edicao")
    public int getNrmEdicao() {
        return nrmEdicao;
    }
    
    public void setNrmEdicao(int nrmEdicao) {
        this.nrmEdicao = nrmEdicao;
    }
    @JsonProperty("data")
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
    @JsonProperty("categoria")
    public CategoriaObra getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaObra categoria) {
        this.categoria = categoria;
    }

    public void addPalavraChave(String palavra) {
        palavraChave.add(palavra);
    }

    @Override
    public String toString() {
        //return String.format("Isbn: %s Titulo: %s Autores: %s Palavras-Chave %s Categoria: %s", isbn, titulo, autores.toString(), palavraChave.toString(),categoria.getDescricao());
    return String.format("Isbn: %s Titulo: %s Autores: %s Palavras-Chave %s  Categoria: %s", isbn, titulo, autores.toString(),palavraChave.toString() ,categoria.getDescricao());
    }

    /**
     * @param autores the autores to set
     */
    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    /**
     * @param palavraChave the palavraChave to set
     */
    public void setPalavraChave(List<String> palavraChave) {
        this.palavraChave = palavraChave;
    }
    
}
