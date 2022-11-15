package is20231.cienciastopbe.models.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import is20231.cienciastopbe.models.dao.IProductoDao;
import is20231.cienciastopbe.models.entity.Producto;

@Service
public class IProductoServiceImpl implements IProductoService {

    @Autowired
    private IProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return (List<Producto>) productoDao.findAll();
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