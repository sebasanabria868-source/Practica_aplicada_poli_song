package co.edu.poli.negocio;

import co.edu.poli.datos.DBConnection;
import java.sql.*;

public class adminManager {

    private Connection conexion;

    /**
     * Constructor: obtiene la conexión desde DBConnection (Singleton)
     */
    public adminManager() {
        this.conexion = DBConnection.getConnection();
    }

    public void listarUsuarios() {
    String sql = """
        SELECT u.id_usuario, u.nombre, c.correo, u.contrasena
        FROM Usuario u
        INNER JOIN Correo c ON u.correo_id = c.id_correo
    """;

    try (Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        System.out.println("---- LISTA DE USUARIOS ----");
        while (rs.next()) {
            int id = rs.getInt("id_usuario");
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            String contrasena = rs.getString("contrasena");

            System.out.println(id + " | " + nombre + " | " + correo + " | " + contrasena);
        }

    } catch (SQLException e) {
        System.err.println("Error al listar usuarios: " + e.getMessage());
    }
}
    public void listarProveedores() {
    String sql = """
        SELECT p.id_proveedor, p.nombre, c.correo, p.contrasena, p.calificaciones
        FROM Proveedor p
        INNER JOIN Correo c ON p.correo_id = c.id_correo
    """;

    try (Statement stmt = conexion.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        System.out.println("---- LISTA DE PROVEEDORES ----");
        while (rs.next()) {
            int id = rs.getInt("id_proveedor");
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            String contrasena = rs.getString("contrasena");
            int calificaciones = rs.getInt("calificaciones");

            System.out.println(id + " | " + nombre + " | " + correo + 
                               " | " + contrasena + " | calificaciones: " + calificaciones);
        }

    } catch (SQLException e) {
        System.err.println("Error al listar proveedores: " + e.getMessage());
    }
}


   
    public boolean eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM Usuario WHERE id_usuario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }
    }

    
    public boolean eliminarProveedor(int idProveedor) {
        String sql = "DELETE FROM Proveedor WHERE id_proveedor = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProveedor);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar proveedor: " + e.getMessage());
            return false;
        }
    }

}
