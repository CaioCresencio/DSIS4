/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import dsis4.relatorioPDF.GravadorPDF;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author caio
 */
public class JanelaExportacao extends JanelaPadrao {
    
    private JPanel panelPrincipal;
    private JPanel panel1;
    private JButton botaoPDF;
    private JButton botaoXML;
    private JButton botaoJSON;
    private JLabel textoExportacao;
    
    public JanelaExportacao(String titulo) {
        super(titulo);
        carregarComponentes();
        pack();
    }
    
    private void carregarComponentes(){
        panelPrincipal = new JPanel(super.layout);
        panel1 = new JPanel(super.layout);
        javax.swing.border.Border border = BorderFactory.createEtchedBorder();
        panel1.setBorder(border);
        
        ImageIcon iconPDF = new ImageIcon("imgs/icon_pdf_m.png");
        ImageIcon iconXML = new ImageIcon("imgs/icon_xml.png");
        ImageIcon iconJSON = new ImageIcon("imgs/icon_json.png");
        botaoPDF = new JButton(iconPDF);
        botaoPDF.addActionListener(this::exportarPDF);
        botaoXML = new JButton(iconXML);
        botaoJSON = new JButton(iconJSON);
        
        textoExportacao = new JLabel("Escolha uma opção de exportação");
        
        fixarComponentes();
        
    }
    private void fixarComponentes(){
        adicionarComponente(textoExportacao, 0,0,GridBagConstraints.CENTER, 4, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(botaoPDF, 1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(botaoXML, 1,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(botaoJSON, 1,2, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(panel1, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panelPrincipal);
        adicionarComponente(panelPrincipal, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, this);
    }
    private void exportarPDF(ActionEvent e){
          GravadorPDF g = new GravadorPDF("relatorio.pdf");
    }
    
}
