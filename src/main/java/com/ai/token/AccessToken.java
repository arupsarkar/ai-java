package com.ai.token;

import java.util.Objects;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {

	@JsonProperty("access_token")
	private String token;

	@JsonProperty("expires_in")
	private int expiresIn;

	@JsonProperty("token_type")
	private String tokenType;

	@JsonProperty("message")
	private String errorMessage;

	public AccessToken() {
	}

	public String getToken() {
		return token;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public String getTokenType() {
		return tokenType;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean isValid() {
		return Objects.isNull(errorMessage);
	}

	public String toString() {
		return new ToStringBuilder(this).append("accessToken", getToken()).append("tokenType", getTokenType())
				.append("expiresIn", getExpiresIn()).append("errors", getErrorMessage()).toString();
	}
}
