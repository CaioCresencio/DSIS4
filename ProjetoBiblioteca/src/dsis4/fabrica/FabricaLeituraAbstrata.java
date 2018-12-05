package dsis4.fabrica;

import dsis4.util.AlgoritmoLeitura;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author caio
 */
public abstract class FabricaLeituraAbstrata {
    
    public static FabricaLeituraAbstrata getFabrica(String formato) {
        FabricaLeituraAbstrata fabrica = new FabricaLeituraXML();
        if(formato.equals("JSON")) {
            fabrica = new FabricaLeituraJSON();
        }
        return fabrica;
    }
    
    public abstract AlgoritmoLeitura getAlgoritmo(String leitor, String arquivo);
    
}
