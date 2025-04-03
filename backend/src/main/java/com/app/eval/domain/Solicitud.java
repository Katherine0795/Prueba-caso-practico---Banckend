package com.app.eval.domain;

import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.*;
import java.time.LocalDate;

@Table("tb_solicitud")
public class Solicitud {
	@Id
	@Column("idsolicitud")
	private Integer idSolicitud;
	
	private String marca;
	
	@Column("tiposolicitud")
	private String tipoSolicitud;

	@Column("fechaenv")
	private LocalDate fechaEnvio;
	
	@Column("numerocontacto")
	private String numeroContacto;
	
	@Column("nombrecontacto")
	private String nombreContacto;

	public Integer getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Integer idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public LocalDate getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(LocalDate fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getNumeroContacto() {
		return numeroContacto;
	}

	public void setNumeroContacto(String numeroContacto) {
		this.numeroContacto = numeroContacto;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	
}
