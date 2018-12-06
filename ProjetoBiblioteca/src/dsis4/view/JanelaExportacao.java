/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import dsis4.dao.ObraDAO;

import dsis4.fabrica.FabricaGravacaoAbstrata;
import dsis4.relatorioPDF.GravadorPDF;
import dsis4.util.AlgoritmoGravacao;
import dsis4.util.TipoGravador;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *
 * @author caio
 */
public class JanelaExportacao extends JanelaPadrao {
    
    private JPanel panelPrincipal;
    private JPanel panel1;
    private JButton botaoPDF;

    private JButton botaoSelecionar;
    private JLabel textoExportacao;
 
    private JComboBox comboGravador;

    
    private ObraDAO obraDAO;
    
    public JanelaExportacao(String titulo) {
        super(titulo);
        carregarComponentes();
        pack();
        obraDAO = new ObraDAO();
    }
    
    private void carregarComponentes(){
        comboGravador = new JComboBox(TipoGravador.getValues());
        
        panelPrincipal = new JPanel(super.layout);
        panel1 = new JPanel(super.layout);
        javax.swing.border.Border border = BorderFactory.createEtchedBorder();
        panel1.setBorder(border);
        
        ImageIcon iconPDF = new ImageIcon("imgs/icon_pdf_m.png");
        ImageIcon iconXML = new ImageIcon("imgs/icon_xml.png");
        ImageIcon iconJSON = new ImageIcon("imgs/icon_json.png");
        
            
        botaoPDF = new JButton(iconPDF);
        botaoPDF.addActionListener(this::exportarPDF);
        botaoSelecionar = new JButton("Exportar XML/JSON");
        botaoSelecionar.addActionListener(this::exportarArquivo);

        
        textoExportacao = new JLabel("Escolha uma opção de exportação");
        
        fixarComponentes();
        
    }
    
    private void fixarComponentes(){
        adicionarComponente(textoExportacao, 0,0,GridBagConstraints.CENTER, 4, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(botaoPDF, 1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(comboGravador, 1,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(botaoSelecionar, 1,2, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(panel1, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panelPrincipal);
        adicionarComponente(panelPrincipal, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, this);
    }
    private void exportarPDF(ActionEvent e){
        GravadorPDF g = new GravadorPDF("relatorio.pdf");
        g.criaPDF(obraDAO.listarObras());
        System.out.println(obraDAO.listarObras().toString());
        JOptionPane.showMessageDialog(null, "Exportação PDF realizada!");
    }
    private void exportarArquivo(ActionEvent e){
       
         FabricaGravacaoAbstrata fab = FabricaGravacaoAbstrata.getFabrica(TipoGravador.valueOf(comboGravador.getSelectedItem().toString()).getFormato());
         AlgoritmoGravacao alg = fab.getAlgoritmo(comboGravador.getSelectedItem().toString());
         alg.gravar(obraDAO.listarObras());
         JOptionPane.showMessageDialog(this, "Exportado com sucesso!");
    }


    
}
