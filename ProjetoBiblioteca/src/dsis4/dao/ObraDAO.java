/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.dao;

import dsis4.banco.ConexaoBD;
import dsis4.entidades.CategoriaObra;
import dsis4.entidades.ListaObra;
import dsis4.entidades.ObraLiteraria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caio
 */
public class ObraDAO {
    
    public void salvar(ObraLiteraria o){
        CategoriaObraDAO catDAO = new CategoriaObraDAO();
        String sql = "INSERT INTO obra_literaria(id_obra, isbn, qtd_exemplares,nrm_edicao, data_publicacao,editora, titulo_obra,codigo_categoria)"
                + " VALUES (seq_obraLiteraria.nextval,?,?,?,?,?,?,?)";
        
        try(Connection con = ConexaoBD.getInstance().getConnection()){
            if(!verificaObra(con, o.getIsbn())){
                try(PreparedStatement pStat = con.prepareStatement(sql,new String[]{"id_obra"})){
                   
                    con.setAutoCommit(false);
                    int categoria = catDAO.getCodigo(con,o.getCategoria().getDescricao());//busca o codigo da categoria
                    System.out.println(categoria);
                    pStat.setString(1, o.getIsbn());//Consideranco que o isbn pode conter caracteres
                    pStat.setInt(2, o.getQtdExemplares());
                    pStat.setInt(3, o.getNrmEdicao());
                    pStat.setDate(4,java.sql.Date.valueOf(o.getDataPublicacao()) );
                    pStat.setString(5,o.getEditora());
                    pStat.setString(6, o.getTitulo());
                    pStat.setInt(7, categoria);
                    pStat.executeUpdate();
                    
                    int id_obra = getPKObra(pStat,con);
                    o.setIdObra(id_obra);
                    
                    CategoriaObra catObra = new CategoriaObra(o.getCategoria().getCodigoCategoria(),getDescricaoCategoria(con,o.getCategoria().getCodigoCategoria()));
                    o.setCategoria(catObra);
                    
                    System.out.println(o.getCategoria().getDescricao());
                    salvarExemplar(con,o);
                    salvarAutor(con,o);
                    salvarPc(con,o);

                    con.commit();

                    System.out.println("Salvo com sucesso!");
                }catch(SQLException erro){
                    con.rollback();
                    throw new RuntimeException(erro);
                }
            }else{
                System.out.println("Obra já cadastrada!");
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
      
    }
    
    private String getDescricaoCategoria(Connection con, int codigo){
        String descricao = "Sem descricão";
    
        String sql = "SELECT descricao FROM categoria_literaria WHERE codigo_categoria = ?" ;
       
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            pStat.setInt(1, codigo);
            pStat.executeUpdate();
            
            try(ResultSet rs = pStat.executeQuery()){
                
                if(rs.next()){
                   
                    descricao = rs.getString(1);
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return descricao;
        
    }
    private int getPKObra(PreparedStatement pStat, Connection con){
        int id = 0;
        try(ResultSet rs = pStat.getGeneratedKeys()){
            if(rs.next()){
                id = rs.getInt(1);
            }
            return id;
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    private boolean verificaObra(Connection con, String isbn){
        boolean verifica = false;
        String sql = "SELECT COUNT(isbn) FROM obra_literaria WHERE isbn = ?";
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            pStat.setString(1, isbn);
            pStat.executeUpdate();
            
            try(ResultSet rs = pStat.executeQuery()){
                int retorno = -1;
                if(rs.next()){
                    retorno = rs.getInt(1);
                }
                if(retorno >  0){
                    verifica = true;
                }
                
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return verifica;
    }
     
    private void salvarExemplar(Connection con, ObraLiteraria o){
        if(o.getQtdExemplares() == 0){
            o.setQtdExemplares(1);
        }     
        ExemplarDAO exDAO = new ExemplarDAO();     
        
        for(int i = 1; i <= o.getQtdExemplares(); i++){
            
            exDAO.salvar(o.getIdObra(), i, con);
        }
        
    }
    
    private void salvarAutor(Connection con, ObraLiteraria o){
        String sql = "INSERT INTO  autor(id_autor,nome) VALUES (seq_autor.nextval,?)";
        String sql_lista = "INSERT INTO lista_autores(id_autor,id_obra) VALUES (seq_autor.currval,?) ";
        try(PreparedStatement pStat = con.prepareStatement(sql);
            PreparedStatement pStat2 = con.prepareStatement(sql_lista) ){
            for(String s: o.getAutores()){
                
                pStat.setString(1,s);
                pStat.executeUpdate();
          
                pStat2.setInt(1,o.getIdObra());
                pStat2.executeUpdate();
                
                
            }
            
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
   
    private void salvarPc(Connection con, ObraLiteraria o){
        String sql = "INSERT INTO  palavra_chave(id_palavra,conteudo) VALUES (seq_palavraChave.nextval,?)";
        String sql_lista = "INSERT INTO lista_palavras(id_obra,id_palavra) VALUES (? , seq_palavraChave.currval )";
        try(PreparedStatement pStat = con.prepareStatement(sql);
            PreparedStatement pStat2 = con.prepareStatement(sql_lista)){
            for(String s: o.getPalavras()){
                
                pStat.setString(1,s);
                pStat.executeUpdate();
          
                pStat2.setInt(1,o.getIdObra());
                pStat2.executeUpdate();
                
            
            }
            
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
//    public List<ObraLiteraria> listarTodosNoIntervalo(int min, int max){
//        String query = ;
//    }
//    
    
    public ListaObra listarObras(){
        String sql = "select id_obra , isbn, qtd_exemplares , nrm_edicao,data_publicacao, editora,titulo_obra ,codigo_categoria, cl.descricao from"
                + " obra_literaria JOIN categoria_literaria cl USING (codigo_categoria) ";
        ListaObra listaObra = null;
        ExemplarDAO eDAO = new ExemplarDAO();
        List<ObraLiteraria> lista = new ArrayList<>();
        try(Connection con = ConexaoBD.getInstance().getConnection()){
            try(PreparedStatement pStat = con.prepareStatement(sql)){
                pStat.executeUpdate();
                
                try(ResultSet rs = pStat.executeQuery()){
                    while(rs.next()){
                        
                        int id_obra = rs.getInt("id_obra");
                        String ibsn = rs.getString("isbn");
                        int qtd_exemplares = rs.getInt("qtd_exemplares");
                        int nrm_edicao = rs.getInt("nrm_edicao");
                        LocalDate data = (rs.getDate("data_publicacao").toLocalDate());
                        String editora = rs.getString("editora");
                        String titulo = rs.getString("titulo_obra");
                        int codigo_categoria = rs.getInt("codigo_categoria");
                        String descricao = rs.getString("descricao");
                        CategoriaObra co = new CategoriaObra(codigo_categoria,descricao);
                                
                        ObraLiteraria obra = new ObraLiteraria(id_obra, ibsn, qtd_exemplares, nrm_edicao, data, editora, titulo, co, getAutores(con, id_obra),getAutores(con, id_obra), eDAO.totalDisponiveis(id_obra));
                        lista.add(obra);
                    }
                }
                
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return new ListaObra(lista);
    }
    
    public List<String> getAutores(Connection con, int id_obra){
        List<String> listaAutores = new ArrayList<>();
        String sql = "SELECT nome,titulo_obra FROM autor JOIN lista_autores USING (id_autor) JOIN obra_literaria USING(id_obra) WHERE id_obra = ?";
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            pStat.setInt(1, id_obra);
            pStat.executeUpdate();      
            try(ResultSet rs = pStat.executeQuery()){
                while(rs.next()){
                    String autor = rs.getString(1);
                    listaAutores.add(autor);
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return listaAutores;
        
    }
    public List<String> getPalavrasChave(Connection con, int id_obra){
        List<String> listaPalavras = new ArrayList<>();
        String sql = "SELECT conteudo FROM palavra_chave JOIN lista_palavras USING (id_palavra) JOIN obra_literaria USING(id_obra) WHERE id_obra = ?";
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            pStat.setInt(1, id_obra);
            pStat.executeUpdate();      
            try(ResultSet rs = pStat.executeQuery()){
                while(rs.next()){
                    String autor = rs.getString(1);
                    listaPalavras.add(autor);
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return listaPalavras;
        
    }
}
