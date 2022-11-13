package is20231.cienciastopbe.models.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import is20231.cienciastopbe.models.dao.IUsuarioDao;
import is20231.cienciastopbe.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService{

    @Autowired
    private IUsuarioDao usuarioDao;
    
    @Override
    @Transactional(readOnly=true)
    public Usuario findById(Long id) {
        // TODO Auto-generated method stub
        return usuarioDao.findById(id).orElse(null);
    }

    @Override
    @Transactional()
    public Usuario save(Usuario usuario) {
        // TODO Auto-generated method stub
        return usuarioDao.save(usuario);
    }

    @Override
    @Transactional()
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        usuarioDao.deleteById(id);
    }
}
