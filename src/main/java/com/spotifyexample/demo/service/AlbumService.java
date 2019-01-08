package com.spotifyexample.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class AlbumService {
	private static String URLALBUM = "https://api.spotify.com/v1/albums/";
	private static String TOKEN = "BQDZbMJkpIFeZit3JYszXvR-9mmdaxV19mU4NNJWD-IUQk-ZMRTZ-wsTjnTWnKnIH2sT22k0V8Pr4kDJUY8";
	
	public String consultaAlbum(String idAlbum) throws IOException {
		

		try {
		String urlalbum = URLALBUM.concat(idAlbum);
	    URL url = new URL(urlalbum);
	    String auth = "Bearer ".concat(TOKEN);
	    StringBuilder sb = new StringBuilder();	
			
			
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.setRequestProperty("Accept","application/json");
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestProperty("Authorization",auth);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
		
	    for (int c; (c = in.read()) >= 0;)
	        sb.append((char)c);

	    System.out.println(sb.toString());
	    return sb.toString();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		return null;

	}
}
