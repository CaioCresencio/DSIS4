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
import javax.swing.JInternalFrame;
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
    private JLabel logo;
    private JDesktopPane desktop;
    
    private JMenuItem cadastrarObras;
    private JMenuItem carregarObras;
    private JMenuItem emprestimo;
    private JMenuItem devolucao;
    private JMenuItem consultas;
    private JMenuItem exportacao;
   
    
    public JanelaPrincipal(){
        super("--Sistema de biblioteca--");
        criarComponentes();
        configurarJanela();
    }
    private void criarComponentes(){
        
        desktop = new JDesktopPane();
       
        barra = new JMenuBar();
        menu = new JMenu("Arquivo");
        
        
        ImageIcon icon = new ImageIcon("imgs/ifsp.png");
        icon.setImage(icon.getImage().getScaledInstance(800, 458, 400));
        logo = new JLabel(icon);
        cadastrarObras = new JMenuItem("Cadastrar obras");
        carregarObras = new JMenuItem("Carregar obras");
        emprestimo = new JMenuItem("Emprestimo");
        devolucao = new JMenuItem("Devolução");
        consultas = new JMenuItem("Consultar");
        exportacao = new JMenuItem("Exportar");
        
        menu.add(cadastrarObras);
        cadastrarObras.addActionListener(this::abrirJanelaCadastro);
        menu.add(carregarObras);
        carregarObras.addActionListener(this::abrirJanelaCarregar);
        menu.add(emprestimo);
        emprestimo.addActionListener(this::abrirJanelaEmprestimo);
        menu.add(devolucao);
        devolucao.addActionListener(this::abrirJanelaDevoulcao);
        menu.add(consultas);
        consultas.addActionListener(this::abrirJanelaConsultas);
        menu.add(exportacao);
        exportacao.addActionListener(this::abrirJanelaExportacao);
        
        barra.add(menu);
        setJMenuBar(barra);
        desktop.add(logo);
        add(desktop);
      
    }
    
    public void abrirJanelaCadastro(ActionEvent e){
        JanelaCadastro jc = new JanelaCadastro();
        carregarJanela(jc);
    }
    public void abrirJanelaCarregar(ActionEvent e){
        
    }
    public void abrirJanelaEmprestimo(ActionEvent e){
        
    }
    public void abrirJanelaDevoulcao(ActionEvent e){
        
    }
    public void abrirJanelaConsultas(ActionEvent e){
        
    }
    public void abrirJanelaExportacao(ActionEvent e){
        
    }
    
    private void configurarJanela(){
        setVisible(true);
        setSize(1200,800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void carregarJanela(JInternalFrame janela){
        desktop.add(janela);
        desktop.moveToFront(menu);
    }
    
}
