package com.cienciasTop.models.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cienciasTop.models.entity.Usuario;
import com.cienciasTop.models.service.IUsuarioService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {
	
    @Autowired
    private IUsuarioService usuario_Service;
    
    @Autowired
	private BCryptPasswordEncoder passwordEncoder;
    
    /* ------------------------------ CRUD ------------------------------ */
    
    /* ------------------------------ CREATE ------------------------------ */
    /**
     * Método para crear un nuevo usuario.
     * @param usuario Usuario que será creado.
     * @return Regresa un mensaje de éxito si el usuario fue creado, error en otro caso.
     */
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/agregar")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Usuario usuario){
        Usuario usuarioNuevo = null;
        Map<String,Object> response = new HashMap<>();
        try {
            usuarioNuevo = usuario_Service.save(usuario);
            passwordEncoder.encode(usuarioNuevo.getPassword());
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El usuario ha sido creado con éxito.");
        response.put("usuario",usuarioNuevo);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }
    /* ------------------------------ CREATE ------------------------------ */
    
    /* ------------------------------ READ ------------------------------ */
    
    /**
     * Método para mostrar a todos los usuarios.
     * @return Regresa una lista con todos los usuarios.
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/buscar/todo")
    public List<Usuario> index(){
    	return usuario_Service.findAll();
    }
    
    /**
     * Método para mostrar un usuario por su id
     * @param id Identificador del usuario a buscar
     * @return Regresa al usuario con identificador id, en otro caso una excepción según el caso
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Usuario usuario = usuario_Service.findById(id);
        Map<String, Object> response = new HashMap<>();
        try {
            usuario = usuario_Service.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            // response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(usuario == null){
            response.put("mensaje", "El usuario ID:".concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
    }
    
    /**
     * Método para buscar un usuario por su número de cuenta.
     * @param cuenta Número de cuenta del usuario a buscar.
     * @return Regresa el usuario que coincide con la cuenta, error en otro caso.
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/buscar/cuenta/{cuenta}")
    public ResponseEntity<?> findByCuenta(@PathVariable Long cuenta){
        Usuario usuario = usuario_Service.findByCuenta(cuenta);
        Map<String, Object> response = new HashMap<>();
        try {
            usuario = usuario_Service.findByCuenta(cuenta);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(usuario == null){
            response.put("mensaje", "La cuenta:".concat(cuenta.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
    }
    
    /**
     * Método para buscar un usuario por su correo.
     * @param correo Correo del usuario a buscar.
     * @return Regresa al usuario que coincide con el correo o error en otro caso.
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/buscar/correo/{correo}")
    public ResponseEntity<?> findByCorreo(@PathVariable String correo){
        Usuario usuario = usuario_Service.findByCorreo(correo);
        Map<String, Object> response = new HashMap<>();
        try {
            usuario = usuario_Service.findByCorreo(correo);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(usuario == null){
            response.put("mensaje", "El correo:".concat(correo.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
    }
    
    /**
     * Método para buscar a los usuarios por el nombre.
     * @param nombre Nombre de los usuarios a buscar.
     * @return Regresa a los usuarios que coincidan con el nombre, error en otro caso.
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/buscar/nombre/{nombre}")
    public List<Usuario> findByNombre(@PathVariable String nombre){
    	return usuario_Service.findByNombre(nombre);
    }

     /**
     * Regresa la cantidad de alumnos activos por la carrera pasada.
     * @param carrera a buscar activos.
     * @return numero de alumnos activos por carrera pasada.
     */
    private int numero_activos_por_carrera(String carrera) {
    	List<Usuario> lista = usuario_Service.findAll();
    	int cantidad = 0;
        Usuario iterador = new Usuario();
    	for(int i = 0; i < lista.size(); i++){
            iterador = lista.get(i);
            if(iterador.getEsActivo() && iterador.getCarrera().equals(carrera))
              cantidad++;
        }
     return cantidad;
    }

    /**
     * Las carreras registradas en la pagina web.
     * @return lista de carreras registradas.
     */
    private List<String> carreras_registradas(){
        List<Usuario> lista = usuario_Service.findAll();
        List<String> carreras = new ArrayList<>();
        
        Usuario iterador = new Usuario();
        for(int i = 0; i < lista.size(); i++){
            iterador = lista.get(i);
            if(!estaDentro(iterador.getCarrera(), carreras))
                carreras.add(iterador.getCarrera());
        }        
      return carreras;
    }
    
    /**
     * Checa si un string esta dentro de la lista de string pasada.
     * @param valor string a buscar
     * @param lista lista donde buscar
     * @return True si esta dentro, false si no esta dentro.
     */
    private boolean estaDentro(String valor, List<String> lista){
        for(int i = 0; i < lista.size(); i++){
            if(lista.get(i).equals(valor))
               return true;
        }
        return false;
    }

    /**
     * Numero de alumnos activos por carrera.
     * @return Lista de arreglos por carrera, en cada arreglo la posicion 0 pertenece al nombre de la carrera y la posicion 1 a la cantidad de alumnos activos en esa carrera.
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/numero_activos")
    public List<String[]> numero_activos() {
        List<String> carreras = carreras_registradas();
        List<String[]> alumnos_carreras = new ArrayList<>();
        String[] valor = new String[2];
        for(int i = 0; i < carreras.size(); i++){
            valor[0] = carreras.get(i);
            valor[1] = numero_activos_por_carrera(carreras.get(i)) + "";
          alumnos_carreras.add(valor.clone());
        }
      return alumnos_carreras;
    }

    /**
     * Regresa la cantidad de usuarios inactivos en la pagina web.
     * @return cantidad de usuarios inactivos en la pagina.
     */
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/numero_inactivos")
    public int numero_inactivos() {
    	List<Usuario> lista = usuario_Service.findAll();
    	int cantidad = 0;
        Usuario iterador = new Usuario();
    	for(int i = 0; i < lista.size(); i++){
            iterador = lista.get(i);
            if(!iterador.getEsActivo())
              cantidad++;
        }
     return cantidad;
    }

    /**
	 * Dado una lista de usuarios y el id de un usuario, elimina todas las apariciones del usuario.
	 * @param lista lista de usuarios.
	 * @param id id del usuario.
	 */
    private void elimina_usuario(List<Usuario> lista, Long id){
        for(int i = 0; i < lista.size(); i++){
			if(lista.get(i).getId_usuario().equals(id)) {
				lista.remove(i);
			}
		}
	}
    
    /**
	 * Dado una lista devuelve el usuario que mas penalizaciones tiene y lo elimina de la lista.
	 * @param lista la lista de usuarios.
	 * @return el usuario que mas penalizaciones tiene.
	 */
    private Usuario usuario_mas_penalizado(List<Usuario> lista) {
		Long valor1 = 0L;
		Usuario usuario = new Usuario();
    	for(int i = 0; i < lista.size(); i++) {
			if(lista.get(i).getPenalizaciones() > valor1){
                valor1 = lista.get(i).getPenalizaciones();
                usuario = lista.get(i);
            }
		}
	    elimina_usuario(lista, usuario.getId_usuario());
      return usuario;
    }

    /**
	 * Devuelve una lista con los nombres de los usuarios que mas han tenido penalizaciones, 10 usuarios de ser posible.
	 * @return lista de los nombres de los usuarios.
	 */
	@Secured({"ROLE_ADMIN"})
    @GetMapping("/usuarios_mas_penalizados")
	public List<String> usuarios_mas_penalizados() {
    	List<Usuario> lista = usuario_Service.findAll();
        int cantidad_usuarios = 0;
		List<String> usuarios = new ArrayList<>();
		String nombre_completo = "";
		if(lista.size() < 10){
			cantidad_usuarios = lista.size();
		} else {
			cantidad_usuarios = 10;
		}

        while (cantidad_usuarios > 0) {
			Usuario usuario = usuario_mas_penalizado(lista);
			nombre_completo = usuario.getNombre() + " " + usuario.getApellidoPaterno() + " " + usuario.getApellidoMaterno();
			usuarios.add(nombre_completo);
			nombre_completo = "";
			cantidad_usuarios--;
		}
		return usuarios;
    }

    /* ------------------------------ READ ------------------------------ */
    
    /* ------------------------------ UPDATE ------------------------------ */
    
    /**
     * Método para actualizar un usuario por su id.
     * @param usuario Usuario a modificar.
     * @param id Identificador del usuario a modificar.
     * @return Mensaje de éxito si el usuario se modificó, error en otro caso.
     */
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/editar/{id}")
    public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id){
        Usuario current_Usuario = this.usuario_Service.findById(id);
        Usuario usuario_Update = null;
        Map<String,Object> response = new HashMap<>();
        if(current_Usuario == null){
            response.put("mensaje", "Error: no se puede editar el usuario ID:".concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
        	current_Usuario.setUsername(usuario.getUsername());
        	current_Usuario.setCuenta(usuario.getCuenta());
            current_Usuario.setNombre(usuario.getNombre());
            current_Usuario.setApellidoPaterno(usuario.getApellidoPaterno());
            current_Usuario.setApellidoMaterno(usuario.getApellidoMaterno());
            current_Usuario.setNumeroCel(usuario.getNumeroCel());
            current_Usuario.setCorreo(usuario.getCorreo());
            current_Usuario.setCarrera(usuario.getCarrera());
            current_Usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            current_Usuario.setEsActivo(usuario.getEsActivo());
            current_Usuario.setPumapuntos(usuario.getPumapuntos());
            current_Usuario.setRoles(usuario.getRoles());
            usuario_Update = usuario_Service.save(current_Usuario);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        response.put("mensaje","El usuario ha sido actualizado con éxito.");
        response.put("usuario", usuario_Update);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

      /**
     * Función para cambiar la contraseña de un usuario por su id.
     * @param password_1 Contraseña nueva.
     * @param id Identificador del usuario al que será actualizado su contraseña.
     * @return Mensaje de éxito si se actualizó la contraseña, error en otro caso.
     */
    @PostMapping("/updateContrasena/{password_1}/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable String password_1, @PathVariable Long id) {
            Usuario current_Usuario = this.usuario_Service.findById(id);
            Usuario usuario_Update = null;
            Map<String, Object> response = new HashMap<>();
            if (current_Usuario == null) {
                response.put("mensaje", "Error: no se puede cambiar la contraseña del usuario ID:"
                        .concat(id.toString().concat(" no existe en la base de datos.")));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            try {
                current_Usuario.setPassword(passwordEncoder.encode(password_1));
                usuario_Update = usuario_Service.save(current_Usuario);
            } catch (DataAccessException e) {
                response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.put("mensaje", "Se ha cambiado la contraseña con exito.");
            response.put("usuario", usuario_Update);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    
    /**
     * Método para sumar puntos a  un usuario por su id.
     * @param usuario Usuario a sumar puntos.
     * @param id Identificador del usuario a modificar.
     * @return Mensaje de éxito si el usuario se modificó, error en otro caso.
     */
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/sumarPuntos/{id}")
    public ResponseEntity<?> updateSumaPuntos(@RequestBody Usuario usuario, @PathVariable Long id){
        Usuario current_Usuario = this.usuario_Service.findById(id);
        Usuario usuario_Update = null;
        Map<String,Object> response = new HashMap<>();
        if(current_Usuario == null){
            response.put("mensaje", "Error: no se puede editar el usuario ID:".concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
        	if(usuario.getPumapuntos() < 0) {
        		response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
        		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        	}else {
        		current_Usuario.setPumapuntos(current_Usuario.getPumapuntos() + usuario.getPumapuntos());
        		usuario_Update = usuario_Service.save(current_Usuario);
        	}
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        response.put("mensaje","El usuario ha sido actualizado con éxito.");
        response.put("usuario", usuario_Update);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }
    
    /**
     * Método para sumar puntos a  un usuario por su id.
     * @param usuario Usuario a sumar puntos.
     * @param id Identificador del usuario a modificar.
     * @return Mensaje de éxito si el usuario se modificó, error en otro caso.
     */
    @Secured({"ROLE_ADMIN"})
    @PostMapping("/restarPuntos/{id}")
    public ResponseEntity<?> updateRestaPuntos(@RequestBody Usuario usuario, @PathVariable Long id){
        Usuario current_Usuario = this.usuario_Service.findById(id);
        Usuario usuario_Update = null;
        Map<String,Object> response = new HashMap<>();
        if(current_Usuario == null){
            response.put("mensaje", "Error: no se puede editar el usuario ID:".concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
        	if(usuario.getPumapuntos() < 0) {
        		response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
        		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        	}else {
        		current_Usuario.setPumapuntos(current_Usuario.getPumapuntos() - usuario.getPumapuntos());
        		usuario_Update = usuario_Service.save(current_Usuario);
        	}
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        response.put("mensaje","El usuario ha sido actualizado con éxito.");
        response.put("usuario", usuario_Update);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }
    
    /* ------------------------------ UPDATE ------------------------------ */
    
    /* ------------------------------ DELETE ------------------------------ */
    
    /**
     * Método para eliminar un usuario por su id.
     * @param id Identificador del usuario a eliminar.
     * @return Regresa un mensaje de éxito si el usuario se eliminó, error en otro caso.
     */
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
        try {
            usuario_Service.deleteById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el usuario en la base de datos.");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
		response.put("mensaje", "El usuario ha sido eliminado con exito");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }
    /* ------------------------------ DELETE ------------------------------ */
}
