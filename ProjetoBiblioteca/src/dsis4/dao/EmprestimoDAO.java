/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.dao;

import dsis4.banco.ConexaoBD;
import dsis4.entidades.Exemplar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author caio
 */
public class EmprestimoDAO {
    
    public void salvar(int prontuarioL, List<Exemplar> exemplares, int prontuario_func){
        String sql = "INSERT INTO emprestimo (codigo_emp,data_dev,data_emp,status,codigo_exemplar,prontuario_leitor,prontuario_func)" +
                        "VALUES (seq_emprestimo.nextval,?,?,'EM ANDAMENTO',?,?,?)";
        
        try(Connection con = ConexaoBD.getInstance().getConnection()){
            if(verificarEmprestimo(con, prontuarioL, exemplares) ){
                LocalDate dataAtual = LocalDate.now();
                LocalDate dataDev = dataAtual.plusDays(Long.valueOf(tempoEmprestimo(con, prontuarioL)));
                
                try(PreparedStatement pStat = con.prepareStatement(sql)){
                    con.setAutoCommit(false);
                    
                    for(Exemplar e : exemplares){
                        //System.out.println(e);
                        pStat.setDate(1, java.sql.Date.valueOf(dataDev));
                        pStat.setDate(2, java.sql.Date.valueOf(dataAtual));
                        pStat.setInt(3, e.getId());
                        pStat.setInt(4, prontuarioL);
                        pStat.setInt(5, prontuario_func);
                        pStat.executeUpdate();
                        
                        alteraStatusExemplar(con,e.getId());
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
    private boolean verificarEmprestimo(Connection con, int prontuario, List<Exemplar> exemplares){
        boolean permicao = false;
        
        if(!leitorBloqueado(con, prontuario) && (exemplares.size() + emprestimosEmAndamento(con, prontuario)) <= 3  ){
            //EXEMPLARES DIFERENTES ENTRE SI
            Map <Integer, Integer> m = new HashMap<>();
            for(Exemplar e : exemplares ){
                int i = e.getId();
                if(m.get(i) == null){
                    m.put(i, i);
                }
            }
            boolean possuiIgual = false;
        
            if( m.size() == exemplares.size()){
                for(Exemplar e: exemplares){
                    if(confereDuplicidadeExemplar(con,e.getId(),prontuario) || confereDuplicidadeNalista(exemplares)){
                        possuiIgual = true;
                        System.out.println("Exemplar da mesma obra");
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
                     "JOIN emprestimo e USING(codigo_exemplar) WHERE prontuario_leitor = ?" +
                     "AND id_obra =  (SELECT id_obra FROM obra_literaria JOIN exemplar USING (id_obra) WHERE codigo_exemplar = ?)"+
                     "AND e.status = 'EM ANDAMENTO'";
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
    
    private boolean confereDuplicidadeNalista(List<Exemplar> exemplares){

        boolean retorno = false;
        
        for(int i = 0; i<exemplares.size()&&!retorno; i++){
            for(int j = i+1; j<exemplares.size()&&!retorno; i++){
                if(exemplares.get(i).getId_obra() == exemplares.get(j).getId_obra()){
                    retorno = true;
                }  
            }
        }
         
        return retorno;
    }
      
    public List<List<String>> relatorioPendentes(LocalDate data, int min, int max){
        ConexaoBD conexao = ConexaoBD.getInstance();
        String query = "select data_emp, data_dev, titulo_obra , nrm_edicao, codigo_exemplar, prontuario_leitor\n" +
                        "from emprestimo e\n" +
                        "join exemplar \n" +
                        "using(codigo_exemplar)\n" +
                        "join obra_literaria\n" +
                        "using(id_obra)"+
                        "where (e.data_dev < ? and e.status = 'EM ANDAMENTO') or\n" +
                        "e.status = 'EM ANDAMENTO'";
        
        String sql = String.format("select t.* from (select e.*, rownum rnum from (%s)e where rownum <= ?)t where rnum >= ?", query);
                              
        List<List<String>> lista = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
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
                    List<String> aux = new ArrayList<>();
                    
                    data_emp = rs.getDate(1).toLocalDate().format(formatter);
                    dados.add(data_emp);
                    data_dev = rs.getDate(2).toLocalDate().format(formatter);
                    dados.add(data_dev);
                    titulo = rs.getString(3);
                    dados.add(titulo);
                    nro_ed = String.valueOf(rs.getInt(4));
                    dados.add(nro_ed);
                    cod_exemplar = String.valueOf(rs.getInt(5));
                    dados.add(cod_exemplar);
                    
                    aux = recuperaLeitor(con, rs.getInt(6));
                    
                    nome = aux.get(0);
                    dados.add(nome); 
                    descricao = aux.get(1);
                    dados.add(descricao);

                    lista.add(dados);
                }

                return lista;
            }
        }
        catch(SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    private List<String> recuperaLeitor(Connection con, int prontuario){
        String sql = "select l.nome, c.descricao \n" +
                     "from leitor l    \n" +
                     "join categoria_leitor c\n" +
                     "using(codigo_categoria)\n" +
                     "where l.prontuario_leitor = ?";
        List<String> leitores = new ArrayList<>();
       
        try(PreparedStatement pStat = con.prepareStatement(sql)){
            pStat.setInt(1, prontuario);
            pStat.executeUpdate();
            try(ResultSet rs = pStat.executeQuery()){
                if(rs.next()){
                    leitores.add(rs.getString(1));
                    leitores.add(rs.getString(2));
                }
                
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return leitores;
    }
    
    
    public int buscaTotalPendentes(){
        ConexaoBD conexao = ConexaoBD.getInstance();
        String sql = "select count(*)\n" +
                     "from emprestimo e\n" +
                     "where (e.data_dev < SYSDATE and e.status = 'EM ANDAMENTO') or\n" +
                     "e.status = 'EM ANDAMENTO'";
        int qtd = 0;
        
        try (
            Connection con = conexao.getConnection();
            PreparedStatement pStat = con.prepareStatement(sql))
        {
            
                
            pStat.executeUpdate();
            try(ResultSet rs = pStat.executeQuery()){
                if(rs.next()){
                    qtd = rs.getInt(1);
                }
                return qtd;
            }

            }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    
    public int getCodigoEmprestimo(int prontuario, int exemplar){
        ConexaoBD conexao = ConexaoBD.getInstance();
        String sql = "SELECT codigo_emp FROM emprestimo WHERE status = 'EM ANDAMENTO' AND codigo_exemplar = ? AND" +
                     "prontuario_leitor = ?";
        int codEmp = 0;
        try (
            Connection con = conexao.getConnection();
            PreparedStatement pStat = con.prepareStatement(sql))
        {
            pStat.setInt(1, exemplar);
            pStat.setInt(2, prontuario);
            
            try(ResultSet rs = pStat.executeQuery()){
                if(rs.next()){
                    codEmp = rs.getInt(1);
                }
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return codEmp;
        
    }
    
    public List<Exemplar> getListExemplares(){
        List<Exemplar> listaExemplar = new ArrayList<>();
        String sql = "SELECT ";
        try(Connection con = ConexaoBD.getInstance().getConnection();
            PreparedStatement pStat = con.prepareStatement(sql)){
            pStat.executeUpdate();
            try(ResultSet rs = pStat.executeQuery()){
                
            }catch(SQLException e){
                
            }
        }catch(SQLException e){
            
        }
        return listaExemplar;
    }
    
}
