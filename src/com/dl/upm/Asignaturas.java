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
import com.dl.db.upm.DBAdapter;
import com.dl.upm.SimpleGestureFilter.SimpleGestureListener;

import java.util.ArrayList;

public class Asignaturas extends Activity implements SimpleGestureListener{

    DBAdapter BD;
    Cursor dataAs;
    String[] array = new String[200];
    ListView lista;
    String id_consulta;
    ArrayList<ListaAsignaturas> datosassign;
    ListaAsignaturas list;
    String base;
    private SimpleGestureFilter detector; //SWIPE

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.asignatura, menu);
        return true;
    }
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        Intent i = new Intent(Asignaturas.this, Detalle_Asignatura.class);
        switch (item.getItemId()){
            case R.id.insert:
                startActivity(i);
                break;
            case R.id.update:
                i.putExtra("IDAsignatura",Long.valueOf(id_consulta));
                startActivity(i);
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogos);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        detector=new SimpleGestureFilter(this,this); //SWIPE
        llenaLista();
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                id_consulta = array[position];
                base = "asignatura";

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(view.getContext());
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Seguro que desea eliminar la Asignatura ?");
                //dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogo1, int id) {
                        BD.open();
                        BD.elimina(base, Integer.parseInt(id_consulta));
                        BD.close();
                        onRestart();
                        Toast.makeText(getApplicationContext(), "Se ha eliminado la Asignatura Seleccionada", Toast.LENGTH_LONG).show();
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
        llenaLista();
    }

    void llenaLista (){
        BD = new DBAdapter(this);
        BD.open();
        dataAs = BD.fetchAll_asignatura();

        lista = (ListView) findViewById(R.id.listItems);
        datosassign = new ArrayList<ListaAsignaturas>();
        int i=0;
        while (dataAs.moveToNext()){
            array[i]=dataAs.getString(0);
            Cursor cGpo= BD.fetch_grupos(dataAs.getString(2));
            cGpo.moveToFirst(); String gpo = cGpo.getString(1);
            list = new ListaAsignaturas(getResources().getDrawable(R.drawable.im_menu_asignatura),dataAs.getString(1), gpo,dataAs.getInt(0));
            datosassign.add(list);
            i++;
        }
        adapterAsignaturas adp = new adapterAsignaturas(this, datosassign, new BtnClickListener() {
            @Override
            public void onBtnClick(int position) {

            }
        });
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
                base = "asignatura";
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Seguro que desea eliminar la Asignatura ?");
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogo1, int id) {
                        BD.open();
                        BD.elimina(base, Integer.parseInt(id_consulta));
                        BD.close();
                        onRestart();
                        Toast.makeText(getApplicationContext(), "Se ha eliminado la Asignatura Seleccionada", Toast.LENGTH_LONG).show();
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
