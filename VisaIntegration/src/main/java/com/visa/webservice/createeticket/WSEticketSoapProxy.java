package com.visa.webservice.createeticket;

public class WSEticketSoapProxy implements com.visa.webservice.createeticket.WSEticketSoap {
  private String _endpoint = null;
  private com.visa.webservice.createeticket.WSEticketSoap wSEticketSoap = null;
  
  public WSEticketSoapProxy() {
    _initWSEticketSoapProxy();
  }
  
  public WSEticketSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSEticketSoapProxy();
  }
  
  private void _initWSEticketSoapProxy() {
    try {
      wSEticketSoap = (new com.visa.webservice.createeticket.WSEticketLocator()).getWSEticketSoap();
      if (wSEticketSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)wSEticketSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)wSEticketSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (wSEticketSoap != null)
      ((javax.xml.rpc.Stub)wSEticketSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.visa.webservice.createeticket.WSEticketSoap getWSEticketSoap() {
    if (wSEticketSoap == null)
      _initWSEticketSoapProxy();
    return wSEticketSoap;
  }
  
  public java.lang.String generaEticket(java.lang.String xmlIn) throws java.rmi.RemoteException{
    if (wSEticketSoap == null)
      _initWSEticketSoapProxy();
    return wSEticketSoap.generaEticket(xmlIn);
  }
  
  
}