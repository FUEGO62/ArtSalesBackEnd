package com.bytebuilder.services;

import com.bytebuilder.data.models.ArtWork;
import com.bytebuilder.dtos.*;

import java.util.List;

public interface UserService {

    void signUp(UserSignUpRequest userSignUpRequest);
    UserLogInResponse logIn(UserLogInRequest userLogInRequest);
    List<ArtWork> uploadArtWork(UploadArtWorkRequest uploadArtWorkRequest);
    List<ArtWork> takeDownArtWork(TakeDownRequest takeDownRequest);
    List<ArtWork> addToCart(ModifyCartRequest modifyCartRequest);
    List<ArtWork> removeFromCart(ModifyCartRequest modifyCartRequest);
    List<ArtWork> viewCart(String email);
    List<ArtWork> viewArtWorks(String email);
    List<CreateArtWorkResponse> viewGallery();


}
