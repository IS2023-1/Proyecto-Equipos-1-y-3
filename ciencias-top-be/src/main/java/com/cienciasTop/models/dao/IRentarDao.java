package com.cienciasTop.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cienciasTop.models.entity.Rentar;

public interface IRentarDao extends CrudRepository<Rentar, Long>{
    
    //Método para hacer el historial de renta
    @Query(
			value= "SELECT * FROM rentas WHERE usuario_id = :usuario_id;", 
			nativeQuery = true)
    //Método para 
	public List<Rentar> historial(Long usuario_id);

}
