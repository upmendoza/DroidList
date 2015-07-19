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
public class Asignatura {
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String ID_GRUPO = "id_grupo";
    public static final String ID_HORARIO = "id_horario";
    @DatabaseField(generatedId = true, columnName = ID)
    private Integer id;
    @DatabaseField(columnName = NOMBRE)
    private String nombre;
    @DatabaseField(columnName = ID_GRUPO)
    private int id_grupo;
    @DatabaseField(columnName = ID_HORARIO)
    private int id_horario;




    public static void save(Context context, Asignatura a) {
            try {
                if(getIndex(a.getId(),context)!=null)
                    DBManager.get(context).getAsignatura().create(a);
                else
                    DBManager.get(context).getAsignatura().update(a);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static List<Asignatura> getArray(Context context) {
            List<Asignatura> array = new ArrayList<Asignatura>();
            try {
                array = DBManager.get(context).getAsignatura().queryForAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return array;
        }


        public static Asignatura getIndex(int index,Context context)
        {
            try {
                if(DBManager.get(context).getAsignatura().queryForEq(ID,index).size() > 0)
                {
                    return DBManager.get(context).getAsignatura().queryForEq(ID,index).get(0);
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(Integer id_grupo) {
        this.id_grupo = id_grupo;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }
}
