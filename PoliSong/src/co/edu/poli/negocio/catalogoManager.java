package co.edu.poli.negocio;

import co.edu.poli.model.vinilo;
import java.util.*;


public class catalogoManager {

    private List<vinilo> catalogo;


    public catalogoManager() {
        
        catalogo = new ArrayList<>();
    }


    public void agregarViniloCatalogo(vinilo nuevoVinilo) {
        if (nuevoVinilo == null) {
            System.out.println("No se puede agregar un vinilo nulo.");
            return;
        }

        for (vinilo v : catalogo) {
            if (v.getId_vinilo() == nuevoVinilo.getId_vinilo()) {
                System.out.println("Ya existe un vinilo con el ID " + nuevoVinilo.getId_vinilo());
                return;
            }
        }

        catalogo.add(nuevoVinilo);
        System.out.println("Vinilo agregado correctamente: " + nuevoVinilo.getNombre());
    }

    
    public void eliminarViniloCatalogo(int idVinilo) {
        Iterator<vinilo> iterator = catalogo.iterator();
        boolean eliminado = false;

        while (iterator.hasNext()) {
            vinilo v = iterator.next();
            if (v.getId_vinilo() == idVinilo) {
                iterator.remove();
                eliminado = true;
                System.out.println("Vinilo eliminado: " + v.getNombre());
                break;
            }
        }

        if (!eliminado) {
            System.out.println("No se encontró ningún vinilo con el ID " + idVinilo);
        }
    }

   
    public void listarCatalogoProveedor() {
        if (catalogo.isEmpty()) {
            System.out.println("El catálogo está vacío.");
            return;
        }

        System.out.println("\nCatálogo de Vinilos:");
        for (vinilo v : catalogo) {
            System.out.println("-----------------------------------");
            System.out.println("ID: " + v.getId_vinilo());
            System.out.println("Nombre: " + v.getNombre());
            System.out.println("Artista: " + v.getArtista());
            System.out.println("Año: " + v.getAnio());
            System.out.println("Precio: $" + v.getPrecio());
            System.out.println("Inventario: " + v.getInventario());
        }
        System.out.println("-----------------------------------\n");
    }

    public List<vinilo> getCatalogo() {
        return catalogo;
    }
}
