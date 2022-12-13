package com.cienciasTop.models.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cienciasTop.models.entity.Rentar;
import com.cienciasTop.models.service.IRentarService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/rentar")
public class RentarRestController {
	
	@Autowired
    private IRentarService rentar_Service;
	
	/* ------------------------------ CREATE ------------------------------ */
	
	@PostMapping("/agregar")
	public ResponseEntity<?> create(@RequestBody Rentar renta){
		Rentar nueva_Renta = null;
        Map<String,Object> response = new HashMap<>();
        
        try {
        	LocalDateTime fecha_actual = LocalDateTime.now();
        	nueva_Renta = rentar_Service.save(renta);
        	nueva_Renta.setFecha_de_renta(fecha_actual);
        } catch(DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos.");
            response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String,Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("mensaje","La renta del producto ha sido creado con éxito.");
        response.put("renta",nueva_Renta);
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
