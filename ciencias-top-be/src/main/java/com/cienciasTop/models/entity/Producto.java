package com.cienciasTop.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_producto;

	@Column(name = "codigo", unique = true, updatable = false, nullable = false, columnDefinition = "CHAR(12) CHECK(CHAR_LENGTH(codigo) = 12)")
	private String codigo;

	@Column(name = "nombre", length = 50, nullable = false, unique = true)
	private String nombre;

	@Column(name = "descripcion", length = 128, nullable = false)
	private String descripcion;

	@Column(name = "disponibles", columnDefinition = "INT NOT NULL CHECK(disponibles >= 0)")
	private Integer disponibles;

	@Column(name = "ruta_imagen", length = 50, nullable = false)
	private String rutaImagen;

	@Column(name = "costo", columnDefinition = "INT NOT NULL CHECK(costo >= 0)")
	private Integer costo;

	@Column(name = "dias_a_prestar", columnDefinition = "INT NOT NULL CHECK(dias_a_prestar > 0)")
	private Integer diasAPrestar;

	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Rentar> usuario = new ArrayList<>();
	
	// ------------ Getters y Setters --------------

	public List<Rentar> getUsuario() {
		return usuario;
	}

	public void setUsuario(List<Rentar> usuario) {
		this.usuario = usuario;
	}

	public Long getId_producto() {
		return id_producto;
	}

	public void setId_producto(Long id_producto) {
		this.id_producto = id_producto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getDisponibles() {
		return disponibles;
	}

	public void setDisponibles(Integer disponibles) {
		this.disponibles = disponibles;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}

	public Integer getCosto() {
		return costo;
	}

	public void setCosto(Integer costo) {
		this.costo = costo;
	}

	public Integer getDiasAPrestar() {
		return diasAPrestar;
	}

	public void setDiasAPrestar(Integer diasAPrestar) {
		this.diasAPrestar = diasAPrestar;
	}

}
