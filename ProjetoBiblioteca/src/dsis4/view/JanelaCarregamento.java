/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author caio
 */
public class JanelaCarregamento extends JanelaPadrao{
    
    private JFileChooser chooser;
    private JButton botaoCaminho;
    private JButton botaoCarregar;
    private JPanel painel;
    private JPanel painelArquivo;
    private JPanel painelBotao;
    private JTextField caminho;
    private JLabel labelCaminho;
    
    
    public JanelaCarregamento(String titulo){
        super(titulo);
        carregarJanela();
        configurarJanela();
    }
    public void configurarJanela(){
        pack();
    }
    public void carregarJanela(){
        painel = new JPanel(super.layout);
        painelArquivo = new JPanel(super.layout);
        painelBotao = new JPanel(super.layout);
        
        caminho = new JTextField(15);
        caminho.setPreferredSize(new Dimension(30,30));
        labelCaminho = new JLabel("Caminho doa arquivo:");
        
        javax.swing.border.Border border = BorderFactory.createEtchedBorder();
        painelArquivo.setBorder(border);
        painelBotao.setBorder(border);
        
        ImageIcon icon = new ImageIcon("imgs/icon_pesquisa.png");
        botaoCaminho = new JButton("Procurar",icon);
        botaoCaminho.addActionListener(this::adicionarArquivo);
         
        ImageIcon icon_c = new ImageIcon("imgs/icon_pesquisa.png");
        botaoCarregar = new JButton("Carregar",icon_c);
        botaoCarregar.addActionListener(this::carregarArquivo);
               
        adicionarComponente(labelCaminho, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, painelArquivo);
        adicionarComponente(caminho, 0,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, painelArquivo);
        adicionarComponente(botaoCaminho ,0,2, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, painelArquivo);
        adicionarComponente(painelArquivo, 0,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH,painel);
        adicionarComponente(botaoCarregar, 1,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH,painelBotao);
        adicionarComponente(painelBotao, 1,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH,painel);
        adicionarComponente(painel, 0,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH,this);
        
    }
    private void adicionarArquivo(ActionEvent e){
        chooser = new JFileChooser();
        chooser.setDialogTitle("Procura arquivo");
        
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("xml files (*.xml) json files (*.json)","xml","json");
        chooser.setFileFilter(filtro);
        int retorno = chooser.showOpenDialog(this);
      
        System.out.println(chooser.getDialogTitle());
        System.out.println(chooser.getSelectedFile());
        if(retorno == JFileChooser.APPROVE_OPTION){
            System.out.println("Pego!");
            caminho.setText(chooser.getSelectedFile().getPath());
        }else{
            
        }
    }
    private void carregarArquivo(ActionEvent e){
        
    }
    
}
