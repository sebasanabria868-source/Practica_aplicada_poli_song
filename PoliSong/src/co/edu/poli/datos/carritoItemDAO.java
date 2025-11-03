package co.edu.poli.datos;

import co.edu.poli.model.carritoItem;
import java.sql.*;

public class carritoItemDAO {

    public carritoItemDAO() {}

    public void createItem(carritoItem item) {
        String sql = "INSERT INTO carrito_item (id_item, id_carrito, tipo_producto, id_producto, cantidad) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getId_item());
            stmt.setInt(2, item.getId_carrito());
            stmt.setString(3, item.getTipo_producto());
            stmt.setInt(4, item.getId_porducto()); // si actualizas el modelo a id_producto, cambiar aquí a getId_producto()
            stmt.setInt(5, item.getCantidad());

            stmt.executeUpdate();
            System.out.println("carritoItemDAO -> createItem: Item creado correctamente");

        } catch (SQLException e) {
            System.out.println("carritoItemDAO -> createItem: Error al crear item");
            System.out.println("Detalles: " + e.getMessage());
        }
    }

    public carritoItem readItem(int id) {
        String sql = "SELECT ci.id_item, ci.id_carrito, ci.tipo_producto, ci.id_producto, ci.cantidad FROM carrito_item ci WHERE ci.id_item = ?";
        carritoItem item = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                item = new carritoItem(
                        rs.getInt("id_item"),
                        rs.getInt("id_carrito"),
                        rs.getString("tipo_producto"),
                        rs.getInt("id_producto"),
                        rs.getInt("cantidad")
                );
                System.out.println("carritoItemDAO -> readItem: Item encontrado");
            } else {
                System.out.println("carritoItemDAO -> readItem: Item no existe");
            }

        } catch (SQLException e) {
            System.out.println("carritoItemDAO -> readItem: Error al leer item");
            System.out.println("Detalles: " + e.getMessage());
        }

        return item;
    }

    public void updateItem(carritoItem item) {
        String sql = "UPDATE carrito_item SET id_carrito = ?, tipo_producto = ?, id_producto = ?, cantidad = ? WHERE id_item = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, item.getId_carrito());
            stmt.setString(2, item.getTipo_producto());
            stmt.setInt(3, item.getId_porducto()); // cambiar si actualizas modelo
            stmt.setInt(4, item.getCantidad());
            stmt.setInt(5, item.getId_item());

            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("carritoItemDAO -> updateItem: Item actualizado");
            else System.out.println("carritoItemDAO -> updateItem: Item no existe");

        } catch (SQLException e) {
            System.out.println("carritoItemDAO -> updateItem: Error al actualizar item");
            System.out.println("Detalles: " + e.getMessage());
        }
    }

    public void deleteItem(int id) {
        String sql = "DELETE FROM carrito_item WHERE id_item = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("carritoItemDAO -> deleteItem: Item eliminado");
            else System.out.println("carritoItemDAO -> deleteItem: Item no existe");
        } catch (SQLException e) {
            System.out.println("carritoItemDAO -> deleteItem: Error al eliminar item");
            System.out.println("Detalles: " + e.getMessage());
        }
    }
}
