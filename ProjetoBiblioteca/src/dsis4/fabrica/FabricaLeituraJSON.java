/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.fabrica;


import dsis4.entidades.ListaObra;
import dsis4.entidades.ObraLiteraria;
import dsis4.excecoes.ExcecaoLeitorGson;
import dsis4.excecoes.ExcecaoLeitura;
import dsis4.json.ManipuladorGson;
import dsis4.json.ManipuladorJackson;
import dsis4.util.AlgoritmoLeitura;


/**
 *
 * @author caio
 */
public class FabricaLeituraJSON extends FabricaLeituraAbstrata{

    @Override
    public AlgoritmoLeitura getAlgoritmo(String leitor, String arquivo) throws ExcecaoLeitura{
        AlgoritmoLeitura algo = null; 
        
        if(leitor.equals("GSON")){    
            ManipuladorGson m = new ManipuladorGson (arquivo);
            m.setClazz(ListaObra.class);
            algo = m;
        }else if(leitor.equals("JACKSON")){
                ManipuladorJackson mJ = new ManipuladorJackson (arquivo);
                mJ.setClazz(ObraLiteraria.class);
                algo = new ManipuladorJackson(arquivo);
        }else{
            throw new ExcecaoLeitorGson();
        }
        
        
        return algo;
    
    }
    
}
