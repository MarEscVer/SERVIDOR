package com.example.libros;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.libros.entity.Libro;
import com.example.libros.model.LibroDto;

/**
 * Clase de prueba para LibrosApplication.
 */
@SpringBootTest
class LibrosApplicationTests {

    /**
     * Prueba para verificar la correcta creación de instancias de LibroDto.
     */
    @Test
    void crearValoresCorrectoLibroDto() {

        LibroDto libroDto = new LibroDto();
        libroDto.setId(1L);
        libroDto.setAutor("Carlos Ruiz Zafón");
        libroDto.setIsbn("1234567890");
        libroDto.setPublicadoEn(1999);
        libroDto.setTitulo("Marina");

        assertEquals(1L, libroDto.getId(), "El ID no coincide");
        assertEquals("Carlos Ruiz Zafón", libroDto.getAutor(), "El Autor no coincide");
        assertEquals("1234567890", libroDto.getIsbn(), "El ISBN no coincide");
        assertEquals(1999, libroDto.getPublicadoEn(), "El año de publicación no coincide");
        assertEquals("Marina", libroDto.getTitulo(), "El Título no coincide");
    }

    /**
     * Prueba para verificar la correcta creación de instancias de Libro.
     */
    @Test
    void crearValoresCorrectoLibroEntity() {

        Libro libro = new Libro();
        libro.setId(1L);
        libro.setAutor("Carlos Ruiz Zafón");
        libro.setIsbn("1234567890");
        libro.setPublicadoEn(1999);
        libro.setTitulo("Marina");

        assertEquals(1L, libro.getId(), "El ID no coincide");
        assertEquals("Carlos Ruiz Zafón", libro.getAutor(), "El Autor no coincide");
        assertEquals("1234567890", libro.getIsbn(), "El ISBN no coincide");
        assertEquals(1999, libro.getPublicadoEn(), "El año de publicación no coincide");
        assertEquals("Marina", libro.getTitulo(), "El Título no coincide");
    }
}
