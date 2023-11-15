package com.example.libros.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.libros.entity.Libro;

/**
 * Clase de prueba para LibroRepository.
 */
@DataJpaTest
public class LibroRepositoryTest {

    @Autowired
    private LibroRepository libroRepository;

    private Libro libro;

    /**
     * Configuración inicial antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        libro = new Libro();
        libro.setAutor("Autor test");
        libro.setIsbn("1234567890");
        libro.setPublicadoEn(1999);
        libro.setTitulo("Libro test");
    }

    /**
     * Prueba para verificar el método save en LibroRepository.
     */
    @Test
    public void testSaveLibro() {
        Libro nuevoLibro = new Libro();
        nuevoLibro.setAutor("Nuevo test");
        nuevoLibro.setIsbn("1234567890");
        nuevoLibro.setPublicadoEn(1999);
        nuevoLibro.setTitulo("Nuevo test");
        Libro guardado = libroRepository.save(nuevoLibro);

        assertThat(guardado).hasFieldOrPropertyWithValue("autor", "Nuevo test");
    }

    /**
     * Prueba para verificar el método findById en LibroRepository.
     */
    @Test
    public void testFindLibroById() {
        libroRepository.save(libro);
        Libro encontrado = libroRepository.findById(libro.getId()).orElse(null);

        assertThat(encontrado).as("El libro encontrado no coincide con el libro guardado.").isEqualTo(libro);
    }

    /**
     * Prueba para verificar el método findAll en LibroRepository.
     */
    @Test
    public void testFindAllLibros() {
        libroRepository.save(libro);
        List<Libro> libros = (List<Libro>) libroRepository.findAll();

        assertThat(libros).as("La lista de libros está vacía.").isNotEmpty();
        assertThat(libros).as("La lista de libros no contiene el libro guardado.").contains(libro);
    }

    /**
     * Prueba para verificar el método delete en LibroRepository.
     */
    @Test
    public void testDeleteLibro() {
        libroRepository.save(libro);
        libroRepository.delete(libro);
        Libro eliminado = libroRepository.findById(libro.getId()).orElse(null);

        assertThat(eliminado).as("El libro no se eliminó correctamente.").isNull();
    }
}
