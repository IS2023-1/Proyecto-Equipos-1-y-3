package com.cienciasTop.models.service;

import java.util.List;

import com.cienciasTop.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findById(Long id);
	
	public Usuario findByUsername(String username);
	
    public Usuario findByCorreo(String correo);
    
    public Usuario findByCuenta(Long cuenta);
    
    public List<Usuario> findByNombre(String name);
	
	public List<Usuario> findAll();
	
	public Usuario save(Usuario usuario);
	
    public void deleteById(Long id);
}
