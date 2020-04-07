package com.ai.einstein;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PredictResponse {

	@JsonProperty("message")
	private String errors;

	private List<Map<String, String>> probabilities;

	public PredictResponse() {
	}

	public List<Map<String, String>> getProbabilities() {
		return probabilities;
	}

	public String getErrors() {
		return errors;
	}

	public String toString() {
		return new ToStringBuilder(this).append("probabilities", probabilities).toString();
	}

}
