package com.cienciasTop.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_usuario;
	
	@Column(unique = true, length = 20)
	private String username;
	
	@Column(name = "cuenta", unique = true, updatable = true, nullable = false, columnDefinition = "BIGINT CHECK(cuenta BETWEEN 100000 AND 999999999)")
	private Long cuenta;

	@Column(name = "nombre", length = 50, nullable = false)
	private String nombre;

	@Column(name = "apellidoPaterno", length = 50, nullable = false)
	private String apellidoPaterno;

	@Column(name = "apellidoMaterno", length = 50, nullable = false)
	private String apellidoMaterno;

	@Column(name = "numero_cel", columnDefinition = "CHAR(10) NOT NULL CHECK(CHAR_LENGTH(numero_cel) = 10) UNIQUE")
	private Long numeroCel;

	@Column(name = "correo", columnDefinition = "VARCHAR(50) NOT NULL CHECK(correo LIKE '%.unam.mx%') UNIQUE")
	private String correo;

	@Column(name = "carrera", length = 50, nullable = true)
	private String carrera;

	@Column(name = "contrasena", length = 60, nullable = false)
	private String password;

	@Column(name = "esActivo", nullable = false)
	private Boolean esActivo;

	@Column(name = "pumapuntos", columnDefinition = "INT NOT NULL CHECK(pumapuntos >= 0 AND pumapuntos <= 500)")
	private Integer pumapuntos;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinTable(name="usuarios_roles", joinColumns= @JoinColumn(name="usuario_id"),
	inverseJoinColumns=@JoinColumn(name="role_id"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"usuario_id", "role_id"})})
	private List<Role> roles;
	
	// ----------- Getters y Setters --------------

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getCuenta() {
		return cuenta;
	}

	public void setCuenta(Long cuenta) {
		this.cuenta = cuenta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public Long getNumeroCel() {
		return numeroCel;
	}

	public void setNumeroCel(Long numeroCel) {
		this.numeroCel = numeroCel;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEsActivo() {
		return esActivo;
	}

	public void setEsActivo(Boolean esActivo) {
		this.esActivo = esActivo;
	}

	public Integer getPumapuntos() {
		return pumapuntos;
	}

	public void setPumapuntos(Integer pumapuntos) {
		this.pumapuntos = pumapuntos;
	}		
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
