package co.edu.poli.negocio;

import co.edu.poli.datos.DBConnection;
import java.sql.*;;

public class usuarioManager {

   

    public usuarioManager() {
    }

    

    public void registrarUsuario(String nombre, int correoId, String contrasena) {
        String sql = "INSERT INTO usuario (nombre, correo_id, contrasena) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nombre);
            stmt.setInt(2, correoId);
            stmt.setString(3, contrasena);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Usuario registrado correctamente.");
            } else {
                System.out.println("No se pudo registrar el usuario.");
            }

        } catch (SQLException e) {
            System.out.println("Error al registrar usuario: " + e.getMessage());
        }
    }

   

    public void actualizarUsuario(int idUsuario, String nuevoNombre, String nuevaContrasena) {
        String sql = "UPDATE usuario SET nombre = ?, contrasena = ? WHERE id_usuario = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nuevaContrasena);
            stmt.setInt(3, idUsuario);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Usuario actualizado correctamente.");
            } else {
                System.out.println("No se encontró el usuario con ID " + idUsuario);
            }

        } catch (SQLException e) {
            System.out.println("Error al actualizar usuario: " + e.getMessage());
        }
    }

    
    public void eliminarUsuario(int idUsuario) {
        String sql = "DELETE FROM usuario WHERE id_usuario = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuario);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Usuario eliminado correctamente.");
            } else {
                System.out.println("No se encontró el usuario con ID " + idUsuario);
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar usuario: " + e.getMessage());
        }
    }

    
    public void listarUsuarios() {
        String sql = "SELECT u.id_usuario, u.nombre, c.correo FROM usuario u INNER JOIN correo c ON u.correo_id = c.id_correo";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("---- LISTA DE USUARIOS ----");
            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                System.out.println("ID: " + id + " | Nombre: " + nombre + " | Correo: " + correo);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
    }
}
