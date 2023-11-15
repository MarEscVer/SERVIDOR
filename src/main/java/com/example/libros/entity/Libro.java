package com.example.libros.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa una entidad persistente en la base de datos. Esta clase Libro se
 * utiliza para modelar cómo se almacenan y recuperan los datos de libros en la
 * base de datos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Libro {

	/**
	 * Identificador único del libro.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Título del libro.
	 */
	@Column
	private String titulo;

	/**
	 * Autor del libro.
	 */
	@Column
	private String autor;

	/**
	 * Número ISBN del libro.
	 */
	@Column
	private String isbn;

	/**
	 * Año de publicación del libro.
	 */
	@Column
	private Integer publicadoEn;
}
