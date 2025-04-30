package com.bytebuilder.dtos;

import com.bytebuilder.data.models.ArtWork;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserLogInResponse {
    private String id;
    private String email;
    private String phoneNumber;
    private String fullName;
    private Object address;
    private List<ArtWork> artWorks;
    private List<ArtWork> cart;
}
