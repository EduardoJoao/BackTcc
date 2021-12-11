package com.example.backtcc.domain.ApiExterna;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeoLocalizationResponse {

    private String lat;
    private String lon;
    private EnderecoApiExterna address;
}
