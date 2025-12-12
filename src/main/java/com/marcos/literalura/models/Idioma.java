package com.marcos.literalura.models;

public enum Idioma {
    es("es","español") ,en("en","ingles");

    private String diminutivo;
    private String idiomaEspanol;


    private Idioma(String diminutivo, String idiomaEspanol){
        this.diminutivo = diminutivo;
        this.idiomaEspanol = idiomaEspanol;
    }

    public static Idioma obtenerValoresPorDiminutivo(String idioma){
        for (Idioma i : Idioma.values()){
            if (i.diminutivo.toLowerCase().equalsIgnoreCase(idioma)){
                return i;
            }
        }
        throw new IllegalArgumentException("No se encontro ningun idioma con el valor de " + idioma);
    }

    public static Idioma obtenerValores(String idioma){
        for (Idioma i : Idioma.values()){
            if (i.idiomaEspanol.toLowerCase().equalsIgnoreCase(idioma)){
                return i;
            }
        }
        throw new IllegalArgumentException("No se encontro ningun idioma con el valor de " + idioma);
    }

}
