package com.dl.db.upm;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by IntelliJ IDEA.
 * User: Ulises
 * Date: 16/03/12
 * Time: 13:31
 * To change this template use File | Settings | File Templates.
 */
public class DBTables {
    // Enunciados SQL para la creación de tablas de la Base de Datos
    //Falta especificar la longitud de cada uno de los campos.

    private static final String DATABASE_CREATE_GRUPO = "create table grupo (_id integer primary key autoincrement,"
            + "nombre text not null, " + "carrera text not null);";

    //Se modifico: ID y se añadieron los campos: appat, apmat, email.
    private static final String DATABASE_CREATE_ALUMNO = "create table lista_alumnos (_id integer primary key autoincrement, matricula text not null, "
            + "nombre text not null, " + "appat text not null, " + "apmat text not null, " + "email text not null);";

    private static final String DATABASE_CREATE_ASIGNATURA = "create table asignatura (_id integer primary key autoincrement,"
            + "nombre text not null, "+"id_grupo integer, "+"id_horario integer);";

    private static final String DATABASE_CREATE_HORARIO = "create table horario (_id integer primary key autoincrement,"
            + "id_asignatura integer not null, " + "dia text not null, " + "hora_inicio text not null, " + "hora_fin text not null, " + "selected integer not null);";

    private static final String DATABASE_CREATE_INCIDENCIA = "create table incidencia (_id integer primary key autoincrement,"
            + "id_asignatura integer not null, " + "id_estado integer not null, " + "fecha text not null);";

    private static final String DATABASE_CREATE_ESTADO = "create table estado (_id integer primary key autoincrement,"
            + "estado text not null);";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE_GRUPO);
        database.execSQL(DATABASE_CREATE_ALUMNO);
        database.execSQL(DATABASE_CREATE_ASIGNATURA);
        database.execSQL(DATABASE_CREATE_HORARIO);
        database.execSQL(DATABASE_CREATE_INCIDENCIA);
        database.execSQL(DATABASE_CREATE_ESTADO);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(DBTables.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS grupo");
        database.execSQL("DROP TABLE IF EXISTS lista_alumnos");
        database.execSQL("DROP TABLE IF EXISTS asignatura");
        database.execSQL("DROP TABLE IF EXISTS horario");
        database.execSQL("DROP TABLE IF EXISTS incidencia");
        database.execSQL("DROP TABLE IF EXISTS estado");
        onCreate(database);
    }
}
