package com.marcos.literalura.services;

public interface ConvertirDatos {
    <T> T convertirDatos( Class<T> clase, String json);
}
