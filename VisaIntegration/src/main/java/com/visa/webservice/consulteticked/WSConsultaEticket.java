/**
 * WSConsultaEticket.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.visa.webservice.consulteticked;

public interface WSConsultaEticket extends javax.xml.rpc.Service {

/**
 * Consultas en Linea resultado de la transacción
 */
    public java.lang.String getWSConsultaEticketSoapAddress();

    public com.visa.webservice.consulteticked.WSConsultaEticketSoap getWSConsultaEticketSoap() throws javax.xml.rpc.ServiceException;

    public com.visa.webservice.consulteticked.WSConsultaEticketSoap getWSConsultaEticketSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
