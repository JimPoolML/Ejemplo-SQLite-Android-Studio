package appjpm4everyone.ejemplo_sqlite.utilidades;

import java.io.Serializable;

/**
 * Created by SIMON on 19/01/2018.
 */

//implements Serializable, de esta form el objeto puede ser enviado entre actividades
public class Utilidades implements Serializable{

    //Constantes campos, tablas  Usuario
    //Se crean las constantes a recomendacion de AndroidStudio
    //Para que el código sea más flexible
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_TELEFONO = "telefono";
/*
    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "
            +""+ TABLA_USUARIO + " (" +CAMPO_ID+" "+
            "INTEGER, " +CAMPO_NOMBRE+ "TEXT," +CAMPO_TELEFONO+ " TEXT)";*/

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE " +
            ""+TABLA_USUARIO+" ("+CAMPO_ID+" " +
            "INTEGER, "+CAMPO_NOMBRE+" TEXT,"+CAMPO_TELEFONO+" TEXT)";


    //Constantes campos, tablas  Mascota
    //Se crean las constantes a recomendacion de AndroidStudio
    //Para que el código sea más flexible
    public static final String TABLA_MASCOTA = "mascota";
    public static final String CAMPO_ID_MASCOTA = "id_mascota";
    public static final String CAMPO_NOMBRE_MASCOTA = "nombre_mascota";
    public static final String CAMPO_RAZA_MASCOTA = "raza_mascota";
    public static final String CAMPO_ID_DUENO = "id_dueno";


    public static final String CREAR_TABLA_MASCOTA="CREATE TABLE " +
            ""+TABLA_MASCOTA+" ("+CAMPO_ID_MASCOTA+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE_MASCOTA+" TEXT, "+CAMPO_RAZA_MASCOTA+" TEXT,"+CAMPO_ID_DUENO+" INTEGER)";

}
