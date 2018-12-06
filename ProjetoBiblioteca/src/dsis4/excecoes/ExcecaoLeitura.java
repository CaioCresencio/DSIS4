/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.excecoes;

/**
 *
 * @author Windows
 */
public abstract class ExcecaoLeitura extends Exception{
    public ExcecaoLeitura(String msg){
        super(msg);
    }
}
