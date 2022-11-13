package is20231.cienciastopbe.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="productos")
public class Producto implements Serializable{

    private static final long serialVersionUID = 1L;
    
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idProducto;
    @Column(name = "nombre", length = 50, nullable = false, unique = true)
    private String nombre;
    @Column(name = "descripcion", length = 128, nullable = false)
    private String descripcion;
    @Column(name = "ruta_imagen", length = 50, nullable = false)
    private String rutaImagen;
    @Column(name = "costo", nullable = false)
    private Integer costo;
    @Column(name = "dias_a_prestar", nullable = false)
    private Integer diasAPrestar;
    @Column(name = "cantidad")
    private Integer cantidad;

    public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaImagen() {
        return this.rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public Integer getCosto() {
        return this.costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Integer getDiasAPrestar() {
        return this.diasAPrestar;
    }

    public void setDiasAPrestar(Integer diasAPrestar) {
        this.diasAPrestar = diasAPrestar;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

}