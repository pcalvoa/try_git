/**
 * RemoteActivatorService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.tid.atica.htc.core.dao.local.ws;

public interface RemoteActivatorService extends javax.xml.rpc.Service {
    public java.lang.String getRemoteActivatorAddress();

    public com.tid.atica.htc.core.dao.local.ws.RemoteActivator getRemoteActivator() throws javax.xml.rpc.ServiceException;

    public com.tid.atica.htc.core.dao.local.ws.RemoteActivator getRemoteActivator(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
