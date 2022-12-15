package com.cienciasTop.models.service;

import java.util.List;

import com.cienciasTop.models.entity.Rentar;

public interface IRentarService {
	
	public Rentar save(Rentar renta);
	
	public List<Rentar> findAll();
}
