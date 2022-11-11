package is20231.cienciastopbe.models.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import is20231.cienciastopbe.models.entity.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long> {

    Optional<Producto> findByName(String nombre);
    
}
