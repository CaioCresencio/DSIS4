package dsis4.view;

import dsis4.entidades.ListaObra;
import dsis4.relatorioPDF.GravadorPDF;
import dsis4.xml.LeitorDOM;


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
//        
//        List<Integer> exemplares = new ArrayList(); 
//        exemplares.add(1);
//        EmprestimoDAO emp = new EmprestimoDAO();
//        emp.salvar(1710052,exemplares,1);
//            
//         DevolucaoDAO d = new DevolucaoDAO();
//         d.devolver(3,1710052);

//        JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
        
//        ManipuladorGson m = new ManipuladorGson("obra.json");;
//        ListaObra lista = m.ler(ListaObra.class);
//        m.setArquivo("obra1.json");
//        m.gravar(lista);
//        System.out.println(lista.toString());
        
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
        
        
        LeitorDOM d = new LeitorDOM("obra.xml");
       
        ListaObra lista2 = (ListaObra) d.ler();
       
        System.out.println(lista2.toString());
        
        GravadorPDF g = new GravadorPDF("relatorio.pdf");
        
        g.criaPDF(lista2);

      
    }
    
}
