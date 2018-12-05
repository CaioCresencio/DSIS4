/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import dsis4.dao.EmprestimoDAO;
import dsis4.entidades.Exemplar;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
    
    private JButton botaoAdicionar;
    private JButton botaoRemover;
    private JButton botaoCadastrar;
    
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    
    private EmprestimoDAO emprestimoDAO;
    
    
    public JanelaEmprestimo(String titulo) {
        super(titulo);
        carregarComponentes();
        emprestimoDAO = new EmprestimoDAO();
        
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
        model.addColumn("Codigo dos exemplares");
        
        table = new JTable();
        table.setModel(model);
        table.setFillsViewportHeight(true);
       
        JTableHeader header = table.getTableHeader();
        header.setPreferredSize(new Dimension(header.getPreferredSize().width,30));
        
        ImageIcon iconAdc = new ImageIcon("imgs/icon_adicionar.png");
        ImageIcon iconRem = new ImageIcon("imgs/icon_remover.png");
        ImageIcon iconCad = new ImageIcon("imgs/icon_cadastro.png");
        
        botaoAdicionar = new JButton(iconAdc);
        botaoAdicionar.addActionListener(this::adicionarExemplar);
        botaoRemover = new JButton(iconRem);
        botaoRemover.addActionListener(this::removerExemplar);
        botaoCadastrar = new JButton("Cadastro",iconCad);
        botaoCadastrar.addActionListener(this::cadastrarExemplares);
        
        
        scroll = new JScrollPane();
        scroll.setViewportView(table);
        scroll.setPreferredSize(new Dimension(200,header.getPreferredSize().height+3*table.getRowHeight()));
       
       
        
        fixarComponentes();
       
    }
    
    private void fixarComponentes(){
        
        adicionarComponente(labelLeitor, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(labelFuncionario, 0,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(fieldLeitor, 1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        adicionarComponente(fieldFuncionario, 1,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel1);
        
        adicionarComponente(labelExemplares,0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel2);
        adicionarComponente(fieldExemplar, 1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel2);
        adicionarComponente(botaoAdicionar, 1,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel2);
        adicionarComponente(botaoRemover, 1,2, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel2);
        adicionarComponente(scroll,2,0, GridBagConstraints.CENTER, 1,2 ,GridBagConstraints.BOTH, panel2);
        adicionarComponente(botaoCadastrar,2,1, GridBagConstraints.CENTER, 2,1 ,GridBagConstraints.BOTH, panel2);
        
        adicionarComponente(panel1, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panelPrincipal);
        adicionarComponente(panel2, 1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panelPrincipal);
        adicionarComponente(panelPrincipal, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, this);
    }
    
    private void cadastrarExemplares(ActionEvent e){
        List<Integer> lista = getExemplares();
        
        if( lista.size() > 0){
            List<Exemplar> listaE;
            listaE = emprestimoDAO.getListExemplares(lista);
            if(listaE != null){
                int prontuarioL = Integer.parseInt(fieldLeitor.getText());
                int prontuarioF = Integer.parseInt(fieldFuncionario.getText());
                emprestimoDAO.salvar(prontuarioL, listaE,prontuarioF);
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
    
    private void adicionarExemplar(ActionEvent e){
        if(model.getRowCount() < 3 ){
            Object[] o = new Object[1];
            try{
                int i = Integer.parseInt(fieldExemplar.getText());
                o[0] = i;
                model.addRow(o);
                fieldExemplar.setText("");
            }catch(NumberFormatException erro){
                JOptionPane.showMessageDialog(null,"Coloque um exemplar válido!");
            }
        }
    }
    private void removerExemplar(ActionEvent e){
        if(model.getRowCount() > 0){
            model.removeRow(model.getColumnCount()-1);
        }
    }
    
    
    
}
