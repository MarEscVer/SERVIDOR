package com.example.libros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.libros.model.Idioma;
import com.example.libros.model.LibroDto;
import com.example.libros.services.LibroService;

import jakarta.validation.Valid;

/**
 * Controlador para gestionar las operaciones relacionadas con los libros.
 */
@Controller
public class LibroController {

    @Autowired
    LibroService libroService;

    /**
     * Maneja las solicitudes GET a la ruta raíz ("/") para mostrar todos los libros.
     * @param model El modelo que se utilizará en la vista.
     * @return La vista "index".
     */
    @GetMapping("/")
    public String readAllLibros(Model model) {
        model.addAttribute("libros", libroService.readAllLibro());
        model.addAttribute("idiomas", Idioma.values());
        return "index";
    }

    /**
     * Maneja las solicitudes GET a la ruta "/idioma" para cambiar el idioma.
     * @param idioma El idioma seleccionado.
     * @return Redirige según el idioma seleccionado.
     */
    @GetMapping("/idioma")
    public String idioma(@RequestParam String idioma) {
        // Redirige según el idioma seleccionado
        if (idioma.equals(Idioma.EN.toString())) {
            return "redirect:/?lang=en";
        } else {
            return "redirect:/?lang=es";
        }
    }

    /**
     * Maneja las solicitudes GET para mostrar el formulario de creación de libros.
     * @param model El modelo que se utilizará en la vista.
     * @return La vista "libro-form".
     */
    @GetMapping("/libro/nuevo")
    public String seeCreateLibroForm(Model model) {
        LibroDto libroDto = new LibroDto();
        model.addAttribute("libro", libroDto);
        return "libro-form";
    }

    /**
     * Maneja las solicitudes POST para procesar la creación de libros.
     * @param libro El libro a crear.
     * @param result El resultado de la validación.
     * @return Redirige a la página principal si la creación es exitosa, vuelve al formulario en caso de errores.
     */
    @PostMapping("/libro")
    public String createLibro(@Valid @ModelAttribute("libro") LibroDto libro, BindingResult result) {
        if (result.hasErrors()) {
            // En caso de errores de validación, volver al formulario
            return "libro-form";
        }
        libroService.createLibro(libro);
        return "redirect:/";
    }

    /**
     * Maneja las solicitudes GET para mostrar el formulario de edición de libros.
     * @param id El ID del libro a editar.
     * @param model El modelo que se utilizará en la vista.
     * @return La vista "libro-form".
     */
    @GetMapping("/libro/editar/{id}")
    public String seeUpdateLibroForm(@PathVariable Long id, Model model) {
        LibroDto libro = libroService.readLibro(id);
        model.addAttribute("libro", libro);
        return "libro-form";
    }

    /**
     * Maneja las solicitudes POST para procesar la edición de libros.
     * @param libro El libro a editar.
     * @param result El resultado de la validación.
     * @return Redirige a la página principal si la edición es exitosa, vuelve al formulario en caso de errores.
     */
    @PostMapping("/libro/editar")
    public String updateLibroForm(@Valid @ModelAttribute("libro") LibroDto libro, BindingResult result) {
        if (result.hasErrors()) {
            // En caso de errores de validación, volver al formulario
            return "libro-form";
        }
        libroService.updateLibro(libro);
        return "redirect:/";
    }
    
    /**
     * Maneja las solicitudes GET para eliminar un libro por su ID.
     * @param id El ID del libro a eliminar.
     * @return Redirige a la página principal
     */
	@GetMapping("/libro/eliminar/{id}")
	public String deleteLibro(@PathVariable Long id) {
		libroService.deleteLibro(id);
		return "redirect:/";
	}
}
