/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.dao;

import dsis4.banco.ConexaoBD;
import dsis4.entidades.ObraLiteraria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author caio
 */
public class ObraDAO {
    
    public void salvar(ObraLiteraria o){
        String sql = "INSERT INTO obra_literaria(id_obra, isbn, qtd_exemplares,nrm_edicao, data_publicacao,editora, titulo_obra,codigo_categoria)"
                + " VALUES (seq_obraLiteraria.nextval,?,?,?,?,?,?,?)";
        
        try(Connection con = ConexaoBD.getInstance().getConnection()){
            if(!verificaObra(con, o.getIsbn())){
                try(PreparedStatement pStat = con.prepareStatement(sql,new String[]{"id_obra"})){

                    con.setAutoCommit(false);

                    pStat.setString(1, o.getIsbn());//Consideranco que o isbn pode conter caracteres
                    pStat.setInt(2, o.getQtdExemplares());
                    pStat.setInt(3, o.getNrmEdicao());
                    pStat.setDate(4,java.sql.Date.valueOf(o.getDataPublicacao()) );
                    pStat.setString(5,o.getEditora());
                    pStat.setString(6, o.getTitulo());
                    pStat.setInt(7, o.getCategoria().getCodigoCategoria());
                    pStat.executeUpdate();

                    int id_obra = getPKObra(pStat,con);
                    o.setIdObra(id_obra);
                    salvarExemplar(con,o);

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
        String sql = "INSERT INTO exemplar(codigo_exemplar, status,numero_exemplar,id_obra)"
                + "VALUES (seq_exemplar.nextval,?,?,?)";
        
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            
            for(int i = 1; i <= o.getQtdExemplares(); i++){
                
                pStat.setString(1,"DISPONIVEL");
                pStat.setInt(2, i);
                pStat.setInt(3, o.getIdObra());
                
                pStat.executeUpdate();
            }
        }catch(SQLException erro){
            throw new RuntimeException(erro);
        }
    }
    private void salvarPc(Connection con, ObraLiteraria o){
        String sql = "INSERT INTO palavra_chave( )";
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            
        }catch(SQLException e){
            
        }
    }
       
    
    
}
