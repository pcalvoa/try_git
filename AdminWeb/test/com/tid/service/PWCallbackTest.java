package com.tid.service;

import java.io.IOException;

import org.apache.ws.security.WSPasswordCallback;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.UnsupportedCallbackException;

import static org.mockito.Mockito.*;

public class PWCallbackTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testHandle() throws IOException, UnsupportedCallbackException {
		
		//Create mock
		WSPasswordCallback mock = mock(WSPasswordCallback.class);		
		Callback[] callbacks = new Callback[1];		
		callbacks[0] = mock;
		
		when(mock.getIdentifer()).thenReturn("admin");
		
		PWCallback callback = new PWCallback();
		callback.handle(callbacks);
		verify(mock).setPassword("password");
				
	}
	
	@Test
	public final void testHandleError() throws IOException, UnsupportedCallbackException {
		
		//Create mock
		WSPasswordCallback mock = mock(WSPasswordCallback.class);		
		Callback[] callbacks = new Callback[1];		
		callbacks[0] = mock;
		
		when(mock.getIdentifier()).thenReturn("error");				
		PWCallback callback = new PWCallback();
		callback.handle(callbacks);		
				
		verify(mock, never()).setPassword(anyString());
		
	}
	
	@Test(expected=UnsupportedCallbackException.class)
	public final void testHandleException() throws IOException, UnsupportedCallbackException {
		
		//Create mock
		Callback mock = mock(Callback.class);		
		Callback[] callbacks = new Callback[1];		
		callbacks[0] = mock;
		
		PWCallback callback = new PWCallback();
		callback.handle(callbacks);
		
	}

}
