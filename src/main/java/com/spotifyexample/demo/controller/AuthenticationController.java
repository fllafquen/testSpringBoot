package com.spotifyexample.demo.controller;

import com.spotifyexample.demo.service.SpotifyService;
import com.spotifyexample.model.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class AuthenticationController {

    @Autowired
    private SpotifyService spotifyService;

    @GetMapping("/")
    public ResponseEntity<Token> index() throws IOException {
        return spotifyService.getAuthorize();
    }
}
