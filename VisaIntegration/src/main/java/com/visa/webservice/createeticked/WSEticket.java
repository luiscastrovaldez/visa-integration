/**
 * WSEticket.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.visa.webservice.createeticked;

public interface WSEticket extends javax.xml.rpc.Service {

/**
 * Consultas en Linea de transacciones
 */
    public java.lang.String getWSEticketSoapAddress();

    public com.visa.webservice.createeticked.WSEticketSoap getWSEticketSoap() throws javax.xml.rpc.ServiceException;

    public com.visa.webservice.createeticked.WSEticketSoap getWSEticketSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
