package is20231.cienciastopbe.models.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import is20231.cienciastopbe.models.dao.IRentarDao;
import is20231.cienciastopbe.models.entity.Rentar;

@Service
public class IRentarServiceImpl implements IRentarService{

    @Autowired
    private IRentarDao rentarDao;
    @Autowired
    private IUsuarioDao usuarioDao;    
    @Autowired
    private IProductoDao productoDao;

    @Override
    @Transactional()
    public Usuario save(Usuario usuario) {
        return rentarDao.save(usuario);
    }

    @Override
    @Transactional()
    public void deleteById(Long id) {
        rentarDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long idLong) {
        return productoDao.findById(idLong);
    }


    @Override
    public Optional<Producto> findByName(String nombre) {
        return productoDao.findByName(nombre);
    }
}
