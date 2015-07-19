package com.dl.upm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.*;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.dl.adapters.adapterAlumnos;
import com.dl.db.upm.DBAdapter;

import java.util.ArrayList;

public class Alumnos extends Activity implements SimpleGestureFilter.SimpleGestureListener {

    DBAdapter Alumnos;
    Cursor dataAl;
    String[] array = new String[200];
    String id_consulta;
    String base;
    ListView lista;
    ArrayList<listaAlumnos> datosassign;
    listaAlumnos list;
    private SimpleGestureFilter detector; //SWIPE

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.alumnos, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        Intent i = new Intent(Alumnos.this, Detalle_Alumnos.class);
        switch (item.getItemId()){
            case R.id.insert:
                startActivity(i);
                break;
            case R.id.update:
                i.putExtra("IDAlumno",id_consulta);
                startActivity(i);
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogos);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        detector = new SimpleGestureFilter(this,this); //SWIPE
        fillList();
        //HAY QUE CAMBIAR EL LONGCLICK POR UN GESTURE LISTENER
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                id_consulta = array[position];
                base = "lista_alumnos";

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(view.getContext());
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿Seguro que desea eliminar el Alumno ?");
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialogo1, int id) {
                        Alumnos.open();
                        Alumnos.elimina(base, Integer.parseInt(id_consulta));
                        Alumnos.close();
                        Toast.makeText(getApplicationContext(), "Se ha eliminado el Alumno seleccionado", Toast.LENGTH_LONG).show();
                        onRestart();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogo1.show();
                return false;
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_consulta = array[position];
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fillList();
    }

    void fillList(){
        Alumnos = new DBAdapter(this);
        Alumnos.open();
        dataAl = Alumnos.fetchAll_alumnos();

        lista = (ListView) findViewById(R.id.listItems);
        datosassign = new ArrayList<listaAlumnos>();

        //Se introducen los datos de la base de datos dentro de nuestra lista personalizada
        int i=0;
        while (dataAl.moveToNext()){
            array[i]=dataAl.getString(0);
            list = new listaAlumnos(getResources().getDrawable(R.drawable.im_menu_alumno),dataAl.getString(2)+" "+dataAl.getString(3)+" "+dataAl.getString(4),dataAl.getString(1),dataAl.getString(5));
            datosassign.add(list);
            i++;
        }

        //adaptamos e incorporamos los valores de ListaAsignatura a nuestro ListView.
        adapterAlumnos adp = new adapterAlumnos(this, datosassign);
        lista.setAdapter(adp);
    }
    //SWIPE
    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }
    @Override
    public void onSwipe(int direction) {
        String str = "";

        switch (direction) {

            case SimpleGestureFilter.SWIPE_RIGHT : str = "Swipe Right";
            case SimpleGestureFilter.SWIPE_LEFT :  str = "Swipe Left";
                base = "lista_alumnos";

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿Seguro que desea eliminar el Alumno ?");
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {


                    public void onClick(DialogInterface dialogo1, int id) {
                        Alumnos.open();
                        Alumnos.elimina(base, Integer.parseInt(id_consulta));
                        Alumnos.close();
                        Toast.makeText(getApplicationContext(), "Se ha eliminado el Alumno seleccionado", Toast.LENGTH_LONG).show();
                        onRestart();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {

                    }
                });
                dialogo1.show();
                break;
            case SimpleGestureFilter.SWIPE_DOWN :  str = "Swipe Down";
                break;
            case SimpleGestureFilter.SWIPE_UP :    str = "Swipe Up";
                break;

        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDoubleTap() {
        Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();
    }

}
