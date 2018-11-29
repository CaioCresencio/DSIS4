package dsis4.adapter;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aluno
 */
public class DataAdapter extends XmlAdapter<String, LocalDate>{

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    @Override
    public LocalDate unmarshal(String v) throws Exception {
        LocalDate data = LocalDate.parse(v, formatter);
        return data;
    }

    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.format(formatter);
    }
    
}
