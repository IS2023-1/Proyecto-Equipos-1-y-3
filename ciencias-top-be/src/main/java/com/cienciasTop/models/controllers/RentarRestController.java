package com.cienciasTop.models.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

    /**
	 * Dado una lista devuelve el producto mas rentados del mes y lo elimina de la lista.
	 * @param lista la lista de rentas.
	 * @return nombre del producto mas rentado.
	 */
    private String productos_mas_rentado(List<Rentar> lista) {
		int valor2 = 0;
		int valor1 = 0;
		String nombre_producto = "";
		LocalDate actual = LocalDate.now();
		LocalDate limite = actual.minusMonths(1);
    	for(int i = 0; i < lista.size(); i++) {
			for(int j = i + 1; j < lista.size(); j++) {
			   if(lista.get(i).getProducto().getNombre().equals(lista.get(j).getProducto().getNombre()) && !lista.get(j).getFecha_de_renta().isBefore(limite)){
                  valor2++;
			   } 
			}

			if(valor2 > valor1){
				valor1 = valor2;
                nombre_producto = lista.get(i).getProducto().getNombre();
			}

			valor2 = 0;
		}
	 elimina_producto(lista, nombre_producto);
      return nombre_producto;
    }

	/**
	 * Dado una lista de rentas y el nombre de un producto, elimina todas las rentas del producto.
	 * @param lista lista de rentas.
	 * @param nombre nombre del producto.
	 */
    private void elimina_producto(List<Rentar> lista, String nombre){
        for(int i = 0; i < lista.size(); i++){
			if(lista.get(i).getProducto().getNombre().equals(nombre)) {
				lista.remove(i);
			}
		}
	}

	/**
	 * Devuelve una lista de los productos mas rentados del mes, 5 productos de ser posible.
	 * @return lista de los nombres de los productos.
	 */
	@Secured({"ROLE_ADMIN"})
    @GetMapping("/productos_mas_rentados")
	public List<String> productos_mas_rentados() {
    	List<Rentar> lista = rentar_Service.findAll();
        int cantidad_productos = 0;
		List<String> productos = new ArrayList<>();
		if(lista.size() < 5){
			cantidad_productos = lista.size();
		} else {
			cantidad_productos = 5;
		}

        while (cantidad_productos > 0) {
			productos.add(productos_mas_rentado(lista));
			cantidad_productos--;
		}

		return productos;

    }
    
    /**
	 * Dado una lista devuelve el usuario que mas ha rentado en la semana y lo elimina de la lista.
	 * @param lista la lista de rentas.
	 * @return el usuario que mas rento.
	 */
    private Usuario usuario_mas_rento(List<Rentar> lista) {
		int valor2 = 0;
		int valor1 = 0;
		Long id_usuario = 0L;
		Usuario usuario = new Usuario();
		LocalDate actual = LocalDate.now();
		LocalDate limite = actual.minusDays(7);
    	for(int i = 0; i < lista.size(); i++) {
			for(int j = i; j < lista.size(); j++) {
			   if(lista.get(i).getUsuario().getId_usuario().equals(lista.get(j).getUsuario().getId_usuario()) && !lista.get(j).getFecha_de_renta().isBefore(limite)){
                  valor2++;
			   } 
			}

			if(valor2 > valor1){
				valor1 = valor2;
                id_usuario = lista.get(i).getUsuario().getId_usuario();
				usuario = lista.get(i).getUsuario();
			}

			valor2 = 0;
		}
	    elimina_usuario(lista, id_usuario);
      return usuario;
    }

	/**
	 * Dado una lista de rentas y el id de un usuario, elimina todas las apariciones del usuario.
	 * @param lista lista de rentas.
	 * @param id id del usuario.
	 */
    private void elimina_usuario(List<Rentar> lista, Long id){
        for(int i = 0; i < lista.size(); i++){
			if(lista.get(i).getUsuario().getId_usuario().equals(id)) {
				lista.remove(i);
			}
		}
	}    

    /**
	 * Devuelve una lista con los nombres de los usuarios que mas han rentado de la semana, 5 usuarios de ser posible.
	 * @return lista de los nombres de los usuarios.
	 */
	@Secured({"ROLE_ADMIN"})
    @GetMapping("/usuarios_mas_han_rentado")
	public List<String> usuarios_mas_rentados() {
    	List<Rentar> lista = rentar_Service.findAll();
        int cantidad_usuarios = 0;
		List<String> usuarios = new ArrayList<>();
		String nombre_completo = "";
		if(lista.size() < 5){
			cantidad_usuarios = lista.size();
		} else {
			cantidad_usuarios = 5;
		}

        while (cantidad_usuarios > 0) {
			Usuario usuario = usuario_mas_rento(lista);
			nombre_completo = usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno();
			usuarios.add(nombre_completo);
			nombre_completo = "";
			cantidad_usuarios--;
		}
		return usuarios;
    }

	/* ------------------------------ READE ------------------------------ */
}
