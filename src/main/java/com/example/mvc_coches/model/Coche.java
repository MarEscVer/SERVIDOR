package com.example.mvc_coches.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad que representa un coche en la base de datos. Contiene las propiedades
 * básicas de un coche y utiliza anotaciones para la validación de sus campos.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coche {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotBlank(message = "La marca no puede estar vacía")
	@Size(max = 50, message = "La marca no puede tener más de 50 caracteres")
	private String marca;

	@NotBlank(message = "La matrícula no puede estar vacía")
	@Size(min = 7, max = 7, message = "La matrícula debe tener 7 caracteres")
	private String matricula;

	@NotBlank(message = "El color no puede estar vacío")
	private String color;

	/**
	 * Ventajas de separar la lógica de negocio de los aspectos de presentación de la aplicación.
	 * La separación de la lógica de negocio de los aspectos de presentación en una
	 * aplicación web ofrece significativas ventajas. Facilita un mantenimiento más
	 * sencillo y menos propenso a errores, ya que los cambios en uno de los
	 * aspectos no afectan al otro. Mejora la escalabilidad, permitiendo que cada
	 * componente se desarrolle y escale de manera independiente. Esta separación
	 * también beneficia la gestión del equipo de desarrollo, permitiendo una
	 * especialización más clara y una colaboración más eficiente entre diseñadores
	 * y desarrolladores. Además, mejora la capacidad de prueba de la aplicación, ya
	 * que permite realizar pruebas más específicas y enfocadas en cada componente
	 * por separado, aumentando la calidad y la fiabilidad del software.
	 */
}