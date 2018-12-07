/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import dsis4.dao.DevolucaoDAO;
import dsis4.dao.EmprestimoDAO;
import dsis4.dao.ExemplarDAO;
import dsis4.entidades.Exemplar;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Windows
 */
public class JanelaDevolucao extends JanelaPadrao{
    private JPanel panelPrincipal;
    private JPanel panel1;
    private JPanel panel2;

    private JButton buttonDevolucao;
    private JButton buttonAdicionar;
    private JButton botaoRemover;
    
    private JLabel labelProntuario;
    private JLabel labelCodigoEx;
    private JTextField fieldProntuario;
    private JTextField fieldCodigoEx;
    
    private List<Exemplar> exemplares;
    private ExemplarDAO eDAO;
    private DevolucaoDAO dDAO;
    
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    
    
    public JanelaDevolucao(String titulo) {
        super(titulo);
        criarComponentes();
        
    }
    
    private void criarComponentes() {
        panelPrincipal = new JPanel(super.layout);
        panel1 = new JPanel(super.layout);
        panel1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Informações"));
        
        panel2 = new JPanel(super.layout);
        panel2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Devolucao"));
        exemplares = new ArrayList<>();
        eDAO = new ExemplarDAO();
        dDAO = new DevolucaoDAO();
        
        labelProntuario = new JLabel("Digite o prontuario: ");
        labelCodigoEx = new JLabel("Digite o codigo exemplar: ");
        fieldCodigoEx = new JTextField(10);
        fieldProntuario = new JTextField(10);
        
        ImageIcon iconAdc = new ImageIcon("imgs/icon_adicionar.png");
        ImageIcon iconRem = new ImageIcon("imgs/icon_remover.png");
        ImageIcon iconCad = new ImageIcon("imgs/icon_cadastro.png");
        
        
        buttonAdicionar = new JButton(iconAdc);
        buttonAdicionar.addActionListener(this::adicionarExemplar);
        botaoRemover = new JButton(iconRem);
        botaoRemover.addActionListener(this::removerExemplar);
        buttonDevolucao = new JButton("Devolver",iconCad);
        buttonDevolucao.addActionListener(this::geraDevolucao);
        buttonDevolucao.setEnabled(false);
        
        model = new DefaultTableModel();
        model.addColumn("Codigo dos exemplares");
        
        table = new JTable();
        table.setModel(model);
        table.setFillsViewportHeight(true);
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width,30));
        
        scroll = new JScrollPane();
        scroll.setViewportView(table);
        scroll.setPreferredSize(new Dimension(200,header.getPreferredSize().height+3*table.getRowHeight()));
 

        fixarComponentes();
    }
    
    private void fixarComponentes(){

        adicionarComponente(labelProntuario, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        
        adicionarComponente(fieldProntuario, 1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        
        
        adicionarComponente(labelCodigoEx,0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel2);
        adicionarComponente(fieldCodigoEx, 1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel2);
        adicionarComponente(buttonAdicionar, 1,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel2);
        adicionarComponente(botaoRemover, 1,2, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel2);
        adicionarComponente(scroll,2,0, GridBagConstraints.CENTER, 1,2 ,GridBagConstraints.BOTH, panel2);
        adicionarComponente(buttonDevolucao,2,1, GridBagConstraints.CENTER, 2,1 ,GridBagConstraints.BOTH, panel2);
        
        adicionarComponente(panel1, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panelPrincipal);
        adicionarComponente(panel2, 1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panelPrincipal);
        adicionarComponente(panelPrincipal, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, this);
    }

    
    private void geraDevolucao(ActionEvent e){
        
        int pront = Integer.parseInt(fieldProntuario.getText());
        if(validaCampos()){
            dDAO.geraDevolucao(exemplares, pront);
            JOptionPane.showMessageDialog(null,"Devolvido com sucesso!");
            limparCampos();
        }
    }
    private boolean validaCampos(){
        boolean validacao = false;
        
        if( fieldProntuario.getText().length() > 0 && exemplares.size() > 0){
            validacao = true;
            
            try{
               int validaEdicao  = Integer.parseInt(fieldProntuario.getText());  
            }catch(NumberFormatException e){
              JOptionPane.showMessageDialog(null,"Numero de prontuario invalido!");
              validacao = false;
            }
            
            
        }else{
          JOptionPane.showMessageDialog(null,"Existem campos não preenchidos!");
        }
            
       return validacao;
    }
    
    private void adicionarExemplar(ActionEvent e){
        int codEx = Integer.parseInt(fieldCodigoEx.getText());
        
        Exemplar ex = eDAO.buscaExemplar(codEx);
        
        exemplares.add(ex);
        buttonDevolucao.setEnabled(true);
        
        if(model.getRowCount() < 3 ){
            Object[] o = new Object[1];
            try{
                int i = Integer.parseInt(fieldCodigoEx.getText());
                o[0] = i;
                model.addRow(o);
                fieldCodigoEx.setText("");
            }catch(NumberFormatException erro){
                JOptionPane.showMessageDialog(null,"Coloque um exemplar válido!");
            }
        }
        
    }

    
    private List<Integer> getExemplares(){
        List<Integer> exemplares = new ArrayList<>();
        if(model.getRowCount() > 0){
            for(int i = 0; i< model.getRowCount(); i++){
               exemplares.add(Integer.parseInt(String.valueOf(model.getValueAt(i, 0))));
            }
        }
        return exemplares;
    }
    
    
    private void removerExemplar(ActionEvent e){
        if(model.getRowCount() > 0){
            model.removeRow(model.getColumnCount()-1);
        }
    }
    private void limparCampos(){
        fieldProntuario.setText("");
        fieldCodigoEx.setText("");
        exemplares = new ArrayList<>();
        model.setNumRows(0);
    }
    
}
