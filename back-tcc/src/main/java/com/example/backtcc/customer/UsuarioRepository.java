package com.example.backtcc.customer;

import com.example.backtcc.customer.rowmapper.EnderecoRowMapper;
import com.example.backtcc.customer.rowmapper.UsuarioRowMapper;
import com.example.backtcc.domain.Endereco;
import com.example.backtcc.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class UsuarioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int save(Usuario usuario) {
        return jdbcTemplate.update(
                "insert into usuario (nome, cpfCnpj, contato, email, senha, tpUsuario, idEnd, dtInclusao) values(?,?,?,?,?,?,?,?)",
                usuario.getNome(), usuario.getCpfcnpj(),usuario.getContato(),
                usuario.getEmail(), usuario.getSenha(), usuario.getTpUsuario(), usuario.getIdend(), LocalDateTime.now());
    }

    public Usuario findByUsuario(String email, String senha) {

        String sql = "SELECT * FROM USUARIO WHERE email = ? AND senha = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{email, senha}, new UsuarioRowMapper());

    }
}
