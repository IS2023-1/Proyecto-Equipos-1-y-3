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

	
	// Muestra todos los productos
	@Override
	@Transactional(readOnly=true)
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return (List<Producto>) productoDao.findAll();
	}

	// Muestra un producto por su id
	@Override
	@Transactional(readOnly=true)
	public Producto findById(Long id) {
		// TODO Auto-generated method stub
		return productoDao.findById(id).orElse(null);
	}

	// Agrega un producto
	@Override
	@Transactional()
	public Producto save(Producto producto) {
		// TODO Auto-generated method stub
		return productoDao.save(producto);
	}

	// Elimina un producto a traves de su id
	@Override
	@Transactional()
	public void delete(Long id) {
		// TODO Auto-generated method stub
		productoDao.deleteById(id);
		
	}

}
