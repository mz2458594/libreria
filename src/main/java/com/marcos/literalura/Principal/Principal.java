package com.marcos.literalura.Principal;

import com.marcos.literalura.models.*;
import com.marcos.literalura.repositories.LibroRepository;
import com.marcos.literalura.services.Convertir;
import com.marcos.literalura.services.TraerDatos;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private Scanner sn = new Scanner(System.in);
    private Convertir convertirDatos = new Convertir();
    private TraerDatos traerDatos = new TraerDatos();
    private final String URL = "https://gutendex.com/books/";
    private LibroRepository libroRepository;

    public Principal(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void menu() {
        Integer opcion = -1;
        while (opcion != 0) {
            System.out.println("""
                    Elija la opcion a traves de su numero;
                    1- buscar libro por titulo
                    2- listar libros registrados
                    3- listar autores registrados
                    4- listar autores vivos en un determinado año
                    5- listar libros por idioma
                    6- encontrar autor por nombre
                    0- salir
                    """);
            opcion = sn.nextInt();
            sn.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    librosRegistrados();
                    break;
                case 3:
                    autoresRegistrados();
                    break;
                case 4:
                    autoresByYear();
                    break;
                case 5:
                    autoresPorIdioma();
                    break;
                case 6:
                    obtenerAutorPorNombre();
                    break;
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Ingrese una opción valida");
                    break;
            }
        }
    }

    private void obtenerAutorPorNombre() {
        System.out.println("Ingrese el nombre del autor");
        var nombre = sn.nextLine();

        Optional<List<Autor>> autor = libroRepository.getAuthorByName(nombre).stream().findFirst();
        autor.ifPresentOrElse(System.out::println, () -> {
            System.out.println("No se encontro a ningun autor con ese nombre");
        });
    }

    private void autoresPorIdioma() {
        System.out.println("Ingrese el idioma que desea buscar");
        var language = sn.nextLine();


        libroRepository.findByIdioma(Idioma.obtenerValores(language)).forEach(System.out::println);
    }

    private void autoresByYear() {
        System.out.println("Ingrese el año");
        var year = sn.nextInt();

        libroRepository.findAuthorByYear(year).forEach(System.out::println);
    }

    private void autoresRegistrados() {
        libroRepository.getAutores().forEach(System.out::println);
    }

    private void librosRegistrados() {
        libroRepository.findAll().forEach(System.out::println);
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el titulo del libro");
        var titulo = sn.nextLine();
        var json = traerDatos.convertirDatos(URL + "?search=" + titulo.toLowerCase().replace(" ", "+"));
        var datos = convertirDatos.convertirDatos(Resultados.class, json);

        Optional<DatosLibro> libro = datos.datosLibro().stream()
                .findFirst();

        libro.ifPresent(e -> {
            //SI EL LIBRO YA EXISTE
            libroRepository.findByTitulo(e.titulo())
                    .ifPresentOrElse((l) -> {
                                System.out.println("Este titulo ya existe");
                            },
                            () -> {
                                Libro book = new Libro(e);
                                libroRepository.save(book);
                            });
        });

    }


}
