/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.dao;

import dsis4.banco.ConexaoBD;
import dsis4.entidades.Leitor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author caio
 */
public class EmprestimoDAO {
    
    public void salvar(Leitor l){
        String sql = "INSERT INTO ";
        
        try(Connection con = ConexaoBD.getInstance().getConnection()){
           
            try(PreparedStatement pStat = con.prepareStatement(sql)){

                con.setAutoCommit(false);
                
                tempoEmprestimo(con, l);
                con.commit();

                System.out.println("Salvo com sucesso!");
            }catch(SQLException erro){
                con.rollback();
                throw new RuntimeException(erro);
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
      
    }
    private int tempoEmprestimo(Connection con,Leitor l ){
        String sql = "select tempo_emprestimo from categoria_leitor WHERE codigo_categoria = ?";
        
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            //pStat.setInt(1, );
        }catch(SQLException e){
            
        }
        return 1;
        
    }
      
    
      
}
