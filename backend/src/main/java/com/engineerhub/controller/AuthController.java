package com.engineerhub.controller;



import com.engineerhub.dto.request.LoginRequest;
import com.engineerhub.dto.request.RegisterRequest;
import com.engineerhub.dto.response.LoginResponse;
import com.engineerhub.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String test() {
        return "AuthController is working!";
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }
    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {

        return authService.login(request);

    }
}