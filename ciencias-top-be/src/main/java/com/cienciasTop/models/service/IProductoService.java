package com.cienciasTop.models.service;

import java.util.List;

import com.cienciasTop.models.entity.Producto;

public interface IProductoService {
	
	public Producto findById(Long id);
	
    public Producto findByCodigo(String codigo);
	
    public Producto findByNombre(String nombre);
    
	public List<Producto> findAll();
	
	public Producto save(Producto producto);
	
	public void delete(Long id);
	
}