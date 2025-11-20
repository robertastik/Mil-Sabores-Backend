package com.ms_backend.mil_sabores_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms_backend.mil_sabores_backend.model.Producto;
import com.ms_backend.mil_sabores_backend.repository.ProductoRepository;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepo;

    public List<Producto> obtenerProductos() {
        return productoRepo.findAll();
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepo.findById(id).orElse(null);
    }

    public List<Producto> crearProductos(List<Producto> productos) {
        return productoRepo.saveAll(productos);
    }

    public Producto crearProducto(Producto producto) {
        return productoRepo.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto producto) {
        Producto productoExistente = obtenerProductoPorId(id);
        if (productoExistente != null) {
            productoExistente.setNombre(producto.getNombre());
            productoExistente.setCategoria(producto.getCategoria());
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setPrecio(producto.getPrecio());
            productoExistente.setIngredientes(producto.getIngredientes());
            productoExistente.setImagenUrl(producto.getImagenUrl());
            return productoRepo.save(productoExistente);
        }
        return null;
    }

    public void eliminarProducto(Long id) {
        productoRepo.deleteById(id);
    }
}
