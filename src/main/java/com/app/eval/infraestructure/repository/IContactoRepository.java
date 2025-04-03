package com.app.eval.infraestructure.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.app.eval.domain.Contacto;
import reactor.core.publisher.Flux;

@Repository
public interface IContactoRepository extends ReactiveCrudRepository<Contacto, Integer> {
    Flux<Contacto> findByIdSolicitud(Integer idSolicitud);
}