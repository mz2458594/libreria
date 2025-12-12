package com.marcos.literalura.services;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TraerDatos {

    public String convertirDatos(String url){
        HttpClient client = HttpClient.newBuilder().build();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(url))
                .build()
                ;
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException exception){
            throw new RuntimeException("No se encontro ningun libro");
        }

        return response.body();

    }

}
