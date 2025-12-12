package com.marcos.literalura.repositories;

import com.marcos.literalura.models.Autor;
import com.marcos.literalura.models.Idioma;
import com.marcos.literalura.models.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT a FROM Libro l JOIN l.autores a")
    List<Autor> getAutores();

    @Query("SELECT a FROM Libro l JOIN l.autores a WHERE a.birth_year <= :year AND a.death_year > :year")
    List<Autor> findAuthorByYear(Integer year);

    List<Libro> findByIdioma(Idioma idioma);

    Optional<List<Libro>> findByTitulo(String titulo);

    @Query("SELECT DISTINCT a FROM Libro l JOIN l.autores a WHERE a.name ILIKE %:name%")
    Optional<List<Autor>> getAuthorByName(String name);

    List<Libro> findTop10ByOrderByDescargasDesc();
}
