/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.dao;

import dsis4.banco.ConexaoBD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
      
    public List<List<String>> buscaPaginada(LocalDate data, int min, int max){
        ConexaoBD conexao = ConexaoBD.getInstance();
        String query = "select data_emp, data_dev, titulo_obra , nrm_edicao, codigo_exemplar , descricao, nome\n" +
                        "from emprestimo e\n" +
                        "join exemplar \n" +
                        "using(codigo_exemplar)\n" +
                        "join obra_literaria\n" +
                        "using(id_obra), (select l.nome, c.descricao \n" +
                        "                 from leitor l\n" +
                        "                 join emprestimo\n" +
                        "                 using(prontuario_leitor) \n" +
                        "                 join categoria_leitor c\n" +
                        "                 using(codigo_categoria))\n" +
                        "where e.data_dev > ? or\n" +
                        "e.status = 'EM ANDAMENTO'";
        
        String sql = String.format("select t.* from (select e.*, rownum rnum from (%s)e where rownum <= ?)t where rnum >= ?", query);
                            
        
        List<List<String>> lista = new ArrayList<>();      
        
        try (
            Connection con = conexao.getConnection();
            PreparedStatement pStat = con.prepareStatement(sql))
        {      
            pStat.setDate(1, Date.valueOf(data));
            pStat.setInt(2, max);
            pStat.setInt(3, min);
            
            try(ResultSet rs = pStat.executeQuery()) {
                while(rs.next()) {
                    List<String> dados = new ArrayList<>();
                    String nome, titulo, nro_ed, descricao, cod_exemplar, data_emp, data_dev;
                    
                    data_emp = String.valueOf(rs.getDate(1));
                    dados.add(data_emp);
                    data_dev = String.valueOf(rs.getDate(2));
                    dados.add(data_dev);
                    titulo = rs.getString(3);
                    dados.add(titulo);
                    nro_ed = String.valueOf(rs.getInt(4));
                    dados.add(nro_ed);
                    cod_exemplar = String.valueOf(rs.getInt(5));
                    dados.add(cod_exemplar);
                    descricao = rs.getString(6);
                    dados.add(descricao);
                    nome = rs.getString(7);
                    dados.add(nome);
                    
                    lista.add(dados);
                }

                return lista;
            }
        }
        catch(SQLException erro) {
            throw new RuntimeException(erro);
        }
    } 
}
