/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;


import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author caio
 */
public abstract class JanelaPadrao extends JInternalFrame{
   
    
    protected GridBagLayout layout;
    protected GridBagConstraints constraints;
    //categoria
    
    public JanelaPadrao(String titulo){
        super(titulo,true,true,true,true);
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        configurarJanela();
    }

    
    public void adicionarComponente(JComponent component, int linha, int coluna, int posicao, int colunas, int linhas, int preenche, Container painel){
        constraints.gridy = linha;
        constraints.gridx = coluna;
        constraints.insets = new Insets(10,10,10,10);
        
        constraints.gridwidth = colunas;
        constraints.gridheight = linhas;
        constraints.anchor = posicao;
        constraints.fill = preenche;
        
        layout.setConstraints(component,constraints);
        
        painel.add(component);
        
    }
    private void configurarJanela(){
        setVisible(true);
        setSize(800,600);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    }
    
}
