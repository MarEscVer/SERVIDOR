package com.example.mvc_coches.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.mvc_coches.model.Coche;
import com.example.mvc_coches.services.CocheService;

/**
 * Pruebas unitarias para el controlador {@link CocheController}.
 */
@WebMvcTest(CocheController.class)
public class CocheControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CocheService servicio;

    private Coche coche;

    @BeforeEach
    void setUp() {
        coche = new Coche();
        coche.setId(1);
        coche.setMarca("Toyota");
        coche.setMatricula("1234ABC");
        coche.setColor("Rojo");
    }

    //Prueba para la creación de un coche válido
    @Test
    void testCreateCoche() throws Exception {
    	when(servicio.saveCoche(any())).thenReturn(coche);
    	
    	mockMvc.perform(post("/agregar")
        	.flashAttr("coche", coche))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/coches"));
    }

    //Prueba para la creación de un coche no válido
    @Test
    void testCreateCoche_KO() throws Exception {
        Coche coche = new Coche(); // Coche sin datos para forzar errores de validación
        mockMvc.perform(post("/agregar")
        	.flashAttr("coche", coche))
            .andExpect(status().isOk())
            .andExpect(model().attributeHasErrors("coche"))
            .andExpect(view().name("form"));
    }

    //Prueba para ver el formulario de actualización de un coche.
    @Test
    void testSeeUpdateCocheForm() throws Exception {
        when(servicio.readCocheById(any())).thenReturn(coche);
        
        mockMvc.perform(get("/coche/editar/{id}", coche.getId()))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("coche"))
            .andExpect(view().name("form"));
    }
    
    //Prueba para ver el formulario de creación de un coche.
    @Test
    void testSeeCreateCocheForm() throws Exception {
        
        mockMvc.perform(get("/coche/nuevo"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("coche"))
            .andExpect(view().name("form"));
    }

    //Prueba para eliminar un coche.
    @Test
    void testDeleteCoche() throws Exception {
        
        mockMvc.perform(get("/coche/eliminar/{id}", coche.getId()))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("/coches"));
    }
    
    //Prueba para obtener todos los coches.
    @Test
    void testReadAllCoches() throws Exception{
    	when(servicio.readAllCoches()).thenReturn(Arrays.asList(coche));

        mockMvc.perform(get("/coches"))
                .andExpect(view().name("coches"))
                .andExpect(model().attributeExists("coches"))
                .andExpect(model().attribute("coches", Arrays.asList(coche)));
    }
    
}
