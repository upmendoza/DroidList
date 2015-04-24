package com.dl.upm;

import android.app.*;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.ArrayList;



public class HorarioListAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<listaHorarios> items;
    private static LayoutInflater inflater=null; //NUEVA
    public Resources resources; //NUEVA
    listaHorarios item=null; //NUEVA
    int i=0; //NUEVA
    public static String hora;


    public HorarioListAdapter(Activity activity, ArrayList<listaHorarios> items, Resources r) {
        this.activity = activity;
        this.items = items;
        this.resources=r;
        inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount(){
        return items.size();
    }
    @Override
    public Object getItem(int position){
        return position;
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    public static class viewHolder {
        public TextView hrInicio;
        public TextView hrFin;
        public TextView dia;
        public CheckBox selec;

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        final viewHolder holder;

        if(convertView == null){
            v = inflater.inflate(R.layout.horario_row,null);
            holder = new viewHolder();
            holder.dia      =   (TextView) v.findViewById(R.id.txtDia);
            holder.hrInicio =   (TextView) v.findViewById(R.id.txtHoraIn);
            holder.hrFin    =   (TextView) v.findViewById(R.id.txtHoraOut);
            holder.selec    =   (CheckBox) v.findViewById(R.id.chkDia);
            v.setTag(holder);
        }
        else holder = (viewHolder) v.getTag();

        if(items.size()<=0) holder.dia.setText("N/A");
        else{
            //item = null;
            item = items.get(position);
            holder.dia.setText(item.getDia());
            holder.hrInicio.setText(item.getHoraInicio());
            holder.hrFin.setText(item.getHoraFin());
            holder.selec.setChecked(item.isSeleccionado());
        }

        holder.selec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "El día " + item.getDia() + " esta seleccionado; " + item.isSeleccionado(), Toast.LENGTH_SHORT);
                item.setSeleccionado(holder.selec.isChecked());
                notifyDataSetChanged();
            }
        });
        holder.hrInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.get(position).setHoraInicio(timePicker(v));
                notifyDataSetChanged();
            }
        });
        holder.hrFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.get(position).setHoraFin(timePicker(v));
                notifyDataSetChanged();
                TextView hr = (TextView) v.findViewById(R.id.txtHoraOut);
                item.setHoraFin(items.get(position).getHoraFin());
                hr.setText(items.get(position).getHoraFin());
            }
        });

        return v;

    }

    String timePicker (final View v){

        int TIME_DIALOG_ID = 0;

        TimePickerDialog.OnTimeSetListener mTimeSetListener =
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hora = Integer.toString(hourOfDay)+":"+Integer.toString(minute);

                    }
                };

        TimePickerDialog dlgTime = new TimePickerDialog(v.getContext(),mTimeSetListener,0,0,false);
        dlgTime.show();
        return hora;
    }


}
