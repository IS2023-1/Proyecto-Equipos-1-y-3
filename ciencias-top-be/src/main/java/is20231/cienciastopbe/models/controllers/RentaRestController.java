package is20231.cienciastopbe.models.controllers;

import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;


import org.apache.catalina.connector.Response;
import org.hibernate.exception.DataException;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import is20231.cienciastopbe.models.entity.Producto;
import is20231.cienciastopbe.models.service.IProductoService;

@RestController
@RequestMapping("/rentar")
public class RentaRestController {

    @Autowired
    private IProductoService productoService;
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("/buscar/idproducto/{idproducto}")
    public Optional<Producto> buscaIdProducto(@PathVariable Long idproducto) {
        return productoService.findByIdProducto(idproducto);
    }

    @GetMapping("/usuarios/{id}")
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
    @PutMapping("/usuarios/{id}")
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
            currentUsuario.setNombre(usuario.getNombre());
            currentUsuario.setApellidoPaterno(usuario.getApellidoPaterno());
            currentUsuario.setApellidoMaterno(usuario.getApellidoMaterno());
            currentUsuario.setCorreo(usuario.getCorreo());
            currentUsuario.setNumeroCel(usuario.getNumeroCel());
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

    @DeleteMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
        try {
            usuarioService.deleteById(id);
        } catch (Exception e) {
            response.put("mensaje", "Error al eliminar el usuario en la base de datos.");
            response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<Map<String,Object>>(response,HttpStatus.OK);
    }
/*     @GetMapping("buscar/nombre/{nombre}")
    public Optional<Producto> buscaNombre(@PathVariable String nombre) {
        return productoService.findByName(nombre);
    } */
}
