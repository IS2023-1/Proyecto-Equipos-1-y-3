package is20231.cienciastopbe.models.controllers;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import is20231.cienciastopbe.models.entity.Usuario;
import is20231.cienciastopbe.models.service.IUsuarioService;

@CrossOrigin (origins = {"http://localhost:5432"})
@RestController
@RequestMapping("/api")
public class AdministradorRestController extends UsuarioRestController {

    @Autowired
    private IUsuarioService usuarioService;

    // --------------------Caso de uso: Editar usuario------------------------------------------------------

    /**
     * Funcion auxiliar para actualizar el nombre del usuario con ID
     * 
     * @param nombre  nuevo nombre.
     * @param paterno nuevo apellido paterno.
     * @param materno nuevo apellido materno.
     * @param id      id del usuario a cambiar el nombre.
     * @return El mensaje de exito o error al cambiar los datos.
     */
    @PutMapping("/admin/updateNombre/{nombre}/{paterno}/{materno}/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateNombre(@RequestBody String nombre, @RequestBody String paterno,
            @RequestBody String materno, @PathVariable Long id) {
        Usuario currentUsuario = this.usuarioService.findById(id);
        Usuario usuarioUpdate = null;
        Map<String, Object> response = new HashMap<>();
        if (currentUsuario == null) {
            response.put("mensaje", "Error: no se puede editar el nombre del usuario ID:"
                    .concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            currentUsuario.setNombre(nombre);
            currentUsuario.setApellidoPaterno(paterno);
            currentUsuario.setApellidoMaterno(materno);
            usuarioUpdate = usuarioService.save(currentUsuario);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El usuario ha cambiado de nombre con éxito.");
        response.put("usuario", usuarioUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /**
     * Funcion auxiliar para actualizar el correo del usuario con ID
     * 
     * @param correo nuevo correo.
     * @param id     id del usuario a cambiar el correo.
     * @return El mensaje de exito o error al cambiar los datos.
     */
    @PutMapping("/admin/updateCorreo/{correo}/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateCorreo(@RequestBody String correo, @PathVariable Long id) {
        Usuario currentUsuario = this.usuarioService.findById(id);
        Usuario usuarioUpdate = null;
        Map<String, Object> response = new HashMap<>();
        if (currentUsuario == null) {
            response.put("mensaje", "Error: no se puede editar el correo del usuario ID:"
                    .concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            currentUsuario.setCorreo(correo);
            usuarioUpdate = usuarioService.save(currentUsuario);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El usuario ha sido cambiado de correo con éxito.");
        response.put("usuario", usuarioUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /**
     * Funcion auxiliar para actualizar el numero de celular del usuario con ID
     * 
     * @param numeroCel nuevo numero.
     * @param id        id del usuario a cambiar el numero de celular.
     * @return El mensaje de exito o error al cambiar los datos.
     */
    @PutMapping("/admin/updateNumero/{numeroCel}/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateNumero(@RequestBody Integer numeroCel, @PathVariable Long id) {
        Usuario currentUsuario = this.usuarioService.findById(id);
        Usuario usuarioUpdate = null;
        Map<String, Object> response = new HashMap<>();
        if (currentUsuario == null) {
            response.put("mensaje", "Error: no se puede editar el numero del usuario ID:"
                    .concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            currentUsuario.setNumeroCel(numeroCel);
            usuarioUpdate = usuarioService.save(currentUsuario);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El usuario ha cambiado con éxito su numero.");
        response.put("usuario", usuarioUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /**
     * Funcion auxiliar para actualizar la carrera del usuario con ID
     * 
     * @param carrera nueva carrera.
     * @param id      id del usuario a cambiar la carrera.
     * @return El mensaje de exito o error al cambiar los datos.
     */
    @PutMapping("/admin/updateCarrera/{carrera}/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateCarrera(@RequestBody String carrera, @PathVariable Long id) {
        Usuario currentUsuario = this.usuarioService.findById(id);
        Usuario usuarioUpdate = null;
        Map<String, Object> response = new HashMap<>();
        if (currentUsuario == null) {
            response.put("mensaje", "Error: no se puede editar la carrera del usuario ID:"
                    .concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            currentUsuario.setCarrera(carrera);
            usuarioUpdate = usuarioService.save(currentUsuario);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El usuario ha cambiado de carrera con éxito.");
        response.put("usuario", usuarioUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /**
     * Funcion auxiliar para actualizar los pumapuntos del usuario con ID
     * 
     * @param pumapuntos Nueva cantidad de pumapuntos a poner.
     * @param id         id del usuario a cambiar los pumapuntos.
     * @return El mensaje de exito o error al cambiar los datos.
     */
    @PutMapping("/admin/updatePumapuntos/{pumapuntos}/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updatePumapuntos(@RequestBody Integer pumapuntos, @PathVariable Long id) {
        Usuario currentUsuario = this.usuarioService.findById(id);
        Usuario usuarioUpdate = null;
        Map<String, Object> response = new HashMap<>();
        if (currentUsuario == null) {
            response.put("mensaje", "Error: no se puede editar los pumapuntos del usuario ID:"
                    .concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            currentUsuario.setPumapuntos(pumapuntos);
            usuarioUpdate = usuarioService.save(currentUsuario);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El usuario ha editado los pumapuntos con éxito.");
        response.put("usuario", usuarioUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    /**
     * Funcion auxiliar para actualizar el estado del usuario con ID
     * 
     * @param esActivo nuevo estado del usuario.
     * @param id       id del usuario a cambiar el estado.
     * @return El mensaje de exito o error al cambiar los datos.
     */
    @PutMapping("/admin/updateEstado/{esActivo}/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> updateEstado(@RequestBody Boolean esActivo, @PathVariable Long id) {
        Usuario currentUsuario = this.usuarioService.findById(id);
        Usuario usuarioUpdate = null;
        Map<String, Object> response = new HashMap<>();
        if (currentUsuario == null) {
            response.put("mensaje", "Error: no se puede actualizar el estado del usuario ID:"
                    .concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {
            currentUsuario.setEsActivo(esActivo);
            usuarioUpdate = usuarioService.save(currentUsuario);
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al actualizar el usuario en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El usuario ha sido actualizado con éxito.");
        response.put("usuario", usuarioUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    // ---------------------------------Fin de caso de uso---------------------------------------------------------

    @PutMapping("/usuarios/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id) {
        Usuario currentUsuario = this.usuarioService.findById(id);
        Usuario usuarioUpdate = null;
        Map<String, Object> response = new HashMap<>();
        if (currentUsuario == null) {
            response.put("mensaje", "Error: no se puede editar el usuario ID:"
                    .concat(id.toString().concat(" no existe en la base de datos.")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
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
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje", "El usuario ha sido creado con éxito.");
        response.put("usuario", usuarioUpdate);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

}
