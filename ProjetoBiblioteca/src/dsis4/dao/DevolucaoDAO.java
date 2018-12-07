/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.dao;

import dsis4.banco.ConexaoBD;
import dsis4.entidades.Exemplar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;


/**
 *
 * @author caio
 */
public class DevolucaoDAO {
    
    public void geraDevolucao(List<Exemplar> exemplares, int prontuario_l){
    
        for(Exemplar e : exemplares){
            devolver(e.getId(), prontuario_l);
        }
    
    }

    private void devolver(int exemplar,int prontuario_l){

        try(Connection con = ConexaoBD.getInstance().getConnection()){
            con.setAutoCommit(false);
            if(existeEmprestimo(con, exemplar, prontuario_l)){
                if(estaAtrasado(con, exemplar)){
                    if(emprestimosEmAndamento(con, exemplar, prontuario_l ) > 0){
                        bloqueiaUsuario(con, exemplar);                       
                    }else{
                        desbloquearUsuario(con, exemplar);     
                    }
                }
                EmprestimoDAO e = new EmprestimoDAO();
                devolverExemplar(con, exemplar, prontuario_l, e.getCodigoEmprestimo(prontuario_l, exemplar));
                con.commit();
                System.out.println("Devolvido com sucesso!");
            }else{
                System.out.println("Emprestimo inexistente!");
            }
            
             
         }catch(SQLException e){
             throw new RuntimeException(e);
         }
    }
    
    private void devolverExemplar(Connection con, int exemplar, int prontuario_l, int cod_emprestimo){
        String sql_exemplar = "UPDATE exemplar SET status = 'DISPONIVEL' WHERE status = 'EMPRESTADO' AND codigo_exemplar = ?";
        String sql_emprestimo = "UPDATE emprestimo SET status = 'CONCLUIDO' WHERE status = 'EM ANDAMENTO' AND codigo_exemplar = ? ";
        String sql_devolucao = "INSERT INTO devolucao values(seq_dev.nextval, SYSDATE, ?, ?, ?)";
         try(PreparedStatement pStat = con.prepareStatement(sql_exemplar)){
            pStat.setInt(1, exemplar);
            pStat.executeUpdate();
            
            try(PreparedStatement pStat2 = con.prepareStatement(sql_emprestimo)){
                pStat2.setInt(1, exemplar);
                pStat2.executeUpdate();
              
                try(PreparedStatement pStat3 = con.prepareStatement(sql_devolucao)){
                    pStat3.setInt(1, cod_emprestimo);
                    pStat3.setInt(2, exemplar);
                    pStat3.setInt(3, prontuario_l);
                    pStat3.executeUpdate();
                
                }
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
    
    private boolean existeEmprestimo(Connection con, int exemplar, int prontuario_l){
        String sql = "SELECT data_emp FROM emprestimo WHERE codigo_exemplar = ? AND status = 'EM ANDAMENTO' AND prontuario_leitor = ?";
        LocalDate retorno = null;
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            
            pStat.setInt(1, exemplar);
            pStat.setInt(2, prontuario_l);
            pStat.executeUpdate();
            
            try(ResultSet rs = pStat.executeQuery()){       
                return rs.next();  
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    private LocalDate procuraDataEmprestimo(Connection con, int exemplar){
        String sql = "SELECT data_emp FROM emprestimo WHERE codigo_exemplar = ? AND status = 'EM ANDAMENTO'";
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
    
    private int emprestimosEmAndamento(Connection con, int exemplar, int prontuario_l){
        String sql = "SELECT COUNT(codigo_emp) FROM emprestimo WHERE status = 'EM ANDAMENTO' AND codigo_exemplar =  ? AND prontuario_leitor = ?";
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
