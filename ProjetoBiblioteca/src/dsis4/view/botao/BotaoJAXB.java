/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.view.botao;

import dsis4.entidades.ListaObra;
import dsis4.xml.AlgoritmoLeituraXML;
import dsis4.xml.LeitorJAXB;


/**
 *
 * @author caio
 */
public class BotaoJAXB extends BotaoAbstrato {

    public BotaoJAXB(String titulo) {
        super(titulo);
    }

    @Override
    public AlgoritmoLeituraXML getAlgoritmo() {
        LeitorJAXB leitor = new LeitorJAXB("obra.xml");
        leitor.setClasses(ListaObra.class);
        
        return leitor;
    }
    
}
