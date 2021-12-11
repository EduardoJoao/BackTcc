package com.example.backtcc.domain.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    private String nome;
    private String cpfCnpj;
    private String email;
    private String password;
    private String contato;
    private String endereco;
    private String cidade;
    private String bairro;
    private String estado;
    private String cep;
    private String tpUsuario;

}
