package com.example.backtcc.request;

import com.example.backtcc.domain.ApiExterna.GeoLocalizationResponse;
import com.example.backtcc.domain.response.BusacarEstadoCidadeResponse;
import com.example.backtcc.domain.response.LatitudeLongitude;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


@Component
public class RequestInformation {

    Gson gson = new Gson();

    private RestTemplate getRestTeamplate(){
        return new RestTemplateBuilder().rootUri("https://nominatim.openstreetmap.org").build();
    }

    public BusacarEstadoCidadeResponse requestInformationLocalToEndereco(String latitude, String longitude){
        var rest = getRestTeamplate();
        var url = String.format("/reverse?format=jsonv2&lat=%s&lon=%s", latitude, longitude);
        var response = rest.exchange(url, HttpMethod.GET, null, String.class);

        GeoLocalizationResponse responseObject = gson.fromJson(response.getBody(), GeoLocalizationResponse.class);

        return BusacarEstadoCidadeResponse.builder()
                .estado(responseObject.getAddress().getState())
                .cidade(responseObject.getAddress().getCity())
                .build();
    }

    public LatitudeLongitude requestInformationEnderecoToLocal(String endereco){
        var rest = getRestTeamplate();
        var url = String.format("/?addressdetails=1&q=%s&format=json&limit=1", endereco);
        var response = rest.exchange(url, HttpMethod.GET, null, String.class);

        Type listType = new TypeToken<ArrayList<GeoLocalizationResponse>>(){}.getType();
        List<GeoLocalizationResponse> responseObject = gson.fromJson(response.getBody(), listType);


        return LatitudeLongitude.builder()
                .latitude(responseObject.get(0).getLat())
                .longitude(responseObject.get(0).getLon())
                .build();
    }
}
