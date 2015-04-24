package com.dl.upm;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.dl.db.upm.DBAdapter;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ulises Ponce Mendoza on 14/04/2015.
 */
public class Horario extends Activity {

    DBAdapter BD = new DBAdapter(this);
    ListView lista;
    HorarioListAdapter adp;
    public Horario customListView;
    ArrayList<listaHorarios> horarios = new ArrayList<listaHorarios>();
    long idAsignatura;
    String sDias[]={"Lunes","Martes","Mi�rcoles","Jueves","Viernes"};

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario);
        customListView=this;
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        Intent i =getIntent();
        idAsignatura = i.getExtras().getLong("AS_ID");
        llenarLista();
        Resources res = getResources();
        lista = (ListView) findViewById(R.id.lstDiasHoras);
        adp = new HorarioListAdapter(Horario.this, horarios,res);
        lista.setAdapter(adp);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    lista.setAdapter(adp);
            }
        });

    }

    void updateDB() {
        //BD.open();
        //Cursor cHorarios = BD.fetch_horario(Long.toString(idAsignatura));
        //if (cHorarios.getCount()==0) {
            for(int i=0;i<5;i++){
                System.out.println(horarios.get(i).getDia()+"-"+horarios.get(i).isSeleccionado()+"-"+horarios.get(i).getHoraInicio()+
                "-"+horarios.get(i).getHoraFin());
            }
        //}
        /*else{
            for(int i=0;i<5;i++){
                final listaHorarios item;
                if(cHorarios.getInt(5)==0) item = new listaHorarios(sDias[i],"00:00","00:00",false,i);
                else item = new listaHorarios(cHorarios.getString(2),cHorarios.getString(3),cHorarios.getString(4),(cHorarios.getInt(5)==1)?true:false,cHorarios.getLong(1));
                horarios.add(item);
            }
        }*/
        //"create table horario (_id, id_asignatura, dia, hora_inicio, hora_fin, selected)
      //adaptamos e incorporamos los valores de ListaAsignatura a nuestro ListView.

    }
    void llenarLista() {
        BD.open();
        Cursor cHorarios = BD.fetch_horario(Long.toString(idAsignatura));
        if (cHorarios.getCount()==0) {
            for(int i=0;i<5;i++){
                final listaHorarios item = new listaHorarios(sDias[i],"00:00","00:00",false,i);
                horarios.add(item);
            }
        }
        else{
            for(int i=0;i<5;i++){
                final listaHorarios item;
                if(cHorarios.getInt(5)==0) item = new listaHorarios(sDias[i],"00:00","00:00",false,i);
                else item = new listaHorarios(cHorarios.getString(2),cHorarios.getString(3),cHorarios.getString(4),(cHorarios.getInt(5)==1),cHorarios.getLong(1));
                horarios.add(item);
            }
        }
        //"create table horario (_id, id_asignatura, dia, hora_inicio, hora_fin, selected)
        //adaptamos e incorporamos los valores de ListaAsignatura a nuestro ListView.

    }

    //ACTION BAR
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.alta_grupo, menu);
        return true;
    }
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()){
            case R.id.finish:
                updateDB();
                finishActivity(1);
                break;
        }
        return super.onMenuItemSelected(featureId, item);
    }
}
