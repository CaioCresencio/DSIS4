/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.fabrica;


import dsis4.entidades.ListaObra;
import dsis4.json.ManipuladorGson;
import dsis4.util.AlgoritmoLeitura;
import dsis4.xml.LeitorDOM;


/**
 *
 * @author caio
 */
public class FabricaLeituraJSON extends FabricaLeituraAbstrata{

    @Override
    public AlgoritmoLeitura getAlgoritmo(String leitor, String arquivo) {
        AlgoritmoLeitura algo = null; 
        ManipuladorGson m = new ManipuladorGson (arquivo);
        m.setClazz(ListaObra.class);
        algo = m;
        
        if(leitor.equals("JACKSON")){
            algo = new LeitorDOM(arquivo);
        }
        
        return algo;
    
    }
    
}
