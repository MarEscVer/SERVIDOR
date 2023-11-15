package com.example.libros.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import com.example.libros.model.LibroDto;
import com.example.libros.services.LibroService;

/**
 * Clase de prueba para LibroController.
 */
@WebMvcTest(LibroController.class)
public class LibroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibroService libroService;

    private LibroDto libroDto;

    /**
     * Configuración inicial antes de cada prueba.
     */
    @BeforeEach
    void setUp() {
        libroDto = new LibroDto();
        libroDto.setId(1L);
        libroDto.setAutor("Autor test");
        libroDto.setIsbn("1234567890");
        libroDto.setPublicadoEn(1999);
        libroDto.setTitulo("Libro test");
    }

    /**
     * Prueba para verificar el método readAllLibros en LibroController.
     */
    @Test
    void testReadAllLibros() throws Exception {
        when(libroService.readAllLibro()).thenReturn(Arrays.asList(libroDto));

        mockMvc.perform(get("/"))
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("libros"))
                .andExpect(model().attribute("libros", Arrays.asList(libroDto)));
    }

    /**
     * Prueba para verificar el método seeCreateLibroForm en LibroController.
     */
    @Test
    void testSeeCreateLibroForm() throws Exception {
        mockMvc.perform(get("/libro/nuevo"))
                .andExpect(view().name("libro-form"))
                .andExpect(model().attributeExists("libro"));
    }

    /**
     * Prueba para verificar el método seeUpdateLibroForm en LibroController.
     */
    @Test
    void testSeeUpdateLibroForm() throws Exception {
        when(libroService.readLibro(any())).thenReturn(libroDto);

        mockMvc.perform(get("/libro/editar/{id}", libroDto.getId()))
                .andExpect(view().name("libro-form"))
                .andExpect(model().attributeExists("libro"))
                .andExpect(model().attribute("libro", libroDto));
    }

    /**
     * Prueba para verificar el método updateLibroForm en LibroController.
     */
    @Test
    void testUpdateLibroForm() throws Exception {
        when(libroService.updateLibro(any())).thenReturn(libroDto);

        mockMvc.perform(post("/libro/editar")
                .flashAttr("libro", libroDto))
                .andExpect(redirectedUrl("/"));
    }

    /**
     * Prueba para verificar el método updateLibroForm con errores en LibroController.
     */
    @Test
    void testUpdateLibroForm_KO() throws Exception {
        LibroDto libroDtoVacio = new LibroDto();
        mockMvc.perform(post("/libro/editar")
                .flashAttr("libro", libroDtoVacio))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("libro"))
                .andExpect(view().name("libro-form"));
    }

    /**
     * Prueba para verificar el método createLibro en LibroController.
     */
    @Test
    void testCreateLibro() throws Exception {
        mockMvc.perform(post("/libro")
                .flashAttr("libro", libroDto))
                .andExpect(redirectedUrl("/"));
    }

    /**
     * Prueba para verificar el método createLibro con errores en LibroController.
     */
    @Test
    void testCreateLibro_KO() throws Exception {
        LibroDto libroDtoVacio = new LibroDto();
        mockMvc.perform(post("/libro")
                .flashAttr("libro", libroDtoVacio))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("libro"))
                .andExpect(view().name("libro-form"));
    }

    /**
     * Prueba para verificar el método deleteLibro en LibroController.
     */
    @Test
    void testDeleteLibro() throws Exception {
        mockMvc.perform(get("/libro/eliminar/{id}", libroDto.getId()))
                .andExpect(redirectedUrl("/"));
    }
}
