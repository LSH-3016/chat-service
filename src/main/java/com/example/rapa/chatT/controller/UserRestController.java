package com.example.rapa.chatT.controller;

import com.example.rapa.chatT.service.UserService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api"})
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping({"/login"})
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String username = (String)loginData.get("username");
        String password = (String)loginData.get("password");
        boolean success = this.userService.authenticate(username, password);
        return success ? ResponseEntity.ok().build() : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "로그인 실패"));
    }

    @PostMapping({"/register"})
    public ResponseEntity<?> register(@RequestBody Map<String, String> registerData) {
        try {
            this.userService.registerUser((String)registerData.get("username"), (String)registerData.get("password"), (String)registerData.get("nickname"));
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }
}
