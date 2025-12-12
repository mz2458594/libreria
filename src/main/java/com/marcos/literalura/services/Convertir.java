package com.marcos.literalura.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Convertir implements ConvertirDatos{

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertirDatos(Class<T> clase, String json){
        try {
        return mapper.readValue(json, clase);
        } catch (JsonProcessingException e){
            throw new RuntimeException("Error al procesar los datos");
        }
    }
}
