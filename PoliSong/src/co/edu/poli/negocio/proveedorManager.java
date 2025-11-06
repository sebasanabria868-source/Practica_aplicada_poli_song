package co.edu.poli.negocio;

import co.edu.poli.datos.DBConnection;
import java.sql.*;

public class proveedorManager {

    public proveedorManager() {}

   
    public void registrarProveedor(String nombre, int correo_id, String contrasena, int calificaciones) {
        String sql = """
            INSERT INTO proveedor (nombre, correo_id, contrasena, calificaciones)
            VALUES (?, ?, ?, ?);
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setInt(2, correo_id);
            ps.setString(3, contrasena);
            ps.setInt(4, calificaciones);

            ps.executeUpdate();
            System.out.println("Proveedor registrado correctamente.");

        } catch (SQLException e) {
            System.err.println("Error al registrar proveedor: " + e.getMessage());
        }
    }

    
    public void actualizarProveedor(int id_proveedor, String nuevoNombre, String nuevaContrasena, int nuevaCalificacion) {
        String sql = """
            UPDATE proveedor
            SET nombre = ?, contrasena = ?, calificaciones = ?
            WHERE id_proveedor = ?;
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoNombre);
            ps.setString(2, nuevaContrasena);
            ps.setInt(3, nuevaCalificacion);
            ps.setInt(4, id_proveedor);

            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println("Proveedor actualizado correctamente.");
            else
                System.out.println("No se encontró el proveedor con ID: " + id_proveedor);

        } catch (SQLException e) {
            System.err.println("Error al actualizar proveedor: " + e.getMessage());
        }
    }

    
    public void eliminarProveedor(int id_proveedor) {
        String sql = "DELETE FROM proveedor WHERE id_proveedor = ?;";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id_proveedor);
            int filas = ps.executeUpdate();

            if (filas > 0)
                System.out.println("Proveedor eliminado correctamente.");
            else
                System.out.println("No se encontró el proveedor con ID: " + id_proveedor);

        } catch (SQLException e) {
            System.err.println("Error al eliminar proveedor: " + e.getMessage());
        }
    }

    
    public void listarProveedores() {
        String sql = """
            SELECT p.id_proveedor, p.nombre, c.correo, p.calificaciones
            FROM proveedor p
            JOIN correo c ON p.correo_id = c.id_correo;
        """;

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("---- LISTA DE PROVEEDORES ----");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_proveedor") +
                                   " | Nombre: " + rs.getString("nombre") +
                                   " | Correo: " + rs.getString("correo") +
                                   " | Calificación: " + rs.getInt("calificaciones"));
            }

        } catch (SQLException e) {
            System.err.println("Error al listar proveedores: " + e.getMessage());
        }
    }

    
    public void verPedidosPendientes() {
    System.out.println("\n? CONSULTANDO PEDIDOS PENDIENTES...");
    String sql = 
        "SELECT pd.id_pedido, pd.id_detalle, pd.tipo_producto, pd.id_producto, pd.cantidad, pd.precio_unitario " +
        "FROM pedido_detalle pd " +
        "INNER JOIN pedido p ON pd.id_pedido = p.id_pedido " +
        "WHERE p.estado = 'Pendiente'";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        System.out.println("Conexión a BD establecida correctamente");

        boolean hayResultados = false;
        System.out.println("---- LISTA DE PEDIDOS PENDIENTES ----");

        while (rs.next()) {
            hayResultados = true;

            int idPedido = rs.getInt("id_pedido");
            int idDetalle = rs.getInt("id_detalle");
            String tipoProducto = rs.getString("tipo_producto");
            int idProducto = rs.getInt("id_producto");
            int cantidad = rs.getInt("cantidad");
            double precioUnitario = rs.getDouble("precio_unitario");

            System.out.printf("Pedido ID: %d | Detalle ID: %d | Tipo: %s | Producto ID: %d | Cantidad: %d | Precio: %.2f\n",
                    idPedido, idDetalle, tipoProducto, idProducto, cantidad, precioUnitario);
        }

        if (!hayResultados) {
            System.out.println("No hay pedidos pendientes en este momento.");
        }

    } catch (SQLException e) {
        System.out.println("Error al consultar pedidos pendientes: " + e.getMessage());
    }
}


    
    public void gestionarInventarioVinilo(int id_vinilo, int nuevoInventario) {
        String sql = """
            UPDATE vinilo
            SET inventario = ?
            WHERE id_vinilo = ?;
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, nuevoInventario);
            ps.setInt(2, id_vinilo);

            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println("Inventario actualizado correctamente para vinilo ID: " + id_vinilo);
            else
                System.out.println("No se encontró el vinilo con ID: " + id_vinilo);

        } catch (SQLException e) {
            System.err.println("Error al actualizar inventario de vinilo: " + e.getMessage());
        }
    }
}
