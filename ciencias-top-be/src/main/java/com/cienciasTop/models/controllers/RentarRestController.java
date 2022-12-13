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
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/agregar")
	public ResponseEntity<?> create(@RequestBody Rentar renta,@PathVariable Long id_usuario, @PathVariable Long id_producto){
        Map<String,Object> response = new HashMap<>();
        Usuario usuario = usuario_Service.findById(id_usuario);
    	Producto producto = producto_Service.findById(id_producto);
    	
    	if (usuario == null && producto == null) {
    		response.put("mensaje", "Error: No se puede llevar a cabo la renta debido a que no "
    				      + "existe el usuario que renta o el producto a rentar");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
    	} else {
    		try {
    			LocalDate fecha_de_renta = LocalDate.now();
    			LocalDate fecha_de_entrega = fecha_de_renta.plusMonths(1);
            	renta.setUsuario(usuario);
            	renta.setProducto(producto);
            	renta.setFecha_de_renta(fecha_de_renta);
            	renta.setFecha_de_entrega(fecha_de_entrega);
            	rentar_Service.save(renta);
            } catch(DataAccessException e) {
                response.put("mensaje", "Error al realizar el insert en la base de datos.");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
            }
    	}
        response.put("mensaje","La renta del producto ha sido creado con éxito.");
        response.put("renta",renta);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	/* ------------------------------ CREATE ------------------------------ */
	
	/* ------------------------------ READE ------------------------------ */
	@GetMapping("/buscar/todo")
	public List<Rentar> index(){
    	return rentar_Service.findAll();
    }
	/* ------------------------------ READE ------------------------------ */
}
