package com.spotifyexample.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.spotifyexample.demo.service.AuthService;

@RestController
@RequestMapping("/")
public class AuthenticationController {

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String index() throws Exception {
    	return  authService.getToken();
    }

}
