package com.example.demo.services;

import java.util.List;
import java.util.Set;

import com.example.demo.model.Autor;
import com.example.demo.model.Libro;

import jakarta.validation.Valid;


public interface ServicioLibreria {
	Libro guardarLibro(Libro libro);
	void eliminarLibro(Long id);
	Libro obtenerLibroPorId(Long id);
	List<Libro> listarTodosLosLibros();
	Libro actualizarLibro(Long id, Libro libro);
	Set<Autor> obtenerAutores();
	Autor obternerAutor(Long id);
	void guardarAutor(@Valid Autor autor);
	
}
