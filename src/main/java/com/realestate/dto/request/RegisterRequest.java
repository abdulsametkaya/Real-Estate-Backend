package com.realestate.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @Size(max = 50)
    @NotNull(message = "Please provide your first name")
    @NotBlank(message = "Firstname can not be white space")
    private String firstName;

    @Size(max = 50)
    @NotNull(message = "Please provide your last name")
    @NotBlank(message = "LastName can not be white space")
    private String lastName;

    @Size(min = 4, max = 20,message="Please Provide Correct Size for Password")
    @NotNull(message = "Please provide your password")
    @NotBlank(message = "Password can not be white space")
    private String password;

    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Please provide valid phone number")
    @Size(min = 14, max = 14)
    @NotNull(message = "Please provide your phone number")
    @NotBlank(message = "Phone can not be white space")
    private String phone;

    @Email(message = "Please provide valid email")
    @Size(min = 5, max = 20)
    @NotNull(message = "Please provide your email")
    @NotBlank(message = "Email can not be white space")
    private String email;

}
