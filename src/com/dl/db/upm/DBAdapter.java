package com.dl.db.upm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import com.dl.upm.R;


public class DBAdapter {

    // Definición de los campos de la base de datos

    // Campos de la Tabla GRUPO
    public static final String GP_ID = "_id";
    public static final String GP_NOMBRE = "nombre";
    public static final String GP_carrera = "carrera";
    public static final String DB_GRUPO = "grupo";

    // Campos de la Tabla ALUMNOS
    public static final String AL_ID = "_id";
    public static final String AL_MAT = "matricula";
    public static final String AL_NOMBRE = "nombre";
    public static final String AL_APPAT = "appat";
    public static final String AL_APMAT = "apmat";
    public static final String AL_EMAIL = "email";
    public static final String DB_ALUMNOS = "lista_alumnos";

    // Campos de la Tabla ASIGNATURA
    public static final String AS_ID = "_id";
    public static final String AS_NOMBRE = "nombre";
    public static final String AS_IDGPO = "id_grupo";
    public static final String AS_IDHR = "id_horario";
    public static final String DB_ASIGNATURA = "asignatura";

    // Campos de la Tabla HORARIO
    public static final String HR_ID = "_id";
    public static final String HR_IDAS = "id_asignatura";
    public static final String HR_DIA = "dia";
    public static final String HR_HRINICIO = "hora_inicio";
    public static final String HR_HRFIN = "hora_fin";
    public static final String DB_HORARIO = "horario";

    // Campos de la Tabla INCIDENCIA
    public static final String IN_ID = "_id";
    public static final String IN_IDAS = "id_asignatura";
    private static final String IN_IDEDO = "id_estado";
    private static final String IN_FECHA = "fecha";
    public static final String DB_INCIDENCIA = "incidencia";

    // Campos de la Tabla estado
    public static final String ES_ID = "_id";
    public static final String ES_ESTADO = "estado";
    public static final String DB_EDO = "estado";


    private Context context;
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DBAdapter(Context context) {
        this.context = context;
    }

    public DBAdapter open() throws SQLException {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }


    //INICIA APARTADO DE INSERCION DE REGISTROS EN TABLAS
    public long tomaLista(int IDCA, int IDE, String FECHA) {
        ContentValues values = new ContentValues();
        values.put(IN_IDAS, IDCA);
        values.put(IN_IDEDO, IDE);
        values.put(IN_FECHA, FECHA);
        return db.insert(DB_INCIDENCIA, null, values);
    }

