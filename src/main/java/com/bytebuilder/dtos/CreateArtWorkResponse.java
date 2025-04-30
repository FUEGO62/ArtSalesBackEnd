package com.bytebuilder.dtos;


import com.bytebuilder.data.models.ArtCategory;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;

@Getter
@Setter
public class CreateArtWorkResponse {

    private String id;
    private ArtCategory category;
    private String title;
    private String description;
    private String image;
    private Object address;
    private int stock;
    private double price;
}
