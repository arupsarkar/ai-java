package com.ai.http;

import java.io.IOException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import com.ai.token.AccessToken;

public class AccessTokenRequest extends Request{

	private final String assertion;
	
	public AccessTokenRequest(String assertion) {
		super("");
		// TODO Auto-generated constructor stub
		this.assertion = assertion;
	}
	
	public AccessToken submit() throws IOException {
	    Form form = new Form();
	    form.param("assertion", assertion);
	    form.param("grant_type", "urn:ietf:params:oauth:grant-type:jwt-bearer");
	    
	    Entity<Form> entity = Entity.form(form);
	    Response response = client.target(EINSTEIN_VISION_URL + "/v2/oauth2/token")
	            .request()
	            .post(entity);	    
	    
	    if (!isSuccessful(response)) {
	        throw new IOException("Error occurred while fetching Access Token " + response);
	      }
	    
		return readResponseAs(response, AccessToken.class);
		
	}

}
