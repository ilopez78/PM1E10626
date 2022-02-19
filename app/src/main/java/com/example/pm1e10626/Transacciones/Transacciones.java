package com.example.pm1e10626.Transacciones;

public class Transacciones {
    /* tablas */
    public  static  final String tablacontactos = "Contactos";
    /* campos */
    public static final String id = "id";
    public static final String pais = "pais";
    public static final String nombre = "nombre";
    public static final String telefono = "telefono";
    public static final String nota = "nota";

    /* tablas -CREATE, DROP */
    public static final String CreateTableContactos = "CREATE TABLE Contactos( id INTEGER PRIMARY KEY AUTOINCREMENT, pais TEXT, nombre TEXT, telefono INTEGER," +
            "nota TEXT)";

    public static final String DropTableContactos = "DROP TABLE IF EXISTS Contactos";

    /* Creacion del nombre de la base de datos */
    public static final String NameDataBase = "DBExamen";
}//transacciones
