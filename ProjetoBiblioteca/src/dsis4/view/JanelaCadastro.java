/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view;

import com.toedter.calendar.JDateChooser;
import dsis4.dao.CategoriaObraDAO;
import dsis4.dao.ObraDAO;
import dsis4.entidades.CategoriaObra;
import dsis4.entidades.ObraLiteraria;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author caio
 */
public class JanelaCadastro extends JanelaPadrao {
    private JPanel painel;
    private JPanel painelPrincipal;
    private JPanel painel2;
    private JPanel painel3;
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
    
    private JTextField fieldAutores;
    private JLabel labelAutores;

    private JTextField fieldPc;
    private JLabel labelPc;
    
    private JLabel labelCombo;
    private JComboBox comboCategoria;
    
    private JButton botaoAutor;
    private JButton botaoPc;
    private JButton botao;
    
    private JLabel labelCalendario;
    private JDateChooser calendario;
    
    private JTable tabelaAutor;
    private JTable tabelaPc;
    
    private JScrollPane barraRolagem;
    private JScrollPane barraRolagemPc;
    private JScrollPane barraTudo;
    
    private DefaultTableModel modelo ;
    private DefaultTableModel modeloPc;
    
    private List<String> listaAutores;
    private List<String> listaPalavras;
    
    private CategoriaObraDAO categoriaDAO;
    
    ObraDAO obraDAO  = new ObraDAO();
    
    
    
    public JanelaCadastro(String titulo){
        super(titulo);
        carregarJanela();
        carregarComponentes();
        setSize(1000,800);
        listaAutores = new ArrayList<>();
        listaPalavras = new ArrayList<>();
        categoriaDAO = new CategoriaObraDAO();
        preencherCombo();
        
    }
    
