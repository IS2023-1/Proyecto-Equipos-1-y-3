package is20231.cienciastopbe.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rentar")
public class Rentar implements Serializable{

    private static final long serialVersionUID = 1L;
    
	@IdProducto
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
   
    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long id) {
        this.id = idProducto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}