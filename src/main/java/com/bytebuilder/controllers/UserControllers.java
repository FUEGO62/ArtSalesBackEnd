package com.bytebuilder.controllers;

import com.bytebuilder.data.models.ArtWork;
import com.bytebuilder.dtos.*;
import com.bytebuilder.services.ArtWorkService;
import com.bytebuilder.services.UserService;
import com.bytebuilder.utils.JwtUtil;
import com.bytebuilder.utils.StatusEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserControllers {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ArtWorkService artWorkService;

    @Autowired
    private UserService userService;
    
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserSignUpRequest userSignUpRequest, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.badRequest().body(entity);
        }
        try {
            userService.signUp(userSignUpRequest);
            entity.setMessage(HttpStatus.CREATED.toString());
            return ResponseEntity.status(HttpStatus.CREATED).body(entity);
        }
        catch (Exception e) {
            entity.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(entity);
        }
    }

    @PostMapping("/logIn")

    public ResponseEntity<?> logIn(@RequestBody @Valid UserLogInRequest userLogInRequest,BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.badRequest().body(entity);
        }
        try{
            UserLogInResponse userLogInResponse = userService.logIn(userLogInRequest);
            String token = jwtUtil.generateToken(userLogInRequest.getEmail());
            entity.setMessage(token);
            entity.setResponse(userLogInResponse);
            return ResponseEntity.ok().body(entity);
        }
        catch (NullPointerException e){
            entity.setMessage("invalid email or password");
            return ResponseEntity.badRequest().body(entity);
        }
        catch (Exception e) {
            entity.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(entity);
        }
    }

    @PostMapping("/uploadArtWork")
    public ResponseEntity<?> uploadArtWork(@RequestBody @Valid CreateArtWorkRequest createArtWorkRequest, BindingResult bindingResult, @AuthenticationPrincipal UserDetails userDetails) {

        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.badRequest().body(entity);
        }
        try {
            UploadArtWorkRequest uploadArtWorkRequest = new UploadArtWorkRequest();
            uploadArtWorkRequest.setEmail(userDetails.getUsername());
            uploadArtWorkRequest.setCreateArtWorkRequest(createArtWorkRequest);
            List<ArtWork> artworks = userService.uploadArtWork(uploadArtWorkRequest);
            entity.setMessage(HttpStatus.CREATED.toString());
            entity.setResponse(artworks);
            return ResponseEntity.ok().body(entity);
        }
        catch (NullPointerException e){
            entity.setMessage("invalid email or password");
            return ResponseEntity.badRequest().body(entity);
        }
        catch (Exception e) {
            entity.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(entity);
        }
    }

    @GetMapping("/viewGallery")
    public ResponseEntity<?> viewGallery(){
        return ResponseEntity.ok(userService.viewGallery());
    }

    @PostMapping("/viewBasedOnCategory")
    public ResponseEntity<?> viewBasedOnCategory(@RequestBody @Valid  ViewCategoryRequest request, BindingResult bindingResult){
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.badRequest().body(entity);
        }
        try {
            return ResponseEntity.ok(artWorkService.getAllArtWorksByCategory(request.getCategory()));
        }
        catch (Exception e) {
            entity.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(entity);
        }
    }

    @GetMapping("/viewArtworks")
    public ResponseEntity<?> viewArtWorks(@AuthenticationPrincipal UserDetails userDetails) {
        StatusEntity entity = new StatusEntity();
        try {
            return ResponseEntity.ok(userService.viewArtWorks(userDetails.getUsername()));
        }
        catch (NullPointerException e){
            entity.setMessage("invalid email or password");
            return ResponseEntity.badRequest().body(entity);
        }
        catch (Exception e) {
            entity.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(entity);
        }
    }

    @PostMapping("/addToCart")
    public ResponseEntity<?> addToCart(@AuthenticationPrincipal UserDetails userDetails,@Valid @RequestBody ModifyCartRequest request, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        try{
            List<ArtWork> response = userService.addToCart(request);
            entity.setResponse(response);
            entity.setMessage(HttpStatus.CREATED.toString());
            return ResponseEntity.ok().body(entity);
        }
        catch (NullPointerException e){
            entity.setMessage("invalid email or password");
            return ResponseEntity.badRequest().body(entity);
        }
        catch (Exception e) {
            entity.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(entity);
        }
    }

    @PostMapping("/removeFromCart")
    public ResponseEntity<?> removeFromCart(@AuthenticationPrincipal UserDetails userDetails,@Valid @RequestBody ModifyCartRequest request, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        System.out.println("hit method");
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.badRequest().body(entity);
        }
        try{
            System.out.println("hit try block");
            List<ArtWork> response = userService.removeFromCart(request);
            entity.setResponse(response);
            entity.setMessage(HttpStatus.CREATED.toString());
            System.out.println("finished try block");
            return ResponseEntity.ok().body(entity);
        }
        catch (NullPointerException e){
            entity.setMessage("invalid email or password");
            return ResponseEntity.badRequest().body(entity);
        }
        catch (Exception e) {
            System.out.println("error in question??");
            System.out.println(e);
            entity.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(entity);
        }
    }

    @GetMapping("/viewCart")
    public ResponseEntity<?> viewCart(@AuthenticationPrincipal UserDetails userDetails) {
        StatusEntity entity = new StatusEntity();
        try {
            return ResponseEntity.ok(userService.viewCart(userDetails.getUsername()));
        }
        catch (NullPointerException e){
            entity.setMessage("invalid email or password");
            return ResponseEntity.badRequest().body(entity);
        }
        catch (Exception e) {
            entity.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(entity);
        }
    }

    @PostMapping("/takeDownArtWork")
    public ResponseEntity<?> takeDownArtWork(@AuthenticationPrincipal UserDetails userDetails,@RequestBody  TakeDownRequest request, BindingResult bindingResult) {
        StatusEntity entity = new StatusEntity();
        if (bindingResult.hasErrors()) {
            entity.setMessage(bindingResult.getAllErrors().get(0).getDefaultMessage());
            return ResponseEntity.badRequest().body(entity);
        }
        try {
            List<ArtWork> response = userService.takeDownArtWork(request);
            entity.setResponse(response);
            entity.setMessage(HttpStatus.OK.toString());
            return ResponseEntity.ok().body(entity);
        }
        catch (NullPointerException e){
            entity.setMessage("invalid email or password");
            return ResponseEntity.badRequest().body(entity);
        }
        catch (Exception e) {
            entity.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(entity);
        }
    }

}
