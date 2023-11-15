package com.example.libros.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.libros.entity.Libro;
import com.example.libros.repository.LibroRepository;
import com.example.libros.services.impl.LibroServiceImpl;
import com.example.mapper.LibroMapper;

/**
 * Clase de prueba para LibroServiceImpl.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class LibroServiceTest {

    @InjectMocks
    private LibroServiceImpl libroServiceImpl;

    @Mock
    private LibroRepository libroRepository;

    private Libro libro;

    /**
     * Configuración inicial antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        libro = new Libro();
        libro.setId(1L);
        libro.setAutor("Autor test");
        libro.setIsbn("1234567890");
        libro.setPublicadoEn(1999);
        libro.setTitulo("Libro test");
    }

    /**
     * Prueba para verificar el método readAllLibro.
     */
    @Test
    void testReadAllLibros() {
        when(libroRepository.findAll()).thenReturn(Arrays.asList(libro));

        assertEquals(Arrays.asList(libro).size(), libroServiceImpl.readAllLibro().size(),
                "Debería devolver una lista con un solo libro");
    }

    /**
     * Prueba para verificar el método readLibro con un ID válido.
     */
    @Test
    void testFindLibroById() {
        when(libroRepository.findById(any())).thenReturn(Optional.of(libro));

        assertEquals(libro.getId(), libroServiceImpl.readLibro(libro.getId()).getId(),
                "Debería devolver el ID del libro esperado");
    }

    /**
     * Prueba para verificar el manejo de excepciones en el método readLibro con un ID no válido.
     */
    @Test
    void testFindLibroById_KO() {
        when(libroRepository.findById(any())).thenThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class, () -> libroServiceImpl.readLibro(libro.getId()),
                "Debería lanzar una excepción IllegalArgumentException");
    }

    /**
     * Prueba para verificar el método createLibro.
     */
    @Test
    void testSaveLibro() {
        when(libroRepository.save(any())).thenReturn(libro);

        assertEquals(libro.getId(), libroServiceImpl.createLibro(LibroMapper.maptoLibroDto(libro)).getId(),
                "Debería devolver el ID del libro guardado");
    }

    /**
     * Prueba para verificar el método updateLibro.
     */
    @Test
    void testUpdateLibro() {
        when(libroRepository.save(any())).thenReturn(libro);

        assertEquals(libro.getId(), libroServiceImpl.updateLibro(LibroMapper.maptoLibroDto(libro)).getId(),
                "Debería devolver el ID del libro actualizado");
    }

    /**
     * Prueba para verificar el método deleteLibro sin excepciones.
     */
    @Test
    void testDeleteLibro() {
        doNothing().when(libroRepository).deleteById(libro.getId());

        assertDoesNotThrow(() -> libroServiceImpl.deleteLibro(libro.getId()),
                "No debería lanzar una excepción al eliminar un libro existente");
    }

    /**
     * Prueba para verificar el manejo de excepciones en el método deleteLibro.
     */
    @Test
    void testDeleteLibro_KO() {
        doThrow(new IllegalArgumentException()).when(libroRepository).deleteById(libro.getId());

        assertThrows(IllegalArgumentException.class, () -> libroServiceImpl.deleteLibro(libro.getId()),
                "Debería lanzar una excepción IllegalArgumentException al intentar eliminar un libro");
    }
}
