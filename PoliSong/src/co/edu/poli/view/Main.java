package co.edu.poli.view;

import java.util.Date;
import co.edu.poli.datos.*;
import co.edu.poli.model.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== INICIO PRUEBAS POLISONG =====");
        
        // DAO Instances
        correoDAO correoDao = new correoDAO();
        administradorDAO adminDao = new administradorDAO();
        usuarioDAO usuarioDao = new usuarioDAO();
        proveedorDAO proveedorDao = new proveedorDAO();
        cancionDAO cancionDao = new cancionDAO();
        playlist_cancionDAO playlistCancionDao = new playlist_cancionDAO();
        playListDAO playlistDao = new playListDAO();
        discoMP3DAO mp3Dao = new discoMP3DAO();
        discomp3_cancionDAO discoCancionDao = new discomp3_cancionDAO();
        viniloDAO viniloDao = new viniloDAO();
        vinilo_cancionDAO viniloCancionDao = new vinilo_cancionDAO();

        try {

            System.out.println("\n--- Creando correos ---");
            correoDao.createCorreo(new correo("admin@polisong.com"));
            correoDao.createCorreo(new correo("user@polisong.com"));
            correoDao.createCorreo(new correo("proveedor@polisong.com"));

            System.out.println("\n--- Creando administrador ---");
            adminDao.createAdmin(new administrador(1, "Admin PoliSong", "admin@polisong.com", "adminpass"));

            System.out.println("\n--- Creando usuario ---");
            usuarioDao.createUsuario(new usuario(1, "Usuario Test", "user@polisong.com", "userpass"));

            System.out.println("\n--- Creando proveedor ---");
            proveedorDao.createProveedor(new proveedor(1, "Proveedor 1", "proveedor@polisong.com", "provpass", 5));

            System.out.println("\n--- Creando canciones ---");
            int idC1 = cancionDao.createCancion(new cancion("Cancion A", 200, 3000, 5.0));
            int idC2 = cancionDao.createCancion(new cancion("Cancion B", 180, 2500, 4.5));

            System.out.println("\n--- Creando Playlist ---");
            playlistDao.createPlayList(new playList(1, 1, "Mi Playlist", true));
            playlistCancionDao.addCancionToPlaylist(1, idC1);
            playlistCancionDao.addCancionToPlaylist(1, idC2);

            System.out.println("\n--- Creando Disco MP3 ---");
            mp3Dao.createMP3(new discoMP3(1, "Disco MP3 X", new Date(), null));
            discoCancionDao.addCancionToDisco(1, idC1);

            System.out.println("\n--- Creando Vinilo ---");
            viniloDao.createVinilo(new vinilo(1, "Vinilo X", "Artista X", 2024, 120000, 10, null));
            viniloCancionDao.addCancionToVinilo(1, idC2);

            System.out.println("\n===== PRUEBAS READ =====");
            usuarioDao.readUsuario(1);
            playlistDao.readPlayList(1);
            mp3Dao.readMP3(1);
            viniloDao.readVinilo(1);

            System.out.println("\n===== PRUEBAS UPDATE =====");
            usuarioDao.updateUsuario(new usuario(1, "Usuario Modificado", "user@polisong.com", "newpass"));
            viniloDao.updateVinilo(new vinilo(1, "Vinilo Modificado", "Artista X", 2024, 150000, 5, null));

            System.out.println("\n===== PRUEBAS DELETE =====");
            // playlistCancionDao.removeCancionFromPlaylist(1, idC1);
            // usuarioDao.deleteUsuario(1);
            // playlistDao.deletePlayList(1);

        } catch (Exception e) {
            System.out.println("Error en pruebas: " + e.getMessage());
        }

        System.out.println("===== FIN PRUEBAS POLISONG =====");
    }
}
