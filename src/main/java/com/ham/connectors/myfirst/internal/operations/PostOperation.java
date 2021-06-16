package com.ham.connectors.myfirst.internal.operations;

import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.mule.runtime.extension.api.annotation.Alias;
import org.mule.runtime.extension.api.annotation.param.Connection;
import org.mule.runtime.extension.api.annotation.param.MediaType;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ham.connectors.myfirst.internal.CustomParameters.CustomParamPostOperation;
import com.ham.connectors.myfirst.internal.connection.CustomConnection;

public class PostOperation {
	private static final Logger LOGGER = LoggerFactory.getLogger(PostOperation.class);
	
	@MediaType (value = ANY, strict = false)
	@Alias("PostDetails")
	public String postDetails(@Connection CustomConnection c, @ParameterGroup(name = "postparams") CustomParamPostOperation pg) {
		LOGGER.info("Executing PostDetails Operation");
		String response = null;
		try
		{
			URLConnection con = c.getConnection();
			String jsonstring = "{\"name\":\"" + pg.getName() + "\",\"job\":\"" + pg.getJob() + "\"}";
			con.setDoOutput(true);
			if(con instanceof HttpsURLConnection)
			{
				LOGGER.info("Processing HTTPS request");
				HttpsURLConnection https = (HttpsURLConnection) con;
				https.setRequestMethod("POST");
				https.setRequestProperty("Content-Type", "application/json; utf-8");
				try(OutputStream os = con.getOutputStream()) {
					byte[] input = jsonstring.getBytes("utf-8");
					os.write(input,0,input.length);
				}
				response = getHttpResponse(https);
			}
			else {
				LOGGER.info("Processing HTTP request");
				HttpURLConnection http = (HttpURLConnection) con;
				http.setRequestMethod("POST");
				http.setRequestProperty("Content-Type", "application/json; utf-8");
				try(OutputStream os = con.getOutputStream()) {
					byte[] input = jsonstring.getBytes("utf-8");
					os.write(input,0,input.length);
				}
				response = getHttpResponse(http);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return response.toString();
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
