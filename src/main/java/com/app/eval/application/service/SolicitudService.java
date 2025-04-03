package com.app.eval.application.service;

import com.app.eval.domain.Contacto;
import com.app.eval.domain.Solicitud;
import com.app.eval.domain.dto.SolicitudDTO;
import com.app.eval.infraestructure.repository.IContactoRepository;
import com.app.eval.infraestructure.repository.ISolicitudRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SolicitudService {
    private final ISolicitudRepository solicitudRepository;
    private final IContactoRepository contactoRepository;

    public SolicitudService(ISolicitudRepository solicitudRepository, 
                            IContactoRepository contactoRepository) {
        this.solicitudRepository = solicitudRepository;
        this.contactoRepository = contactoRepository;;
    }

    public Mono<SolicitudDTO> registrarSolicitud(SolicitudDTO solicitudDTO) {
        Solicitud solicitud = new Solicitud();
        solicitud.setMarca(solicitudDTO.getMarca());
        solicitud.setTipoSolicitud(solicitudDTO.getTipoSolicitud());
        solicitud.setFechaEnvio(solicitudDTO.getFechaEnvio());

        return solicitudRepository.save(solicitud)
            .map(savedSolicitud -> {
                solicitudDTO.setIdSolicitud(savedSolicitud.getIdSolicitud());
                return solicitudDTO;  
            });
    }

    public Mono<List<Contacto>> registrarContactos(SolicitudDTO solicitudDTO) {
        if (solicitudDTO.getContactos() != null && !solicitudDTO.getContactos().isEmpty()) {
            Flux<Contacto> contactos = Flux.fromIterable(solicitudDTO.getContactos())
                    .map(contactoDTO -> new Contacto(
                            contactoDTO.getNumeroContacto(),
                            contactoDTO.getNombreContacto(),
                            solicitudDTO.getIdSolicitud()));  

            return contactoRepository.saveAll(contactos)
                    .collectList();  
        } else {
            return Mono.just(Collections.emptyList()); 
        }
    }
    
    public Mono<SolicitudDTO> buscarSolicitudPorId(Integer idSolicitud) {
        return solicitudRepository.findById(idSolicitud)
                .flatMap(solicitud -> contactoRepository.findByIdSolicitud(idSolicitud)
                        .collectList()
                        .map(contactos -> new SolicitudDTO(solicitud, contactos))
                );
    }

    public Flux<SolicitudDTO> listarSolicitudes() {
        return solicitudRepository.findAll()
                .flatMap(solicitud -> contactoRepository.findByIdSolicitud(solicitud.getIdSolicitud())
                        .collectList()
                        .map(contactos -> new SolicitudDTO(solicitud, contactos))
                );
    }
}
