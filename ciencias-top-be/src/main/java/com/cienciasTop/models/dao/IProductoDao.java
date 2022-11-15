package com.cienciasTop.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.cienciasTop.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto,Long>{
	
     // Optional<Producto> findByName(String nombre);
     
}
