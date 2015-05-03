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

    DBAdapter dAlumnos;
    Cursor cAlumnos, dataAsistencia;
    String[] array = new String[200];
    ListView lista;
    String id_consulta;
    String id_alumno;
    ArrayList<listaAlumnos> datosassign;
    ArrayList<Integer> listIdAlumnos;
    listaAlumnos list;
    String base;
    private Bundle mBundle;
    int opcionElegida = -1;

    private final String ASISTENCIA = "1";
    private final String JUSTIFICACION = "2";
    private final String RETARDO = "3";
    private final String FALTA = "4";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toma_asistencia);

        llenaLista();


        lista = (ListView) findViewById(R.id.listItems);

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, final long id) {
                id_alumno = String.valueOf(array[position]);
                dAlumnos = new DBAdapter(getApplicationContext());
                dAlumnos.open();
                cAlumnos = dAlumnos.fetch_alumnos(id_alumno);
                dataAsistencia = dAlumnos.fetch_incidencia(id_alumno, getFechaActual());
                int columnAlumnoEstado = dataAsistencia.getColumnIndex(dAlumnos.IN_IDEDO);

                try {
                    opcionElegida = Integer.parseInt(dataAsistencia.getString(3));
                } catch (Exception e) {
                    opcionElegida = -1;
                    e.printStackTrace();
                }
                /*if (opcionElegida < 0)
                    opcionElegida = -1; */
                //id_consulta = array[position];

                // base = "Alumno";
                final String[] items = {"Asistencia", "Justificación", "Retardo", "Falta"};
                AlertDialog.Builder builder = new AlertDialog.Builder(Asistencia_grupos.this);
                builder.setTitle("Seleccione")
                        .setSingleChoiceItems(items, opcionElegida,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int item) {
                                        Toast.makeText(getApplicationContext(), "Elegido: " + items[item], Toast.LENGTH_LONG).show();
                                        String id_Estado = "";
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

                                        // int columnAlumnoId = dataAsistencia.getColumnIndex(dAlumnos.IN_AL);
                                        // String id_alumno = cAlumnos.getString(columnAlumnoId);
                                        dAlumnos.tomaLista("0", id_alumno, id_Estado, getFechaActual());
                                        llenaLista();
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

                                             id_alumno = String.valueOf(array[position]);
                                             dAlumnos = new DBAdapter(getApplicationContext());
                                             dAlumnos.open();
                                             cAlumnos = dAlumnos.fetch_alumnos(id_alumno);
                                             dataAsistencia = dAlumnos.fetch_incidencia(id_alumno, getFechaActual());
                                             int columnAlumnoEstado = dataAsistencia.getColumnIndex(dAlumnos.IN_IDEDO);

                                             try {
                                                 opcionElegida = Integer.parseInt(dataAsistencia.getString(columnAlumnoEstado));
                                             } catch (Exception e) {
                                                 opcionElegida = -1;
                                                 e.printStackTrace();
                                             }
                                             id_alumno = String.valueOf(array[position]);


                                             dataAsistencia = dAlumnos.fetch_incidencia(id_alumno, getFechaActual());

                                            //Aquí se va a cambiar de presente a falta y viceversa.
                                             try {
                                                 if (opcionElegida == Integer.parseInt(ASISTENCIA)) {
                                                     dAlumnos.tomaLista("0", id_alumno, FALTA, getFechaActual());   //El id asignatura lo dejé vacío (con un 0) por que no lo tengo.
                                                     Toast.makeText(getApplicationContext(), "ASISTENCIA", Toast.LENGTH_LONG).show();
                                                 } else {
                                                     dAlumnos.tomaLista("0", id_alumno, ASISTENCIA, getFechaActual());
                                                     Toast.makeText(getApplicationContext(), "FALTA ", Toast.LENGTH_LONG).show();
                                                 }
                                             } catch (Exception e) {
                                                 //No hay registro entonces se crea uno nuevo
                                                 dAlumnos.tomaLista("0", id_alumno, ASISTENCIA, getFechaActual());
                                                 e.printStackTrace();
                                             }
                                             llenaLista();

                                             //Toast.makeText(getApplicationContext(), "ID Alumno: " + id_alumno, Toast.LENGTH_LONG).show();

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








