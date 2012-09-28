package com.tid.service;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.*;

import org.apache.ws.security.WSPasswordCallback;

/**
 * Callback para la autenticaci�n AXIS
 * @author TID GR
 *
 */
public class PWCallback implements CallbackHandler {

	public void handle(Callback[] callbacks) throws IOException,
	UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof WSPasswordCallback) {
				WSPasswordCallback pc = (WSPasswordCallback)callbacks[i];
				// set the password given a username
				if ("pablo".equals(pc.getIdentifer())) {
					pc.setPassword("pablo");
				}
				else if ("sergio".equals(pc.getIdentifer())) {
					pc.setPassword("sergio");
				}
				else if ("admin".equals(pc.getIdentifer())) {
					pc.setPassword("password");
				}
			} else {

				throw new UnsupportedCallbackException(callbacks[i], "Unrecognized Callback");

			}

		}

	}

}
