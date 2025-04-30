package com.bytebuilder.dtos;

import com.bytebuilder.data.models.ArtCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CreateArtWorkRequest {

    @NotNull(message = "choose a category")
    private ArtCategory category;
    @NotBlank
    @NotNull(message = "title is a required field")
    private String title;
    @NotBlank
    @NotNull(message = "description is a required field")
    private String description;
    @NotBlank
    @NotNull(message = "image is a required field")
    private String image;
    @NotNull(message = "address is a required field")
    private Object address;
    @NotNull(message = "stock is a required field")
    @Min(1)
    private int stock;
    @NotNull(message = "price is a required field")
    @DecimalMin(value = "1.0")
    private double price;
}
