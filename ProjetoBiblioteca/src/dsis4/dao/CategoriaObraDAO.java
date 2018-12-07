/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.dao;

import dsis4.banco.ConexaoBD;
import dsis4.entidades.CategoriaObra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author caio
 */
public class CategoriaObraDAO {
    
    public List<CategoriaObra> getCategorias(){
        List<CategoriaObra> lista = new ArrayList<>();
        String sql = "SELECT codigo_categoria, descricao FROM categoria_literaria";
        try(Connection con = ConexaoBD.getInstance().getConnection()){
           try(PreparedStatement pStat = con.prepareStatement(sql)){
               pStat.executeUpdate();
               
               try(ResultSet rs = pStat.executeQuery()){
                    CategoriaObra catObra;
                    int codigo_categoria;
                    String descricao;
                    while(rs.next()){
                        codigo_categoria = rs.getInt("codigo_categoria");
                        descricao = rs.getString("descricao");
                        catObra = new CategoriaObra(codigo_categoria,descricao);
                        lista.add(catObra);
                    }

               }catch(SQLException erro){
                   throw new RuntimeException(erro);
               }
           }catch(SQLException erro){
               throw new RuntimeException(erro);
           }
       }catch(SQLException erro){
           throw new RuntimeException(erro);
       }
        return lista;
    }
    
    public int getCodigo(Connection con, String descricao){
        String sql = "SELECT codigo_categoria FROM categoria_literaria WHERE descricao = ?";
        int codigo = 0;
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            
            pStat.setString(1, descricao);
            pStat.executeUpdate();
            try(ResultSet rs = pStat.executeQuery()){
                if(rs.next()){
                    codigo = rs.getInt(1);
                }
            }
        }catch(SQLException erro){
            throw new RuntimeException(erro);
        }
        return codigo;
    }
    
}
