package dsis4.view;



import dsis4.entidades.ListaObra;
import dsis4.fabrica.FabricaGravacaoAbstrata;
import dsis4.fabrica.FabricaLeituraAbstrata;
import javax.swing.SwingUtilities;




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
       //EmprestimoDAO emp = new EmprestimoDAO();
        //emp.salvar(1710125,exemplares1,1);
        
        
       //List<List<String>> exemplares = emp.relatorioPendentes(LocalDate.now(), 1, 5);
       //List<String> exemplares = emp.relatorioPendentes(LocalDate.now(), 1, 5)
//        
        //System.out.println(exemplares.get(1).get(2));
//            
//         DevolucaoDAO d = new DevolucaoDAO();
//         d.devolver(1,1710125);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JanelaPrincipal janela = new JanelaPrincipal();
            }
        });
        
         /*FabricaLeituraAbstrata fab = FabricaLeituraAbstrata.getFabrica("JSON");
         ListaObra lista  = (ListaObra)fab.getAlgoritmo("GSON","exportacao.json").ler();
         System.out.println(lista.toString());
         
         FabricaGravacaoAbstrata fab2 = FabricaGravacaoAbstrata.getFabrica("JSON");
         fab2.getAlgoritmo("JACKSON", "teste.json").gravar(lista);
//        LeitorSAX s = new LeitorSAX("obra.xml");*/
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
