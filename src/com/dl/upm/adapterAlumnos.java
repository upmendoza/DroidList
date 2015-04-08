package com.dl.upm;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DELL on 04/12/2014.
 */
public class adapterAlumnos extends BaseAdapter{
    protected Activity activity;
    protected ArrayList<listaAlumnos> items;

    public adapterAlumnos(Activity activity, ArrayList<listaAlumnos> items){
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount(){
        return items.size();
    }
    @Override
    public Object getItem(int arg0){
        return items.get(arg0);
    }

    @Override
    public long getItemId(int position){
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;

        if(convertView == null){
            LayoutInflater inf = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.list_rowalumnos, null);
        }
        listaAlumnos list = items.get(position);

        ImageView Picture= (ImageView)v.findViewById(R.id.imagenAlumno);
        Picture.setImageDrawable(list.getPicture());

        TextView nombre = (TextView) v.findViewById(R.id.nombreAlumno);
        nombre.setText(list.getNombre());

        TextView matricula = (TextView) v.findViewById(R.id.matriculaAlumno);
        matricula.setText(list.getMatriculaAlumno());

        TextView correo = (TextView) v.findViewById(R.id.correoAlumno);
        correo.setText(list.getCorreoAlumno());

        //String idAlumno = list.getIdAlumno();

        return v;
    }
}
