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
public class ExcecaoLeitorGson extends ExcecaoLeitura{
    public ExcecaoLeitorGson(){
        super("API indisponivel para leitura de arquivos JSON");
    }
}
