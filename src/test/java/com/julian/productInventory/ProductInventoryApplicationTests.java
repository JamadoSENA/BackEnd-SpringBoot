package com.julian.productInventory;

import com.julian.productInventory.entities.Producto;
import com.julian.productInventory.repositories.ProductoRepositorio;
import com.julian.productInventory.service.imp.ProductoImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductInventoryApplicationTests {

	@Mock
	private ProductoRepositorio productoRepository;

	@InjectMocks
	private ProductoImp productoImp;

	@Test
	public void testCrearProducto() {
		Producto producto = new Producto("Paracetamol", "Medicamento", "5.00", "100");

		when(productoRepository.save(producto)).thenReturn(producto);

		Producto result = productoImp.create(producto);

		assertEquals("Paracetamol", result.getNombre());
		assertEquals("100", result.getCantidad());
	}

	@Test
	public void testBuscarProductoPorId() {
		// Simula un producto
		Producto producto = new Producto("Ibuprofeno", "Medicamento", "6.00", "50");

		// Simula el comportamiento del repositorio para buscar un producto por ID
		when(productoRepository.findById("abc123")).thenReturn(java.util.Optional.of(producto));

		// Llama al método findById del servicio
		Producto result = productoImp.findById("abc123");

		// Verifica que el nombre y la cantidad del producto sean los esperados
		assertEquals("Ibuprofeno", result.getNombre());
		assertEquals("50", result.getCantidad());
	}
}
