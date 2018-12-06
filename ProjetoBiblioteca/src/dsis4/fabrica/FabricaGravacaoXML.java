/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.fabrica;

import dsis4.entidades.ListaObra;
import dsis4.util.AlgoritmoGravacao;
import dsis4.xml.ManipuladorJAXB;

/**
 *
 * @author caio
 */
public class FabricaGravacaoXML extends FabricaGravacaoAbstrata  {
    String arquivo = "exportacao.xml";
    @Override
    public AlgoritmoGravacao getAlgoritmo(String leitor) {
        AlgoritmoGravacao alg;
        ManipuladorJAXB manipulador= new ManipuladorJAXB(arquivo);
        manipulador.setClasses(ListaObra.class);
        alg = manipulador;
        
        return alg;
    }
    
}
