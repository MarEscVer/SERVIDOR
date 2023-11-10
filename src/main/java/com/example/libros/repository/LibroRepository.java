package com.example.libros.repository;

import org.springframework.stereotype.Repository;

import com.example.libros.entity.Libro;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface LibroRepository extends CrudRepository<Libro, Long>{

}
