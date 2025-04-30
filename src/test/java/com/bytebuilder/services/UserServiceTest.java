package com.bytebuilder.services;

import com.bytebuilder.data.models.ArtCategory;
import com.bytebuilder.data.repositories.UserRepository;
import com.bytebuilder.dtos.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private ArtWorkService artWorkService;
//
//    @Test
//    public void testThatUserCanSignUpWithCorrectDetails() {
//        UserSignUpRequest request = new UserSignUpRequest();
//        request.setAddress("connal rd");
//        request.setEmail("jesse@gmail.com");
//        request.setPassword("12345678");
//        request.setPhoneNumber("09095321068");
//        request.setFullName("Jesse Ola-Israel");
//        userService.signUp(request);
//        assertEquals(1, userRepository.count());
//    }
//
//    @Test
//    public void testThatUserCanLogInWithValidCredentials() {
//        UserLogInRequest request = new UserLogInRequest();
//        request.setEmail("jesse@gmail.com");
//        request.setPassword("12345678");
//        UserLogInResponse userLogInResponse = userService.logIn(request);
//        assertEquals("jesse@gmail.com", userLogInResponse.getEmail());
//    }
//
//
//    @Test
//    public void testThatUserCantLogInWithInvalidCredentials() {
//        UserLogInRequest request = new UserLogInRequest();
//        request.setEmail("jesse@gmail.com");
//        request.setPassword("1234567");
//        assertThrows(IllegalArgumentException.class, () -> userService.logIn(request));
//    }
//
//    @Test
//    public void testThatUserCanUploadArtWork(){
//        String email = "jesse@gmail.com";
//
//        CreateArtWorkRequest request = new CreateArtWorkRequest();
//        request.setCategory(ArtCategory.NATURE);
//        request.setTitle("mother nature");
//        request.setDescription("a nice view");
//        request.setPrice(10000);
//        request.setStock(1);
//        request.setImage("//image path");a
//        request.setAddress("weston road flows");
//
//        UploadArtWorkRequest uploadArtWorkRequest = new UploadArtWorkRequest();
//        uploadArtWorkRequest.setEmail(email);
//        uploadArtWorkRequest.setCreateArtWorkRequest(request);
//        List<?> artworks = userService.uploadArtWork(uploadArtWorkRequest);
//        int length = userRepository.findByEmail(email).getArtWorks().size();
//        assertEquals(2, length);
//        int gallerySize = artWorkService.getAllArtWorks().size();
//        assertEquals(3, gallerySize);
//
//    }

}