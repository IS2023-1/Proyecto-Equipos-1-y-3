package com.cienciasTop.models.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.CrossOrigin;
>>>>>>> origin/Merge
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cienciasTop.models.entity.Usuario;
import com.cienciasTop.models.service.IUsuarioService;

<<<<<<< HEAD
// @CrossOrigin(origins = {"http://localhost:4200"})
=======
@CrossOrigin(origins = {"http://localhost:4200"})
>>>>>>> origin/Merge
@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {
    @Autowired
    private IUsuarioService usuarioService;
    
    @GetMapping("/buscar/todo")
    public List<Usuario> index(){
    	return usuarioService.findAll();
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> show(@PathVariable Long id){
        Usuario usuario = usuarioService.findById(id);
        Map<String, Object> response = new HashMap<>();
        try {
            usuario = usuarioService.findById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(usuario == null){
            response.put("mensaje", "El usuario ID:".concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
    }

    @PostMapping("/agregar")
    //@ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody Usuario usuario){
        Usuario usuarioNuevo = null;
        Map<String,Object> response = new HashMap<>();
        try {
            usuarioNuevo = usuarioService.save(usuario);
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","El usuario ha sido creado con éxito.");
        response.put("usuario",usuarioNuevo);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    // --------------------Caso de uso: Restablecer Contraseña------------------------------------------------------

    /**
     * Funcion auxiliar para verificar que dos contraseñas sean iguales.
     * 
     * @param password1 primera contraseña.
     * @param password2 segunda contraseña.
     * @return True si son iguales, False si son distintas.
     */
    private boolean sonIguales(String password1, String password2) {
        return password1.equals(password2);
    }

    /**
     * Funcion para cambiar la contraseña de un usuario segun su id y las dos
     * contraseñas dadas en el sistema.
     * 
     * @param password1 primer contraseña nueva pasada.
     * @param password2 segunda contraseña nueva pasada.
     * @param id        id del usuario al cual cambiar la contraseña.
     * @return Mensaje de error o exito segun sea el caso.
     */
    @PutMapping("/usuarios/updateContrasena/{password1}/{password2}/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateContrasena(@RequestBody String password1, @RequestBody String password2,
            @PathVariable Long id) {
        if (sonIguales(password1, password2)) {
            Usuario currentUsuario = this.usuarioService.findById(id);
            Usuario usuarioUpdate = null;
            Map<String, Object> response = new HashMap<>();
            if (currentUsuario == null) {
                response.put("mensaje", "Error: no se puede cambiar la contraseña del usuario ID:"
                        .concat(id.toString().concat(" no existe en la base de datos.")));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
            }
            try {
                currentUsuario.setPassword(password1);
                usuarioUpdate = usuarioService.save(currentUsuario);
            } catch (DataAccessException e) {
                response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
                response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
                return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.put("mensaje", "Se a cambiado la contraseña con exito :D.");
            response.put("usuario", usuarioUpdate);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Error: no se puede cambiar la contraseña del usuario ID:"
                    .concat(id.toString().concat(" ya que las contraseñas no son iguales.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Funcion para actualizar un usuario segun su id y el usuario modificado.
     * 
     * @param usuario usuario modificado en el frontend.
     * @param id        id del usuario al cual modificar.
     * @return Mensaje de error o exito segun sea el caso.
     */
    @PostMapping("/editar/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id){
        Usuario currentUsuario = this.usuarioService.findById(id);
        Usuario usuarioUpdate = null;
        Map<String,Object> response = new HashMap<>();
        if(currentUsuario == null){
            response.put("mensaje", "Error: no se puede editar el usuario ID:".concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
        	currentUsuario.setCuenta(usuario.getCuenta());
            currentUsuario.setNombre(usuario.getNombre());
            currentUsuario.setApellidoPaterno(usuario.getApellidoPaterno());
            currentUsuario.setApellidoMaterno(usuario.getApellidoMaterno());
            currentUsuario.setNumeroCel(usuario.getNumeroCel());
            currentUsuario.setCorreo(usuario.getCorreo());
            currentUsuario.setCarrera(usuario.getCarrera());
            currentUsuario.setPassword(usuario.getPassword());
            currentUsuario.setEsActivo(usuario.getEsActivo());
            currentUsuario.setPumapuntos(usuario.getPumapuntos());
            usuarioUpdate = usuarioService.save(currentUsuario);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }        
        response.put("mensaje","El usuario ha sido actualizado con éxito.");
        response.put("usuario", usuarioUpdate);
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
        try {
            usuarioService.deleteById(id);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al eliminar el usuario en la base de datos.");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
		response.put("mensaje", "El usuario ha sido eliminado con exito");
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }
    
    //Controlador para buscar un usuario por su nombre
    @GetMapping("/buscar/nombre/{nombre}")
    public List<Usuario> findByNombre(@PathVariable String nombre){
    	return usuarioService.findByNombre(nombre);
    }
    
    //Controlador para buscar un usuario por su correo
    @GetMapping("/buscar/correo/{correo}")
    public ResponseEntity<?> findByCorreo(@PathVariable String correo){
        Usuario usuario = usuarioService.findByCorreo(correo);
        Map<String, Object> response = new HashMap<>();
        try {
            usuario = usuarioService.findByCorreo(correo);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(usuario == null){
            response.put("mensaje", "El usuario ID:".concat(correo.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
    }
    
    //Controlador para buscar un usuario por su numero de cuenta o trabajador
    @GetMapping("/buscar/cuenta/{cuenta}")
    public ResponseEntity<?> findByCuenta(@PathVariable Long cuenta){
        Usuario usuario = usuarioService.findByCuenta(cuenta);
        Map<String, Object> response = new HashMap<>();
        try {
            usuario = usuarioService.findByCuenta(cuenta);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar la consulta en la base de datos");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        if(usuario == null){
            response.put("mensaje", "El usuario ID:".concat(cuenta.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Usuario>(usuario,HttpStatus.OK);
    }
    

}
