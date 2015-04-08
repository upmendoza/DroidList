package com.dl.upm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

public class Catalogo extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m_catalogo);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    public void alumnos(View v)
    {
        Intent i = new Intent(Catalogo.this, Alumnos.class);
        startActivity(i);
    }
    public void grupos(View v)
    {
        Intent i = new Intent(Catalogo.this, Grupos.class);
        startActivity(i);
    }
    public void asignaturas(View v)
    {
        Intent i = new Intent(Catalogo.this, Asignaturas.class);
        startActivity(i);
    }
    public void asistencia(View v)
    {
        Intent i = new Intent(Catalogo.this, Asistencia.class);
        startActivity(i);
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
}
