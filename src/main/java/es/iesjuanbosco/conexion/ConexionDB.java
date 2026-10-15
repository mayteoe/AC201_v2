package es.iesjuanbosco.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // Formato de la URL:  jdbc:mariadb://  <host> : <puerto> / <nombre_de_la_BD>
    private static final String URL="jdbc:sqlite:empresa.db";
    //private static final String URL = "jdbc:mariadb://localhost:3306/empresa";

    // Deben coincidir con MARIADB_USER y MARIADB_PASSWORD
    private static final String USUARIO = "root";
    private static final String PASSWORD = "toor";

    // Constructor privado: es una clase de utilidad, no se instancia (new ConexionBD()).

    private ConexionDB() {
    }

    /**
     * Abre una conexión NUEVA cada vez que se llama.
     *
     * IMPORTANTE: quien la pida es responsable de cerrarla. Por eso en los DAO
     * se usa siempre try-with-resources, que la cierra automáticamente aunque
     * ocurra un error.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }
}