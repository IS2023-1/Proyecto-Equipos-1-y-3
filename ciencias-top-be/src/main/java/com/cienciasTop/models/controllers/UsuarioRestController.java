package com.cienciasTop.models.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cienciasTop.models.entity.Usuario;
import com.cienciasTop.models.service.IUsuarioService;

// @CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/usuarios")
public class UsuarioRestController {
    @Autowired
    private IUsuarioService usuarioService;
    
    @GetMapping("/buscar/todo")
    public List<Usuario> index(){
    	return usuarioService.findAll();
    }

    @GetMapping("/id/{id}")
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

    @PostMapping("/editar/{id}")
    //@ResponseStatus(HttpStatus.CREATED)
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
        response.put("mensaje","El usuario ha sido creado con éxito.");
        response.put("usuario",usuarioUpdate);
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
    @GetMapping("/buscar/{nombre}")
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
    @GetMapping("/cuenta/{cuenta}")
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
