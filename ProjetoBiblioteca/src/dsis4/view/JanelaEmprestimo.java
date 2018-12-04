/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author caio
 */
public class JanelaEmprestimo extends JanelaPadrao {
    
    private JPanel panelPrincipal;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel labelLeitor;
    private JLabel labelFuncionario;
    private JLabel labelExemplares;
    private JTextField fieldLeitor;
    private JTextField fieldFuncionario;
    private JTextField fieldExemplar;
    
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    
    public JanelaEmprestimo(String titulo) {
        super(titulo);
        carregarComponentes();
    }
    
    private void carregarComponentes(){
        panelPrincipal = new JPanel(super.layout);
        panel1 = new JPanel(super.layout);
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Informações"));
        
        panel2 = new JPanel(super.layout);
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Exemplares"));
        
        labelLeitor = new JLabel("Prontuario do leitor:");
        labelFuncionario = new JLabel("Prontuario do funcionario: ");
        labelExemplares = new JLabel("Codigo dos exemplares: ");
        
        fieldLeitor = new JTextField(10);
        fieldFuncionario = new JTextField(10);
        fieldExemplar = new JTextField(10);
        
                
        model = new DefaultTableModel();
        table = new JTable(3,1);
        table.setModel(model);
        table.setFillsViewportHeight(true);
        model.addColumn("Codigo dos exemplares");
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        
        scroll = new JScrollPane();
        scroll.setViewportView(table);
        
        fixarComponentes();
       
    }
    
    private void fixarComponentes(){
        
        adicionarComponente(labelLeitor, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(labelFuncionario, 0,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(fieldLeitor, 1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(fieldFuncionario, 1,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        
        adicionarComponente(labelExemplares,0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel2);
        adicionarComponente(fieldExemplar, 0,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel2);
        adicionarComponente(scroll,1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel2);
        
        adicionarComponente(panel1, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panelPrincipal);
        adicionarComponente(panel2, 1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panelPrincipal);
        adicionarComponente(panelPrincipal, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, this);
    }
    
    
}
