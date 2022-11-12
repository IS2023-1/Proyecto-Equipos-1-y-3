package is20231.cienciastopbe.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", length = 50, nullable = false, unique = true)
    private String nombre;
    @Column(name = "apellidoPaterno", length = 50, nullable = false, unique = true)
    private String apellidoPaterno;
    @Column(name = "apellidoMaterno", length = 50, nullable = false, unique = true)
    private String apellidoMaterno;
    @Column(name = "correo", length = 50, nullable = false)
    private String correo;
    @Column(name = "numeroCel", nullable = false)
    private Integer numeroCel;
    @Column(name = "carrera", length = 50, nullable = false)
    private String carrera;
    @Column(name = "password", length = 20, nullable = false)
    private String password;
    @Column(name = "esActivo", length = 20, nullable = false)
    private Bool esActivo;
    @Column(name = "pumapuntos", length = 50, nullable = false)
    private Integer pumapuntos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Integer getNumeroCel() {
        return numeroCel;
    }

    public void setNumeroCel(Integer numeroCel) {
        this.numeroCel = numeroCel;
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

    public Bool getEsActivo() {
        return esActivo;
    }

    public void setEsActivo(Bool esActivo) {
        this.esActivo = esActivo;
    }
    
    public Integer getPumapuntos() {
        return pumapuntos;
    }

    public void setPumapuntos(Integer pumapuntos) {
        this.pumapuntos = pumapuntos;
    }
}