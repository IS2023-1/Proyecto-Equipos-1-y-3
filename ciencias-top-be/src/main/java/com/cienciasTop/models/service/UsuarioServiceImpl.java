package com.cienciasTop.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cienciasTop.models.dao.IUsuarioDao;
import com.cienciasTop.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	 @Autowired
	  private IUsuarioDao usuarioDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findAll() {
		return (List<Usuario>)usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public Usuario save(Usuario usuario) {
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional()
	public void deleteById(Long id) {
		usuarioDao.deleteById(id);
		
	}

	// Método para buscar a un usuario por su nombre
	@Override
	@Transactional(readOnly=true)
	public List<Usuario> findByNombre(String nombre) {
		return usuarioDao.findByNombre(nombre);
	}
	
	// Método para buscar a un usuario por su correo
	@Override
	@Transactional(readOnly=true)
	public Usuario findByCorreo(String correo) {
		return usuarioDao.findByCorreo(correo);
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findByCuenta(Long cuenta) {
		return usuarioDao.findByCuenta(cuenta);
	}

}
