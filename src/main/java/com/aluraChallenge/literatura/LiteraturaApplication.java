package com.aluraChallenge.literatura;

import com.aluraChallenge.literatura.application.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	private final Application application;

	@Autowired
	public LiteraturaApplication(Application application) {
		this.application = application;
	}

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		application.run();
		//TODO: 6) Interactuando con el usuario
		//TODO: 7) Consultar libros
		//TODO: 8) Consultar autores
		//TODO: 9) Persistencia de datos
		//TODO: 10) Listando libros por idiomas
		//TODO: 11) Listando autores vivos en determinado año

	}
}