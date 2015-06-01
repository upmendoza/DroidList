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
    public static final String AP_PAT = "appat";
    public static final String AP_MAT = "apmat";
    public static final String MATRICULA = "matricula";
    public static final String EMAIL = "email";
    public static final String ID_GRUPO = "id_grupo";
    @DatabaseField(generatedId = true, columnName = ID)
    private Integer id;
    @DatabaseField(columnName = NOMBRE)
    private String nombre;
    @DatabaseField(columnName = AP_PAT)
    private String appat;
    @DatabaseField(columnName = AP_MAT)
    private String apmat;
    @DatabaseField(columnName = MATRICULA)
    private String matricula;
    @DatabaseField(columnName = EMAIL)
    private String email;
    @DatabaseField(columnName = ID_GRUPO)
    private Integer id_grupo;



    public static void save(Context context, Asignatura alumno) {
            try {
                if(getIndex(alumno.getId(),context)!=null)
                    DBManager.get(context).getAlumno().create(alumno);
                else
                    DBManager.get(context).getAlumno().update(alumno);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static List<Asignatura> getArray(Context context) {
            List<Asignatura> array = new ArrayList<Asignatura>();
            try {
                array = DBManager.get(context).getAlumno().queryForAll();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return array;
        }


        public static Asignatura getIndex(int i,Context context)
        {
            try {
                if(DBManager.get(context).getAlumno().queryForEq(ID,i).size() > 0)
                {
                    return DBManager.get(context).getAlumno().queryForEq(ID,i).get(0);
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

    public String getAppat() {
        return appat;
    }

    public void setAppat(String appat) {
        this.appat = appat;
    }

    public String getApmat() {
        return apmat;
    }

    public void setApmat(String apmat) {
        this.apmat = apmat;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(Integer id_grupo) {
        this.id_grupo = id_grupo;
    }
}
