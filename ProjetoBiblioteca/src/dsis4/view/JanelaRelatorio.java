/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import com.toedter.calendar.JDateChooser;
import dsis4.dao.EmprestimoDAO;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Windows
 */
public class JanelaRelatorio extends JanelaPadrao{
    private JPanel panel;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scroll;
    private JButton buttonAtualizar;
    private JButton buttonAnterior;
    private JButton buttonProximo;
    private JButton buttonFechar;
    private JDateChooser calendario;
    
    private int min;
    private int max;
    private long total;
    
    public JanelaRelatorio(String titulo) {
        super(titulo);
        criarComponentes();
        configurarJanela();
    }
    
    private void criarComponentes() {
        panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Listagem"));
        
        model = new DefaultTableModel();
        table = new JTable();
        table.setModel(model);
        table.setFillsViewportHeight(true);
                
        model.addColumn("Data Emprestimo");
        model.addColumn("Data Devolucao");
        model.addColumn("Titulo");
        model.addColumn("Edicao");
        model.addColumn("Codigo Exemplar");
        model.addColumn("Nome Leitor");
        model.addColumn("Categoria Leitor");
        
        scroll = new JScrollPane();
        scroll.setViewportView(table);

        buttonAtualizar = new JButton("Carregar");
        buttonAtualizar.addActionListener(this::carregarPendentes);
        
        buttonAnterior = new JButton("<");
        buttonAnterior.addActionListener(this::anterior);
        buttonAnterior.setEnabled(false);
        
        buttonProximo = new JButton(">");
        buttonProximo.addActionListener(this::proximo);
        buttonProximo.setEnabled(false);
        
        buttonFechar = new JButton("Fechar");
        buttonFechar.addActionListener(this::fechar);
        calendario = new JDateChooser();
        
        fixarComponentes();
    }
    
    private void fixarComponentes(){
        adicionarComponente(calendario, 0,0,GridBagConstraints.CENTER, 4, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(buttonAtualizar, 0,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(buttonAnterior, 0,2, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(buttonProximo, 0,3, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(buttonFechar, 0,4,GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(scroll, 1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, panel);
        adicionarComponente(panel, 0,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, this);
    }
    
    private void configurarJanela() {
        setSize(800,650);
        //pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    private void carregarPendentes(ActionEvent e) {
        EmprestimoDAO relatorio = new EmprestimoDAO();
        try {
            total = relatorio.buscaTotalPendentes();      
            buscarPrimeiro();
        }
        catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a busca");
            System.out.println(erro);
        }
    }
    
    private void buscarPrimeiro() {
        min = 1;
        max = 10;
        buttonAnterior.setEnabled(false);
        if(max < total) {
            buttonProximo.setEnabled(true);
        }
        preencherTabela();
    }
    
    private void anterior(ActionEvent e) {
        min -= 10;
        max -= 10;
        if(min == 1) {
            buttonAnterior.setEnabled(false);
        }
        buttonProximo.setEnabled(true);
        preencherTabela();
    }
    
    private void proximo(ActionEvent e) {
        min += 10;
        max += 10;
        if(max >= total) {
            buttonProximo.setEnabled(false);
        }
        buttonAnterior.setEnabled(true);
        preencherTabela();
    }
    
    private void preencherTabela() {
        EmprestimoDAO relatorio = new EmprestimoDAO();     
        LocalDate data = calendario.getDate().toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
        try {
            List<List<String>> pendentes = relatorio.relatorioPendentes(data, min, max);
            
            model.setNumRows(0);
            
            System.out.println(total);
            for(List<String> pendente: pendentes) {
                Object[] linha = new Object[model.getColumnCount()];
                linha[0] = pendente.get(0);
                linha[1] = pendente.get(1);
                linha[2] = pendente.get(2);
                linha[3] = pendente.get(3);
                linha[4] = pendente.get(4);
                linha[5] = pendente.get(5);
                linha[6] = pendente.get(6);
                
                System.out.println(pendente.get(4));
                model.addRow(linha);
            }
        }
        catch(Exception erro) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro durante a busca");
            System.out.println(erro);
        }
    }
    
    private void fechar(ActionEvent e) {
        System.exit(0);
    }
}
