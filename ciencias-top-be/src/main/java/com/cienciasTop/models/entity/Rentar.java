package com.cienciasTop.models.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Rentar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_usuario", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_producto", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Producto producto;
	
	@Column(name = "fecha_de_renta", columnDefinition = "TIMESTAMP")
	private LocalDateTime fecha_de_renta; 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public LocalDateTime getFecha_de_renta() {
		return fecha_de_renta;
	}

	public void setFecha_de_renta(LocalDateTime fecha_de_renta) {
		this.fecha_de_renta = fecha_de_renta;
	}
}
