package com.example.backtcc.domain.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LatitudeLongitude {

    private String latitude;
    private String longitude;
}
