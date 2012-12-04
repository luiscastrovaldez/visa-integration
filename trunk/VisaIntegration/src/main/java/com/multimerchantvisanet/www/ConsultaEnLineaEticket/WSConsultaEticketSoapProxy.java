package com.multimerchantvisanet.www.ConsultaEnLineaEticket;

public class WSConsultaEticketSoapProxy implements com.multimerchantvisanet.www.ConsultaEnLineaEticket.WSConsultaEticketSoap {
  private String _endpoint = null;
  private com.multimerchantvisanet.www.ConsultaEnLineaEticket.WSConsultaEticketSoap wSConsultaEticketSoap = null;
  
  public WSConsultaEticketSoapProxy() {
    _initWSConsultaEticketSoapProxy();
  }
  
  public WSConsultaEticketSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initWSConsultaEticketSoapProxy();
  }
  
  private void _initWSConsultaEticketSoapProxy() {
    try {
      wSConsultaEticketSoap = (new com.multimerchantvisanet.www.ConsultaEnLineaEticket.WSConsultaEticketLocator()).getWSConsultaEticketSoap();
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
  
  public com.multimerchantvisanet.www.ConsultaEnLineaEticket.WSConsultaEticketSoap getWSConsultaEticketSoap() {
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