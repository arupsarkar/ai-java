package com.ai.einstein;

import java.io.IOException;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.Boundary;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.ai.http.Request;

public class PredictSentimentResponse extends Request {
	private String TAG = PredictSentimentResponse.class.getName();
	private final String token;
	private final String modelId;
	private final String document;
	private final String FULL_EINSTEIN_LANG_URL = EINSTEIN_VISION_URL + "/v2/language/sentiment";
	
	public PredictSentimentResponse(String token, String modelId, String document) {
		super(token);
		this.token = token;
		this.modelId = modelId;
		this.document = document;
	}
	
	public PredictResponse submit() throws IOException {
		
	    FormDataMultiPart formPart = new FormDataMultiPart();
	    formPart.field("modelId", modelId);
	    formPart.field("document", document);

	    MediaType contentType = formPart.getMediaType();
	    contentType = Boundary.addBoundary(contentType);
	    Entity<FormDataMultiPart> entity = Entity.entity(formPart, contentType);
		
	    Response response = client.target(FULL_EINSTEIN_LANG_URL)
		        .request()
		        .header("Authorization", "Bearer " + token)
		        .post(entity);	    
	    
	    if (!isSuccessful(response)) {
		      throw new IOException("Error occurred while making prediction call " + response);
		    }		
		
		return readResponseAs(response, PredictResponse.class);
		


	}

}
