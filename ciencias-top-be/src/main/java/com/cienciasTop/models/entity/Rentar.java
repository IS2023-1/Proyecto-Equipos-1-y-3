package com.cienciasTop.models.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rentar")
public class Rentar implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "id_usuario", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "id_producto", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Producto producto;
	
	@Column(columnDefinition = "DATE")
	private LocalDate fecha_de_renta; 
	
	@Column(columnDefinition = "DATE")
	private LocalDate fecha_de_entrega;
	
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
	
	public LocalDate getFecha_de_renta() {
		return fecha_de_renta;
	}

	public void setFecha_de_renta(LocalDate fecha_de_renta) {
		this.fecha_de_renta = fecha_de_renta;
	}
	
	public LocalDate getFecha_de_entrega() {
		return fecha_de_entrega;
	}

	public void setFecha_de_entrega(LocalDate fecha_de_entrega) {
		this.fecha_de_entrega = fecha_de_entrega;
	}
}
