package com.dl.upm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.dl.db.upm.DBAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Asistencia_grupos extends Activity {

    DBAdapter dAlumnos, Asistencia;
    Cursor cAlumnos, dataAsistencia;
    String[] array = new String[200];
    ListView lista;
    String id_consulta;
    String id_alumno;
    ArrayList<listaAlumnos> datosassign;
    ArrayList<Integer>listIdAlumnos;
    listaAlumnos list;
    String base;
    private Bundle mBundle;
    int opcionElegida = -1;

    private final int ASISTENCIA = 1;
    private final int JUSTIFICACION = 2;
    private final int RETARDO = 3;
    private final int FALTA = 4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toma_asistencia);

        llenaLista();
        if (opcionElegida < 0)
            opcionElegida = -1;


        lista = (ListView) findViewById(R.id.listItems);

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, final long id) {
                //id_consulta = array[position];
                id_alumno = String.valueOf(array[position]);
                // base = "Alumno";
                final String[] items = {"Asistencia", "Justificación", "Retardo", "Falta"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Asistencia_grupos.this);
                builder.setTitle("Seleccione")
                        .setSingleChoiceItems(items, opcionElegida,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int item) {
                                        Toast.makeText(getApplicationContext(), "Elegido: " + items[item], Toast.LENGTH_LONG).show();
                                        int id_Estado = 0;
                                        if ((items[item] == items[0])) {
                                            id_Estado = ASISTENCIA;

                                        } else if ((items[item] == items[1])) {
                                            id_Estado = JUSTIFICACION;

                                        } else if ((items[item] == items[2])) {
                                            id_Estado = RETARDO;

                                        } else if ((items[item] == items[3])) {
                                            id_Estado = FALTA;

                                        }

                                        dialog.dismiss();
                                        dAlumnos = new DBAdapter(getApplicationContext());
                                        dAlumnos.open();
                                        cAlumnos = dAlumnos.fetch_alumnos(id_alumno);
                                        Asistencia.tomaLista(0, cAlumnos.getInt(0), id_Estado, getFechaActual());

                                        //Aquí se va a guardar la selección mediante insert o update a la bd

                                        // Log.i("Asistencia", "Opción elegida: " + items[item]);
                                    }
                                });
                AlertDialog alert = builder.create();
                alert.show();


                return true;
            }

        });


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                         @Override
                                         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                             //id_consulta = position;
                                             id_alumno = String.valueOf(array[position]);
                                             Asistencia = new DBAdapter(getApplicationContext());
                                             Asistencia.open();
                                             dAlumnos = new DBAdapter(getApplicationContext());
                                             dAlumnos.open();

                                             cAlumnos = dAlumnos.fetch_alumnos(id_alumno);
                                             if (cAlumnos.getInt(0) == ASISTENCIA)
                                                 Asistencia.tomaLista(0, cAlumnos.getInt(0), FALTA, getFechaActual());
                                             else
                                                 Asistencia.tomaLista(0, cAlumnos.getInt(0), ASISTENCIA, getFechaActual());
                                             //if ()
                                             Toast.makeText(getApplicationContext(), "ID Alumno: " + id_alumno, Toast.LENGTH_LONG).show();
                                             //Aquí se va a cambiar de presente a falta y viceversa.
                                         }
                                     }

        );

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        llenaLista();
    }

    void llenaLista() {
        //Consulta de todos los Alumnos.
        mBundle = getIntent().getExtras();
        String idGrupo = mBundle.getString("id");
        int id_grupo = Integer.parseInt(idGrupo);
        dAlumnos = new DBAdapter(this);
        dAlumnos.open();
        cAlumnos = dAlumnos.fetchAll_alumnosByGroup(id_grupo);

        lista = (ListView) findViewById(R.id.listItems);
        datosassign = new ArrayList<listaAlumnos>();// dAlumnos.fetchAll_alumnos();

        int i = 0;
        while (cAlumnos.moveToNext()) {
            array[i] = cAlumnos.getString(0);
            list = new listaAlumnos(getResources().getDrawable(R.drawable.im_menu_alumno), cAlumnos.getString(1) + " " + cAlumnos.getString(2) + " " + cAlumnos.getString(3), "", "");
            datosassign.add(list);
            i++;
        }
        adapterAlumnosAsistencia adp = new adapterAlumnosAsistencia(this, datosassign);
        lista.setAdapter(adp);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


   /* @Override
    public void onDoubleTap() {
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
    }*/

    void insertarEstatusAsistencia() {

    }

    public static String getFechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat(
                "dd-MM-yyyy");
        return formateador.format(ahora);
    }
}








