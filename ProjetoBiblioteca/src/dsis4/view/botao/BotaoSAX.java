/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view.botao;

import dsis4.xml.AlgoritmoLeituraXML;
import dsis4.xml.LeitorSAX;


/**
 *
 * @author caio
 */
public class BotaoSAX extends BotaoAbstrato {

    public BotaoSAX(String titulo) {
        super(titulo);
    }

    @Override
    public AlgoritmoLeituraXML getAlgoritmo() {
        LeitorSAX leitor = new LeitorSAX("obra.xml");
        return leitor;
    }
    
}
