package com.example.mvc_coches.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mvc_coches.model.Coche;
import com.example.mvc_coches.repository.CocheRepository;
import com.example.mvc_coches.services.CocheService;

import jakarta.validation.Valid;

@Service
public class CocheServiceImpl implements CocheService {

	@Autowired
	private CocheRepository repositorio;

	/**
	 * Recupera todos los coches disponibles en la base de datos.
	 * 
	 * @return Lista de todos los coches.
	 */
	@Override
	public List<Coche> readAllCoches() {
		return repositorio.findAll();
	}

	/**
	 * Guarda un coche en la base de datos. Si el coche ya existe (basado en el ID),
	 * será actualizado.
	 * 
	 * @param coche El coche a guardar.
	 */
	@Override
	public Coche saveCoche(@Valid Coche coche) {
		return repositorio.save(coche);
	}

	/**
	 * Busca un coche por su ID en la base de datos.
	 * 
	 * @param id El ID del coche que se quiere recuperar.
	 * @return El coche encontrado.
	 */
	@Override
	public Coche readCocheById(Integer id) {
		try {
			Coche coche = repositorio.findById(id).orElse(new Coche());
			return coche;
		} catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getLocalizedMessage());
		}
	}

	/**
	 * Elimina un coche de la base de datos por su ID.
	 * 
	 * @param id El ID del coche a eliminar.
	 */
	@Override
	public void deleteCoche(Integer id) {
		try {
			repositorio.deleteById(id);
		} catch (IllegalArgumentException e) {
			// Captura excepciones relacionadas con el acceso a la base de datos
			throw new IllegalArgumentException(e.getLocalizedMessage());
		}

	}

}
