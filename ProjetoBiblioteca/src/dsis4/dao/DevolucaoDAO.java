/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.dao;

import dsis4.banco.ConexaoBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;


/**
 *
 * @author caio
 */
public class DevolucaoDAO {
    
    public void devolver(int exemplar,int prontuario_l){
        
        
        try(Connection con = ConexaoBD.getInstance().getConnection()){
            con.setAutoCommit(false);
            if(estaAtrasado(con, exemplar)){
                    if(emprestimosEmAndamento(con, exemplar) > 0){
                        bloqueiaUsuario(con, exemplar);
                        
                    }else{
                        desbloquearUsuario(con, exemplar);
                        
                    }
            }
            devolverExemplar(con, exemplar);
            con.commit();
            System.out.println("Devolvido com sucesso!");
             
         }catch(SQLException e){
             throw new RuntimeException(e);
         }
    }
    
    private void devolverExemplar(Connection con, int exemplar){
        String sql_exemplar = "UPDATE exemplar SET status = 'DISPONIVEL' WHERE status = 'EMPRESTADO' AND codigo_exemplar = ?";
        String sql_emprestimo = "UPDATE emprestimo SET status = 'CONCLUIDO' WHERE status = 'EM ANDAMENTO' AND codigo_exemplar = ? ";
         try(PreparedStatement pStat = con.prepareStatement(sql_exemplar)){
            pStat.setInt(1, exemplar);
            pStat.executeUpdate();
            
            try(PreparedStatement pStat2 = con.prepareStatement(sql_emprestimo)){
                pStat2.setInt(1, exemplar);
                pStat2.executeUpdate();
                System.out.println("eita");
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
                
           
        }catch(SQLException e){
            throw new RuntimeException(e);
        } 
    }
    
    private void bloqueiaUsuario(Connection con, int exemplar){
        String sql = "UPDATE  leitor SET status  = 'BLOQUEADO' WHERE prontuario_leitor = " +
                    "(SELECT prontuario_leitor FROM leitor JOIN emprestimo USING(prontuario_leitor) WHERE codigo_exemplar = ?)";
         try(PreparedStatement pStat = con.prepareStatement(sql)){
            
            pStat.setInt(1, exemplar);
            pStat.executeUpdate();
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        } 
    }
    
    private void desbloquearUsuario(Connection con, int exemplar){
        String sql = "UPDATE  leitor SET status  = 'BLOQUEADO' WHERE prontuario_leitor = " +
                    "(SELECT prontuario_leitor FROM leitor JOIN emprestimo USING(prontuario_leitor) WHERE codigo_exemplar = ?)";
         try(PreparedStatement pStat = con.prepareStatement(sql)){
            
            pStat.setInt(1, exemplar);
            pStat.executeUpdate();
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        } 
    }
    
    private boolean estaAtrasado(Connection con, int exemplar){
        boolean retorno = false;
        Period periodo = Period.between(procuraDataEmprestimo(con, exemplar),LocalDate.now());
        if(periodo.getDays() > 0 || periodo.getMonths() > 0 ){
            retorno = true;
        }
        return retorno;
    }
    private LocalDate procuraDataEmprestimo(Connection con, int exemplar){
        String sql = "SELECT data_emp FROM emprestimo WHERE codigo_exemplar = ?";
        LocalDate retorno = null;
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            
            pStat.setInt(1, exemplar);
            pStat.executeUpdate();
            
            try(ResultSet rs = pStat.executeQuery()){
                if(rs.next()){
                    retorno = rs.getDate(1).toLocalDate();
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return retorno;
    }
    
    private int emprestimosEmAndamento(Connection con, int exemplar){
        String sql = "SELECT COUNT(codigo_emp) FROM emprestimo WHERE status = 'EM ANDAMENTO' AND codigo_exemplar <>  ? AND prontuario_leitor = " +
"            (SELECT prontuario_leitor FROM emprestimo  WHERE codigo_exemplar = ?) ";
        int qtd = 0;
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            pStat.setInt(1, exemplar);
            pStat.setInt(2, exemplar);
            pStat.executeUpdate();
            try(ResultSet rs = pStat.executeQuery()){
                if(rs.next()){
                    qtd = rs.getInt(1);
                }
            }catch(SQLException e){
               throw new RuntimeException(e);
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return qtd;
        
    }
    
}
