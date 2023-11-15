package com.example.mapper;

import com.example.libros.entity.Libro;
import com.example.libros.model.LibroDto;

/**
 * Clase que proporciona métodos estáticos para mapear entre las entidades Libro y LibroDto.
 */
public class LibroMapper {

    /**
     * Convierte un objeto LibroDto a un objeto Libro.
     *
     * @param libroDto LibroDto a convertir.
     * @return Libro resultante de la conversión.
     */
    public static Libro maptoLibro(LibroDto libroDto) {
        return Libro.builder()
                .id(libroDto.getId())
                .autor(libroDto.getAutor())
                .isbn(libroDto.getIsbn())
                .publicadoEn(libroDto.getPublicadoEn())
                .titulo(libroDto.getTitulo())
                .build();
    }

    /**
     * Convierte un objeto Libro a un objeto LibroDto.
     *
     * @param libro Libro a convertir.
     * @return LibroDto resultante de la conversión.
     */
    public static LibroDto maptoLibroDto(Libro libro) {
        return LibroDto.builder()
                .id(libro.getId())
                .autor(libro.getAutor())
                .isbn(libro.getIsbn())
                .publicadoEn(libro.getPublicadoEn())
                .titulo(libro.getTitulo())
                .build();
    }
}
