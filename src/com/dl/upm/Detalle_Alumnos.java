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

public class Detalle_Alumnos extends Activity  {


    private DBAdapter dAlumnos;
    private Cursor cAlumnos;
    private boolean esAlta;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.alta_alumnos, menu);
        //UPM-DESPUÉS DE REALIZADO EL PRIMER COMIT MODIFICAR LOS MENU alta_*.xml PARA INTEGRARLOS EN UNO SOLO PUES HAY DESPERDICIO DE CODIGO.
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.finish:
                guardar_cambios();
                break;

        }
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_alumnos);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dAlumnos = new DBAdapter(this);
        Bundle extras = getIntent().getExtras(); //RECUPERANDO EL ID EN CASO DE ACTUALIZACIÓN
        if (extras == null) esAlta = true;
        else {
            esAlta = false;
            dAlumnos.open();
            cAlumnos = dAlumnos.fetch_alumnos(extras.getString("IDAlumno"));
            cAlumnos.moveToFirst();
            EditText matricula = (EditText) findViewById(R.id.matricula_txt);        matricula.setText(cAlumnos.getString(1));
            EditText nombre = (EditText) findViewById(R.id.nombre_txt);              nombre.setText(cAlumnos.getString(2));
            EditText apaterno = (EditText) findViewById(R.id.apaterno_txt);          apaterno.setText(cAlumnos.getString(3));
            EditText amaterno = (EditText) findViewById(R.id.amaterno_txt);          amaterno.setText(cAlumnos.getString(4));
            EditText celectronico = (EditText) findViewById(R.id.celectronico_txt);  celectronico.setText(cAlumnos.getString(5));
            dAlumnos.close(); // Se guardan los datos que vienen del layout grupo
        }
    }

    public void guardar_cambios() {
        dAlumnos.open();
        EditText matricula = (EditText) findViewById(R.id.matricula_txt);
        EditText nombre = (EditText) findViewById(R.id.nombre_txt);
        EditText apaterno = (EditText) findViewById(R.id.apaterno_txt);
        EditText amaterno = (EditText) findViewById(R.id.amaterno_txt);
        EditText celectronico = (EditText) findViewById(R.id.celectronico_txt);

        if (matricula.getText().toString().equals("")) {
            Toast.makeText(this, "ERROR el campo MATRICULA esta VACIO ", Toast.LENGTH_SHORT).show();
        } else {
            if (nombre.getText().toString().equals("")) {
                Toast.makeText(this, "ERROR el campo NOMBRE esta VACIO ", Toast.LENGTH_SHORT).show();
            } else {
                if (apaterno.getText().toString().equals("")) {
                    Toast.makeText(this, "ERROR el campo APELLIDO PATERNO esta VACIO ", Toast.LENGTH_SHORT).show();
                } else {
                    if (amaterno.getText().toString().equals("")) {
                        Toast.makeText(this, "ERROR el campo APELLIDO MATERNO esta VACIO ", Toast.LENGTH_SHORT).show();
                    } else {
                        if (celectronico.getText().toString().equals("")) {
                            Toast.makeText(this, "ERROR el campo CORREO esta VACIO", Toast.LENGTH_SHORT).show();
                        } else {
                            if (!celectronico.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                                Toast.makeText(this, "ERROR el campo CORREO NO ES VALIDO ", Toast.LENGTH_SHORT).show();
                            } else {
                                if (esAlta) {
                                    dAlumnos.creaAlumno(matricula.getText().toString(), nombre.getText().toString(), apaterno.getText().toString(), amaterno.getText().toString(), celectronico.getText().toString());
                                    matricula.setText("");
                                    nombre.setText("");
                                    apaterno.setText("");
                                    amaterno.setText("");
                                    celectronico.setText("");
                                }
                                else dAlumnos.updateAlumno(matricula.getText().toString(), nombre.getText().toString(), apaterno.getText().toString(), amaterno.getText().toString(), celectronico.getText().toString());
                                dAlumnos.close();

                            }
                        }
                    }
                }
            }
        }
    }
}
