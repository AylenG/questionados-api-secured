package com.app.Questionados.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Questionados.entity.Pregunta;

@Repository
public interface IPreguntaRepository extends JpaRepository<Pregunta, Long> {

	Page<Pregunta> findAll(Pageable pageable);
}
