package com.ai.services;

import java.io.IOException;
import java.util.Date;

import com.ai.http.AccessTokenRequest;
import com.ai.token.AccessToken;
import com.ai.token.AssertionGenerator;

public class AccessTokenProvider {
	private static String TAG = AccessTokenProvider.class.getName();
	private AccessToken accessToken;
	private final String email;
	private final String privateKey;
	private final long durationInSeconds;

	public static AccessTokenProvider getProvider(String email, String privateKey, long durationInSeconds) {
		return new AccessTokenProvider(email, privateKey, durationInSeconds);
	}

	private AccessTokenProvider(String email, String privateKey, long durationInSeconds) {
		this.email = email;
		this.privateKey = privateKey;
		this.durationInSeconds = durationInSeconds;
		System.out.println(new Date() + " : email " + email);
		System.out.println(new Date() + " : privateKey " + privateKey);
		System.out.println(new Date() + " : durationInSeconds " + String.valueOf(durationInSeconds));
		refreshToken();
	}

	public AccessToken getAccessToken() {
		return accessToken;
	}

	public void refreshToken() {
		String assertion = AssertionGenerator.generateJWTAssertion(email, privateKey, durationInSeconds);
		System.out.println(new Date() + ":" + TAG + ":" + assertion);
		
		AccessTokenRequest tokenRequest = new AccessTokenRequest(assertion);
		try {
			accessToken = tokenRequest.submit();
			System.out.println(new Date() + " : " + accessToken);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
