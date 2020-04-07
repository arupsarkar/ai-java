package com.ai.einstein;

import java.io.IOException;
import java.util.Date;

import com.ai.http.Request;

public class PredictSentimentResponse extends Request {
	private String TAG = PredictSentimentResponse.class.getName();
	private String token;
	public PredictSentimentResponse(String token) {
		super(token);
		this.token = getToken();
		System.out.println(new Date() + " : " + TAG + " : " + token);
	}
	
	public PredictSentimentResponse submit() throws IOException {
		
		System.out.println(new Date() + " : " + TAG + " : Sentiment analysis - Start");
		System.out.println(new Date() + " : " + TAG + " : Sentiment analysis - End");
		return null;
	}

}