    //Esta porcion de codigo no se modficara.
    public long creaGrupo(String NOMBRE, String CARRERA) {
        CharSequence text;
        long result;
        int duration = Toast.LENGTH_SHORT;
        Cursor MiCursor = db.query(DB_GRUPO, new String[]{GP_NOMBRE, GP_carrera}, "nombre = ? and carrera = ?", new String[]{NOMBRE, CARRERA}, null, null, null);

        if (MiCursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(GP_NOMBRE, NOMBRE);
            values.put(GP_carrera, CARRERA);
            result = db.insert(DB_GRUPO, null, values);
            text = "Entrada Registrada";       //CORREGIR esta situación mas adelante pues no se asegura que la inserción se haga correctamente
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return result;
        } else {
            text = "La Entrada no puede ser ingresada, pues crearía datos duplicados";   //Mejorar con Try Cath
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return -1;
    }
    //Esta porcion de codigo no se modficara.
    public long creaAsignatura(String NOMBRE, int VALOR) {
        CharSequence text;
        int duration = Toast.LENGTH_SHORT;
        Cursor MiCursor = db.query(DB_ASIGNATURA, new String[]{AS_NOMBRE, AS_IDGPO}, "nombre = ? and id_grupo = ?", new String[]{NOMBRE, Integer.toString(VALOR)}, null, null, null);

        if (MiCursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(AS_NOMBRE, NOMBRE);
            values.put(AS_IDGPO, VALOR);
            text = "Entrada Registrada";       //CORREGIR esta situación mas adelante pues no se asegura que la inserción se haga correctamente
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return db.insert(DB_ASIGNATURA, null, values);
        } else {
            text = "La Entrada no puede ser ingresada, pues crearía datos duplicados";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return -1;

    }

    public long creaAlumno(String MATRICULA, String NOMBRE, String APPAT, String APMAT, String EMAIL) {
        CharSequence text;
        int duration = Toast.LENGTH_SHORT;
        Cursor MiCursor = db.query(DB_ALUMNOS, new String[]{AL_MAT}, "matricula = ?", new String[]{MATRICULA}, null, null, null);

        if (MiCursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(AL_MAT, MATRICULA);
            values.put(AL_NOMBRE, NOMBRE);
            values.put(AL_APPAT, APPAT);
            values.put(AL_APMAT, APMAT);
            values.put(AL_EMAIL, EMAIL);
            text = "Entrada Registrada";       //CORREGIR esta situación mas adelante pues no se asegura que la inserción se haga correctamente
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return db.insert(DB_ALUMNOS, null, values);
        } else {
            text = "La Entrada no puede ser ingresada, pues crearía datos duplicados";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return -1;
    }

    public long creaHorario(int IDASIG, String DIA, String HRINICIO, String HRFIN) {
        CharSequence text;
        int duration = Toast.LENGTH_SHORT;
        Cursor MiCursor = db.query(DB_HORARIO, null, "id_asignatura = ? and dia = ?", new String[]{String.valueOf(IDASIG), DIA}, null, null, null);

        if (MiCursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(HR_IDAS, IDASIG);
            values.put(HR_DIA, DIA);
            values.put(HR_HRINICIO, HRINICIO);
            values.put(HRFIN, HRFIN);
            text = "Entrada Registrada";       //CORREGIR esta situación mas adelante pues no se asegura que la inserción se haga correctamente
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return db.insert(DB_HORARIO, null, values);
        } else {
            text = "La Entrada no puede ser ingresada, pues crearía datos duplicados";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return -1;
    }

    public long creaEstado(String ESTADO) {
        CharSequence text;
        int duration = Toast.LENGTH_SHORT;
        Cursor MiCursor = db.query(DB_EDO, null, "estado = ? ", new String[]{ESTADO}, null, null, null);

        if (MiCursor.getCount() == 0) {
            ContentValues values = new ContentValues();
            values.put(ES_ESTADO, ESTADO);
            text = "Entrada Registrada";       //CORREGIR esta situación mas adelante pues no se asegura que la inserción se haga correctamente
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return db.insert(DB_EDO, null, values);
        } else {
            text = "La Entrada no puede ser ingresada, pues crearía datos duplicados";
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return -1;
    }
//FINALIZA APARTADO DE INSERCION DE REGISTROS EN TABLAS


//ELIMINACION DE UN REGISTRO EN PARTICULAR
    public boolean elimina(String Tabla, int ID) {
        try {
            return db.delete(Tabla, "_id=" + Integer.toString(ID), null) > 0;
        } catch (SQLException e) {
            return false;
        }
    }


/*
* CONSULTAS DE ELEMENTOS DE TABLAS
*/
    public Cursor fetchAll(String DataBase) {
        return db.query(DataBase, null, null, null, null, null, null);
    }

    public Cursor fetchAll_grupos() {
        return db.query(DB_GRUPO, new String[]{GP_ID, GP_NOMBRE,
                GP_carrera}, null, null, null, null, null);
    }
    public Cursor fetchAll_asignatura() {
        return db.query(DB_ASIGNATURA, new String[]{AS_ID, AS_NOMBRE,AS_IDGPO}, null, null, null, null, null);
    }
    public Cursor fetchAll_alumnos() {
        return db.query(DB_ALUMNOS, new String[]{AL_ID, AL_MAT, AL_NOMBRE, AL_APPAT, AL_APMAT, AL_EMAIL},null, null, null, null, null);
    }

    public Cursor fetch_grupos(String vIDGPO) {
        return db.query(DB_GRUPO, new String[]{GP_ID, GP_NOMBRE,
                GP_carrera}, "_id = ?", new String[]{vIDGPO}, null, null, null);
    }
    public Cursor fetch_alumnos(String vID) {
        return db.query(DB_ALUMNOS, null, "_id=?", new String[]{vID}, null, null, null);
    }
    public Cursor fetch_asignaturas(String vID) {
        return db.query(DB_ASIGNATURA, null, "_id=?", new String[]{vID}, null, null, null);
    }
    public Cursor fetch_horario(String vID) {
        return db.query(DB_HORARIO, null, "id_asignatura = ?", new String[]{vID}, null, null, null);
    }
    public Cursor fetch_asignatura_carga(int vValue) {
        String MY_QUERY = "SELECT asignatura._id, asignatura.nombre, carga.ID_GPO" + " FROM asignatura INNER JOIN carga ON asignatura._id = carga.ID_AS1" + " WHERE (((carga.ID_GPO)=?));";
        return db.rawQuery(MY_QUERY, new String[]{String.valueOf(vValue)});
    }
/*
* CONSULTAS DE ELEMENTOS DE TABLAS
*/

/*
* ACTUALIZACION/MODIFICACIÓN DE DATOS EXISTENTES
*/

    public long updateGrupo(String NOMBRE, String[] NUEVOSDATOS) {
    CharSequence text;
    long result;
    int duration = Toast.LENGTH_SHORT;
    Cursor MiCursor = db.query(DB_GRUPO, new String[]{GP_NOMBRE, GP_carrera}, "nombre = ?", new String[]{NOMBRE}, null, null, null);

    if (MiCursor.getCount() == 0) {
        ContentValues values = new ContentValues();
        values.put(GP_NOMBRE, NUEVOSDATOS[0]);
        values.put(GP_carrera, NUEVOSDATOS[1]);
        result = db.update(DB_GRUPO, values,"nombre = ?",new String[]{NOMBRE});
        text = "Entrada Registrada";       //CORREGIR esta situación mas adelante pues no se asegura que la ACTUALIZACION se haga correctamente
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        return result;
    } else {
        text = "La Entrada no puede ser ACTUALIZADA, pues crearía datos duplicados";   //Mejorar con Try Cath
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    return -1;
}
    public long updateAlumno(String matricula,String nombre, String ApPat, String ApMat, String email) {
        CharSequence text;
        long result;
        int duration = Toast.LENGTH_SHORT;
        Cursor MiCursor = db.query(DB_ALUMNOS, new String[]{AL_NOMBRE}, "matricula = ?", new String[]{matricula}, null, null, null);

        if (MiCursor.getCount() == 1) {
            ContentValues values = new ContentValues();
            values.put(AL_MAT, matricula);
            values.put(AL_NOMBRE, nombre);
            values.put(AL_APPAT, ApPat);
            values.put(AL_APMAT, ApMat);
            values.put(AL_EMAIL, email);
            result = db.update(DB_ALUMNOS, values,"matricula = ?",new String[]{matricula});
            text = "Entrada Actualizada";       //CORREGIR esta situación mas adelante pues no se asegura que la ACTUALIZACION se haga correctamente
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return result;
        } else {
            text = "La Entrada no pude ser ACTUALIZADA, es probable que existan datos duplicados o no se halla encontrado el registro";   //Mejorar con Try Cath
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return -1;
    }

    public long updateAsignatura(String vID, String NOMBRE, int VALOR) {
        CharSequence text;
        long result;
        int duration = Toast.LENGTH_SHORT;
        Cursor MiCursor = db.query(DB_ASIGNATURA, new String[]{AS_NOMBRE}, "_id = ?", new String[]{vID}, null, null, null);

        if (MiCursor.getCount() == 1) {
            ContentValues values = new ContentValues();
            values.put(AS_NOMBRE, NOMBRE);
            values.put(AS_IDGPO, VALOR);
            result = db.update(DB_ASIGNATURA, values,"_id = ?",new String[]{vID});
            text = "Entrada ACTUALIZADA";       //CORREGIR esta situación mas adelante pues no se asegura que la ACTUALIZACION se haga correctamente
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return result;
        } else {
            text = "La Entrada no puede ser ACTUALIZADA, pues no se ha encontrado el registro";   //Mejorar con Try Cath
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return -1;
    }

    public long updateGrupos(String vID, String NOMBRE, String Carrera) {
        CharSequence text;
        long result;
        int duration = Toast.LENGTH_SHORT;
        Cursor MiCursor = db.query(DB_GRUPO, new String[]{GP_NOMBRE}, "_id = ?", new String[]{vID}, null, null, null);

        if (MiCursor.getCount() == 1) {
            ContentValues values = new ContentValues();
            values.put(GP_NOMBRE, NOMBRE);
            values.put(GP_carrera,Carrera);
            result = db.update(DB_GRUPO, values,"_id = ?",new String[]{vID});
            text = "Entrada ACTUALIZADA";       //CORREGIR esta situación mas adelante pues no se asegura que la ACTUALIZACION se haga correctamente
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return result;
        } else {
            text = "La Entrada no puede ser ACTUALIZADA, pues no se ha encontrado el registro";   //Mejorar con Try Cath
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        return -1;
    }
}
