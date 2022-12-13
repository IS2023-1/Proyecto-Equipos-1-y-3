package com.cienciasTop.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.cienciasTop.models.dao.IRentarDao;
import com.cienciasTop.models.entity.Rentar;

public class RentarService implements IRentarService {
	
	@Autowired
	private IRentarDao rentarDao;

    @Override
	@Transactional(readOnly=true)
	public List<Rentar> findAll() {
		return (List<Rentar>)rentarDao.findAll();
	}

}
