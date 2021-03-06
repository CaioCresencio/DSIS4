/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 *
 * @author caio
 */
public class JanelaPrincipal extends JFrame{
    
    private JMenuBar barra;
    private JMenu menu;
    private JMenu menuArquivos;
    private JLabel logo;
    private JDesktopPane desktop;
    
    private JMenuItem cadastrarObras;
    private JMenuItem carregarObras;
    private JMenuItem emprestimo;
    private JMenuItem devolucao;
    private JMenuItem consultas;
    private JMenuItem exportacao;
    private JMenuItem carregarArquivo;
    private JMenuItem relatorioPendente;
   
    
    public JanelaPrincipal(){
        super("--Sistema de biblioteca--");
        criarComponentes();
        configurarJanela();
    }
    private void criarComponentes(){
        
        desktop = new JDesktopPane();
       
        barra = new JMenuBar();
        menu = new JMenu("Arquivo");
        menuArquivos = new JMenu("Ler/Exportar");
        
        
        ImageIcon icon = new ImageIcon("imgs/ifsp.png");
        icon.setImage(icon.getImage().getScaledInstance(800, 458, 400));
        logo = new JLabel(icon);
        cadastrarObras = new JMenuItem("Cadastrar obras");
        carregarObras = new JMenuItem("Carregar obras");
        emprestimo = new JMenuItem("Emprestimo");
        devolucao = new JMenuItem("Devolução");
        consultas = new JMenuItem("Consultar");
        exportacao = new JMenuItem("Exportar");
        carregarArquivo = new JMenuItem("Carregar arquivo!");
        relatorioPendente = new JMenuItem("Relatorio pendentes");
        
        menu.add(cadastrarObras);
        cadastrarObras.addActionListener(this::abrirJanelaCadastro);
        cadastrarObras.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        menu.add(emprestimo);
        emprestimo.addActionListener(this::abrirJanelaEmprestimo);
        emprestimo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        menu.add(devolucao);
        devolucao.addActionListener(this::abrirJanelaDevoulcao);
        devolucao.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
       
        menuArquivos.add(exportacao);
        exportacao.addActionListener(this::abrirJanelaExportacao);
        exportacao.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuArquivos.add(carregarObras);
        carregarObras.addActionListener(this::abrirJanelaCarregar);
        carregarObras.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
 
        menu.add(relatorioPendente);
        relatorioPendente.addActionListener(this::abrirJanelaRelatorio);
        relatorioPendente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        
        
        barra.add(menu);
        barra.add(menuArquivos);
        setJMenuBar(barra);
        desktop.add(logo);
        add(desktop);
      
    }
    
    public void abrirJanelaCadastro(ActionEvent e){
        JanelaCadastro jc = new JanelaCadastro("Janela de cadastro");
        carregarJanela(jc);
    }
    public void abrirJanelaCarregar(ActionEvent e){
        JanelaCarregamento jc = new JanelaCarregamento("Carregamento");
        carregarJanela(jc);
    }
    public void abrirJanelaEmprestimo(ActionEvent e){
        JanelaEmprestimo je = new JanelaEmprestimo("Empréstimo de obras");
        carregarJanela(je);
    }
    public void abrirJanelaDevoulcao(ActionEvent e){
        JanelaDevolucao jd = new JanelaDevolucao("Devolucao de obras");
        carregarJanela(jd);
    }
    public void abrirJanelaExportacao(ActionEvent e){
        JanelaExportacao je = new JanelaExportacao("---Exportação---");
        carregarJanela(je);
    }
//    public void abrirJanelaArquivo(ActionEvent e){
//        JanelaArquivos jXML = new JanelaArquivos("Carregamento de arquivo");
//        carregarJanela(jXML);
//    }
    
    public void abrirJanelaRelatorio(ActionEvent e){
        JanelaRelatorio jr = new JanelaRelatorio("Janela de Relatorio");
        carregarJanela(jr);
    }
    
    private void configurarJanela(){
        setVisible(true);
        setSize(1200,1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void carregarJanela(JanelaPadrao janela){
        desktop.add(janela);
        desktop.moveToFront(janela);
    }
    
}
