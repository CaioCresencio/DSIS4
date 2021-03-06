/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.adapter;

import dsis4.entidades.CategoriaObra;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author caio
 */
public class JAXBCategoriaObraAdapter extends XmlAdapter <String, CategoriaObra> {

    @Override
    public CategoriaObra unmarshal(String vt) throws Exception {
        CategoriaObra catObra = new CategoriaObra(vt);
        return catObra;
    }

    @Override
    public String marshal(CategoriaObra bt) throws Exception {
        return bt.getDescricao();
    }
    
}
