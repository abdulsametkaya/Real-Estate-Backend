package com.realestate.Controller;

import com.realestate.Security.jwt.JwtUtils;
import com.realestate.Service.UserService;
import com.realestate.dto.request.LoginRequest;
import com.realestate.dto.request.RegisterRequest;
import com.realestate.dto.response.CREResponse;
import com.realestate.dto.response.LoginResponse;
import com.realestate.dto.response.ResponseMessage;
import lombok.AllArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping()
@AllArgsConstructor
public class UserJwtController {

    private UserService userService;

    private AuthenticationManager authenticationManager;

    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<CREResponse> registerUser(@Valid @RequestBody RegisterRequest registerRequest){

        userService.registerUser(registerRequest);

        CREResponse cre = new CREResponse();
        cre.setMessage(ResponseMessage.REGISTER_MESSAGE);
        cre.setSuccess(true);

        return new ResponseEntity<>(cre, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));

        String token = jwtUtils.generateToken(authentication);

        LoginResponse response = new LoginResponse();
        response.setToken(token);

        return ResponseEntity.ok(response);
    }

}
