package com.dl.models;

import android.content.Context;
import com.dl.db.upm.DBManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julian on 12/05/2015.
 */
@DatabaseTable
public class Incidencia {
    public static final String ID = "id";
    public static final String ID_ASIGNATURA = "id_asignatura";
    public static final String ID_ALUMNO = "id_alumno";
    public static final String ESTADO = "estado";
    public static final String FECHA = "fecha";


    @DatabaseField(generatedId = true, columnName = ID)
    private Integer id;
    @DatabaseField(columnName = ID_ASIGNATURA)
    private int id_asignatura;
    @DatabaseField(columnName = ID_ALUMNO)
    private int id_alumno;
    @DatabaseField(columnName = ESTADO)
    private String estado;
    @DatabaseField(columnName = FECHA)
    private String fecha;


    public static void save(Context context, Incidencia i) {
        try {
            if (getIndex(i.getId(), context) != null)
                DBManager.get(context).getIncidencia().create(i);
            else
                DBManager.get(context).getIncidencia().update(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Incidencia> getArray(Context context) {
        List<Incidencia> array = new ArrayList<Incidencia>();
        try {
            array = DBManager.get(context).getIncidencia().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }


    public static Incidencia getIndex(int index, Context context) {
        try {
            if (DBManager.get(context).getIncidencia().queryForEq(ID, index).size() > 0) {
                return DBManager.get(context).getIncidencia().queryForEq(ID, index).get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(int id_asignatura) {
        this.id_asignatura = id_asignatura;
    }


    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
