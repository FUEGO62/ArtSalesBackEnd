package com.bytebuilder.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TakeDownRequest {
    private String artWorkId;
    private String email;
}
