package com.example.libros.services;

import java.util.List;

import com.example.libros.model.LibroDto;

public interface LibroService{

	LibroDto createLibro(LibroDto libro);
	void deleteLibro(Long id);
	LibroDto updateLibro(LibroDto libro);
	LibroDto readLibro(Long id);
	List<LibroDto> readAllLibro();
	
}
