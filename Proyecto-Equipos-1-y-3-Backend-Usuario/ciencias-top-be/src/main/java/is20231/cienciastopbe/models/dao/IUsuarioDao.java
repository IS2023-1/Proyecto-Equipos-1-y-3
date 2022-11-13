package is20231.cienciastopbe.models.dao;

import org.springframework.data.repository.CrudRepository;

import is20231.cienciastopbe.models.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario,Long>{
    
}
