package com.dl.upm;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;
import com.dl.db.upm.DBAdapter;
/**
 * Created by Yoset on 03/12/2014.
 * Modified by UPM on 07/04/2015
 */

public class Detalle_Asignatura extends Activity
{
    private DBAdapter dAsignaturas;
    private Cursor cAsignatura;
    private boolean esAlta;
    private String IDAs;

    // este metodo siempre se debe declarar, ya que en ella abrilos la base y guardamos en las variables lo que el usuario escribio
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.alta_asignatura, menu);
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
        setContentView(R.layout.agregar_asignaturas);
        dAsignaturas = new DBAdapter(this);
        Bundle extras = getIntent().getExtras(); //RECUPERANDO EL ID EN CASO DE ACTUALIZACI�N
        if (extras == null) esAlta = true;
        else {
            esAlta = false;
            dAsignaturas.open();
            IDAs=extras.getString("IDAsignatura");
            cAsignatura = dAsignaturas.fetch_asignaturas(IDAs);
            cAsignatura.moveToFirst();
            EditText et_asignatura = (EditText) findViewById(R.id.asignatura_txt);
            et_asignatura.setText(cAsignatura.getString(1));
            dAsignaturas.close();
        }
    }

    public void alta(){
        dAsignaturas.open();
        //los datos de los campos son convertidos a tipo string para poder manejarlos y mandarlos a la bd.
        EditText et_asignatura = (EditText) findViewById (R.id.asignatura_txt);
        // se realiza una serie de validaciones, esto es para saber si los campos estan vacios, NOTA: la validacion de DATOS DUPLICADOS se realiza en la clase DBAdapter
        // cuando quieres insertar
        if (et_asignatura.getText().toString().equals("")){
            Toast.makeText(this, "ERROR los campos estan VACIOS ", Toast.LENGTH_SHORT).show();
        }else {
            //se envia a la base de datos los datos del grupo y se cierra la conexion
            if (esAlta) {
                dAsignaturas.creaAsignatura(et_asignatura.getText().toString());
                et_asignatura.setText("");
            }
            else dAsignaturas.updateAsignatura(IDAs,et_asignatura.getText().toString());
            dAsignaturas.close();

        }
    }
}
