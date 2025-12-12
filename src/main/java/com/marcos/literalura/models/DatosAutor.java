package com.marcos.literalura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonProperty("name") String name,
        @JsonProperty("birth_year") Integer birth_year,
        @JsonProperty("death_year") Integer death_year
) {
}
