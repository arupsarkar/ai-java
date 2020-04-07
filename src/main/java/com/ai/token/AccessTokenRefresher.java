package com.ai.token;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.ai.services.AccessTokenProvider;

public class AccessTokenRefresher {

	  public static void schedule(AccessTokenProvider accessTokenProvider, long refreshAfterInSeconds) {
		    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		    executor.scheduleAtFixedRate(accessTokenProvider::refreshToken, refreshAfterInSeconds - 2,
		        refreshAfterInSeconds, TimeUnit.SECONDS);
		  }	
	
}
