package com.app.eval.domain;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.*;

@Table("tb_contacto")
public class Contacto {
	
	@Id
	@Column("idcontacto") 
    private Integer idContacto;
	
	@Column("idsolicitud")
	private Integer idSolicitud;
	
	@Column("numerocontacto")
	private String numeroContacto;
	
	@Column("nombrecontacto")
	private String nombreContacto;
	
	public Contacto() {}
	
	public Contacto(String numeroContacto, String nombreContacto, Integer idSolicitud) {
	    this.numeroContacto = numeroContacto;
	    this.nombreContacto = nombreContacto;
	    this.idSolicitud = idSolicitud;
	}

	public Integer getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Integer idContacto) {
		this.idContacto = idContacto;
	}

	public Integer getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(Integer idSolicitud) {
		this.idSolicitud = idSolicitud;
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
