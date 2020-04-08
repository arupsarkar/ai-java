package com.ai.einstein;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.ai.http.Request;

public class PredictSentimentResponse extends Request {
	private String TAG = PredictSentimentResponse.class.getName();
	private String token;
	private final String FULL_EINSTEIN_LANG_URL = EINSTEIN_VISION_URL + "/v2/language/sentiment";
	
	public PredictSentimentResponse(String token) {
		super(token);
		this.token = getToken();
		System.out.println(new Date() + " : " + TAG + " : " + token);
	}
	
	public PredictResponse submit() throws IOException {
		
		System.out.println(new Date() + " : " + TAG + " : Sentiment analysis - Start");
		Entity entity = null;
		String text = "I like it";
		String body = "{\"modelId\":\"CommunitySentiment\",\"document\":\"" + text + "\"}";
		entity.json(body);
		
		Client client = ClientBuilder.newClient();
		Response response = client.target(FULL_EINSTEIN_LANG_URL)
							.request()
							.header("Authorization", "Bearer " + getToken())
							.post(entity);
		
	    if (!isSuccessful(response)) {
		      throw new IOException("Error occurred while making prediction call " + response);
		    }		
		
	    System.out.println(new Date() + " : " + TAG + " : Sentiment analysis - End " + response.getStatus());			
		
		return readResponseAs(response, PredictResponse.class);
		


	}

}
