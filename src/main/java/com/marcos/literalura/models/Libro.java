package com.marcos.literalura.models;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Autor> autores;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Idioma> idioma;
    private Long descargas;


    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idioma().stream()
//                .map(idioma -> {
//                    return Idioma.obtenerValores(idioma);
//                })
                .map(Idioma::obtenerValoresPorDiminutivo)
                .collect(Collectors.toList());
        ;

        this.autores = datosLibro.autores().stream()
                .map(e -> {
                    Autor autor = new Autor(e);
                    autor.setLibro(this);
                    return autor;
                })
                .collect(Collectors.toList());
        this.descargas = datosLibro.descargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        autores.forEach((e) -> {
            e.setLibro(this);
        });
        this.autores = autores;
    }

    public List<Idioma> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<Idioma> idioma) {
        this.idioma = idioma;
    }

    public Long getDescargas() {
        return descargas;
    }

    public void setDescargas(Long descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
//                ", autores=" + autores +
                ", descargas=" + descargas +
                '}';
    }
}
