/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.fabrica;

import dsis4.entidades.ListaObra;
import dsis4.util.AlgoritmoLeitura;
import dsis4.xml.LeitorDOM;
import dsis4.xml.LeitorSAX;
import dsis4.xml.ManipuladorJAXB;

/**
 *
 * @author caio
 */
public class FabricaLeituraXML extends FabricaLeituraAbstrata {

    @Override
    public AlgoritmoLeitura getAlgoritmo(String leitor, String arquivo) {
        AlgoritmoLeitura algo = new LeitorSAX(arquivo);
        
        if(leitor.equals("DOM")){
            algo = new LeitorDOM(arquivo);
        }else if(leitor.equals("JAXB")){
            ManipuladorJAXB m = new ManipuladorJAXB(arquivo);
            m.setClasses(ListaObra.class);
            algo = m;
        }
        
        return algo;
    }
}
