package com.julian.productInventory.repositories;

import com.julian.productInventory.entities.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepositorio extends MongoRepository<Producto,String> {

}
