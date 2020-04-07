package com.ai.einstein;

import java.io.IOException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.Boundary;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.ai.http.Request;

public class PredictRequest extends Request {

	private final String modelId;
	private final String sampleLocation;
	private final String detectionMode;
	private final String FULL_EINSTEIN_VISION_URL = EINSTEIN_VISION_URL + "/v2/vision/predict";
	private final String FULL_EINSTEIN_LANG_URL = EINSTEIN_VISION_URL + "/v2/language/sentiment";

	public PredictRequest(String token, String modelId, String sampleLocation, String detectionMode) {
		super(token);
		// TODO Auto-generated constructor stub
	    this.modelId = modelId;
	    this.sampleLocation = sampleLocation;		
	    this.detectionMode = detectionMode;
	}
	
	public PredictResponse submit() throws IOException {
	    FormDataMultiPart formPart = new FormDataMultiPart();
	    formPart.field("modelId", modelId);
	    formPart.field("sampleLocation", sampleLocation);

	    MediaType contentType = formPart.getMediaType();
	    contentType = Boundary.addBoundary(contentType);
	    Entity<FormDataMultiPart> entity = Entity.entity(formPart, contentType);
	    
	    String EINSTEIN_URL = "";
	    if(detectionMode.equalsIgnoreCase("VISION")) {
	    	EINSTEIN_URL = FULL_EINSTEIN_VISION_URL;
	    }else if(detectionMode.equalsIgnoreCase("LANGUAGE")) {
	    	EINSTEIN_URL = FULL_EINSTEIN_LANG_URL;
	    }
	    
	    Response response = client.target(EINSTEIN_URL)
	        .request()
	        .header("Authorization", "Bearer " + getToken())
	        .post(entity);

	    if (!isSuccessful(response)) {
	      throw new IOException("Error occurred while making prediction call " + response);
	    }		
		
		return readResponseAs(response, PredictResponse.class);
	}

}
