/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.fabrica;

import dsis4.util.AlgoritmoGravacao;

/**
 *
 * @author caio
 */
public abstract class FabricaGravacaoAbstrata {
     
   
    public static FabricaGravacaoAbstrata getFabrica(String formato) {
        FabricaGravacaoAbstrata fabrica = new FabricaGravacaoXML();
        if(formato.equals("JSON")) {
            fabrica = new FabricaGravacaoJSON();
        }
        return fabrica;
    }
    
    public abstract AlgoritmoGravacao getAlgoritmo(String leitor, String arquivo);
    
}
