package com.realestate.Service;

import com.realestate.domain.Role;
import com.realestate.domain.User;
import com.realestate.domain.enums.RoleType;
import com.realestate.dto.request.LoginRequest;
import com.realestate.dto.request.RegisterRequest;
import com.realestate.exception.ConflictException;
import com.realestate.exception.ResourceNotFoundException;
import com.realestate.exception.message.ErrorMessage;
import com.realestate.repository.RoleRepository;
import com.realestate.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;



    public void registerUser(RegisterRequest registerRequest){

        if(userRepository.existsByEmail(registerRequest.getEmail())){
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST,registerRequest.getEmail()));
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        Role role= roleRepository.findByName(RoleType.ROLE_USER).
              orElseThrow(()->new ResourceNotFoundException(String.format(ErrorMessage.ROLE_NOT_FOUND_MESSAGE,RoleType.ROLE_USER.name())));


        Set<Role> roles=new HashSet<>();
        roles.add(role);

        User user = new User();
        user.setFirstName(registerRequest.getFirstName());
        user.setLastName(registerRequest.getLastName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encodedPassword);
        user.setPhone(registerRequest.getPhone());
        user.setRoles(roles);

        userRepository.save(user);
    }



}
