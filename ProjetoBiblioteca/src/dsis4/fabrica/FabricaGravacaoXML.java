/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.fabrica;

import dsis4.entidades.ListaObra;
import dsis4.json.ManipuladorGson;
import dsis4.util.AlgoritmoGravacao;

/**
 *
 * @author caio
 */
public class FabricaGravacaoXML extends FabricaGravacaoAbstrata  {

    @Override
    public AlgoritmoGravacao getAlgoritmo(String leitor, String arquivo) {
        AlgoritmoGravacao alg;
        ManipuladorGson manipulador= new ManipuladorGson(arquivo);
        manipulador.setClazz(ListaObra.class);
        alg = manipulador;
        
        return alg;
    }
    
}
