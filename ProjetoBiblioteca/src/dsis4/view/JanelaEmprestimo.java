/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import java.awt.GridBagConstraints;
import javax.swing.JPanel;

/**
 *
 * @author caio
 */
public class JanelaEmprestimo extends JanelaPadrao {
    
    private JPanel panelPrincipal;
    private JPanel panel1;
    
    public JanelaEmprestimo(String titulo) {
        super(titulo);
        carregarComponentes();
    }
    
    private void carregarComponentes(){
        panelPrincipal = new JPanel(super.layout);
        panel1 = new JPanel(super.layout);
        
        
        
        
        
        fixarComponentes();
       
    }
    
    private void fixarComponentes(){
        
        adicionarComponente(panel1, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panelPrincipal);
        adicionarComponente(panelPrincipal, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, this);
    }
    
    
}
