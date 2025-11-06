package co.edu.poli.negocio;

import java.util.*;


public class carritoManager {


    private List<String> carrito;

   
    public carritoManager() {
        carrito = new ArrayList<>();
    }

    
    public void agregarProducto(String producto) {
        if (producto == null || producto.isEmpty()) {
            System.out.println("El nombre del producto no puede estar vacío.");
            return;
        }
        carrito.add(producto);
        System.out.println("Producto agregado al carrito: " + producto);
    }

    
    public void eliminarProducto(String producto) {
        if (carrito.remove(producto)) {
            System.out.println("Producto eliminado: " + producto);
        } else {
            System.out.println("El producto no se encuentra en el carrito.");
        }
    }

    
    public void vaciarCarrito() {
        carrito.clear();
        System.out.println("🧹 Carrito vaciado correctamente.");
    }

    
    public void verCarrito() {
        System.out.println("CONTENIDO DEL CARRITO:");
        if (carrito.isEmpty()) {
            System.out.println(" (Carrito vacío)");
        } else {
            for (int i = 0; i < carrito.size(); i++) {
                System.out.println((i + 1) + ". " + carrito.get(i));
            }
        }
    }

    
    public List<String> getCarrito() {
        return carrito;
    }
}
