/**
 * WSConsultaEticketLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.visa.webservice.consulteticked;

public class WSConsultaEticketLocator extends org.apache.axis.client.Service implements com.visa.webservice.consulteticked.WSConsultaEticket {

/**
 * Consultas en Linea resultado de la transacción
 */

    public WSConsultaEticketLocator() {
    }


    public WSConsultaEticketLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSConsultaEticketLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSConsultaEticketSoap
    private java.lang.String WSConsultaEticketSoap_address = "http://cal2testing.sytes.net/WSConsulta/WSConsultaEticket.asmx";

    public java.lang.String getWSConsultaEticketSoapAddress() {
        return WSConsultaEticketSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSConsultaEticketSoapWSDDServiceName = "WSConsultaEticketSoap";

    public java.lang.String getWSConsultaEticketSoapWSDDServiceName() {
        return WSConsultaEticketSoapWSDDServiceName;
    }

    public void setWSConsultaEticketSoapWSDDServiceName(java.lang.String name) {
        WSConsultaEticketSoapWSDDServiceName = name;
    }

    public com.visa.webservice.consulteticked.WSConsultaEticketSoap getWSConsultaEticketSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSConsultaEticketSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSConsultaEticketSoap(endpoint);
    }

    public com.visa.webservice.consulteticked.WSConsultaEticketSoap getWSConsultaEticketSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.visa.webservice.consulteticked.WSConsultaEticketSoapStub _stub = new com.visa.webservice.consulteticked.WSConsultaEticketSoapStub(portAddress, this);
            _stub.setPortName(getWSConsultaEticketSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSConsultaEticketSoapEndpointAddress(java.lang.String address) {
        WSConsultaEticketSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.visa.webservice.consulteticked.WSConsultaEticketSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.visa.webservice.consulteticked.WSConsultaEticketSoapStub _stub = new com.visa.webservice.consulteticked.WSConsultaEticketSoapStub(new java.net.URL(WSConsultaEticketSoap_address), this);
                _stub.setPortName(getWSConsultaEticketSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WSConsultaEticketSoap".equals(inputPortName)) {
            return getWSConsultaEticketSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("https://www.multimerchantvisanet.com/ConsultaEnLineaEticket", "WSConsultaEticket");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("https://www.multimerchantvisanet.com/ConsultaEnLineaEticket", "WSConsultaEticketSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSConsultaEticketSoap".equals(portName)) {
            setWSConsultaEticketSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
