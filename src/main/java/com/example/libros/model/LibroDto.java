package com.example.libros.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa un objeto de transferencia de datos (DTO) para la entidad Libro.
 * Se utiliza como un objeto de transferencia de datos entre las capas de la
 * aplicación (cada de presentaicón y capa de servicio). Es una representación
 * simplificada de un libro que no está directamente relacionada con la base de
 * datos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroDto {

	/**
	 * Identificador único del libro.
	 */
	private Long id;

	/**
	 * Título del libro.
	 */
	@NotEmpty(message = "El título es obligatorio")
	@Size(max = 20, message = "El título debe tener como máximo 20 caracteres")
	private String titulo;

	/**
	 * Autor del libro.
	 */
	@NotEmpty(message = "El autor es obligatorio")
	@Size(max = 20, message = "El autor debe tener como máximo 20 caracteres")
	private String autor;

	/**
	 * Número ISBN del libro.
	 */
	@NotEmpty(message = "El ISBN es obligatorio")
	@Pattern(regexp = "^(\\d{10}|\\d{13})$", message = "El ISBN debe contener 10 o 13 dígitos")
	private String isbn;

	/**
	 * Año de publicación del libro.
	 */
	@NotNull(message = "El año es obligatorio")
	@Min(1900)
	@Max(2023)
	private Integer publicadoEn;
}
