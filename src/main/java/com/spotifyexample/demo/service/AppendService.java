package com.spotifyexample.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

public class AppendService {
	
	public String appendResponseApi(HttpURLConnection con) throws UnsupportedEncodingException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
	    StringBuilder sb = new StringBuilder();	
	    for (int c; (c = in.read()) >= 0;)
	        sb.append((char)c);
	    return sb.toString();
	}
	
	
    
}
