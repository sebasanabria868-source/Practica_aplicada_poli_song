package co.edu.poli.negocio;

import co.edu.poli.datos.DBConnection;
import co.edu.poli.model.administrador;
import co.edu.poli.model.proveedor;
import co.edu.poli.model.usuario;
import java.sql.*;

public class authManager {

    // Variables estáticas para mantener el estado de sesión
    private static usuario usuarioActivo = null;
    private static proveedor proveedorActivo = null;
    private static administrador adminActivo = null;
    private static String rolActivo = null; 

    public authManager() {}

    

    public boolean loginUsuario(String correo, String contrasena) {
        String sql = """
            SELECT u.id_usuario, u.nombre, c.correo, u.contrasena
            FROM usuario u
            JOIN correo c ON u.correo_id = c.id_correo
            WHERE c.correo = ? AND u.contrasena = ?;
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuarioActivo = new usuario(
                    rs.getInt("id_usuario"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena")
                );
                rolActivo = "usuario";
                System.out.println("Sesión iniciada como USUARIO: " + usuarioActivo.getNombre());
                return true;
            } else {
                System.out.println("Correo o contraseña incorrectos (usuario).");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error en loginUsuario: " + e.getMessage());
            return false;
        }
    }

    
    public boolean loginProveedor(String correo, String contrasena) {
        String sql = """
            SELECT p.id_proveedor, p.nombre, c.correo, p.contrasena, p.calificaciones
            FROM proveedor p
            JOIN correo c ON p.correo_id = c.id_correo
            WHERE c.correo = ? AND p.contrasena = ?;
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                proveedorActivo = new proveedor(
                    rs.getInt("id_proveedor"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena"),
                    rs.getInt("calificaciones")
                );
                rolActivo = "proveedor";
                System.out.println("Sesión iniciada como PROVEEDOR: " + proveedorActivo.getNombre());
                return true;
            } else {
                System.out.println("Correo o contraseña incorrectos (proveedor).");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error en loginProveedor: " + e.getMessage());
            return false;
        }
    }

    

    public boolean loginAdmin(String correo, String contrasena) {
        String sql = """
            SELECT a.id_admin, a.nombre, c.correo, a.contrasena
            FROM administrador a
            JOIN correo c ON a.correo_id = c.id_correo
            WHERE c.correo = ? AND a.contrasena = ?;
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                adminActivo = new administrador(
                    rs.getInt("id_admin"),
                    rs.getString("nombre"),
                    rs.getString("correo"),
                    rs.getString("contrasena")
                );
                rolActivo = "admin";
                System.out.println("Sesión iniciada como ADMINISTRADOR: " + adminActivo.getNombre());
                return true;
            } else {
                System.out.println("Correo o contraseña incorrectos (admin).");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error en loginAdmin: " + e.getMessage());
            return false;
        }
    }


    public void logout() {
        usuarioActivo = null;
        proveedorActivo = null;
        adminActivo = null;
        rolActivo = null;
        System.out.println("Sesión cerrada correctamente.");
    }

    // Getters estáticos para obtener el usuario/proveedor/admin activos
    public static usuario getUsuarioActivo() { return usuarioActivo; }
    public static proveedor getProveedorActivo() { return proveedorActivo; }
    public static administrador getAdminActivo() { return adminActivo; }
    public static String getRolActivo() { return rolActivo; }
}
