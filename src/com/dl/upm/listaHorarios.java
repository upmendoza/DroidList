package com.dl.upm;



/**
 * Created by Ulises Ponce Mendoza on 16/04/2015.
 */
public class listaHorarios {

    private String dia;
    private String horaInicio;
    private String horaFin;
    private Boolean seleccionado;
    private long id;

    public listaHorarios(String dia, String horaInicio, String horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.seleccionado=false;
    }


    public listaHorarios(String dia, String horaInicio, String horaFin, Boolean sel, long id) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.seleccionado=sel;
        this.id=id;
    }

    public String getDia() {
        return dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(Boolean seleccionado) {
        this.seleccionado = seleccionado;
    }


    public long getId() {
        return id;
    }
}