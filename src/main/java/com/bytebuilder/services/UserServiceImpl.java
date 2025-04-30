package com.bytebuilder.services;


import com.bytebuilder.data.models.ArtWork;
import com.bytebuilder.data.models.User;
import com.bytebuilder.data.repositories.ArtWorkRepository;
import com.bytebuilder.data.repositories.UserRepository;
import com.bytebuilder.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.bytebuilder.utils.Mapper.map;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private ArtWorkRepository artWorkRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArtWorkService artWorkService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void signUp(UserSignUpRequest userSignUpRequest) {
        if(userRepository.existsByEmail(userSignUpRequest.getEmail())) {
            throw new IllegalArgumentException("Email address already in use");
        }
        User user = map(userSignUpRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserLogInResponse logIn(UserLogInRequest userLogInRequest) {
        User user = userRepository.findByEmail(userLogInRequest.getEmail());
        boolean valid = passwordEncoder.matches(userLogInRequest.getPassword(), user.getPassword());
        if (valid) {
            user.setLogInTime(LocalDateTime.now());
            return map(user);
        }
        throw new IllegalArgumentException("invalid password");
    }

    @Override
    public List<ArtWork> uploadArtWork(UploadArtWorkRequest uploadArtWorkRequest) {
        CreateArtWorkRequest createArtWorkRequest = uploadArtWorkRequest.getCreateArtWorkRequest();
        ArtWork artWork = map(artWorkService.createArtWork(createArtWorkRequest));
        User user = userRepository.findByEmail(uploadArtWorkRequest.getEmail());
        List<ArtWork> artWorks = user.getArtWorks();
        if(artWorks == null) {
            artWorks = new ArrayList<>();
        }
        artWorks.add(artWork);
        user.setArtWorks(artWorks);
        userRepository.save(user);
        return user.getArtWorks();
    }

    @Override
    public List<ArtWork> takeDownArtWork(TakeDownRequest takeDownRequest) {
        User user = userRepository.findByEmail(takeDownRequest.getEmail());
        List<ArtWork> artWorks = user.getArtWorks();
        ArtWork artWorkToRemove = new ArtWork();
        for(ArtWork artWork : artWorks) {
            if(artWork.getId().equals(takeDownRequest.getArtWorkId())) {
                artWork.setStock(0);
                artWorkRepository.save(artWork);
                artWorkToRemove = artWork;
            }
        }
        artWorks.remove(artWorkToRemove);
        user.setArtWorks(artWorks);
        userRepository.save(user);
        return user.getArtWorks();
    }

    @Override
    public List<ArtWork> addToCart(ModifyCartRequest modifyCartRequest) {
        User user = userRepository.findByEmail(modifyCartRequest.getEmail());
        ArtWork artWork = artWorkRepository.findById(modifyCartRequest.getArtWorkId()).get();
        artWork.setStock(artWork.getStock()-1);
        artWorkRepository.save(artWork);
        List<ArtWork> cart = user.getCart();
        cart.add(artWork);
        user.setCart(cart);
        userRepository.save(user);
        return user.getCart();
    }

    @Override
    public List<ArtWork> removeFromCart(ModifyCartRequest modifyCartRequest) {
        User user = userRepository.findByEmail(modifyCartRequest.getEmail());
        List<ArtWork> cart = user.getCart();
        ArtWork artWorkToRemove = new ArtWork();
        for (ArtWork artWork : cart) {
            if(artWork.getId().equals(modifyCartRequest.getArtWorkId())) {
                artWorkToRemove = artWork;
            }
        }
        cart.remove(artWorkToRemove);
        ArtWork artWork = artWorkRepository.findById(modifyCartRequest.getArtWorkId()).get();
        artWork.setStock(artWork.getStock()+1);
        artWorkRepository.save(artWork);
        user.setCart(cart);
        userRepository.save(user);
        return user.getCart();
    }

    @Override
    public List<ArtWork> viewCart(String email) {
        User user = userRepository.findByEmail(email);
        return user.getCart();
    }

    @Override
    public List<ArtWork> viewArtWorks(String email) {
        User user = userRepository.findByEmail(email);
        List<ArtWork> response = new ArrayList<>();
        for(ArtWork artWork: user.getArtWorks()){
            if (artWork.getStock()>0){
                response.add(artWork);
            }
        }
        return response;
    }

    @Override
    public List<CreateArtWorkResponse> viewGallery() {
        return artWorkService.getAllArtWorks();
    }

    @Scheduled(fixedRate = 1000 * 60 * 60 * 4 )
    private void clearCart(){
        int time = LocalDateTime.now().getHour();
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if(time - user.getLogInTime().getHour()>=4){
                List<ArtWork> artWorks = user.getArtWorks();
                for(ArtWork artWork : artWorks){
                    modifyCartRequest.setArtWorkId(artWork.getId());
                    modifyCartRequest.setEmail(user.getEmail());
                    removeFromCart(modifyCartRequest);
                }
            }
        }
    }

}
