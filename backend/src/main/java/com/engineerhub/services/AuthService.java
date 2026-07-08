package com.engineerhub.services;

import com.engineerhub.dto.request.LoginRequest;
import com.engineerhub.dto.request.RegisterRequest;
import com.engineerhub.dto.response.LoginResponse;
import com.engineerhub.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.engineerhub.modals.enums.Role;
import com.engineerhub.modals.User;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    


    public AuthService(UserRepository userRepository,
                    BCryptPasswordEncoder passwordEncoder,
                    AuthenticationManager authenticationManager,
                    JwtService jwtService) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already exists";
        }

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhoneNumber());

        // Encrypt password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Default role
        user.setRole(Role.USER);

        userRepository.save(user);

        return "Registration Successful";
    }

    public LoginResponse login(LoginRequest request) {

    Authentication authentication =
            authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

    User user = (User) authentication.getPrincipal();

    return new LoginResponse(
            jwtService.generateToken(user.getEmail()),
            "Login Successful. JWT will be added next."
    );
}
}