package com.example.backtcc.customer.rowmapper;

import com.example.backtcc.domain.Endereco;
import com.example.backtcc.domain.Usuario;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioRowMapper implements RowMapper<Usuario> {
    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Usuario.builder().id(rs.getLong("id"))
                .nome(rs.getString("nome"))
                .cpfcnpj(rs.getString("cpfcnpj"))
                .contato(rs.getString("contato"))
                .email(rs.getString("email"))
                .senha(rs.getString("senha"))
                .idend(rs.getLong("idend"))
                .dtInclusao(rs.getTimestamp("dtInclusao").toLocalDateTime())
                .build();
    }
}
