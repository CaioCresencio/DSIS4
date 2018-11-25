/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsis4.xml;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *
 * @author caio
 */

public class TratadorErro implements ErrorHandler {

    @Override
    public void warning(SAXParseException saxpe) throws SAXException {
        System.out.println("Warning");
        //throw saxpe;
    }

    @Override
    public void error(SAXParseException saxpe) throws SAXException {
        System.out.println("error");
        throw saxpe;
    }

    @Override
    public void fatalError(SAXParseException saxpe) throws SAXException {
        System.out.println("fatal error");
        throw saxpe;
    }
    
}