    private void carregarJanela(){
        
        comboCategoria = new JComboBox();
        labelCombo = new JLabel("Categoria literaria: ");
        
                
        modelo = new DefaultTableModel();
        modeloPc = new DefaultTableModel();
        
        tabelaAutor = new JTable(modelo);
        tabelaPc = new JTable(modeloPc);
        tabelaAutor.setEnabled(false);
        modelo.addColumn("Nome do autor");
        modeloPc.addColumn("Palavra chave");
        
        labelPc = new JLabel("Palavras chave");     
        fieldPc = new JTextField(10);
        
        label_isbn = new JLabel("ISBN:");
        label_titulo = new JLabel("Titulo:");
        label_pc = new JLabel("Palavras chave:");
        label_autores = new JLabel("Autores:");
        label_nrmEdicao = new JLabel("Numero de edição:");
        label_nrmExemplares = new JLabel("Numero de exemplares:");
        label_editora = new JLabel("Editora:");
        labelCalendario = new JLabel("Data publicação:");
        labelAutores = new JLabel("Adicionar autores:");
        
        fieldAutores = new JTextField(10);
        ISBN = new JTextField(10);
        titulo = new JTextField(10);
        palavras_chave = new JTextField(15);
        autores = new JTextField(10);
        numeroEdicao = new JTextField(10);
        numeroExemplares = new JTextField(10);
        editora = new JTextField(10);
        
        tabelaAutor.setFillsViewportHeight(true);
        tabelaPc.setFillsViewportHeight(true);
        
        painelPrincipal = new JPanel(super.layout);
        painel = new JPanel();
        painel.setLayout(super.layout);
        painel2 = new JPanel();
        painel2.setLayout(super.layout);
        painel3 = new JPanel();
        painel3.setLayout(layout);
        
        barraRolagem = new JScrollPane(tabelaAutor);
        
        ImageIcon icon = new ImageIcon("imgs/icon_cadastro.png");
        
        calendario = new JDateChooser();
        botao = new JButton("Cadastrar",icon);
        botao.addActionListener(this::cadastraObra);
        
        botaoAutor =new JButton("Cadastrar autor",icon);
        botaoAutor.addActionListener(this::registraAutor);
        
        botaoPc = new JButton("Cadastrar Palavra chave",icon);
        botaoPc.addActionListener(this::registraPc);
        
        javax.swing.border.Border border = BorderFactory.createEtchedBorder();
        painel2.setBorder(border);
        painel.setBorder(border);
        painel3.setBorder(border);
        
        barraRolagem = new JScrollPane(tabelaAutor);
        barraRolagemPc = new JScrollPane(tabelaPc);
        barraTudo = new JScrollPane(painelPrincipal);

    }
    private void carregarComponentes(){
        adicionarComponente(label_isbn, 0,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(ISBN, 0,1, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(label_titulo, 1,0, GridBagConstraints.WEST, 2, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(titulo, 1,1, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
//        adicionarComponente(label_pc, 2,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
//        adicionarComponente(palavras_chave, 2,1, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
//        adicionarComponente(label_autores, 3,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
//        adicionarComponente(autores, 3,1, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(label_nrmEdicao, 0,2, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(numeroEdicao, 0,3, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(label_nrmExemplares, 1,2, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(numeroExemplares, 1,3, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(label_editora, 2,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(editora, 2,1, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(labelCalendario, 2,2, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel);
        adicionarComponente(calendario, 2,3, GridBagConstraints.SOUTH, 1,1,GridBagConstraints.BOTH, painel);
        adicionarComponente(labelCombo, 3,0, GridBagConstraints.SOUTH, 2,1,GridBagConstraints.BOTH, painel);
        adicionarComponente(comboCategoria, 3,2, GridBagConstraints.SOUTH, 2,1,GridBagConstraints.BOTH, painel);
        
        adicionarComponente(painel, 0,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painelPrincipal);
        
        adicionarComponente(botao, 0,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painel2);
        adicionarComponente(painel2, 2,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painelPrincipal);
        
        adicionarComponente(labelAutores, 1,0, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, painel3);
        adicionarComponente(fieldAutores, 1,1, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, painel3);
        adicionarComponente(botaoAutor, 3,0, GridBagConstraints.CENTER, 2, 1,GridBagConstraints.BOTH, painel3);
        adicionarComponente(barraRolagem, 2,0, GridBagConstraints.CENTER, 2, 1,GridBagConstraints.BOTH, painel3);
        
        adicionarComponente(labelPc, 1,2, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, painel3);
        adicionarComponente(fieldPc, 1,3, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, painel3);
        adicionarComponente(botaoPc, 3,2, GridBagConstraints.CENTER, 1, 1,GridBagConstraints.BOTH, painel3);
        adicionarComponente(barraRolagemPc, 2,2, GridBagConstraints.CENTER, 2, 1,GridBagConstraints.BOTH, painel3);
        
        adicionarComponente(painel3, 1,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, painelPrincipal);
        
        adicionarComponente(barraTudo, 4,0, GridBagConstraints.WEST, 1, 1,GridBagConstraints.BOTH, this); 
    }
    
    private void cadastraObra(ActionEvent e){
        
        if(validaCampos()){
            obraDAO.salvar(obterCampos());
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");
        }
        
        limparCampos();
       
    }
    
    private void preencherCombo(){
        List<CategoriaObra> categoria = categoriaDAO.getCategorias();
        
        categoria.forEach( c ->{
            comboCategoria.addItem(c);
        });
    }
    private void limparCampos(){
        ISBN.setText("");
        editora.setText("");
        titulo.setText("");
        numeroEdicao.setText("");
        numeroExemplares.setText("");
        fieldPc.setText("");
        fieldAutores.setText("");
        modelo.setNumRows(0);
        modeloPc.setNumRows(0);
        listaPalavras.clear();
        listaAutores.clear();
    }
    
    private void registraPc(ActionEvent e){
        Object[] linha = new Object[1];
        String conteudo = fieldPc.getText();
        if(conteudo != null){
            linha[0] = conteudo;
            modeloPc.addRow(linha);
            listaPalavras.add(conteudo);
        }
    }
    private void registraAutor(ActionEvent e){
        Object[] linha = new Object[1];
        String conteudo = fieldAutores.getText();
        if(conteudo != null){
            linha[0] = conteudo;
            modelo.addRow(linha);
            listaAutores.add(conteudo);
        }
        
    }
    private ObraLiteraria obterCampos(){
        ObraLiteraria obra = null;
        String isbn_o = ISBN.getText();
        String editora_o = editora.getText();
        String titulo_o = titulo.getText();
        int numeroEdicao_o  = Integer.parseInt(numeroEdicao.getText());
        int numeroExemplares_o = Integer.parseInt(numeroExemplares.getText());
        LocalDate publicacao_o = calendario.getDate().toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
        List<CategoriaObra> categoria = categoriaDAO.getCategorias();
        obra = new ObraLiteraria(isbn_o, numeroExemplares_o, numeroEdicao_o, publicacao_o,editora_o , titulo_o, categoria.get(comboCategoria.getSelectedIndex()), listaAutores, listaPalavras);
        
        return obra;
    }
    private boolean validaCampos(){
        boolean validacao = false;
        
        if(ISBN.getText().length() > 0 && editora.getText().length() > 0
               && titulo.getText().length() > 0 && calendario.getDate() != null
                && numeroEdicao.getText().length() > 0 && numeroExemplares.getText().length() > 0){
            validacao = true;
            
            try{
               int validaEdicao  = Integer.parseInt(numeroEdicao.getText());
                
            }catch(NumberFormatException e){
              JOptionPane.showMessageDialog(null,"Numero de edição invalido!");
              validacao = false;
            }
            try{
               int validaExemplares = Integer.parseInt(numeroExemplares.getText());
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Numero de exemplares invalido!");
                validacao = false;
            }
        }else{
          JOptionPane.showMessageDialog(null,"Existem campos não preenchidos!");
        }
            
       return validacao;
    }



}
