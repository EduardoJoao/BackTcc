package com.example.backtcc.customer.rowmapper;

import com.example.backtcc.domain.response.OngsLaresResponse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OngsLaresResponseRowMapper implements RowMapper<OngsLaresResponse> {
    @Override
    public OngsLaresResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
        return OngsLaresResponse.builder()
                .id(rs.getLong("id"))
                .nome(rs.getString("nome"))
                .cpfcnpj(rs.getString("cpfcnpj"))
                .contato(rs.getString("contato"))
                .tpUsuario(verificaRetorno(rs.getString("tpUsuario")))
                .endereco(rs.getString("endereco"))
                .estado(rs.getString("estado"))
                .cidade(rs.getString("cidade"))
                .cep(rs.getString("cep"))
                .bairro(rs.getString("bairro"))
                .build();
    }

    private String verificaRetorno(String valor){
        if (valor.equals("lar")){
            return "Lar Temporário";
        } else {
            return "Ong";
        }

    }
}
