package com.bytebuilder.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UploadArtWorkRequest {

    @NotNull
    private CreateArtWorkRequest createArtWorkRequest;
    @Email
    @NotBlank
    @NotNull
    private String email;

}
