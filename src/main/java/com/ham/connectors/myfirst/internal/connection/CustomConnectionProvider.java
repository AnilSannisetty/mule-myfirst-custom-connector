package com.ham.connectors.myfirst.internal.connection;

import java.net.HttpURLConnection;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.mule.runtime.api.connection.ConnectionException;
import org.mule.runtime.api.connection.ConnectionProvider;
import org.mule.runtime.api.connection.ConnectionValidationResult;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ham.connectors.myfirst.internal.configuration.MyFirstConfiguration;

public class CustomConnectionProvider implements ConnectionProvider<CustomConnection> {
	@ParameterGroup (name = "Connection")
	MyFirstConfiguration myFirstConfig;
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomConnection.class);
	@Override
	public CustomConnection connect() throws ConnectionException {
		// TODO Auto-generated method stub
		LOGGER.info("Before creating connection from Connection provider");
		return new CustomConnection(myFirstConfig);
	}

	@Override
	public void disconnect(CustomConnection connection) {
		// TODO Auto-generated method stub
		connection.invalidate();
		
	}

	@Override
	public ConnectionValidationResult validate(CustomConnection connection) {
		// TODO Auto-generated method stub
		URLConnection con = connection.getConnection();
		ConnectionValidationResult result = null;
		if (con instanceof HttpsURLConnection)
		{
			LOGGER.info("before validating the HTTPS connection");
			try 
			{
				result = (((HttpsURLConnection) con).getResponseCode() == 200) ? ConnectionValidationResult.success() : ConnectionValidationResult.failure("HTTPS test connection failed", new Exception());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			LOGGER.info("After validating the HTTPS connection");
		}
		else
		{
			LOGGER.info("before validating the HTTP connection");
			try 
			{
				result = (((HttpURLConnection) con).getResponseCode() == 200) ? ConnectionValidationResult.success() : ConnectionValidationResult.failure("HTTP test connection failed", new Exception());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			LOGGER.info("After validating the HTTP connection");
		}
		
		return result;
	}

}
