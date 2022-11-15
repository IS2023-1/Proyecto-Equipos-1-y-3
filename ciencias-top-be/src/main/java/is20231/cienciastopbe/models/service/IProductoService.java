package is20231.cienciastopbe.models.service;

import java.util.List;
import java.util.Optional;

import is20231.cienciastopbe.models.entity.Producto;

public interface IProductoService {
    public List<Producto> findAll();
    public Optional<Producto> findById(Long idLong);
    /* public Optional<Producto> findByName(String nombre);
 */}
