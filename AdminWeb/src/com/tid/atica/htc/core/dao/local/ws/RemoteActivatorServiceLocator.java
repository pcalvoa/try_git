/**
 * RemoteActivatorServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tid.atica.htc.core.dao.local.ws;

public class RemoteActivatorServiceLocator extends org.apache.axis.client.Service implements com.tid.atica.htc.core.dao.local.ws.RemoteActivatorService {

    public RemoteActivatorServiceLocator() {
    }


    public RemoteActivatorServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public RemoteActivatorServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for RemoteActivator
    private transient String remoteActivator = "http://rhcolaboravm:8080/axisversion2/services/RemoteActivator";

    public  java.lang.String getRemoteActivatorAddress() {
        return remoteActivator;
    }

    // The WSDD service name defaults to the port name.
    private transient java.lang.String remoteActivatorName = "RemoteActivator";

    public java.lang.String getRemoteActivatorWSDDServiceName() {
        return remoteActivatorName;
    }

    public void setRemoteActivatorWSDDServiceName(java.lang.String name) {
    	remoteActivatorName = name;
    }

    public com.tid.atica.htc.core.dao.local.ws.RemoteActivator getRemoteActivator() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(remoteActivator);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getRemoteActivator(endpoint);
    }

    public com.tid.atica.htc.core.dao.local.ws.RemoteActivator getRemoteActivator(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.tid.atica.htc.core.dao.local.ws.RemoteActivatorSoapBindingStub _stub = new com.tid.atica.htc.core.dao.local.ws.RemoteActivatorSoapBindingStub(portAddress, this);
            _stub.setPortName(getRemoteActivatorWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setRemoteActivatorEndpointAddress(java.lang.String address) {
        remoteActivator = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.tid.atica.htc.core.dao.local.ws.RemoteActivator.class.isAssignableFrom(serviceEndpointInterface)) {
                com.tid.atica.htc.core.dao.local.ws.RemoteActivatorSoapBindingStub _stub = new com.tid.atica.htc.core.dao.local.ws.RemoteActivatorSoapBindingStub(new java.net.URL(remoteActivator), this);
                _stub.setPortName(getRemoteActivatorWSDDServiceName());
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
        if ("RemoteActivator".equals(inputPortName)) {
            return getRemoteActivator();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://rhcolaboravm:8080/axisversion2/services/RemoteActivator", "RemoteActivatorService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://rhcolaboravm:8080/axisversion2/services/RemoteActivator", "RemoteActivator"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("RemoteActivator".equals(portName)) {
            setRemoteActivatorEndpointAddress(address);
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
