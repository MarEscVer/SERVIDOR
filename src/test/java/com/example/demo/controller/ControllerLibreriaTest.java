package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.Autor;
import com.example.demo.model.Libro;
import com.example.demo.services.ServicioLibreria;

@WebMvcTest(LibroController.class)
public class ControllerLibreriaTest {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServicioLibreria servicioLibreria;

    private Libro libroEjemplo;
    private Autor autorEjemplo;

    @BeforeEach
    void setUp() {
        libroEjemplo = new Libro();
        libroEjemplo.setId(1L);
        libroEjemplo.setTitulo("Ejemplo de Libro");

        autorEjemplo = new Autor();
        autorEjemplo.setId(1L);
        autorEjemplo.setNombre("Autor Ejemplo");
    }

    @Test
    void testGuardarLibroSinErrores() throws Exception {
        mockMvc.perform(post("/libros")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("titulo", "Nuevo Libro")
                .param("autor.id", "1")
                .param("categoria", "FANTASIA"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/libros"));

        verify(servicioLibreria, times(1)).guardarLibro(any(Libro.class));
    }

    @Test
    void testActualizarLibroSinErrores() throws Exception {
        mockMvc.perform(post("/libros/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("titulo", "Libro Actualizado")
                .param("autor.id", "1")
                .param("categoria", "FANTASIA"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/libros"));

        verify(servicioLibreria, times(1)).actualizarLibro(eq(1L), any(Libro.class));
    }

    @Test
    void testEliminarLibro() throws Exception {
        mockMvc.perform(get("/libros/eliminar/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/libros"));

        verify(servicioLibreria, times(1)).eliminarLibro(eq(1L));
    }

    @Test
    void testObtenerInformacionAutor() throws Exception {
        when(servicioLibreria.obternerAutor(1L)).thenReturn(autorEjemplo);

        mockMvc.perform(get("/libros/autores/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("autor/info"))
                .andExpect(model().attribute("autor", autorEjemplo));
    }

    @Test
    void testNuevoFormAutor() throws Exception {
        mockMvc.perform(get("/libros/autores/nuevo"))
                .andExpect(status().isOk())
                .andExpect(view().name("autor/form"))
                .andExpect(model().attribute("autor", instanceOf(Autor.class)));
    }

    @Test
    void testNuevoAutorConErrores() throws Exception {
        mockMvc.perform(post("/libros/autores/nuevo")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nombre", ""))  // Nombre en blanco, debería generar un error de validación
                .andExpect(status().isOk())
                .andExpect(view().name("autor/form"))
                .andExpect(model().attributeHasFieldErrors("autor", "nombre"));
    }

    @Test
    void testNuevoAutorSinErrores() throws Exception {
        mockMvc.perform(post("/libros/autores/nuevo")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nombre", "Nuevo Autor"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/libros/autores/nuevo"));

        verify(servicioLibreria, times(1)).guardarAutor(any(Autor.class));
    }
}
