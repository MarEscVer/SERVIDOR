package com.example.mvc_coches.servicesImpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.example.mvc_coches.model.Coche;
import com.example.mvc_coches.repository.CocheRepository;
import com.example.mvc_coches.services.impl.CocheServiceImpl;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CocheServicesImplUnitTest {

	@InjectMocks
	private CocheServiceImpl servicio;

	@Mock
	private CocheRepository repositorio;

	private Coche coche;

	@BeforeEach
	void setUp() {
		coche = new Coche();
		coche.setId(1);
		coche.setMarca("Toyota");
		coche.setMatricula("1234ABC");
		coche.setColor("Rojo");
	}

	//Prueba para la lectura de todos los coches.
	@Test
    void testReadAllCoches() {
    	when(repositorio.findAll()).thenReturn(Arrays.asList(coche));
    	
    	assertEquals(Arrays.asList(coche).size(), servicio.readAllCoches().size(),
    			"Debería devolver una lista con un solo coche");
    	
    }

	//Prueba para guardar un coche.
	@Test
    void testSaveCoche() {
        when(repositorio.save(any())).thenReturn(coche);

        assertEquals(coche.getId(), servicio.saveCoche(coche).getId(),
                "Debería devolver el ID del coche guardado");
    }

	//Prueba para buscar un coche por ID
	@Test
    void testFindLibroById() {
        when(repositorio.findById(any())).thenReturn(Optional.of(coche));

        assertEquals(coche.getId(), servicio.readCocheById(coche.getId()).getId(),
                "Debería devolver el ID del coche esperado");
    }

	//Prueba para buscar un coche por ID que lanza una excepción
	@Test
    void testFindLibroById_KO() {
        when(repositorio.findById(any())).thenThrow(new IllegalArgumentException());

        assertThrows(IllegalArgumentException.class, () -> servicio.readCocheById(coche.getId()),
                "Debería lanzar una excepción IllegalArgumentException");
    }

	//Prueba para eliminar un coche existente
    @Test
    void testDeleteLibro() {
        doNothing().when(repositorio).deleteById(coche.getId());

        assertDoesNotThrow(() -> servicio.deleteCoche(coche.getId()),
                "No debería lanzar una excepción al eliminar un coche existente");
    }

    //Prueba para eliminar un coche que lanza una excepción
    @Test
    void testDeleteLibro_KO() {
        doThrow(new IllegalArgumentException()).when(repositorio).deleteById(coche.getId());

        assertThrows(IllegalArgumentException.class, () -> servicio.deleteCoche(coche.getId()),
                "Debería lanzar una excepción IllegalArgumentException al intentar eliminar un coche");
    }
	
}
