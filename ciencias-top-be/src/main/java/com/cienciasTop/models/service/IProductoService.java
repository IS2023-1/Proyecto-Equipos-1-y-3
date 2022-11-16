package com.cienciasTop.models.service;

import java.util.List;

import com.cienciasTop.models.entity.Producto;

public interface IProductoService {
	public List<Producto> findAll();
	
	public Producto findById(Long id);
	
	public Producto save(Producto producto);
	
	public void delete(Long id);
	
	//public Optional<Producto> findByName(String nombre);
	
	//Método para buscar un usuario por su nombre
    public Producto findByNombre(String nombre);
    
  //Método para buscar un usuario por su codigo
    public Producto findByCodigo(String codigo);
}