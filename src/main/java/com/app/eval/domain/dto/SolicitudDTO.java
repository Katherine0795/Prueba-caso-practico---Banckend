package com.app.eval.domain.dto;

import java.time.LocalDate;
import java.util.List;

import com.app.eval.domain.Contacto;
import com.app.eval.domain.Solicitud;

public class SolicitudDTO {
    private Integer idSolicitud;
    private String marca;
    private String tipoSolicitud;
    private LocalDate fechaEnvio;
    private List<Contacto> contactos;
    
    public SolicitudDTO() {}

    public SolicitudDTO(Solicitud solicitud, List<Contacto> contactos) {
        this.idSolicitud = solicitud.getIdSolicitud();
        this.marca = solicitud.getMarca();
        this.tipoSolicitud = solicitud.getTipoSolicitud();
        this.fechaEnvio = solicitud.getFechaEnvio();
        this.contactos = contactos;
    }

    public Integer getIdSolicitud() { return idSolicitud; }
    public void setIdSolicitud(Integer idSolicitud) { this.idSolicitud = idSolicitud; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getTipoSolicitud() { return tipoSolicitud; }
    public void setTipoSolicitud(String tipoSolicitud) { this.tipoSolicitud = tipoSolicitud; }

    public LocalDate getFechaEnvio() { return fechaEnvio; }
    public void setFechaEnvio(LocalDate fechaEnvio) { this.fechaEnvio = fechaEnvio; }

    public List<Contacto> getContactos() { return contactos; }
    public void setContactos(List<Contacto> contactos) { this.contactos = contactos; }

	@Override
	public String toString() {
		return "SolicitudDTO [idSolicitud=" + idSolicitud + ", marca=" + marca + ", tipoSolicitud=" + tipoSolicitud
				+ ", fechaEnvio=" + fechaEnvio + ", contactos=" + contactos.size() + "]";
	}
    
    
}