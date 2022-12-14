package com.cienciasTop.models.controllers;

import java.time.Duration;
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
	
	/**
	 * Metodo que busca si un producto ya esta rentado por un usuario.
	 * @param id_usuario Identificador del usuario con el que se verificara si ya rento un producto.
	 * @param id_producto Identificador del producto que puede estar rentado por un usuario.
	 * @param producto Producto donde se buscará en la lista de usuarios que hayan rentado este mismo
	 * producto si el usuario con identificador id_usuario ya lo rentó.
	 * @return True si el producto ya fue rentado por el usuario, False en otro caso.
	 */
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
	
	/**
	 * Método para crear una nueva renta a partir del identificador de un usuario y el identificador
	 * de un producto
	 * @param id_usuario Identificador del usuario que rentará el producto.
	 * @param id_producto Identificador del producto que será rentado.
	 * @return Regresa un mensaje de éxito si se pudo llevar a cabo una renta, en otro caso un mensaje
	 * de error.
	 */
	@PostMapping("/agregar/{id_usuario}/{id_producto}")
	public ResponseEntity<?> create(@PathVariable Long id_usuario, @PathVariable Long id_producto){
        Map<String,Object> response = new HashMap<>();
        Usuario usuario = usuario_Service.findById(id_usuario);
    	Producto producto = producto_Service.findById(id_producto);
        LocalDate fecha_de_renta = LocalDate.now();
        LocalDate fecha_de_entrega = fecha_de_renta.plusDays(producto.getDiasAPrestar());
        Rentar renta = new Rentar();
        /**
         * Caso donde el producto no está disponible.
         */
        if (producto.getDisponibles() == 0) {
        	response.put("mensaje", "El producto no está disponible.");
        	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        /**
         * Caso donde el usuario tiene insuficientes pumapuntos.
         */
        if (usuario.getPumapuntos() < producto.getCosto()) {
        	response.put("mensaje", "No cuentas con saldo suficiente.");
        	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
        /**
         * Caso donde el usuario ya rentó dicho producto.
         */
        if (buscar_si_esta_rentado(id_usuario, id_producto, producto)) {
        	response.put("mensaje", "No puedes rentar de nuevo el producto.");
        	return new ResponseEntity<Map<String,Object>>(response,HttpStatus.BAD_REQUEST);
        }
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
        response.put("mensaje","La renta del producto ha sido creado con éxito.");
        response.put("renta", renta);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	/* ------------------------------ CREATE ------------------------------ */
	
	/* ------------------------------ UPDATE ------------------------------ */
	
	/**
	 * Metodo que busca una renta que coincida con el usuario y producto con 
	 * identificador id_usuario e id_producto respectivamente.
	 * @param id_usuario Identificador del usuario que rento un producto con identificador
	 * id_producto.
	 * @param id_producto Identificador del producto que fue rentado por el usuario con 
	 * identificador id_usuario.
	 * @param producto Producto donde se buscará en la lista de usuarios la renta que 
	 * coincida con el usuario y producto con identificador id_usuario e id_producto 
	 * respectivamente.
	 * @return Regresa un objeto Rentar que será la renta que coincida con el usuario y 
	 * producto con identificador id_usuario e id_producto respectivamente, en otro caso null. 
	 */
	private static Rentar buscar_renta(Producto producto, Long id_usuario, Long id_producto) {
		List<Rentar> usuarios = producto.getUsuario();
		if (usuarios.size() == 0) {
			return null;
		}
		for (int i=0; i<usuarios.size();i++) {
			if (usuarios.get(i).getUsuario().getId_usuario() == id_usuario
				&& usuarios.get(i).getProducto().getId_producto() == id_producto) {
				return usuarios.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Metodo que elimina una renta en la lista de usuarios que rentaron un producto
	 * que coincida con el usuario y producto con identificador id_usuario e 
	 * id_producto respectivamente.
	 * @param id_usuario Identificador del usuario que rento un producto con identificador
	 * id_producto.
	 * @param id_producto Identificador del producto que fue rentado por el usuario con 
	 * identificador id_usuario.
	 * @param producto Producto donde se buscará en la lista de usuarios la renta a eliminar
	 * que coincida con el usuario y producto con identificador id_usuario e id_producto 
	 * respectivamente.
	 * @return Regresa un objeto Producto sin la renta que coincida con el usuario y 
	 * producto con identificador id_usuario e id_producto respectivamente, en otro caso regresa el
	 * producto original. 
	 */
	private static Producto eliminar_renta(Producto producto, Long id_usuario, Long id_producto) {
		List<Rentar> usuarios = producto.getUsuario();
		if (usuarios.size() == 0) {
			return producto;
		}
		for (int i=0; i<usuarios.size();i++) {
			if (usuarios.get(i).getUsuario().getId_usuario() == id_usuario
				&& usuarios.get(i).getProducto().getId_producto() == id_producto) {
				usuarios.remove(i);
			}
		}
		producto.setUsuario(usuarios);
		return producto;
	}
	
	/**
	 * Método que devuelve un producto.
	 * @param id_usuario Identificador del usuario que devolverá un producto.
	 * @param id_producto Identificador del producto que será devuelto.
	 * @return Regresa un mensaje de éxito si el producto fue devuelto a tiempo, en otro caso un
	 * mensaje de penalización por devolución tardía.
	 */
	@PostMapping("/devolver/{id_usuario}/{id_producto}")
	public ResponseEntity<?> devolverProducto(@PathVariable Long id_usuario, @PathVariable Long id_producto) {
		Map<String,Object> response = new HashMap<>();
		Producto producto = producto_Service.findById(id_producto);
		Usuario usuario = usuario_Service.findById(id_usuario);
		Rentar renta = buscar_renta(producto, id_usuario, id_producto);
		LocalDate entrega_virtual = renta.getFecha_de_entrega();
		LocalDate entrega_fisica = LocalDate.now();		
		
		if (!entrega_fisica.isBefore(entrega_virtual)) {
			producto = eliminar_renta(producto, id_usuario, id_producto);
			producto.setDisponibles(producto.getDisponibles()+1);
			usuario.setPumapuntos(usuario.getPumapuntos() - 20);
			producto_Service.save(producto);
			usuario_Service.save(usuario);
			response.put("mensaje","El producto con ID: "
					                .concat(Long.toString(id_producto))
					                .concat(" se devolvió tarde"));
			return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
		}
		response.put("mensaje", "El producto fue devuelto a tiempo.");
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	/* ------------------------------ UPDATE ------------------------------ */
	
	
	/* ------------------------------ READE ------------------------------ */
	
	/**
	 * Método que devuelve todas las rentas.
	 * @return Una lista con todas las rentas.
	 */
	@GetMapping("/buscar/todo")
	public List<Rentar> index(){
    	return rentar_Service.findAll();
    }
	/* ------------------------------ READE ------------------------------ */
}
