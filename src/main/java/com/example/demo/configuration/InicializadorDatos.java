package com.example.demo.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.enumerado.Categoria;
import com.example.demo.model.Autor;
import com.example.demo.model.Libro;
import com.example.demo.repository.AutorRepositorio;
import com.github.javafaker.Faker;

@Component
public class InicializadorDatos implements CommandLineRunner  {
	 @Autowired
	 private AutorRepositorio autorRepository;
	 
	 private Faker faker = new Faker();
	 private final Integer AUTORES = 10;
	 private final Integer LIBROS = faker.number().numberBetween(1, 4);
	 
	@Override
	public void run(String... args) throws Exception {
		 List<Categoria> listaCategorias =Arrays.asList(Categoria.values());
	
	        List<Autor> autores = new ArrayList<>();

	        // Crear 10 autores
	        for (int i = 0; i < AUTORES; i++) {
	            Autor autor = new Autor();
	            autor.setNombre(faker.name().fullName());
	            autores.add(autor);

	            // Para cada autor, crear de 1 a 3 libros
	            for (int j = 0; j < LIBROS; j++) {
	                Libro libro = new Libro();
	                libro.setTitulo(faker.book().title());
	                libro.setAutor(autor);
	                libro.setCategoria(listaCategorias.get( faker.random().nextInt(listaCategorias.size())));
	                autor.getLibros().add(libro);
	            }
	        }

	        autorRepository.saveAll(autores);
	}

}
