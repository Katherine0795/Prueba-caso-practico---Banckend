package com.app.eval.web.controller;

import com.app.eval.application.service.SolicitudService;
import com.app.eval.domain.dto.SolicitudDTO;
import com.opencsv.CSVWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/solicitudes")
@CrossOrigin
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    public Mono<SolicitudDTO> registrarSolicitud(@RequestBody SolicitudDTO solicitudDTO) {
        return solicitudService.registrarSolicitud(solicitudDTO)
            .flatMap(savedSolicitudDTO -> {
                return solicitudService.registrarContactos(savedSolicitudDTO)
                    .map(contactosGuardados -> {
                        savedSolicitudDTO.setContactos(contactosGuardados);
                        return savedSolicitudDTO;
                    });
            });
    }

    @GetMapping("/{id}")
    public Mono<SolicitudDTO> obtenerSolicitudPorId(@PathVariable("id") Integer id) {
        return solicitudService.buscarSolicitudPorId(id);
    }

    @GetMapping
    public Flux<SolicitudDTO> listarSolicitudes() {
        return solicitudService.listarSolicitudes();
    }
    
    @GetMapping("/exportar")
    public Mono<ResponseEntity<byte[]>> exportarSolicitudesCSV() {
        return solicitudService.listarSolicitudes()
            .collectList()  
            .map(solicitudes -> {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                try (CSVWriter writer = new CSVWriter(new OutputStreamWriter(outputStream))) {
                    writer.writeNext(new String[] { "ID Solicitud", "Marca", "Tipo Solicitud", "Fecha Envio" });

                    for (SolicitudDTO solicitud : solicitudes) {
                        writer.writeNext(new String[] {
                            String.valueOf(solicitud.getIdSolicitud()),  
                            solicitud.getMarca(),                   
                            solicitud.getTipoSolicitud(),             
                            solicitud.getFechaEnvio().toString()        
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=solicitudes.csv");
                headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(outputStream.toByteArray());
            });
    }
}