package com.n4pst3rcod.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @GetMapping("/")
    public String login() {
        return "logged in";
    }

    @GetMapping("/user")
    public String user(OAuth2AuthenticationToken oidcUser) {
        return null;
    }

//    @GetMapping("/logout")
//    public String userLogout(){
//
//    }
//}
}
