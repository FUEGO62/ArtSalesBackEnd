package com.bytebuilder.dtos;

import com.bytebuilder.data.models.ArtCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewCategoryRequest {

    @NotNull
    private ArtCategory category;
}
