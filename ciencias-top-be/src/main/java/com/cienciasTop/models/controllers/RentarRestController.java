package com.cienciasTop.models.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cienciasTop.models.entity.Producto;
import com.cienciasTop.models.entity.Rentar;
import com.cienciasTop.models.entity.Usuario;
import com.cienciasTop.models.service.IProductoService;
import com.cienciasTop.models.service.IRentarService;
import com.cienciasTop.models.service.IUsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/rentar")
public class RentarRestController {
	
	@Autowired
    private IRentarService rentar_Service;
	
	@Autowired
	private IUsuarioService usuario_Service; 
	
	@Autowired
	private IProductoService producto_Service;
	
	/* ------------------------------ CREATE ------------------------------ */
	
	private static boolean buscar_si_esta_rentado(Long id_usuario, Long id_producto, Producto producto) {
		List<Rentar> usuarios = producto.getUsuario();
		if (usuarios.size() == 0) {
			return false;
		}
		for(int i=0;i<usuarios.size();i++) {
			if (id_usuario == usuarios.get(i).getUsuario().getId_usuario()
				&& id_producto == usuarios.get(i).getProducto().getId_producto()) {
				return true;
			}
		}
		return false;
	}
	
	@PostMapping("/agregar/{id_usuario}/{id_producto}")
	public ResponseEntity<?> create(@PathVariable Long id_usuario, @PathVariable Long id_producto){
        Map<String,Object> response = new HashMap<>();
        Usuario usuario = usuario_Service.findById(id_usuario);
    	Producto producto = producto_Service.findById(id_producto);
        LocalDate fecha_de_renta = LocalDate.now();
        LocalDate fecha_de_entrega = fecha_de_renta.plusDays(producto.getDiasAPrestar());
        Rentar renta = new Rentar();
        if (producto.getDisponibles() > 0
        	&& !buscar_si_esta_rentado(id_usuario, id_producto, producto)) {
        	try {
    			renta.setUsuario(usuario);
    			renta.setProducto(producto);
    			renta.setFecha_de_renta(fecha_de_renta);
    			renta.setFecha_de_entrega(fecha_de_entrega);
    			rentar_Service.save(renta);
    			producto.setDisponibles(producto.getDisponibles()-1);
    			producto_Service.save(producto);
            } catch(DataAccessException e) {
                response.put("mensaje", "Error al realizar el insert en la base de datos.");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
        	response.put("mensaje", "El producto ya está rentado por el usuario.");
        	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        response.put("mensaje","La renta del producto ha sido creado con éxito.");
        response.put("renta", renta);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	//@PostMapping("/devolver/{id_usuario}/{id_producto}")
	
	/* ------------------------------ CREATE ------------------------------ */
	
	/* ------------------------------ READE ------------------------------ */
	@GetMapping("/buscar/todo")
	public List<Rentar> index(){
    	return rentar_Service.findAll();
    }
	/* ------------------------------ READE ------------------------------ */
}
