package com.julian.productInventory.controllers;
import com.julian.productInventory.entities.Producto;
import com.julian.productInventory.service.imp.ProductoImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/producto",method =
        {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.HEAD})
@CrossOrigin("*")
public class ProductoController {

    @Autowired
    private ProductoImp productoImp;

    // Controller CREATE
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Producto producto = new Producto();
            producto.setNombre(request.get("nombre").toString());
            producto.setCategoria(request.get("categoria").toString());
            producto.setPrecio(request.get("precio").toString());
            producto.setCantidad(request.get("cantidad").toString());

            this.productoImp.create(producto);  // Aquí el ProductoImp debería trabajar con MongoDB

            response.put("status", "success");
            response.put("data", "Registro Exitoso");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Controller READ - All
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> findAll() {
        Map<String, Object> response = new HashMap<>();
        try {
            List<Producto> productoList = this.productoImp.findAll();
            response.put("status", "success");
            response.put("data", productoList);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Controller READ - By ID
    @GetMapping("/list/{idProducto}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable String idProducto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Producto producto = this.productoImp.findById(idProducto);  // MongoDB usa String como ID
            response.put("status", "success");
            response.put("data", producto);
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Controller UPDATE
    @PutMapping("/update/{idProducto}")
    public ResponseEntity<Map<String, Object>> update(@PathVariable String idProducto, @RequestBody Map<String, Object> request) {
        Map<String, Object> response = new HashMap<>();
        try {
            Producto producto = this.productoImp.findById(idProducto);

            producto.setNombre(request.get("nombre").toString());
            producto.setCategoria(request.get("categoria").toString());
            producto.setPrecio(request.get("precio").toString());
            producto.setCantidad(request.get("cantidad").toString());

            this.productoImp.update(producto);

            response.put("status", "success");
            response.put("data", "Actualización Exitosa");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Controller DELETE
    @DeleteMapping("/delete/{idProducto}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable String idProducto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Producto producto = this.productoImp.findById(idProducto);
            productoImp.delete(producto);

            response.put("status", "success");
            response.put("data", "Registro Eliminado Correctamente");
        } catch (Exception e) {
            response.put("status", HttpStatus.BAD_GATEWAY);
            response.put("data", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
