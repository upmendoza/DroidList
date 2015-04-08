package com.dl.upm;

/**
 * Creado por Villa.
 * El dia de 04/12/2014.
 * Hora 01:01 PM.
 * Profesor M.C. Ulises Ponse Mendoza
 * Universidad de la Sierra
 * Moctezuma Sonora Mexico.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class adapterGrupo extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<ListaGrupo> items;

    public adapterGrupo(Activity activity, ArrayList<ListaGrupo> items){
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
            v = inf.inflate(R.layout.list_rowasignatura, null);
        }
        ListaGrupo list = items.get(position);

        ImageView Picture= (ImageView)v.findViewById(R.id.foto);
        Picture.setImageDrawable(list.getPicture());

        TextView NomAsignatura = (TextView) v.findViewById(R.id.NombreAsignatura);
        NomAsignatura.setText(list.getNomGrupo());

        return v;
    }
}

