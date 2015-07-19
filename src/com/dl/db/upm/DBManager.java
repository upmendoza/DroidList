package com.dl.db.upm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.dl.models.*;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Julian on 12/05/2015.
 */


public class DBManager extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "DroidList.sqlite";
    private static final int DATABASE_VERSION = 1;

    private static DBManager manager;
    private ConnectionSource connectionSource;
    private Dao<Alumno, Integer> alumno = null;
    private Dao<Asignatura, Integer> asignatura = null;
    private Dao<Grupo, Integer> grupo = null;
    private Dao<Horario, Integer> horario = null;
    private Dao<Incidencia, Integer> incidencia = null;

    private Context context;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public ConnectionSource getHelperConnectionSource() {
        return this.connectionSource;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {

        this.connectionSource = connectionSource;

        try {
            TableUtils.createTable(connectionSource, Alumno.class);
            TableUtils.createTable(connectionSource, Incidencia.class);
            TableUtils.createTable(connectionSource, Asignatura.class);
            TableUtils.createTable(connectionSource, Grupo.class);
            TableUtils.createTable(connectionSource, Horario.class);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        this.connectionSource = connectionSource;

        try {

            while (oldVersion < newVersion) {
                switch (oldVersion) {
                    case 1:
                        break;
                }
                oldVersion++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Dao<Alumno, Integer> getAlumno() {

        if (alumno == null) {
            try {
                alumno = getDao(Alumno.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return alumno;
    }



    public Dao<Asignatura, Integer> getAsignatura() {

        if (asignatura == null) {
            try {
                asignatura = getDao(Asignatura.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return asignatura;
    }

    public Dao<Grupo, Integer> getGrupo() {

        if (grupo == null) {
            try {
                grupo = getDao(Grupo.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return grupo;
    }

    public Dao<Horario, Integer> getHorario() {

            if (horario == null) {
                try {
                    horario = getDao(Horario.class);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return horario;
        }

    public Dao<Incidencia, Integer> getIncidencia() {

            if (incidencia == null) {
                try {
                    incidencia = getDao(Incidencia.class);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return incidencia;
        }


    public static DBManager get(Context context) {
        if (manager == null)
            manager = OpenHelperManager.getHelper(context, DBManager.class);
        return manager;
    }

    public void clearAll() {
        try {
            TableUtils.clearTable(getConnectionSource(), Alumno.class);
            TableUtils.clearTable(getConnectionSource(), Incidencia.class);
            TableUtils.clearTable(getConnectionSource(), Asignatura.class);
            TableUtils.clearTable(getConnectionSource(), Grupo.class);
            TableUtils.clearTable(getConnectionSource(), Horario.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void clear(Class clas) {
        try {
            TableUtils.clearTable(getConnectionSource(), clas);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

