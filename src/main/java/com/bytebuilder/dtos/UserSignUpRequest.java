package com.bytebuilder.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserSignUpRequest {


    @NotNull
    @NotBlank
    @Email(message = "invalid email")
    private String email;
    @NotNull
    @NotBlank
    @Size(min = 8,message = "password is not secure")
    private String password;

    @NotNull
    @NotBlank
    @Size(min = 11, max = 11,message = "invalid phone number")
    private String phoneNumber;
    @NotNull
    @NotBlank
    private String fullName;
    @NotNull
    private Object address;

}

