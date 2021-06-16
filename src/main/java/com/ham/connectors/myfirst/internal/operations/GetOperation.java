package com.ham.connectors.myfirst.internal.operations;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLConnection;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ham.connectors.myfirst.internal.connection.CustomConnection;


public class GetOperation {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GetOperation.class);
	
	@MediaType(value = ANY, strict = false)
	@Alias("GetDetails")
	public String getDetails(@Connection CustomConnection c)
	{
		String response = null;
		try
		{
			LOGGER.info("Sending a getDetails request");
			response = getHttpResponse(c.getConnection());
			LOGGER.info("Response Received");
		}
		catch(Exception e)
		{
			LOGGER.error("Error Occurred");
			e.printStackTrace();
		}
		return response;
	}
	
	private String getHttpResponse (URLConnection con) {
		StringBuilder response = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
			response = new StringBuilder();
			String responseLine = null;
			while((responseLine = br.readLine()) != null)
			{
				response.append(responseLine.trim());
			}
		}
		catch(Exception e)
		{
			LOGGER.error("Error Occurred:");
			e.printStackTrace();
		}
		return response.toString();
	}

}
