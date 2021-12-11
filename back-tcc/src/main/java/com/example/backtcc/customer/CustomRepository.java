package com.example.backtcc.customer;

import com.example.backtcc.customer.rowmapper.OngsLaresResponseRowMapper;
import com.example.backtcc.domain.response.OngsLaresResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<OngsLaresResponse> findByEnderecoEstadoCidade(String cidade, String estado, String tpUsuario) {
        var request = new Object();
        StringBuilder sql = new StringBuilder("SELECT u.id, u.nome, u.cpfcnpj, u.contato, u.tpUsuario,")
                .append(" e.estado, e.cidade, e.cep, e.bairro, e.endereco ")
                .append(" FROM Endereco as e ")
                .append(" inner join usuario as u on u.idend = e.id ")
                .append(" where e.cidade = ? and e.estado = ? ");

        if(tpUsuario != null){
            sql.append(" and u.tpUsuario = ? ");
            request = new Object[]{cidade, estado, tpUsuario};
        }else {
            request = new Object[]{cidade, estado};
        }

       List<OngsLaresResponse>  list= jdbcTemplate.query(sql.toString(), (Object[]) request, new OngsLaresResponseRowMapper());

        return list;
    }
}
