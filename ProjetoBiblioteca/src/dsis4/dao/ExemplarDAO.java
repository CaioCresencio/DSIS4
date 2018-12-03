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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Windows
 */
public class ExemplarDAO {
    public void salvar(int codigoObra, int numero, Connection con){
        String sql = "insert into exemplar(codigo_exemplar, status, numero_exemplar, id_obra) values(seq_exemplar.nextval, ?, ?, ?)";
        try
        {
            try(PreparedStatement pStat = con.prepareStatement(sql)){
                pStat.setString(1, "DISPONIVEL");
                pStat.setInt(3, codigoObra);
                pStat.setInt(2, numero);
                pStat.executeUpdate();

            }catch (RuntimeException erro){
                throw new RuntimeException(erro);
            }
        }
        catch(SQLException erro) {
            throw new RuntimeException(erro);
        }   
    }
    
    public List<Exemplar> buscaExemplares(int codigoObra){
        String sql = "select codigo_exemplar, status, numero_exemplar, id_obra from exemplar where id_obra = ?";
        List<Exemplar> exemplares = new ArrayList<>();
        try(                
                Connection con = ConexaoBD.getInstance().getConnection();
                PreparedStatement pStat = con.prepareStatement(sql);
            ){
            pStat.setInt(1, codigoObra);
            
            try(ResultSet rs = pStat.executeQuery()){
                while(rs.next()){
                    int id = rs.getInt(1);
                    boolean disponivel = rs.getString(2).equals("DISPONIVEL");
                    int numero = rs.getInt(3);
                    int id_obra = rs.getInt(4);
                    Exemplar e = new Exemplar(id,disponivel, numero, id_obra);
                    
                    exemplares.add(e);
                }
                
                return exemplares;
            }
        }catch(SQLException erro){
            throw new RuntimeException(erro);
        }
    }
       
    public int totalDisponiveis(int codigoObra){
        String sql = "select count(*) from exemplar where status = 'DISPONIVEL' and id_obra = ?";
        ConexaoBD conexao = ConexaoBD.getInstance();
        int qtdDisponivel = 0;
        try (
                Connection con = conexao.getConnection();
                PreparedStatement pStat = con.prepareStatement(sql))
        {
            pStat.setInt(1, codigoObra);
            ResultSet rs = pStat.executeQuery();
            
            rs.next();
            qtdDisponivel = rs.getInt(1);
            return qtdDisponivel;
        }
        catch(SQLException erro) {
            throw new RuntimeException(erro);
        }
    }
    
    
}
