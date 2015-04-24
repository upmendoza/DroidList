package com.dl.upm;

import android.graphics.drawable.Drawable;

/**
 * Created by Fito on 12/3/2014.
 */
public class ListaAsignaturas{
    protected Drawable Picture;
    protected String NomAsignatura;
    protected String Grupo;
    protected long id;

        public ListaAsignaturas(Drawable Picture, String NomAsignatura){
            super();
            this.Picture = Picture;
            this.NomAsignatura = NomAsignatura;
        }

        public ListaAsignaturas(Drawable Picture, String NomAsignatura, String Grupo, int id){
            super();
            this.Picture = Picture;
            this.NomAsignatura = NomAsignatura;
            this.Grupo = Grupo;
            this.id =id;
        }

    public Drawable getPicture(){
            return Picture;
        }

    public void setPicture(Drawable Picture){
            this.Picture = Picture;
        }

    public String getNomAsignatura(){
            return NomAsignatura;
        }

    public void setNomAsignatura(String NomAsignatura){
            this.NomAsignatura = NomAsignatura;
        }

    public String getGrupo() {
        return Grupo;
    }

    public long getId(){
            return id;
        }

    public void setId(long id){
            this.id = id;
        }
}