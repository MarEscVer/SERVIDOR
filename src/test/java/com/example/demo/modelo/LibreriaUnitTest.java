package com.example.demo.modelo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.demo.enumerado.Categoria;
import com.example.demo.model.Autor;
import com.example.demo.model.Libro;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class LibreriaUnitTest {
	private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @ParameterizedTest
    @CsvSource({
        "'El Señor de los Anillos', '1', 'FANTASIA', 'Libro válido'",
        "'', '1', 'FANTASIA', 'Título en blanco'",
        "'Harry Potter', , 'FANTASIA', 'ID en blanco'",
        "'Harry Potter', '1', '', 'Categoría en blanco'",
        "'El Señor de los Anillos', '1', 'FANTASIA', 'Libro válido'",
        "'', , '', 'Libro en blanco'"
    })
    void testValidationLibro(String titulo, String idAutor, String categoria, String mensajeTest) {
        Libro libro = new Libro();
        libro.setTitulo(titulo);

        Autor autor = new Autor();
        // Verificar si idAutor es null antes de convertirlo a Long
        autor.setId(idAutor != null && !idAutor.isEmpty() ? Long.parseLong(idAutor) : null);
        libro.setAutor(autor);

        // Verificar que la categoría no está en blanco y manejarla apropiadamente
        if (categoria != null && !categoria.isEmpty()) {
            // Convertir la cadena de categoría al enumerado solo si no está en blanco
            try {
                libro.setCategoria(Categoria.valueOf(categoria));
            } catch (IllegalArgumentException e) {
                // Manejar la excepción si la cadena no coincide con ningún valor en el enumerado
                fail("Categoría no válida: " + categoria);
            }
        }

        // Validar el libro utilizando el validador previamente configurado
        Set<ConstraintViolation<Libro>> violations = validator.validate(libro);

        // Verificar si hay incumplimientos de restricciones de validación
        if (violations.isEmpty()) {
            // Si no hay incumplimientos, la prueba pasa
            assertTrue(violations.isEmpty(), mensajeTest);
        } else {
            // Si hay incumplimientos, la prueba falla y se proporciona un mensaje descriptivo
            assertFalse(violations.isEmpty(), mensajeTest + " - Debería fallar la validación");
        }
    }


    @ParameterizedTest
    @CsvSource({
        "'J.K. Rowling', 'Autor válido'",
        "'', 'Nombre en blanco'",
        "'J.K. Rowling', 'Autor válido'",
        "'', 'Nombre en blanco'"
    })
    void testValidationAutor(String nombre, String mensajeTest) {
        Autor autor = new Autor();
        autor.setNombre(nombre);

        // Validar el autor utilizando el validador previamente configurado
        Set<ConstraintViolation<Autor>> violations = validator.validate(autor);

        // Verificar si hay incumplimientos de restricciones de validación
        if (violations.isEmpty()) {
            // Si no hay incumplimientos, la prueba pasa
            assertTrue(violations.isEmpty(), mensajeTest);
        } else {
            // Si hay incumplimientos, la prueba falla y se proporciona un mensaje descriptivo
            assertFalse(violations.isEmpty(), mensajeTest + " - Debería fallar la validación");
        }
    }
}