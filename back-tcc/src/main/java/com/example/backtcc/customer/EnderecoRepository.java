package com.example.backtcc.customer;

import com.example.backtcc.customer.rowmapper.EnderecoRowMapper;
import com.example.backtcc.domain.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class EnderecoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Endereco endereco) {
        return jdbcTemplate.update(
                "insert into ENDERECO (bairro, cep, cidade, endereco, estado, dtInclusao) values(?,?,?,?,?,?)",
                endereco.getBairro(), endereco.getCep(),endereco.getCidade(), endereco.getEndereco(),
                endereco.getEstado(), LocalDateTime.now());
    }

    public Endereco findByEnderecoCEP(String cep) {

        String sql = "SELECT * FROM ENDERECO WHERE cep = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{cep}, new EnderecoRowMapper());

    }

    public Endereco findByEnderecoEstadoCidade(String cidade, String estado) {

        String sql = "SELECT * FROM ENDERECO WHERE cidade = ? AND estado = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{cidade, estado}, new EnderecoRowMapper());

    }
}
