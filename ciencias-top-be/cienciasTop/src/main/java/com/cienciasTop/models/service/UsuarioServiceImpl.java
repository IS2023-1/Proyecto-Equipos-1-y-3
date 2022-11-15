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
		// TODO Auto-generated method stub
		return (List<Usuario>)usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario findById(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional()
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioDao.save(usuario);
	}

	@Override
	@Transactional()
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		usuarioDao.deleteById(id);
		
	}

}
