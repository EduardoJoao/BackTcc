package com.example.backtcc.domain.request;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BuscarLatitudeLongitudeRequest {

    private List<String> endereco;
}
