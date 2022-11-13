package is20231.cienciastopbe.models.service;

import java.util.List;
import java.util.Optional;

import is20231.cienciastopbe.models.entity.Rentar;

public interface IRentarService {
    public Optional<Producto> findById(Long idLong);
    public Usuario save(Usuario usuario);
    public void deleteById(Long id);
}
