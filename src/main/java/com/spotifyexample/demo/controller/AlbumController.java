package com.spotifyexample.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spotifyexample.demo.service.AlbumService;


@Controller
@RequestMapping("/album/")
public class AlbumController {
	
    @Autowired
    private AlbumService albumService;
	
	@GetMapping(value="/edit/{id}")
	public String editar(@PathVariable("id") String idAlbum) throws IOException {
		return albumService.consultaAlbum(idAlbum);
	}
}
