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
public class Horario {
    public static final String ID = "id";
    public static final String NOMBRE = "nombre";
    public static final String CARRERA = "appat";

    @DatabaseField(generatedId = true, columnName = ID)
    private Integer id;
    @DatabaseField(columnName = NOMBRE)
    private String nombre;
    @DatabaseField(columnName = CARRERA)
    private String carrera;




    public static void save(Context context, Horario grupo) {
                try {
                    if(getIndex(grupo.getId(),context)!=null)
                        DBManager.get(context).getGrupo().create(grupo);
                    else
                        DBManager.get(context).getGrupo().update(grupo);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        public static List<Horario> getArray(Context context) {
            List<Horario> array = new ArrayList<Horario>();
            try {
                array = DBManager.get(context).getGrupo().queryForAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return array;
        }


        public static Horario getIndex(int i,Context context)
        {
            try {
                if(DBManager.get(context).getGrupo().queryForEq(ID,i).size() > 0)
                {
                    return DBManager.get(context).getGrupo().queryForEq(ID,i).get(0);
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

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
