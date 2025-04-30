package com.aquaone.srv.login.controllers;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.aquaone.srv.login.domain.auth.AuthDTO;
import com.aquaone.srv.login.domain.user.User;
import com.aquaone.srv.login.security.token.TokenService;
import com.aquaone.srv.login.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import ch.qos.logback.core.subst.Token;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private TokenService tokenService;
    

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO data){

        
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username().toUpperCase(), data.password());

        try {
            var auth = this.authenticationManager.authenticate(usernamePassword);

            var token = tokenService.generateToken((User) auth.getPrincipal());

            List<User> user = userService.listUser();

            var logedUser = user.stream().filter(users -> users.getNome().equals(data.username().toUpperCase()));
             Map<String, Object> response = new HashMap<>();
             response.put("token", token);
             response.put("user", logedUser);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            LinkedHashMap map = new LinkedHashMap<>();
            map.put("ERROR VERBOSE", HttpStatus.PRECONDITION_FAILED);
            map.put("DETAILED MESSAGE", e.getMessage());

            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(map);
        }
        

    }


   
}
