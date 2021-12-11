package com.example.backtcc.customer.rowmapper;

import com.example.backtcc.customer.Customer;
import com.example.backtcc.domain.Endereco;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EnderecoRowMapper implements RowMapper<Endereco> {

    @Override
    public Endereco mapRow(ResultSet rs, int rowNum) throws SQLException {

        return Endereco.builder().id(rs.getLong("id"))
                .cep(rs.getString("cep"))
                .endereco(rs.getString("endereco"))
                .bairro(rs.getString("bairro"))
                .cidade(rs.getString("cidade"))
                .estado(rs.getString("estado"))
                .dtInclusao(rs.getTimestamp("dtInclusao").toLocalDateTime())
                .build();


    }
}
