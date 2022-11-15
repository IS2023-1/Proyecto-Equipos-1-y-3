package com.cienciasTop.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.cienciasTop.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario,Long> {

}
