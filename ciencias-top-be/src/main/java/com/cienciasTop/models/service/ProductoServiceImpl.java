package com.cienciasTop.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cienciasTop.models.dao.IProductoDao;
import com.cienciasTop.models.entity.Producto;

@Service
public class ProductoServiceImpl implements IProductoService{
	
	@Autowired
	private IProductoDao productoDao;

	@Override
	@Transactional(readOnly=true)
	public Producto findById(Long id) {
		return productoDao.findById(id).orElse(null);
	}
	
	@Override
	public Producto findByCodigo(String codigo) {
		return productoDao.findByCodigo(codigo);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Producto findByNombre(String nombre) {
		return productoDao.findByNombre(nombre);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Producto> findAll() {
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional()
	public Producto save(Producto producto) {
		return productoDao.save(producto);
	}

	@Override
	@Transactional()
	public void delete(Long id) {
		productoDao.deleteById(id);
		
	}
}
