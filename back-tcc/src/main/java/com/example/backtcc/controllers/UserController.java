package com.example.backtcc.controllers;

import com.example.backtcc.customer.CustomRepository;
import com.example.backtcc.customer.EnderecoRepository;
import com.example.backtcc.customer.UsuarioRepository;
import com.example.backtcc.domain.request.*;
import com.example.backtcc.domain.response.*;
import com.example.backtcc.domain.Endereco;
import com.example.backtcc.domain.Usuario;
import com.example.backtcc.request.RequestInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EnderecoRepository enderecoRepository;

    @Autowired
    CustomRepository customRepository;

    @Autowired
    RequestInformation requestInformation;

    @CrossOrigin
    @RequestMapping(value = {"/cadastro"}, method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody UserRequest userRequest) {

        enderecoRepository.save(Endereco.builder()
                .endereco(userRequest.getEndereco())
                .bairro(userRequest.getBairro())
                .cep(userRequest.getCep())
                .cidade(userRequest.getCidade())
                .estado(userRequest.getEstado())
                .build());

        var idEnd = enderecoRepository.findByEnderecoCEP(userRequest.getCep());

        var id = usuarioRepository.save(Usuario.builder()
            .nome(userRequest.getNome())
            .cpfcnpj(userRequest.getCpfCnpj())
            .contato(userRequest.getContato())
            .email(userRequest.getEmail())
            .senha(userRequest.getPassword())
            .tpUsuario(userRequest.getTpUsuario())
            .idend(idEnd.getId().longValue()).build());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = {"/login"}, method = RequestMethod.POST)
    public ResponseEntity<NullValue> login(@RequestBody LoginRequest loginRequest) {

       try {
           var user = usuarioRepository.findByUsuario(loginRequest.getEmail(), loginRequest.getPass());
           return new ResponseEntity<>(HttpStatus.OK);
       } catch (Exception e){
           e.printStackTrace();
       }
         return null;
    }

    @CrossOrigin
    @RequestMapping(value = {"/buscarLares"}, method = RequestMethod.POST)
    public ResponseEntity<List<OngsLaresResponse>> listLares(@RequestBody BuscarLaresRequest buscarLaresRequest) {

        try {

            if(buscarLaresRequest.getCidade().isBlank() && buscarLaresRequest.getEstado().isBlank()){
                new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }
            var user = customRepository.findByEnderecoEstadoCidade(
                    buscarLaresRequest.getCidade(),
                    buscarLaresRequest.getEstado(),
                    buscarLaresRequest.getTpUsuario());

            return ResponseEntity.ok(user);
        } catch (Exception e){
           new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @CrossOrigin
    @RequestMapping(value = {"/buscarEstadoCidade"}, method = RequestMethod.POST)
    public ResponseEntity<BusacarEstadoCidadeResponse> getEstadoCidade(@RequestBody BusacarEstadoCidadeRequest busacarEstadoCidadeRequest) {

        try {

            if(busacarEstadoCidadeRequest.getLatitude().isBlank() || busacarEstadoCidadeRequest.getLatitude().equals("0")
                    && busacarEstadoCidadeRequest.getLongitude().isBlank() || busacarEstadoCidadeRequest.getLongitude().equals("0")){
                new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }
            var user = requestInformation.requestInformationLocalToEndereco(busacarEstadoCidadeRequest.getLatitude(), busacarEstadoCidadeRequest.getLongitude());

            return ResponseEntity.ok(user);
        } catch (Exception e){
            new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }

    @CrossOrigin
    @RequestMapping(value = {"/buscarLatLon"}, method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<List<LatitudeLongitude>> getLatitudeLongitude(@RequestBody List<String> list) {
        List<LatitudeLongitude> listLocation = new ArrayList<>();
        try {
            if(list.isEmpty()){
                new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }

            list.forEach(endereco -> {
                var user = requestInformation.requestInformationEnderecoToLocal(endereco);

                listLocation.add(user);
            });


            return ResponseEntity.ok(listLocation);
        } catch (Exception e){
             new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}
