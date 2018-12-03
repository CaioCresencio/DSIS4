package dsis4.view;


import dsis4.entidades.ListaObra;

import dsis4.relatorioPDF.GravadorPDF;
import dsis4.xml.LeitorDOM;

import dsis4.dao.EmprestimoDAO;
import dsis4.entidades.Exemplar;
import java.time.LocalDate;
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
        
//        ObraDAO obra = new ObraDAO();
//        List<String> autores = new ArrayList();
//        autores.add("Jovens");
//        
//        List<String> palavras = new ArrayList();
//        palavras.add("Sofrencia");
//        ObraLiteraria o = new ObraLiteraria("5354567", 2, 2, LocalDate.now(), "Tste3", "bicho",new CategoriaObra(1,"Obra literaria"), autores,palavras);
//        obra.salvar(o);
        
//        ExemplarDAO eDao = new ExemplarDAO();
//        List<Exemplar> exemplares = eDao.buscaExemplares(6);
//        List<Exemplar> exemplares1 = new ArrayList<>();
//        exemplares1.add(exemplares.get(1));
//        EmprestimoDAO emp = new EmprestimoDAO();
        //emp.salvar(1710125,exemplares1,1);
        
        
//        List<List<String>> exemplares = emp.buscaPaginada(LocalDate.now(), 1, 5);
//        
//        System.out.println(exemplares.get(1).get(2));
//            
//         DevolucaoDAO d = new DevolucaoDAO();
//         d.devolver(1,1710125);

        JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
        
                
//        LeitorSAX s = new LeitorSAX("obra.xml");
//        
//        ListaObra lista = (ListaObra) s.ler();
//        System.out.println(lista.getLista().get(0).getDataPublicacao());
//
//          ManipuladorJackson m = new ManipuladorJackson("teste.json");
//          ObraLiteraria o = new ObraLiteraria("5354567", 2, 2, LocalDate.now(), "Tste3", "bicho",new CategoriaObra(1,"Obra literaria"), null,null);
//          m.gravar(lista);
//
//          m.ler();
          
//        ManipuladorGson m2 = new ManipuladorGson("obra.json");;
//        ListaObra lista2 = m2.ler(ListaObra.class);
//        m2.setArquivo("obra1.json");
//        m2.gravar(lista2);
//        System.out.println(lista2.toString());
        
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
//        s.setArquivo("servidores2.xml");
//        s.gravarXML(lista);
        
        
//        LeitorDOM d = new LeitorDOM("obra.xml");
//       
//        ListaObra lista2 = (ListaObra) d.ler();
//       
//        System.out.println(lista2.toString());
//        
//        GravadorPDF g = new GravadorPDF("relatorio.pdf");
//        
//        g.criaPDF(lista2);

      
    }
    
}
