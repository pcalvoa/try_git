/*
 * $Header: /cvsroot/securityfilter/securityfilter/src/example/org/securityfilter/example/Constants.java,v 1.5 2004/01/26 10:55:37 maxcooper Exp $
 * $Revision: 1.5 $
 * $Date: 2004/01/26 10:55:37 $
 *
 * ====================================================================
 * The SecurityFilter Software License, Version 1.1
 *
 * (this license is derived and fully compatible with the Apache Software
 * License - see http://www.apache.org/LICENSE.txt)
 *
 * Copyright (c) 2002 SecurityFilter.org. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by
 *        SecurityFilter.org (http://www.securityfilter.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The name "SecurityFilter" must not be used to endorse or promote
 *    products derived from this software without prior written permission.
 *    For written permission, please contact license@securityfilter.org .
 *
 * 5. Products derived from this software may not be called "SecurityFilter",
 *    nor may "SecurityFilter" appear in their name, without prior written
 *    permission of SecurityFilter.org.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE SECURITY FILTER PROJECT OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 */

package com.tid;

/**
 * Constants vars
 * 
 */
public interface Constants {

   // shared constants
   public static final String VALID_ROLE = "admin";  
   
   //Configuration files constants
   public static final String CONFIG_CLIENTS = "clients_config";
   public static final String CONFIG_USERS = "users_config";
   public static final String CONFIG_MAIL = "mail_config";
   public static final String CONFIG_DB = "db-config.xml";
   public static final String CONFIG_PAGINATION = "pagination_config";
   public static final String CONFIG_SECURITY_FILE = "client_deploy.wsdd";
   public static final String CONFIG_VALID_RECOVERY_PASSWORD_DAYS = "3";
   
   //Configuration variables
   public static final String CLIENTS_CACHE = "clientsCache";
   public static final String USERS_CACHE = "usersCache";
   public static final String DEFAULT_CLIENT = "defaultClient";
   public static final String CURRENT_CLIENT = "currentClient";
   public static final String PAG_LINKS_TO_DISPLAY = "linksToDisplay";
   public static final String PAG_ITEMS_PAGE = "itemsPage";
   public static final String TEXT_CONTENT_TYPE = "text/html; charset=UTF-8";
   public static final String CLIENT_UPDATED_CENTER = "clientUpdatedCenter";
   public static final String CLIENT_UPDATED_SERVICE = "clientUpdatedService";   
   public static final String CLIENT_UPDATED_USER = "clientUpdatedUser";
   public static final String CLIENT_UPDATED_LICENSE = "clientUpdatedLicense";
   public static final String CLIENT_UPDATED_RECOVERY = "clientUpdatedRecovery";  
   
   //Servlets
   public static final String SERVLET_USER = "/views/user";
   public static final String SERVLET_CENTER = "/views/center";
   public static final String SERVLET_SERVICE = "/views/service";
   public static final String SERVLET_CLIENT = "/views/client";
   public static final String SERVLET_LICENSES = "/views/licensesservlet";
   
   //Services
   public static final String SERVICE_CENTER_NAME = "CentroManager";
   public static final String SERVICE_CENTER_ADDRESS = "/services/CentroManager";
   public static final String SERVICE_SERVICE_NAME = "ServicioManager";
   public static final String SERVICE_SERVICE_ADDRESS = "/services/ServicioManager";
   public static final String SERVICE_USER_NAME = "FullUserManager";
   public static final String SERVICE_USER_ADDRESS = "/services/FullUserManager";
   public static final String SERVICE_SEARCH_NAME = "ConsultasManager";
   public static final String SERVICE_SEARCH_ADDRESS = "/services/ConsultasManager";
   public static final String SERVICE_REMOTEACTIVATOR_NAME = "RemoteActivator";
   public static final String SERVICE_REMOTEACTIVATOR_ADDRESS = "/services/RemoteActivator";
   public static final String SERVICE_LICENSES_NAME = "LicensesManager";
   public static final String SERVICE_LICENSES_ADDRESS = "/services/LicensesManager";
   
   //Parameters
   public static final String PARAM_TYPE = "type";
   public static final String PARAM_QUERY = "query";
   public static final String PARAM_FORMAT = "query";
   public static final String PARAM_OP="op";
   public static final String PARAM_CENTER = "center";
   public static final String PARAM_SERVICE = "service";
   public static final String PARAM_USER = "user";
   public static final String PARAM_ID = "id";
   public static final String PARAM_DESCRIPTION = "description";   
   public static final String PARAM_NAME = "name";
   public static final String PARAM_SURNAME = "surname";
   public static final String PARAM_PASSWORD = "password";
   public static final String PARAM_EMAIL = "email";
   public static final String PARAM_EMAIL_USER = "emailuser";
   public static final String PARAM_EMAIL_PASSWORD = "emailpassword";
   public static final String PARAM_PROFILE = "profile";
   public static final String PARAM_PHONE = "phone";
   public static final String PARAM_MOBILE = "mobile";
   public static final String PARAM_OBSERVATIONS = "observations";
   public static final String PARAM_DATE = "date";
   public static final String PARAM_CENTER_ID = "centerId";
   public static final String PARAM_SERVICE_ID = "serviceId";
   public static final String PARAM_USER_ID = "serviceId";
   public static final String PARAM_URL = "url";
   public static final String PARAM_LICENSES = "licencias";
   public static final String PARAM_ERROR = "error";   
   public static final String PARAM_CODE = "code";
   public static final String PARAM_CLIENT = "system";  
         
   //VALUES
   public static final String VALUE_USER = "user";
   public static final String VALUE_SERVICE = "service";
   public static final String VALUE_CENTER = "center";
   public static final String VALUE_INSERT = "insert";
   public static final String VALUE_UPDATE = "update";
   public static final String VALUE_DELETE = "delete";
   
   public static final String VALUE_NULL="NULL";
   public static final int NOT_CREATED = -1;
   
   public static final String DEFAULT_CENTER = "Default";
   public static final String DEFAULT_SERVICE = "Default";
   public static final int DEFAULT_CENTER_ID = 11;

   //PROFILES
   public static final int PROFILE_ADMINISTRATOR = 1;
   public static final int PROFILE_AMINISTRATOR_LOCAL = 2;
   public static final int PROFILE_USER = 3;
   
   //FORMAT
   public static final String FORMAT_FIELD = "field";
   public static final String FORMAT_JSON = "json";
   
   //QUERY Constructor
   public static final String QUERY_SELECT = "SELECT";
   public static final String QUERY_FROM = "FROM";
   public static final String QUERY_WHERE = "WHERE";
   public static final String QUERY_OP_BEGIN = "LIKE '%";
   public static final String QUERY_OP_END = "%'";
   public static final String QUERY_CONNECTOR = "OR";
   public static final String QUERY_ORDER = "ORDER BY";
   public static final String QUERY_GROUP = "GROUP BY";
   
   //MAIL
   public static final String MAIL_HOST = "host";
   public static final String MAIL_PORT = "port";
   public static final String MAIL_USER = "user";
   public static final String MAIL_PASSWORD = "password";
   public static final String MAIL_FROM = "from";
   public static final String MAIL_SSL = "ssl";
      
   //OTHERS
   public static final String DIALOG_MSG = "dlgMessage";
      
}