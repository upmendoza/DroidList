package com.dl.upm;

/**
 * Creado por Villa.
 * El dia de 04/12/2014.
 * Hora 12:40 PM.
 * Profesor M.C. Ulises Ponse Mendoza
 * Universidad de la Sierra
 * Moctezuma Sonora Mexico.
 */

import android.graphics.drawable.Drawable;
public class ListaGrupo{
    protected Drawable Picture;
    protected String NomGrupo;
    protected long id;


    public ListaGrupo(Drawable Picture, String NomGrupo){
        super();
        this.Picture =Picture;
        this.NomGrupo = NomGrupo;
        this.id = id;
    }

    public ListaGrupo(Drawable Picture, String NomGrupo, long id){
        super();
        this.Picture = Picture;
        this.NomGrupo = NomGrupo;
        this.id = id;
    }

    public Drawable getPicture(){
        return Picture;
    }

    public void setPicture(Drawable Picture){
        this.Picture = Picture;
    }

    public String getNomGrupo(){
        return NomGrupo;
    }

    public void setNomGrupo(String NomGrupo){
        this.NomGrupo = NomGrupo;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
}