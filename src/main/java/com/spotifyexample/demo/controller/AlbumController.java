package com.spotifyexample.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spotifyexample.demo.model.Album;
import com.spotifyexample.demo.service.AlbumService;


@Controller
@RequestMapping("/")
public class AlbumController {
	
    @Autowired
    private AlbumService albumService;
    
    @GetMapping("/")
    public String greetingForm(Model model) {
        model.addAttribute("album", new Album());
        return "index";
    }
    @PostMapping("/listaAlbum")
    public String greetingSubmit(@ModelAttribute Album idAlbum, ModelMap model) throws Exception {
    model.addAttribute(albumService.consultaAlbum(idAlbum.getId())); 
   	return "result";
    }
}
