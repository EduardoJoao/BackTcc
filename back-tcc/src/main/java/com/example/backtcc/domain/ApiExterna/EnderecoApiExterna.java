package com.example.backtcc.domain.ApiExterna;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoApiExterna {

    private String city;
    private String state;
}
