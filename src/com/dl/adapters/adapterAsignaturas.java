package com.dl.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.dl.upm.BtnClickListener;
import com.dl.upm.Horario;
import com.dl.upm.ListaAsignaturas;
import com.dl.upm.R;

import java.util.ArrayList;

/**
 * Created by Fito on 12/3/2014.
 */

public class adapterAsignaturas extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<ListaAsignaturas> items;
    private BtnClickListener mClickListener = null;

    public adapterAsignaturas(Activity activity, ArrayList<ListaAsignaturas> items, BtnClickListener listen){
        this.activity = activity;
        this.items = items;
        this.mClickListener=listen;
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
    public View getView(final int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.list_rowasignatura, null);
        }
        final ListaAsignaturas list = items.get(position);
        ImageView Picture = (ImageView)v.findViewById(R.id.foto);
        Picture.setImageDrawable(list.getPicture());
        TextView NomAsignatura = (TextView) v.findViewById(R.id.NombreAsignatura);
        NomAsignatura.setText(list.getNomAsignatura());
        TextView txtGrupo = (TextView) v.findViewById(R.id.txtGrupo);
        txtGrupo.setText(list.getGrupo());
        ImageView Horario = (ImageView) v.findViewById(R.id.imgHorario);
        Horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "CLIC EN BOTON DE HORARIO - Posici�n: " + position, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), com.dl.upm.Horario.class);
                i.putExtra("AS_ID",list.getId());
                v.getContext().startActivity(i);
            }
        });

        return v;
    }
}
