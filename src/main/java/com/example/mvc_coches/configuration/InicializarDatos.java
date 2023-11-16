package com.example.mvc_coches.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.mvc_coches.model.Coche;
import com.example.mvc_coches.repository.CocheRepository;

@Component
public class InicializarDatos implements CommandLineRunner{
	
	@Autowired
    CocheRepository repositorio;

	@Override
	public void run(String... args) throws Exception {
		Coche c = new Coche();
		c.setColor("Rojo");
		c.setMatricula("123456H");
		c.setMarca("Renault");
		
		repositorio.save(c);
		
	}

}
