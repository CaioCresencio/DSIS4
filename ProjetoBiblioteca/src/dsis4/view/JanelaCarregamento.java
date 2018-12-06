/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import dsis4.entidades.ListaObra;
import dsis4.excecoes.ExcecaoLeitura;
import dsis4.fabrica.FabricaLeituraAbstrata;
import dsis4.util.AlgoritmoLeitura;
import dsis4.util.InsercaoBackground;
import dsis4.util.TipoArquivo;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
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
    private JPanel painelProgressao;
    private JPanel painelBotao;
    private JTextField caminho;
    private JLabel labelCaminho;
    private FabricaLeituraAbstrata fab;
    private JComboBox comboLeitor;
    private JLabel carregamento;
    
    private JProgressBar bar;
    
    private String[] arrayExtensao;
    private String nomeArquivo;
    
    
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
        
        painelProgressao = new JPanel(super.layout);
        
        carregamento = new JLabel();
        comboLeitor = new JComboBox(TipoArquivo.getValues());
        
        bar = new JProgressBar();
        bar.setValue(0);
        bar.setStringPainted(true);
        
        caminho = new JTextField(15);
        caminho.setPreferredSize(new Dimension(30,30));
        labelCaminho = new JLabel("Caminho doa arquivo:");
        
        javax.swing.border.Border border = BorderFactory.createEtchedBorder();
        painelArquivo.setBorder(border);
        painelBotao.setBorder(border);
        painelProgressao.setBorder(border);
        
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
        adicionarComponente(comboLeitor, 0,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH,painelBotao);
        adicionarComponente(painelBotao, 1,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH,painel);
        adicionarComponente(carregamento, 0,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH,painelProgressao);
        adicionarComponente(bar,0,1, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH,painelProgressao);
        adicionarComponente(painelProgressao, 2,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH,painel);
        adicionarComponente(painel, 0,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH,this);
       
    }
    private void adicionarArquivo(ActionEvent e){
        chooser = new JFileChooser();
        chooser.setDialogTitle("Procura arquivo");
        
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("xml files (*.xml) json files (*.json)","xml","json");
        chooser.setFileFilter(filtro);
        int retorno = chooser.showOpenDialog(this);
      
        if(retorno == JFileChooser.APPROVE_OPTION){
            
            caminho.setText(chooser.getSelectedFile().getPath());
            nomeArquivo = chooser.getSelectedFile().getName();
            arrayExtensao = caminho.getText().split("\\.");
        }else{
            
        }
    }
    
    
    
    private void carregarArquivo(ActionEvent e){
        fab = FabricaLeituraAbstrata.getFabrica(arrayExtensao[1]);
        AlgoritmoLeitura algo;

            
        try {
            
            algo = fab.getAlgoritmo(comboLeitor.getSelectedItem().toString(), nomeArquivo);
            int resultado = JOptionPane.showConfirmDialog(null,"Deseja gravar os dados importados no banco?");
            if(resultado == JOptionPane.YES_OPTION){
                salvar(algo);
                
            }
            
        } catch (ExcecaoLeitura ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
            
 
        
    }
    private void salvar(AlgoritmoLeitura algo){
        carregamento.setText("Salvando...");
         InsercaoBackground ib = new InsercaoBackground(carregamento,(ListaObra)algo.ler());
            ib.addPropertyChangeListener(new PropertyChangeListener(){
                @Override
                public void propertyChange(PropertyChangeEvent pce) {
                   if(pce.getPropertyName().equals("progress")){
                       bar.setValue(Integer.parseInt(pce.getNewValue().toString()));
                   }
                }
            });
            ib.execute();
    }
    
}
