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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author caio
 */
public class EmprestimoDAO {
    
    public void salvar(int prontuarioL, List<Integer> exemplares, int prontuario_func){
        String sql = "INSERT INTO emprestimo (codigo_emp,data_dev,data_emp,status,codigo_exemplar,prontuario_leitor,prontuario_func)" +
                        "VALUES (seq_emprestimo.nextval,?,?,'EM ANDAMENTO',?,?,?)";
        
        try(Connection con = ConexaoBD.getInstance().getConnection()){
            if(verificarEmprestimo(con, prontuarioL, exemplares) ){
                LocalDate dataAtual = LocalDate.now();
                LocalDate dataDev = dataAtual.plusDays(Long.valueOf(tempoEmprestimo(con, prontuarioL)));
                
                try(PreparedStatement pStat = con.prepareStatement(sql)){
                    con.setAutoCommit(false);
                    
                    for(Integer e : exemplares){
                        System.out.println(e);
                        pStat.setDate(1, java.sql.Date.valueOf(dataDev));
                        pStat.setDate(2, java.sql.Date.valueOf(dataAtual));
                        pStat.setInt(3, e);
                        pStat.setInt(4, prontuarioL);
                        pStat.setInt(5, prontuario_func);
                        pStat.executeUpdate();
                        
                        alteraStatusExemplar(con,e);
                    }
                     
                    
                    con.commit();

                    System.out.println("Salvo com sucesso!");
                }catch(SQLException erro){
                    con.rollback();
                    throw new RuntimeException(erro);
                }
            }else{
                System.out.println("Erro");
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
     
    }
    
    private void alteraStatusExemplar(Connection con, int exemplar){
        String sql = "UPDATE exemplar SET status = 'EMPRESTADO' WHERE codigo_exemplar = ?";
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            pStat.setInt(1, exemplar);
            pStat.executeUpdate();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }    
         
    }
    private boolean verificarEmprestimo(Connection con, int prontuario,List<Integer> exemplares){
        boolean permicao = false;
        
        if(!leitorBloqueado(con, prontuario) && (exemplares.size() + emprestimosEmAndamento(con, prontuario)) <= 3  ){
            //EXEMPLARES DIFERENTES ENTRE SI
            Map <Integer, Integer> m = new HashMap<>();
            for(Integer e : exemplares ){
                int i = e;
                if(m.get(i) == null){
                    m.put(i, i);
                }
            }
            boolean possuiIgual = false;
            System.out.println("e");
            if( m.size() == exemplares.size()){
                for(Integer e: exemplares){
                    if(confereDuplicidadeExemplar(con,e,prontuario)){
                        possuiIgual = true;
                        System.out.println("eu");
                    }
                }
                if(possuiIgual == false){
                    permicao = true;
                }
            }
        }
        return permicao;
    }
    private boolean leitorBloqueado(Connection con,int prontuario){
        String sql = "SELECT status FROM leitor WHERE prontuario_leitor = ?";
        boolean retorno = true;
        String status = "";
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            pStat.setInt(1,prontuario);
            pStat.executeUpdate();
            
            
            try(ResultSet rs = pStat.executeQuery()){
                if(rs.next()){
                    status = rs.getString(1);
                }
                if(status.equals("DISPONIVEL")){
                    retorno = false;
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return retorno;
    }
    private int tempoEmprestimo(Connection con, int prontuario ){
        String sql = "SELECT tempo_emprestimo FROM categoria_leitor JOIN leitor USING(codigo_categoria)" +
                     "WHERE prontuario_leitor = ?";
        int tempo = 0;
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            pStat.setInt(1, prontuario);
            pStat.executeUpdate();
            try(ResultSet rs = pStat.executeQuery()){
                if(rs.next()){
                    tempo = rs.getInt(1);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return tempo;
        
    }
    
    private int emprestimosEmAndamento(Connection con,int  prontuario_l){
        String sql = "SELECT COUNT(codigo_emp) FROM emprestimo WHERE status = 'EM ANDAMENTO' AND prontuario_leitor =  ?";
        int qtd = 0;
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            pStat.setInt(1, prontuario_l);
            pStat.executeUpdate();
            try(ResultSet rs = pStat.executeQuery()){
                if(rs.next()){
                    qtd = rs.getInt(1);
                }
            }
            
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return qtd;
        
    }
    private boolean confereDuplicidadeExemplar(Connection con, int exemplar, int prontuario){
        String sql = "SELECT COUNT(codigo_emp) FROM obra_literaria JOIN exemplar USING(id_obra)" +
                     "JOIN emprestimo USING(codigo_exemplar) WHERE prontuario_leitor = ?" +
                     "AND id_obra =  (SELECT id_obra FROM obra_literaria JOIN exemplar USING (id_obra) WHERE codigo_exemplar = ?)";
        int qtd = 0;
        boolean retorno = false;
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            pStat.setInt(1, prontuario);
            pStat.setInt(2, exemplar);
            pStat.executeUpdate();
            try(ResultSet rs = pStat.executeQuery()){
                if(rs.next()){
                    qtd = rs.getInt(1);
                    if(qtd > 0 ){
                        retorno = true;
                    }
                }
            }catch(SQLException e){
                throw new RuntimeException(e);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return retorno;
    }
      
    
      
}
