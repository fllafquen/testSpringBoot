package com.spotifyexample.demo.controller;

import com.spotifyexample.demo.service.SpotifyService;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.miscellaneous.Device;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.model_objects.specification.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/")
public class AuthenticationController {

    @Autowired
    private SpotifyService spotifyService;

    @GetMapping("/")
    public ResponseEntity<Object> index() {
        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .header("Location", this.spotifyService.getAuthorizationUri().toString())
                .build();
    }

    @GetMapping("/callback")
    public ResponseEntity<Object> queryMethod(@RequestParam String code) throws IOException, SpotifyWebApiException {
        spotifyService.completeAuthorizationFromCode(code);
        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .header("Location", "/userinfo")
                .build();
    }

    @GetMapping("/userinfo")
    public User userInfo() throws IOException, SpotifyWebApiException {
        return spotifyService.getUserInfo();
    }

    @GetMapping("/devices")
    public Device[] devices() throws IOException, SpotifyWebApiException {
        return spotifyService.getAvailableDevices();
    }
    
    @GetMapping("/album")
    public Paging<AlbumSimplified> album() throws IOException, SpotifyWebApiException {
        return spotifyService.album();
    }
   

    @GetMapping("/logout")
    public ResponseEntity<Object> logout() throws IOException, SpotifyWebApiException {
        this.spotifyService.logout();
        return ResponseEntity
                .status(HttpStatus.SEE_OTHER)
                .header("Location", "/")
                .build();
    }



}
