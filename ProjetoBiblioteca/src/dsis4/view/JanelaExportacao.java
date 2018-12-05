/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import dsis4.dao.ObraDAO;
import dsis4.entidades.ListaObra;
import dsis4.fabrica.FabricaGravacaoAbstrata;
import dsis4.json.ManipuladorGson;
import dsis4.relatorioPDF.GravadorPDF;
import dsis4.xml.ManipuladorJAXB;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    private JButton botaoXML;
    private JButton botaoJSON;
    private JLabel textoExportacao;
    
    private ObraDAO obraDAO;
    
    public JanelaExportacao(String titulo) {
        super(titulo);
        carregarComponentes();
        pack();
        obraDAO = new ObraDAO();
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
        botaoXML.addActionListener(this::exportarArquivo);
        botaoJSON = new JButton(iconJSON);
        botaoJSON.addActionListener(this::exportarArquivo);
        
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
        g.criaPDF(obraDAO.listarObras());
        System.out.println(obraDAO.listarObras().toString());
        JOptionPane.showMessageDialog(null, "Exportação PDF realizada!");
    }
    private void exportarArquivo(ActionEvent e){
         FabricaGravacaoAbstrata fab2 = FabricaGravacaoAbstrata.getFabrica("");
    }
    
    private void exportarXML(ActionEvent e){
       ManipuladorJAXB m = new ManipuladorJAXB("exportacao.xml");
       m.setClasses(ListaObra.class);
       m.gravar(obraDAO.listarObras());
       JOptionPane.showMessageDialog(null, "Exportação XML realizada!");
    }
    
    private void exportarJSON(ActionEvent e){
        ManipuladorGson m = new ManipuladorGson("exportacao.json");
        m.gravar(obraDAO.listarObras());
        JOptionPane.showMessageDialog(null, "Exportação JSON realizada!");
    }
    
}
