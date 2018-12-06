/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.fabrica;

import dsis4.json.ManipuladorGson;
import dsis4.json.ManipuladorJackson;
import dsis4.util.AlgoritmoGravacao;

/**
 *
 * @author caio
 */
public class FabricaGravacaoJSON  extends FabricaGravacaoAbstrata {
    String arquivo = "exportacao.json";
    @Override
    public AlgoritmoGravacao getAlgoritmo(String gravador) {
        AlgoritmoGravacao algo;
        algo = new ManipuladorGson(arquivo);
        if(gravador.equals("JACKSON")){
            algo = new ManipuladorJackson(arquivo);
        }
        return algo;
    }
    
}
