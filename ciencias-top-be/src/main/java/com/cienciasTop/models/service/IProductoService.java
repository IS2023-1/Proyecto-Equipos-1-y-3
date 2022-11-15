package com.cienciasTop.models.service;

import java.util.List;

import com.cienciasTop.models.entity.Producto;

public interface IProductoService {
	public List<Producto> findAll();
	
	public Producto findById(Long id);
	
	public Producto save(Producto producto);
	
	public void delete(Long id);
	
	//public Optional<Producto> findByName(String nombre);
}