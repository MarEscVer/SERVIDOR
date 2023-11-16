package com.example.mvc_coches.services;

import java.util.List;

import com.example.mvc_coches.model.Coche;

import jakarta.validation.Valid;

public interface CocheService {

	List<Coche> readAllCoches();
	Coche saveCoche(@Valid Coche coche);
	Coche readCocheById(Integer id);
	void deleteCoche(Integer id);
}
