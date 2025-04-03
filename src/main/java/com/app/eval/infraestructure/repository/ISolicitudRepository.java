package com.app.eval.infraestructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.app.eval.domain.Solicitud;

@Repository
public interface ISolicitudRepository extends ReactiveCrudRepository<Solicitud, Integer> {

}
