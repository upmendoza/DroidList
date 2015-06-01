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
    public static final String DIA = "dia";
    public static final String HORA_INICIO = "hora_inicio";
    public static final String HORA_FIN = "hora_fin";


    @DatabaseField(generatedId = true, columnName = ID)
    private Integer id;
    @DatabaseField(columnName = ID_ASIGNATURA)
    private int id_asignatura;
    @DatabaseField(columnName = DIA)
    private String dia;
    @DatabaseField(columnName = HORA_INICIO)
    private String hora_inicio;
    @DatabaseField(columnName = HORA_FIN)
    private String hora_fin;


    public static void save(Context context, Incidencia h) {
        try {
            if (getIndex(h.getId(), context) != null)
                DBManager.get(context).getHorario().create(h);
            else
                DBManager.get(context).getHorario().update(h);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Incidencia> getArray(Context context) {
        List<Incidencia> array = new ArrayList<Incidencia>();
        try {
            array = DBManager.get(context).getHorario().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return array;
    }


    public static Incidencia getIndex(int i, Context context) {
        try {
            if (DBManager.get(context).getHorario().queryForEq(ID, i).size() > 0) {
                return DBManager.get(context).getHorario().queryForEq(ID, i).get(0);
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }
}
