package com.example.backtcc.domain;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class Usuario {

    private Long id;
    private String nome;
    private String cpfcnpj;
    private String contato;
    private String email;
    private String senha;
    private String tpUsuario;
    private Long idend;
    private LocalDateTime dtInclusao;

}
