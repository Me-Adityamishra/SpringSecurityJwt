package com.aditya.SpringSecurity.Service;

import com.aditya.SpringSecurity.Entity.Users;
import com.aditya.SpringSecurity.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    public Users register(Users user)
    {
        user.setPassword(encoder.encode((user.getPassword())));
        return  userRepository.save(user);

    }
    public String verify(Users user)
    {
        Authentication authentication=
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated())
        {
            return jwtService.generateToken(user.getUsername());
        }
        return "fails";

    }
}
