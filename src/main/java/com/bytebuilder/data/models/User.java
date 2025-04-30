package com.bytebuilder.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document
@Getter
@Setter
public class User {

    private String id;
    private String email;
    private String password;
    private String phoneNumber;
    private String fullName;

    private Object address;
    private List<ArtWork> artWorks;
    private List<ArtWork> cart;
    private LocalDateTime logInTime;
}
