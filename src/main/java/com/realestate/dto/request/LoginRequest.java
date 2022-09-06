package com.realestate.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Email(message = "Please provide valid email")
    @NotBlank(message = "Email can not be white space")
    private String email;


    @NotNull(message = "Please provide your password")
    @NotBlank(message = "Password can not be white space")
    private String password;
}
