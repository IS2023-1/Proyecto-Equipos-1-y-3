package com.cienciasTop.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cienciasTop.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario,Long> {
	
    public List<Usuario> findByNombre(String name);
    
    public Usuario findByUsername(String username);
    
    public Usuario findByCorreo(String correo);
    
    public Usuario findByCuenta(Long cuenta);

}
