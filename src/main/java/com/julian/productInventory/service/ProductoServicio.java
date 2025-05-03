package com.julian.productInventory.service;
import com.julian.productInventory.entities.Producto;
import java.util.List;

public interface ProductoServicio {
    public List<Producto> findAll() throws Exception;
    public Producto findById(String id);
    public Producto create(Producto producto);
    public void update(Producto producto);
    public void delete(Producto producto);
}

