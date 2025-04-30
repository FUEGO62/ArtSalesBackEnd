package com.bytebuilder.data.repositories;

import com.bytebuilder.data.models.ArtWork;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArtWorkRepository extends MongoRepository<ArtWork, String> {
}
