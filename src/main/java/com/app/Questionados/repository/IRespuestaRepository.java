package com.app.Questionados.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Questionados.entity.Respuesta;

@Repository
public interface IRespuestaRepository extends JpaRepository<Respuesta, Long> {

}
