package com.ai.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import com.ai.token.AccessToken;
import com.ai.token.AccessTokenRefresher;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "AI Services - Start" );
        
        // For Heroku users
        Optional<String> accountIdOpt = Optional
            .ofNullable(System.getenv("EINSTEIN_VISION_ACCOUNT_ID"));
        Optional<String> privateKeyContentOpt = Optional
            .ofNullable(System.getenv("EINSTEIN_VISION_PRIVATE_KEY"));

        String email = null;
        String privateKey = null;

        if (Objects.isNull(args) || args.length == 0) {
          if (!(accountIdOpt.isPresent() && privateKeyContentOpt.isPresent())) {
            System.out.println("Usage: mvn test \"-Dexec.args=<email/accountId>"
                + " <path to private key>\"");
          }
          email = accountIdOpt.get();
          privateKey = privateKeyContentOpt.get();
        } else {
          email = args[0];
          try {
			privateKey = new String(Files.readAllBytes(Paths.get(args[1])));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }

        long durationInSeconds = 60 * 15;
        if(email != null && privateKey != null) {
        	System.out.println("Email : " + email + ", key : " + privateKey);
        }
        
        // Create a AccessToken provider
        AccessTokenProvider tokenProvider = AccessTokenProvider
            .getProvider(email, privateKey, durationInSeconds);
        
        
        // Use this if you want the refresh the token automatically
        // Schedule Token refresher to refresh
        AccessTokenRefresher.schedule(tokenProvider, 60 * 14);        
        
        AccessToken accessToken = tokenProvider.getAccessToken();
        System.out.println(new Date() + " : Access Token : " + accessToken);
        System.out.println( "AI Services - End" );
    }
}
