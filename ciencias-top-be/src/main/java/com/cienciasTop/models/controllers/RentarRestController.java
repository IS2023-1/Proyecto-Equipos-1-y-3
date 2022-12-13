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

import com.cienciasTop.models.entity.Rentar;
import com.cienciasTop.models.service.IRentarService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/rentar")
public class RentarRestController {

    @Autowired
    private IRentarService rentarService;

    /**
     * Busca todos los valores de la tabla rentar
     * @return lista de productos rentados segun x usuario.
     */
    @GetMapping("/buscar/todo") 
	public List<Rentar> index(){
		 return rentarService.findAll();
	}

}
