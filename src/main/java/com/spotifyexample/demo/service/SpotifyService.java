package com.spotifyexample.demo.service;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spotifyexample.model.Token;

import java.io.IOException;


@Service
public class SpotifyService {
	private final static String CLIENTID = "1d2bc933bcdf46beaa7032b806f6ccc7";
	private final static String CLIENTSECRET = "ff3e942c075048ac9ee7a1bbca4b4217";
	private final static String RESPONSETYPE = "token";
	private final static String REDIRECTURI = "http://localhost:8080/callback";
	private final static String fooResourceUrl = "https://accounts.spotify.com/authorize";

	
	
	
	
	public ResponseEntity<Token> getAuthorize() throws IOException {
		
		RestTemplate restTemplate = new RestTemplate();
			
		String  mezcla = fooResourceUrl + "&response_type=" + RESPONSETYPE + "?client_id=" + CLIENTID + "&client_secret=" + CLIENTSECRET + "&redirect_uri="+REDIRECTURI;
		System.out.println(mezcla);
		ResponseEntity<Token> response = restTemplate.exchange(mezcla, HttpMethod.GET,null , Token.class);

		return response;
	}
}
