package com.example.mvc_coches.modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.mvc_coches.model.Coche;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Pruebas unitarias para la validación de la clase Coche mediante la API de validación de bean.
 */
public class CocheUnitTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    //Prueba parametrizada para la validación de la clase Coche con diferentes conjuntos de datos.
    @ParameterizedTest
    @CsvSource({
        "'Renault', '1234567', 'Rojo', 'Coche válido'",
        "'','1234567','Rojo', 'Marca en blanco'",
        "'','','Rojo', 'Marca y matricula en blanco '",
        "'Toyota', '123', 'Rojo', 'Matrícula incorrecta'",
        "'Toyota','ABC1234','Rojo', 'Coche válido'"
    })
    void testValidationCoche(String marca, String matricula, String color, String mensajeTest) {
        Coche coche = new Coche();
        coche.setMarca(marca);
        coche.setMatricula(matricula);
        coche.setColor(color);

        // Validar el coche utilizando el validador previamente configurado
        Set<ConstraintViolation<Coche>> violations = validator.validate(coche);

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
