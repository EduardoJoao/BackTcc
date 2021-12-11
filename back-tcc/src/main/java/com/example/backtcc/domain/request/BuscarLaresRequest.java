package com.example.backtcc.domain.request;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BuscarLaresRequest {

    private String cidade;
    private String estado;
    private String tpUsuario;
}
