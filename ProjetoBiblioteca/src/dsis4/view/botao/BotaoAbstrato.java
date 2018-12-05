/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view.botao;

import dsis4.util.AlgoritmoLeituraXML;
import javax.swing.JButton;

/**
 *
 * @author caio
 */
public abstract class BotaoAbstrato extends JButton {
    public BotaoAbstrato(String titulo){
        super(titulo);
    }
    
    public abstract AlgoritmoLeituraXML getAlgoritmo();
    
}
