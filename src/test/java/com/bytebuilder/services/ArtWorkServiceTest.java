package com.bytebuilder.services;

import com.bytebuilder.data.models.ArtCategory;
import com.bytebuilder.data.models.ArtWork;
import com.bytebuilder.dtos.CreateArtWorkRequest;
import com.bytebuilder.dtos.CreateArtWorkResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ArtWorkServiceTest {

    @Autowired
    private ArtWorkService artWorkService;

    @Test
    public void testThatOneCanCreateArtWork() {

        CreateArtWorkRequest request = new CreateArtWorkRequest();
        request.setCategory(ArtCategory.NATURE);
        request.setTitle("mother nature");
        request.setDescription("a nice view");
        request.setPrice(10000);
        request.setStock(1);
        request.setImage(null);// null temporary for the test
        request.setAddress("weston road flows");
        CreateArtWorkResponse response = artWorkService.createArtWork(request);
        System.out.println(response.getId());
        assertEquals(response.getCategory(), ArtCategory.NATURE);

    }

    @Test
    public void testThatOneCanViewArtWorkBasedOnCategory() {
        List<CreateArtWorkResponse> artWorks = artWorkService.getAllArtWorksByCategory(ArtCategory.NATURE);
        assertEquals(3, artWorks.size());
        List<CreateArtWorkResponse> artWorks2 = artWorkService.getAllArtWorksByCategory(ArtCategory.ABSTRACT);
        assertEquals(0, artWorks2.size());
    }
}