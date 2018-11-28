/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import dsis4.view.botao.BotaoAbstrato;
import dsis4.view.botao.BotaoJAXB;
import dsis4.view.botao.BotaoSAX;
import dsis4.xml.AlgoritmoLeituraXML;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author caio
 */
public class JanelaXML extends JanelaPadrao implements ActionListener{
    private JPanel panel;
    private BotaoAbstrato buttonSAX;
    private BotaoAbstrato buttonJAXB;
    
    private AlgoritmoLeituraXML algoritmo;
    
    
    public JanelaXML(String titulo) {
        super(titulo);
        carregarJanela();
    }
    private void carregarJanela(){
        panel = new JPanel(super.layout);
        javax.swing.border.Border border = BorderFactory.createEtchedBorder();
        panel.setBorder(border);
        
        buttonSAX = new BotaoSAX("SAX");
        buttonSAX.addActionListener(this);
        buttonJAXB = new BotaoJAXB("JAXB");
        buttonJAXB.addActionListener(this);
        
        adicionarComponente(buttonSAX, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(buttonJAXB, 0,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(panel, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, this);
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        BotaoAbstrato botao = (BotaoAbstrato) ae.getSource();
        algoritmo = botao.getAlgoritmo();
        System.out.println(algoritmo.ler());
      
    }
    
}
