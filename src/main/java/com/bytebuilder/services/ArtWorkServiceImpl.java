package com.bytebuilder.services;

import com.bytebuilder.data.models.ArtCategory;
import com.bytebuilder.data.models.ArtWork;
import com.bytebuilder.data.repositories.ArtWorkRepository;
import com.bytebuilder.dtos.CreateArtWorkRequest;
import com.bytebuilder.dtos.CreateArtWorkResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.bytebuilder.utils.Mapper.map;

@Service
public class ArtWorkServiceImpl implements ArtWorkService {

    @Autowired
    private ArtWorkRepository artWorkRepository;

    @Override
    public CreateArtWorkResponse createArtWork(CreateArtWorkRequest request) {
        ArtWork artWork = map(request);
        return map(artWorkRepository.save(artWork));
    }

    @Override
    public List<CreateArtWorkResponse> getAllArtWorks() {
        List<ArtWork> artWorks = artWorkRepository.findAll();
        List<CreateArtWorkResponse> gallery = new ArrayList<>();
        for (ArtWork artWork : artWorks) {
            if(artWork.getStock()>0){
                gallery.add(map(artWork));
            }
        }
        return gallery;
    }

    @Override
    public List<CreateArtWorkResponse> getAllArtWorksByCategory(ArtCategory category) {
        List<ArtWork> artWorks = artWorkRepository.findAll();
        List<CreateArtWorkResponse> gallery = new ArrayList<>();
        for (ArtWork artWork : artWorks) {
            if(artWork.getCategory()==category && artWork.getStock()>0){
                gallery.add(map(artWork));
            }
        }
        return gallery;
    }


}
