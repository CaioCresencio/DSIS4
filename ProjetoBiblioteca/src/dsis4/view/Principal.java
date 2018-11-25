package dsis4.view;


import dsis4.dao.ObraDAO;
import dsis4.entidades.CategoriaObra;
import dsis4.entidades.ObraLiteraria;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


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
        
        ObraDAO obra = new ObraDAO();
        List<String> autores = new ArrayList();
        autores.add("euton");
        
        List<String> palavras = new ArrayList();
        palavras.add("BD");
        ObraLiteraria o = new ObraLiteraria("221eeaeae31233213", 2, 2, LocalDate.now(), "Castanha", "JAsvaldo",new CategoriaObra(1,"Obra literaria"), autores,palavras);
        obra.salvar(o);


        //JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
        
//        LeitorSAX s = new LeitorSAX("obra.xml");
//        
//        ListaObra lista = (ListaObra) s.ler();
//        
//        System.out.println(lista.toString());
//        for( ObraLiteraria o : lista.getLista()){
//            System.out.println(o.getCategoria());
//            System.out.println("\n"+ o.getTitulo());
//        }
        
//        LeitorJAXB s = new LeitorJAXB("obra.xml");
//        
//        s.setClasses(ListaObra.class);
//        
//        ListaObra lista = (ListaObra) s.ler();
//        
//        lista.getLista().get(0).getEditora();
//        
//        for( ObraLiteraria o : lista.getLista()){
//            System.out.println(o.getCategoria());
//            System.out.println("\n"+ o.getTitulo());
//        }
//        
        
//       LeitorDOM d = new LeitorDOM("obra.xml");
//       
//       ListaObra lista2 = (ListaObra) d.ler();
//       
//        System.out.println(lista2.getLista().get(0).getIsbn());
    }
    
}
