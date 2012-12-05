package com.visa.webservice.consulteticked;

public class WSConsultaEticketSoapProxy implements com.visa.webservice.consulteticked.WSConsultaEticketSoap {
  private String _endpoint = null;
  private com.visa.webservice.consulteticked.WSConsultaEticketSoap wSConsultaEticketSoap = null;
  
  public WSConsultaEticketSoapProxy() {
    _initWSConsultaEticketSoapProxy();
  }
  
  public WSConsultaEticketSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSConsultaEticketSoapProxy();
  }
  
  private void _initWSConsultaEticketSoapProxy() {
    try {
      wSConsultaEticketSoap = (new com.visa.webservice.consulteticked.WSConsultaEticketLocator()).getWSConsultaEticketSoap();
      if (wSConsultaEticketSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSConsultaEticketSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSConsultaEticketSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSConsultaEticketSoap != null)
      ((javax.xml.rpc.Stub)wSConsultaEticketSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.visa.webservice.consulteticked.WSConsultaEticketSoap getWSConsultaEticketSoap() {
    if (wSConsultaEticketSoap == null)
      _initWSConsultaEticketSoapProxy();
    return wSConsultaEticketSoap;
  }
  
  public java.lang.String consultaEticket(java.lang.String xmlIn) throws java.rmi.RemoteException{
    if (wSConsultaEticketSoap == null)
      _initWSConsultaEticketSoapProxy();
    return wSConsultaEticketSoap.consultaEticket(xmlIn);
  }
  
  
}