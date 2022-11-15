package com.cienciasTop.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cienciasTop.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario,Long> {
	
	//Método para buscar un usuario por su nombre
    public List<Usuario> findByNombre(String name);
    
    //Método para buscar un usuario por su correo
    public Usuario findByCorreo(String correo);
    
    //Método para buscar un usuario por su número de cuenta o trabajador
    public Usuario findByCuenta(Long cuenta);

}
