package com.example.backtcc.domain.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OngsLaresResponse {

    private Long id;
    private String nome;
    private String cpfcnpj;
    private String contato;
    private String tpUsuario;
    private String estado;
    private String cidade;
    private String cep;
    private String bairro;
    private String endereco;
}
