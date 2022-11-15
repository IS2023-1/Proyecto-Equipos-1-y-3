package is20231.cienciastopbe.models.entity;

import java.io.Serializable;

import javax.persistence.Column;

public class Proveedor extends Usuario implements Serializable {

	 private static final long serialVersionUID = 1L;

    @Column(name = "idProveedor")
    private Long idProveedor;

	public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	
    
}
