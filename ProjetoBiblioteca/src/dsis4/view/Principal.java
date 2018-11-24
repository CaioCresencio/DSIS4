package dsis4.view;

import dsis4.entidades.CategoriaObra;
import dsis4.entidades.ListaObra;
import dsis4.xml.LeitorJAXB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author caio
 */
public class Principal {
    public static void main(String[] args) {
        
        /*ObraDAO obra = new ObraDAO();
        List<String> autores = new ArrayList();
        autores.add("euton");
        ObraLiteraria o = new ObraLiteraria("23311", 2, 2, LocalDate.now(), "Castanha", "JAsvaldo", 2, autores);
        obra.salvar(o);*/
        //JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
        
        /*LeitorSAX s = new LeitorSAX("obra.xml");
        
        ListaObra lista = (ListaObra) s.ler();
        
        System.out.println(lista);*/
        
        LeitorJAXB s = new LeitorJAXB("obra.xml");
        
        s.setClasses(ListaObra.class);
        
        ListaObra lista = (ListaObra) s.ler();
        
        System.out.println(lista);
        
        
    }
    
}
