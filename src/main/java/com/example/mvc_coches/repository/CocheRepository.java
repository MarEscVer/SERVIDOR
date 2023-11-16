package com.example.mvc_coches.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mvc_coches.model.Coche;

@Repository
public interface CocheRepository extends JpaRepository<Coche, Integer>{

}
 