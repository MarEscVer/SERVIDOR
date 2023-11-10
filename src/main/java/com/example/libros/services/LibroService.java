package com.example.libros.services;

import com.example.libros.model.LibroDto;

public interface LibroService{

	LibroDto createLibro(LibroDto libro);
	void deleteLibro(Long id);
	LibroDto updateLibro(LibroDto libro);
	LibroDto readLibro(Long id);
	Iterable<LibroDto> readAllLibro();
	
}
