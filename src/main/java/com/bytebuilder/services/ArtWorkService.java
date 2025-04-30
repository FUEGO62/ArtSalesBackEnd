package com.bytebuilder.services;

import com.bytebuilder.data.models.ArtCategory;
import com.bytebuilder.dtos.CreateArtWorkRequest;
import com.bytebuilder.dtos.CreateArtWorkResponse;

import java.util.List;

public interface ArtWorkService {

    CreateArtWorkResponse createArtWork(CreateArtWorkRequest request);
    List<CreateArtWorkResponse> getAllArtWorks();
    List<CreateArtWorkResponse> getAllArtWorksByCategory(ArtCategory category);

}

