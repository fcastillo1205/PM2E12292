package com.example.pm2e12292.configuracion;

public class transaccion {
    //nombre db
    public static final String dbName="PM02E12292";

    //tablas
    public static final String tablePais="pais";
    public static final String tableContacto="contacto";

    //campos tabla pais
    public static final String idPais="idPais";
    public static final String nombrePais="nombrePais";
    public static final String codigoPais="codigoPais";

    //campos tabla contacto
    public static final String idContacto="idContacto";
    public static final String contactoPais="contactoPais";
    public static final String contactoNombre="contactoNombre";
    public static final String contactoNumero="contactoNumero";
    public static final String contactoNota="contactoNota";
    //Transacciones DDL
    public static final String createTablePais = "CREATE TABLE pais (idPais INTEGER PRIMARY KEY AUTOINCREMENT, nombrePais TEXT, codigoPais TEXT)";
    public static final String dropTablePais = "DROP TABLE IF EXIST pais";

    public static final String createTableContacto = "CREATE TABLE contacto (idContacto INTEGER PRIMARY KEY AUTOINCREMENT, contactoPais TEXT, contactoNombre TEXT, contactoNumero TEXT, contactoNota TEXT)";
    public static final String dropTableContacto = "DROP TABLE IF EXIST contacto";
}
