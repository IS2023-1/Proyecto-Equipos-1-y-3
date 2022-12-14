package com.cienciasTop.models.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cienciasTop.models.entity.Producto;
import com.cienciasTop.models.service.IProductoService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/productos")
public class ProductoRestController {
	
	@Autowired
	private IProductoService productoService;
	
	/* ------------------------------ CREATE ------------------------------*/
	
	/**
     * Método para crear un nuevo producto.
     * @param producto Producto que será creado.
     * @return Regresa un mensaje de éxito si el producto fue creado, error en otro caso.
     */
	@Secured({"ROLE_ADMIN","ROLE_PROV"})
	@PostMapping("/agregar")
	public ResponseEntity<?> create(@RequestBody Producto producto) {
		Producto productoNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			productoNuevo = productoService.save(producto);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El producto ha sido creado con éxito :3");
		response.put("producto", productoNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	/* ------------------------------ CREATE ------------------------------*/
	
	/* ------------------------------ READ ------------------------------*/
	
	/**
     * Método para mostrar a todos los productos.
     * @return Regresa una lista con todos los productos.
     */

	@GetMapping("/buscar/todo") 
	public List<Producto> index(){
		 return productoService.findAll();
	}
	
	/**
     * Método para buscar a los productos por el nombre.
     * @param nombre Nombre de los productos a buscar.
     * @return Regresa a los productos que coincidan con el nombre, error en otro caso.
     */
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<?> findByNombre(@PathVariable String nombre){
        Producto producto = productoService.findByNombre(nombre);
        Map<String, Object> response = new HashMap<>();
        try {
            producto = productoService.findByNombre(nombre);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(producto == null){
            response.put("mensaje", "El usuario ID:".concat(nombre.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Producto>(producto,HttpStatus.OK);
    }
    
    /**
     * Método para buscar a los productos por el código.
     * @param codigo Código de los productos a buscar.
     * @return Regresa a los productos que coincidan con el código, error en otro caso.
     */
    @Secured({"ROLE_ADMIN","ROLE_PROV", "ROLE_USER"})
    @GetMapping("/buscar/codigo/{codigo}")
    public ResponseEntity<?> findByCodigo(@PathVariable String codigo){
        Producto producto = productoService.findByCodigo(codigo);
        Map<String, Object> response = new HashMap<>();
        try {
            producto = productoService.findByCodigo(codigo);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(producto == null){
            response.put("mensaje", "El usuario ID:".concat(codigo.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Producto>(producto,HttpStatus.OK);
    }
	/* ------------------------------ READ ------------------------------*/
	
	/* ------------------------------ UPDATE ------------------------------*/
	
    /**
     * Método para actualizar un producto por su id.
     * @param producto Producto a modificar.
     * @param id Identificador del producto a modificar.
     * @return Mensaje de éxito si el producto se modificó, error en otro caso.
     */
    @Secured({"ROLE_ADMIN","ROLE_PROV"})
	@PostMapping("/editar/{id}")
	//@ResponseStatus(HttpStatus.CREATED)
	public 	ResponseEntity<?> update(@RequestBody Producto producto, @PathVariable Long id) {
		Producto currentProducto = this.productoService.findById(id);
		Producto productoUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		//Error con el id ingresado
		if(currentProducto == null) {
			response.put("mensaje", "Error: no se puede editar el producto ID".concat(id.toString().concat(" no existe en la base de datos :(")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			currentProducto.setCodigo(producto.getCodigo());
			currentProducto.setNombre(producto.getNombre());
			currentProducto.setDescripcion(producto.getDescripcion());
			currentProducto.setDisponibles(producto.getDisponibles());
			currentProducto.setRutaImagen(producto.getRutaImagen());
			currentProducto.setCosto(producto.getCosto());
			currentProducto.setDiasAPrestar(producto.getDiasAPrestar());
			productoUpdate = productoService.save(currentProducto);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar el producto en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido actualizado con éxito :3");
		response.put("producto", productoUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	/* ------------------------------ UPDATE ------------------------------*/
	
	/* ------------------------------ delete ------------------------------*/
	
    /**
     * Método para eliminar un producto por su id.
     * @param id Identificador del producto a eliminar.
     * @return Regresa un mensaje de éxito si el producto se eliminó, error en otro caso.
     */
    @Secured({"ROLE_ADMIN","ROLE_PROV"})
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			productoService.delete(id);
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar el producto de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El producto ha sido eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	/* ------------------------------ Delete ------------------------------*/
	
}
