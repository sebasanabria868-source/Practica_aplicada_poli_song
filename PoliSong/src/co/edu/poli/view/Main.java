package co.edu.poli.view;

import java.util.Date;

import co.edu.poli.datos.*;
import co.edu.poli.model.*;

public class Main {
	public static void main(String[] args) {
       /*
		System.out.println("=== INICIO DE PRUEBA DE LOS 13 DAOs ===");

        // ---------- 1. CORREO ----------
        correoDAO correoDAO = new correoDAO();
        correo cAdmin = new correo("admin@gmail.com");
        correo cUser = new correo("laura@gmail.com");
        correo cProv = new correo("sony@music.com");
        correoDAO.createCorreo(cAdmin);
        correoDAO.createCorreo(cUser);
        correoDAO.createCorreo(cProv);
        correoDAO.readCorreo("admin@gmail.com");

        // ---------- 2. ADMINISTRADOR ----------
        administradorDAO adminDAO = new administradorDAO();
        administrador admin = new administrador(1, "Carlos Ruiz", "admin@gmail.com", "admin123");
        adminDAO.createAdmin(admin);
        adminDAO.readAdmin(1);
        admin.setNombre("Carlos Ruiz Actualizado");
        adminDAO.updateAdmin(admin);

        // ---------- 3. USUARIO ----------
        usuarioDAO userDAO = new usuarioDAO();
        usuario user = new usuario(1, "Laura Gómez", "laura@gmail.com", "12345");
        userDAO.createUsuario(user);
        userDAO.readUsuario(1);
        user.setNombre("Laura Gómez Actualizada");
        userDAO.updateUsuario(user);

        // ---------- 4. PROVEEDOR ----------
        proveedorDAO provDAO = new proveedorDAO();
        proveedor prov = new proveedor(1, "Sony Music", "sony@music.com", "clave123", 5);
        provDAO.createProveedor(prov);
        provDAO.readProveedor(1);
        prov.setCalificaciones(10);
        provDAO.updateProveedor(prov);

        // ---------- 5. CANCIÓN ----------
        cancionDAO cancionDAO = new cancionDAO();
        cancion song = new cancion(1, "Billie Jean", 4.55, 5000.0, 8.4);
        cancionDAO.createCancion(song);
        cancionDAO.readCancion(1);
        song.setPrecio(5500.0);
        cancionDAO.updateCancion(song);

        // ---------- 6. VINILO ----------
        viniloDAO vinDAO = new viniloDAO();
        vinilo vin = new vinilo(1, "Thriller", "Michael Jackson", 1982, 150000.0, 50, null);
        vinDAO.createVinilo(vin);
        vinDAO.readVinilo(1);
        vin.setPrecio(155000.0);
        vinDAO.updateVinilo(vin);

        // ---------- 7. DISCO MP3 ----------
        discoMP3DAO mp3DAO = new discoMP3DAO();
        discoMP3 mp3 = new discoMP3(1, "Hits del 2025", new Date(), null);
        mp3DAO.createMP3(mp3);
        mp3DAO.readMP3(1);
        mp3.setNombre("Hits Globales 2025");
        mp3DAO.updateMP3(mp3);

        // ---------- 8. CATALOGO ----------
        catalogoDAO catDAO = new catalogoDAO();
        catalogo cat = new catalogo(1, 1, 1, new Date()); // id_proveedor=1, id_vinilo=1
        catDAO.createCatalogo(cat);
        catDAO.readCatalogo(1);
        cat.setId_vinilo(1);
        catDAO.updateCatalogo(cat);

        // ---------- 9. CARRITO ----------
        carritoDAO carDAO = new carritoDAO();
        carrito car = new carrito(1, 1, null); // id_usuario=1
        carDAO.createCarrito(car);
        carDAO.readCarrito(1);
        car.setId_usuario(1);
        carDAO.updateCarrito(car);

        // ---------- 10. CARRITO ITEM ----------
        carritoItemDAO carItemDAO = new carritoItemDAO();
        carritoItem item = new carritoItem(1, 1, "vinilo", 1, 2); // id_carrito=1, id_producto=1
        carItemDAO.createItem(item);
        carItemDAO.readItem(1);
        item.setCantidad(3);
        carItemDAO.updateItem(item);

        // ---------- 11. PLAYLIST ----------
        playListDAO plDAO = new playListDAO();
        playList pl = new playList(1, 1, "Favoritos", true); // id_usuario=1
        plDAO.createPlayList(pl);
        plDAO.readPlayList(1);
        pl.setNombre("Favoritos 2025");
        plDAO.updatePlayList(pl);

        // ---------- 12. PEDIDO ----------
        pedidoDAO pedDAO = new pedidoDAO();
        pedido ped = new pedido(1, 1, new Date(), "En proceso"); // id_usuario=1
        pedDAO.createPedido(ped);
        pedDAO.readPedido(1);
        ped.setEstado("Enviado");
        pedDAO.updatePedido(ped);

        // ---------- 13. PEDIDO DETALLE ----------
        pedidoDetalleDAO pedDetDAO = new pedidoDetalleDAO();
        pedidoDetalle det = new pedidoDetalle(1, 1, "vinilo", 1, 2, 150000.0); // id_pedido=1, id_producto=1
        pedDetDAO.createPedDetalle(det);
        pedDetDAO.readPedDetalle(1);
        det.setCantidad(3);
        pedDetDAO.updatePedDetalle(det);

        System.out.println("=== FIN DE PRUEBA DE LOS 13 DAOs ===");
        */
		// ================================================================
		// =========== CREACIÓN DE DATOS BASE PARA RELACIONES ==============
		// ================================================================

		System.out.println("\n=== CREANDO CANCIONES BASE PARA RELACIONES ===");

		cancionDAO cancionDAO = new cancionDAO();

		cancion c1 = new cancion(0, "Imagine", 3.2, 1.99, 5.0);
		int idC1 = cancionDAO.createCancion(c1);

		cancion c2 = new cancion(0, "Bohemian Rhapsody", 5.9, 2.99, 7.5);
		int idC2 = cancionDAO.createCancion(c2);

		// Si quieres confirmar o usarlos después
		System.out.println("Canción 'Imagine' creada con ID: " + idC1);
		System.out.println("Canción 'Bohemian Rhapsody' creada con ID: " + idC2);


		// Verificar lectura
		cancion cancionLeida = cancionDAO.readCancion(1);
		if (cancionLeida != null) {
		    System.out.println("Canción leída correctamente: " + cancionLeida.getNombre());
		}

		// ================================================================
		// ======== RELACIONES N–M ENTRE ENTIDADES Y CANCIONES ============
		// ================================================================

		System.out.println("\n=== PRUEBA DE RELACIONES N–M ===");

		// ---------- RELACIÓN DISCOMP3 ↔ CANCIÓN ----------
		discomp3_cancionDAO d3cDAO = new discomp3_cancionDAO();

		d3cDAO.addCancionToDisco(1, 1); // Disco MP3 1 contiene Canción 1
		d3cDAO.addCancionToDisco(1, 2); // Disco MP3 1 contiene Canción 2

		System.out.println("Canciones en el disco MP3 1: " + d3cDAO.getCancionesByDisco(1));
		System.out.println("Discos MP3 donde aparece la canción 1: " + d3cDAO.getDiscosByCancion(1));


		// ---------- RELACIÓN PLAYLIST ↔ CANCIÓN ----------
		playlist_cancionDAO plcDAO = new playlist_cancionDAO();

		plcDAO.addCancionToPlaylist(1, 1); // Playlist 1 contiene Canción 1
		plcDAO.addCancionToPlaylist(1, 2); // Playlist 1 contiene Canción 2

		System.out.println("Canciones en la playlist 1: " + plcDAO.getCancionesByPlaylist(1));
		System.out.println("Playlists donde aparece la canción 1: " + plcDAO.getPlaylistsByCancion(1));


		// ---------- RELACIÓN VINILO ↔ CANCIÓN ----------
		vinilo_cancionDAO vcDAO = new vinilo_cancionDAO();

		vcDAO.addCancionToVinilo(1, 1); // Vinilo 1 contiene Canción 1
		vcDAO.addCancionToVinilo(1, 2); // Vinilo 1 contiene Canción 2

		System.out.println("Canciones en el vinilo 1: " + vcDAO.getCancionesByVinilo(1));
		System.out.println("Vinilos donde aparece la canción 1: " + vcDAO.getVinilosByCancion(1));

		System.out.println("=== FIN DE PRUEBA DE RELACIONES N–M ===");

	}
}
