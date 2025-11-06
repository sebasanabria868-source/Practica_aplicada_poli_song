package co.edu.poli.negocio;

import co.edu.poli.datos.DBConnection;
import java.sql.*;



public class busquedaManager {

    public busquedaManager() {}

    
    public void buscarUsuarios() {
        System.out.println("BUSCANDO USUARIOS");
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT id_usuario, nombre, correo_id FROM usuario";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("---- LISTA DE USUARIOS ----");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_usuario") +
                        " | Nombre: " + rs.getString("nombre") +
                        " | Correo ID: " + rs.getInt("correo_id"));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar usuarios: " + e.getMessage());
        }
    }

    
    public void buscarProveedores() {
        System.out.println("BUSCANDO PROVEEDORES");
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT id_proveedor, nombre, correo_id, calificaciones FROM proveedor";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("---- LISTA DE PROVEEDORES ----");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_proveedor") +
                        " | Nombre: " + rs.getString("nombre") +
                        " | Correo ID: " + rs.getInt("correo_id") +
                        " | Calificaciones: " + rs.getInt("calificaciones"));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar proveedores: " + e.getMessage());
        }
    }

   
    public void buscarCanciones() {
        System.out.println("BUSCANDO CANCIONES");
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT id_cancion, nombre, duracion, precio, tamano_mb FROM cancion";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("---- LISTA DE CANCIONES ----");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_cancion") +
                        " | Nombre: " + rs.getString("nombre") +
                        " | Duración: " + rs.getBigDecimal("duracion") + " min" +
                        " | Precio: $" + rs.getBigDecimal("precio") +
                        " | Tamaño: " + rs.getBigDecimal("tamano_mb") + " MB");
            }
        } catch (Exception e) {
            System.out.println("Error al buscar canciones: " + e.getMessage());
        }
    }


    public void buscarVinilos() {
        System.out.println("BUSCANDO VINILOS");
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT id_vinilo, nombre, artista, anio, precio, inventario FROM vinilo";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("---- LISTA DE VINILOS ----");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_vinilo") +
                        " | Nombre: " + rs.getString("nombre") +
                        " | Artista: " + rs.getString("artista") +
                        " | Año: " + rs.getInt("anio") +
                        " | Precio: $" + rs.getBigDecimal("precio") +
                        " | Inventario: " + rs.getInt("inventario"));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar vinilos: " + e.getMessage());
        }
    }

   
    public void buscarPlaylistPublicas() {
        System.out.println("BUSCANDO PLAYLISTS PÚBLICAS");
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT id_playlist, nombre, id_usuario FROM playlist WHERE publica = 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            System.out.println("---- PLAYLISTS PÚBLICAS ----");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_playlist") +
                        " | Nombre: " + rs.getString("nombre") +
                        " | ID Usuario: " + rs.getInt("id_usuario"));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar playlists: " + e.getMessage());
        }
    }
}
