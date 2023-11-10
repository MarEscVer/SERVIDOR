package com.example.libros.services.impl;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.example.libros.entity.Libro;
import com.example.libros.model.LibroDto;
import com.example.libros.repository.LibroRepository;
import com.example.libros.services.LibroService;
import com.example.mapper.LibroMapper;

@Service
public class LibroServiceImpl implements LibroService{

    @Autowired
    private LibroRepository libroRepo;

    /**
     * Crea un nuevo libro en la base de datos.
     *
     * @param libroDto Datos del libro a crear.
     * @return LibroDto creado y guardado en la base de datos.
     */
    @Override
    public LibroDto createLibro(LibroDto libroDto) {
        // Mapeo de libroDto (clase usada para transferencia de datos entre las capas de la aplicación) a libro (clase de la base de datos)
        Libro libro = LibroMapper.maptoLibro(libroDto);
        Libro libroSaved = libroRepo.save(libro);
        // Mapeo de libro (clase de la base de datos) a libroDto (clase usada para transferencia de datos entre las capas de la aplicación)
        return LibroMapper.maptoLibroDto(libroSaved);
    }

    /**
     * Elimina un libro de la base de datos por su ID.
     *
     * @param id ID del libro a eliminar.
     * @throws IllegalArgumentException Si hay un problema al acceder a la base de datos.
     */
    @Override
    public void deleteLibro(Long id) {
        try {
            libroRepo.deleteById(id);
        } catch (DataAccessException e) {
        	// Captura excepciones relacionadas con el acceso a la base de datos
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    /**
     * Actualiza un libro existente en la base de datos.
     *
     * @param libroDto Datos del libro a actualizar.
     * @return LibroDto actualizado y guardado en la base de datos.
     */
    @Override
    public LibroDto updateLibro(LibroDto libroDto) {
        Libro libro = LibroMapper.maptoLibro(libroDto);
        Libro libroUpdate = libroRepo.save(libro);
        return LibroMapper.maptoLibroDto(libroUpdate);
    }

    /**
     * Lee un libro de la base de datos por su ID.
     *
     * @param id ID del libro a leer.
     * @return LibroDto leído de la base de datos.
     * @throws IllegalArgumentException Si hay un problema al acceder a la base de datos.
     */
    @Override
    public LibroDto readLibro(Long id) {
        try {
            Libro libroRead = libroRepo.findById(id).orElse(new Libro());
            return LibroMapper.maptoLibroDto(libroRead);
        } catch (DataAccessException e) {
            // Captura excepciones relacionadas con el acceso a la base de datos
            throw new IllegalArgumentException(e.getLocalizedMessage());
        }
    }

    /**
     * Obtiene todos los libros de la base de datos.
     *
     * @return Iterable de LibroDto representando todos los libros en la base de datos.
     */
    @Override
    public Iterable<LibroDto> readAllLibro() {
        Iterable<Libro> libros = libroRepo.findAll();
        // Convierte los libros a un stream y mapea a LibroDto
        Stream<LibroDto> libroDtoStream = StreamSupport.stream(libros.spliterator(), false)
                .map(LibroMapper::maptoLibroDto);
        // Recolecta los resultados en una lista
        return libroDtoStream.collect(Collectors.toList());
    }
}
