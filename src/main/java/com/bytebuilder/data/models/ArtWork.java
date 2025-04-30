package com.bytebuilder.data.models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class ArtWork {

    @Id
    private String id;
    private ArtCategory category;
    private String title;
    private String description;
    private String image;
    private Object address;
    private int stock;
    private double price;
}
