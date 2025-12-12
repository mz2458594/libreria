package com.marcos.literalura;

import com.marcos.literalura.Principal.Principal;
import com.marcos.literalura.repositories.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Autowired
	private LibroRepository libroRepository;

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository);
		principal.menu();
	}
}
