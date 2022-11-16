package com.cienciasTop.models.service;

import java.util.List;

import com.cienciasTop.models.entity.Usuario;

public interface IUsuarioService {
	
	public List<Usuario> findAll();
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario usuario);
	
    public void deleteById(Long id);
    
    //Método para buscar un usuario por su nombre
    public List<Usuario> findByNombre(String name);
    
    //Método para buscar un usuario por su correo
    public Usuario findByCorreo(String correo);
    
    //Método para buscar un usuario por su número de cuenta o trabajador
    public Usuario findByCuenta(Long cuenta);

}
