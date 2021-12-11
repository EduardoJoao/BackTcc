package com.example.backtcc.domain.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BusacarEstadoCidadeResponse {

    private String estado;
    private String cidade;
}
