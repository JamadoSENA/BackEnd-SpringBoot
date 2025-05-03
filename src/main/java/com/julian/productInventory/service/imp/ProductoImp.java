package com.julian.productInventory.service.imp;
import com.julian.productInventory.entities.Producto;
import com.julian.productInventory.repositories.ProductoRepositorio;
import com.julian.productInventory.service.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoImp implements ProductoServicio {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    public ProductoImp(ProductoRepositorio productoRepositorio) {
        this.productoRepositorio = productoRepositorio;
    }

    @Override
    public List<Producto> findAll() throws Exception {
        return this.productoRepositorio.findAll();
    }

    @Override
    public Producto findById(String id) {
        return this.productoRepositorio.findById(id).orElse(null);
    }

    @Override
    public Producto create(Producto producto) {
        this.productoRepositorio.save(producto);
        return producto;
    }

    @Override
    public void update(Producto producto) {
        this.productoRepositorio.save(producto);
    }

    @Override
    public void delete(Producto producto) {
        this.productoRepositorio.delete(producto);
    }
}

