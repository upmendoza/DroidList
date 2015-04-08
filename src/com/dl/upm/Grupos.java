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

import java.util.ArrayList;

public class Grupos extends Activity implements SimpleGestureFilter.SimpleGestureListener{

    DBAdapter Grupo;
    Cursor dataGrupos;
    String[] array = new String[200];
    ListView lista;
    String id_consulta;
    ArrayList<ListaGrupo> datosassign;
    ListaGrupo list;
    String base;
    private SimpleGestureFilter detector;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.agregar_grupo, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        Intent i = new Intent(Grupos.this, Detalle_Grupos.class);
        switch (item.getItemId()){
            case R.id.insert:
                startActivity(i);
                break;
            case R.id.update:
                i.putExtra("IDGrupo",id_consulta);
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
        detector=new SimpleGestureFilter(this,this);
        llenaLista();
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                id_consulta = array[position];
                base = "grupo";

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(view.getContext());
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Seguro que desea eliminar el Grupo ?");
                //dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogo1, int id) {
                        Grupo.open();
                        Grupo.elimina(base, Integer.parseInt(id_consulta));
                        Grupo.close();
                        onRestart();
                        Toast.makeText(getApplicationContext(), "Se ha eliminado el grupo seleccionado", Toast.LENGTH_LONG).show();
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

    void llenaLista() {
        //Consulta de todos los grupos.
        Grupo = new DBAdapter(this);
        Grupo.open();
        dataGrupos = Grupo.fetchAll_grupos();

        lista = (ListView) findViewById(R.id.listItems);
        datosassign = new ArrayList<ListaGrupo>();
        int i =0;
        while (dataGrupos.moveToNext()){
            array[i]=dataGrupos.getString(0);
            list = new ListaGrupo(getResources().getDrawable(R.drawable.im_menu_grupos),dataGrupos.getString(1));
            datosassign.add(list);
            i++;
        }
        adapterGrupo adp = new adapterGrupo(this, datosassign);
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
                base = "grupo";

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Seguro que desea eliminar el Grupo ?");
                //dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialogo1, int id) {
                        Grupo.open();
                        Grupo.elimina(base, Integer.parseInt(id_consulta));
                        Grupo.close();
                        onRestart();
                        Toast.makeText(getApplicationContext(), "Se ha eliminado el grupo seleccionado", Toast.LENGTH_LONG).show();
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








