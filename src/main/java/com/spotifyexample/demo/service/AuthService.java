package com.spotifyexample.demo.service;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.spotifyexample.demo.model.Token;


@Service
public class AuthService {
	private static String CLIENTID = "1d2bc933bcdf46beaa7032b806f6ccc7";
	private static String CLIENTSECRET = "ddbadd5a6f744590bb3e5fae0ada2abf";
	private static String TOKENERQUEST = "https://accounts.spotify.com/api/token";
	private static String GRANTTYPE= "client_credentials";
	
	 public String getToken() throws Exception {
		 String  mezcla = TOKENERQUEST +"?client_id=" + CLIENTID + "&client_secret=" + CLIENTSECRET + "&grant_type=" + GRANTTYPE;
		 
		 URL url = new URL(mezcla);
		 
		 Token token = new Token();   
		    StringBuilder postData = new StringBuilder();
		    byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		    
		    HttpURLConnection con = (HttpURLConnection)url.openConnection();
		    con.setRequestMethod("POST");
		    con.setDoOutput(true);
		    con.getOutputStream().write(postDataBytes);
		    
		    String response = new AppendService().appendResponseApi(con).substring(17, 100);
		    
		    token.setId(response);
		    return  token.getId();
		}
}
