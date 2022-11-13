package is20231.cienciastopbe.models.service;

import is20231.cienciastopbe.models.entity.Usuario;

public interface IUsuarioService {
    public Usuario findById(Long id);
    public Usuario save(Usuario usuario);
    public void deleteById(Long id);
}
