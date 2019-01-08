package com.spotifyexample.demo.service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.spotifyexample.demo.model.Token;


@Service
public class AuthService {
	 
	private static String CLIENTID = "1d2bc933bcdf46beaa7032b806f6ccc7";
	private static String CLIENTSECRET = "ff3e942c075048ac9ee7a1bbca4b4217";
	private static String TOKENERQUEST = "https://accounts.spotify.com/api/token";
	private static String GRANTTYPE= "client_credentials";
	
	 public String getToken() throws Exception {
		 String  mezcla = TOKENERQUEST +"?client_id=" + CLIENTID + "&client_secret=" + CLIENTSECRET + "&grant_type=" + GRANTTYPE;
		    URL url = new URL(mezcla);
		    
		    StringBuilder postData = new StringBuilder();
		    byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		    
		    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		    conn.setRequestMethod("POST");
		    conn.setDoOutput(true);
		    conn.getOutputStream().write(postDataBytes);
		    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		    
		    StringBuilder sb = new StringBuilder();
		    for (int c; (c = in.read()) >= 0;)
		        sb.append((char)c);
		    
		    Token.setName(sb.substring(17, 100));
		    return  Token.getName();
		}
}
