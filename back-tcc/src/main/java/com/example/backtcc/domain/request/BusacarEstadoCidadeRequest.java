package com.example.backtcc.domain.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusacarEstadoCidadeRequest {

    private String latitude;
    private String longitude;
}
