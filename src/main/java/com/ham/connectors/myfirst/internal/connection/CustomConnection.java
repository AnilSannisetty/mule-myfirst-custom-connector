package com.ham.connectors.myfirst.internal.connection;

import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ham.connectors.myfirst.internal.configuration.MyFirstConfiguration;

public class CustomConnection {
	
	URLConnection urlconnection = null;
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomConnection.class);
	
	public CustomConnection(MyFirstConfiguration mfc) {
		urlconnection = createConnection(mfc.getProtocol(),mfc.getHost(), mfc.getBasepath());
		
}
	public URLConnection createConnection(String protocol, String host, String basepath) {
		URLConnection conn = null;
		String urlProtocol = "HTTPS".equalsIgnoreCase(protocol) ? "https://" :"http://";
		try {
			conn = new URL(urlProtocol + host + basepath).openConnection();
			LOGGER.info("Connection opened successfully");
		}
		catch(Exception e) { 
			e.printStackTrace();
		}
		conn.addRequestProperty("User-Agent", "Mozilla");
		return "HTTPS".equalsIgnoreCase(protocol) ? (HttpsURLConnection) conn : (HttpURLConnection) conn;
		}
	public URLConnection getConnection() {
		LOGGER.info("Connection requred in getConnection method");
		return this.urlconnection;
	}
	public void invalidate()
	{
		
		if (this.urlconnection !=null)
		{
			if (urlconnection instanceof HttpsURLConnection)
				((HttpsURLConnection) urlconnection).disconnect();
			else
				((HttpURLConnection) urlconnection).disconnect();
		}
		LOGGER.info("connection disconnected successfully");
	}
		
	}
