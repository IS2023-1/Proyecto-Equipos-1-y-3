package is20231.cienciastopbe.models.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class Administrador extends Usuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "numTrabajador")
    private Long numTrabajador;

	public Long getNumTrabajador() {
		return numTrabajador;
	}

	public void setNumTrabajador(Long numTrabajador) {
		this.numTrabajador = numTrabajador;
	}
	
}
