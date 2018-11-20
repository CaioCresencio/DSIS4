/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import com.toedter.calendar.JCalendar;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author caio
 */
public class JanelaCadastro extends JInternalFrame {
    private JPanel painel;
    private JPanel painelPrincipal;
    private JPanel painel2;
    private JLabel label_isbn;
    private JTextField ISBN;
    private JLabel label_titulo;
    private JTextField titulo;
    private JLabel label_pc;
    private JTextField palavras_chave;
    private JLabel label_autores;
    private JTextField autores;
    private JLabel label_nrmEdicao;
    private JTextField numeroEdicao;
    private JLabel label_nrmExemplares;
    private JTextField numeroExemplares;
    private JLabel label_editora;
    private JTextField editora;
    private JLabel background;
    
    private JButton botao;
    
    private GridBagLayout layout;
    private GridBagConstraints constraints;
    //categoria
    private JCalendar calendario;
    
    public JanelaCadastro(){
        super("Janela de cadastro",true,true,true,true);
        configurarJanela();
        carregarJanela();
    }
    private void carregarJanela(){
        layout = new GridBagLayout();
        constraints = new GridBagConstraints();
        
        label_isbn = new JLabel("ISBN:");
        label_titulo = new JLabel("Titulo:");
        label_pc = new JLabel("Palavras chave:");
        label_autores = new JLabel("Autores:");
        label_nrmEdicao = new JLabel("Numero de edição:");
        label_nrmExemplares = new JLabel("Numero de exemplares:");
        label_editora = new JLabel("Editora:");
        ISBN = new JTextField(10);
        titulo = new JTextField(10);
        palavras_chave = new JTextField(15);
        autores = new JTextField(10);
        numeroEdicao = new JTextField(10);
        numeroExemplares = new JTextField(10);
        editora = new JTextField(10);
        
        painelPrincipal = new JPanel(layout);
        painel = new JPanel();
        painel.setLayout(layout);
        painel2 = new JPanel();
        painel2.setLayout(layout);
        
        
        calendario = new JCalendar();
        botao = new JButton("Cadastrar");
        botao.addActionListener(this::cadastraObra);
        
        javax.swing.border.Border border = BorderFactory.createEtchedBorder();
        painel2.setBorder(border);
        painel.setBorder(border);
        
        adicionarComponente(label_isbn, 0,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(ISBN, 0,1, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(label_titulo, 1,0, GridBagConstraints.WEST, 2, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(titulo, 1,1, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(label_pc, 2,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(palavras_chave, 2,1, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(label_autores, 3,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(autores, 3,1, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(label_nrmEdicao, 0,2, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(numeroEdicao, 0,3, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(label_nrmExemplares, 1,2, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(numeroExemplares, 1,3, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(label_editora, 2,2, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(editora, 2,3, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        
        adicionarComponente(painel, 0,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painelPrincipal);
        
        adicionarComponente(botao, 0,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel2);
        adicionarComponente(painel2, 1,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painelPrincipal);
        
        adicionarComponente(painelPrincipal, 4,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, this);
        
        
    }
    
    private void cadastraObra(ActionEvent e){
        System.out.println(calendario.getDate());
    }
 
    private void adicionarComponente(JComponent component, int linha, int coluna, int posicao, int colunas, int linhas, int preenche, Container painel){
        constraints.gridy = linha;
        constraints.gridx = coluna;
        constraints.insets = new Insets(10,10,10,10);
        
        constraints.gridwidth = colunas;
        constraints.gridheight = linhas;
        constraints.anchor = posicao;
        constraints.fill = preenche;
        
        //component.setFont(new Font("Arial",Font.PLAIN,20));
        
        layout.setConstraints(component,constraints);
        
        painel.add(component);
        
    }
    private void configurarJanela(){
        setVisible(true);
        setSize(800,600);
        //pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    }
}
