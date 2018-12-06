package dsis4.util;

import dsis4.dao.ObraDAO;
import dsis4.entidades.ListaObra;
import dsis4.entidades.ObraLiteraria;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author caio
 */

public class InsercaoBackground extends SwingWorker<Integer,String>{
    private JLabel label;
    private ListaObra lista;
    
    public InsercaoBackground(JLabel label, ListaObra lista){
        this.label = label;
        this.lista = lista;
    }
    @Override
    protected Integer doInBackground() throws Exception {
        ObraDAO oDao = new ObraDAO();
        try{
            int i = 0;
            for(ObraLiteraria o : lista.getLista()){
                oDao.salvar(o);
                i++;
                publish("Obras cadastradas: "+i);
                setProgress(100*(i)/lista.getLista().size());
            }
           
        }catch(Exception e ){
            e.printStackTrace();
            throw new Exception(e);
        }
        return lista.getLista().size();
    }
    @Override
    protected void done(){
        try{
            label.setText("Salvo com sucesso!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void process(List<String> lista){
        System.out.println(lista);
    }
    
}