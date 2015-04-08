package com.dl.upm;

import android.app.*;
import android.database.Cursor;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.dl.db.upm.DBAdapter;

/**
 * Created by Yoset on 03/12/2014.
 * Modified by UPM on 07/04/2015
 */

public class Detalle_Grupos extends Activity
{
    private DBAdapter dGrupos;
    private Cursor cGrupos;
    private boolean esAlta;
    private String IDAs;

    // este metodo siempre se debe declarar, ya que en ella abrilos la base y guardamos en las variables lo que el usuario escribio
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.alta_grupo, menu);
        return true;
    }


    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()){
            case R.id.finish:
                alta();
                break;

        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.agregar_grupos);
        dGrupos = new DBAdapter(this);
        Bundle extras = getIntent().getExtras(); //RECUPERANDO EL ID EN CASO DE ACTUALIZACION
        if (extras == null) esAlta = true;
        else {
            esAlta=false;
            dGrupos.open();
            IDAs=extras.getString("IDGrupo");
            cGrupos=dGrupos.fetch_grupos(IDAs);
            cGrupos.moveToFirst();
            EditText grupo_p = (EditText) findViewById (R.id.edi_grupo);        grupo_p.setText(cGrupos.getString(1));
            EditText carrera_p = (EditText) findViewById (R.id.edi_carrera);    carrera_p.setText(cGrupos.getString(2));
        }

    }

    public void alta(){
        dGrupos.open();
        //los datos de los campos son convertidos a tipo string para poder manejarlos y mandarlos a la bd.
        EditText grupo_p = (EditText) findViewById (R.id.edi_grupo);
        EditText carrera_p = (EditText) findViewById (R.id.edi_carrera);
        // se realiza una serie de validaciones, esto es para saber si los campos estan vacios, NOTA: la validacion de DATOS DUPLICADOS se realiza en la clase DBAdapter
        // cuando quieres insertar
        if (grupo_p.getText().toString().equals("") && carrera_p.getText().toString().equals("")){
            Toast.makeText(this, "ERROR los campos estan VACIOS ", Toast.LENGTH_SHORT).show();
        }else {
            if (grupo_p.getText().toString().equals("")) {
                Toast.makeText(this, "ERROR el  campo GRUPO esta VACIO ", Toast.LENGTH_SHORT).show();
            } else {
                if (carrera_p.getText().toString().equals("")) {
                    Toast.makeText(this, "ERROR el campo CARRERA esta VACIO ", Toast.LENGTH_SHORT).show();
                } else {
                    //se envia a la base de datos los datos del grupo y se cierra la conexion
                    if (esAlta) {
                        dGrupos.creaGrupo(grupo_p.getText().toString(), carrera_p.getText().toString());
                        grupo_p.setText("");
                        carrera_p.setText("");
                    } else dGrupos.updateGrupos(IDAs, grupo_p.getText().toString(), carrera_p.getText().toString());
                    dGrupos.close();

                }
            }
        }
    }


}