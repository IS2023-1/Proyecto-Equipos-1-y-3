package is20231.cienciastopbe.models.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import is20231.cienciastopbe.models.entity.Producto;
import is20231.cienciastopbe.models.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoRestController {

    @Autowired
    private IProductoService productoService;
    
    @GetMapping("/all")
    public List<Producto> index(){
        return productoService.findAll();
    }

    @GetMapping("/buscar/id/{id}")
    public Optional<Producto> buscaId(@PathVariable Long id) {
        return productoService.findById(id);
    }

/*     @GetMapping("buscar/nombre/{nombre}")
    public Optional<Producto> buscaNombre(@PathVariable String nombre) {
        return productoService.findByName(nombre);
    } */
}
