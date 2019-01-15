package com.spotifyexample.demo.service;

import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spotifyexample.demo.model.Album;

@Service
public class AlbumService {
    @Autowired
    private AuthService authService;
    
	private static String URLALBUM = "https://api.spotify.com/v1/albums/";

	public Album consultaAlbum(String idAlbum) throws Exception {
		Album album = new Album();

	    String token = "Bearer ".concat(obtenerToken());
	    
		String urlalbum = URLALBUM.concat(idAlbum);
	    URL url = new URL(urlalbum);
    
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        con.setRequestProperty("Accept","application/json");
        con.setRequestProperty("Content-Type","application/json");
        con.setRequestProperty("Authorization",token);
        
        String response = new AppendService().appendResponseApi(con).toString();
        
	    album.setDetalle(response.toString());
        System.out.println(album.getDetalle());
	    return album;
	}

	private String obtenerToken() throws Exception {
		return authService.getToken();
	}
}
