package com.cienciasTop.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.cienciasTop.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto,Long>{
		
    public Producto findByNombre(String nombre);
    
    public Producto findByCodigo(String codigo);
}
