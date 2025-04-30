package com.bytebuilder.utils;

import com.bytebuilder.data.models.ArtWork;
import com.bytebuilder.data.models.User;
import com.bytebuilder.dtos.CreateArtWorkRequest;
import com.bytebuilder.dtos.CreateArtWorkResponse;
import com.bytebuilder.dtos.UserLogInResponse;
import com.bytebuilder.dtos.UserSignUpRequest;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Mapper {


    public static User map(UserSignUpRequest userSignUpRequest) {
        User user = new User();
        user.setPassword(userSignUpRequest.getPassword());
        user.setEmail(userSignUpRequest.getEmail());
        user.setAddress(userSignUpRequest.getAddress());
        user.setFullName(userSignUpRequest.getFullName());
        user.setPhoneNumber(userSignUpRequest.getPhoneNumber());
        user.setCart(new ArrayList<>());
        user.setArtWorks(new ArrayList<>());
        return user;
    }

    public static UserLogInResponse map(User user) {
        UserLogInResponse userLogInResponse = new UserLogInResponse();
        userLogInResponse.setEmail(user.getEmail());
        userLogInResponse.setFullName(user.getFullName());
        userLogInResponse.setPhoneNumber(user.getPhoneNumber());
        userLogInResponse.setAddress(user.getAddress());
        userLogInResponse.setId(user.getId());
        userLogInResponse.setCart(user.getCart());
        userLogInResponse.setArtWorks(user.getArtWorks());
        return userLogInResponse;
    }

    public static ArtWork map(CreateArtWorkRequest request) {
        ArtWork artWork = new ArtWork();
        artWork.setCategory(request.getCategory());
        artWork.setDescription(request.getDescription());
        artWork.setPrice(request.getPrice());
        artWork.setTitle(request.getTitle());
        artWork.setImage(request.getImage());
        artWork.setAddress(request.getAddress());
        artWork.setStock(request.getStock());
        return artWork;
    }

    public static CreateArtWorkResponse map(ArtWork artWork) {
        CreateArtWorkResponse createArtWorkResponse = new CreateArtWorkResponse();
        createArtWorkResponse.setCategory(artWork.getCategory());
        createArtWorkResponse.setDescription(artWork.getDescription());
        createArtWorkResponse.setPrice(artWork.getPrice());
        createArtWorkResponse.setTitle(artWork.getTitle());
        createArtWorkResponse.setImage(artWork.getImage());
        createArtWorkResponse.setAddress(artWork.getAddress());
        createArtWorkResponse.setStock(artWork.getStock());
        createArtWorkResponse.setId(artWork.getId());
        return createArtWorkResponse;
    }

    public static ArtWork map(CreateArtWorkResponse createArtWorkResponse) {
        ArtWork artWork = new ArtWork();
        artWork.setCategory(createArtWorkResponse.getCategory());
        artWork.setDescription(createArtWorkResponse.getDescription());
        artWork.setTitle(createArtWorkResponse.getTitle());
        artWork.setImage(createArtWorkResponse.getImage());
        artWork.setAddress(createArtWorkResponse.getAddress());
        artWork.setStock(createArtWorkResponse.getStock());
        artWork.setPrice(createArtWorkResponse.getPrice());
        artWork.setId(createArtWorkResponse.getId());
        return artWork;
    }
}
