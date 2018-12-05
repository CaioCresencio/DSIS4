/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import dsis4.dao.DevolucaoDAO;
import dsis4.dao.ExemplarDAO;
import dsis4.entidades.Exemplar;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Windows
 */
public class JanelaDevolucao extends JanelaPadrao{
    private JPanel panel;

    private JButton buttonDevolucao;
    private JButton buttonAdicionar;
    private JLabel labelProntuario;
    private JLabel labelCodigoEx;
    private JTextField fieldProntuario;
    private JTextField fieldCodigoEx;
    private List<Exemplar> exemplares;
    
    
    public JanelaDevolucao(String titulo) {
        super(titulo);
        criarComponentes();
        configurarJanela();
    }
    
    private void criarComponentes() {
        panel = new JPanel(super.layout);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Devolucao"));
        exemplares = new ArrayList<>();
        
        labelProntuario = new JLabel("Digite o prontuario: ");
        labelCodigoEx = new JLabel("Digite o codigo exemplar: ");
        fieldCodigoEx = new JTextField(5);
        fieldProntuario = new JTextField(5);
        

        buttonDevolucao = new JButton("Devolver");
        buttonDevolucao.addActionListener(this::geraDevolucao);
        buttonDevolucao.setEnabled(false);
        
        buttonAdicionar = new JButton("Adicionar a lista");
        buttonAdicionar.addActionListener(this::adicionarExemplar);

        fixarComponentes();
    }
    
    private void fixarComponentes(){
        
        adicionarComponente(labelProntuario, 0,0,GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(fieldProntuario, 0,1,GridBagConstraints.CENTER,1, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(labelCodigoEx, 0,2, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(fieldCodigoEx, 0,3, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(buttonAdicionar, 1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(buttonDevolucao, 1,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(panel, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, this);
    }
    
    private void configurarJanela() {
        setSize(800,650);
        //pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void geraDevolucao(ActionEvent e){
        DevolucaoDAO dDAO = new DevolucaoDAO();
        int pront = Integer.parseInt(fieldProntuario.getText());
        
        dDAO.geraDevolucao(exemplares, pront);
        
    }
    
    private void adicionarExemplar(ActionEvent e){
        int codEx = Integer.parseInt(fieldCodigoEx.getText());
        ExemplarDAO eDAO = new ExemplarDAO();
        Exemplar ex = eDAO.buscaExemplar(codEx);
        
        exemplares.add(ex);
        buttonDevolucao.setEnabled(true);
        
    }
    
}
