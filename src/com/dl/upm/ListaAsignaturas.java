package com.dl.upm;

import android.graphics.drawable.Drawable;

/**
 * Created by Fito on 12/3/2014.
 */
public class ListaAsignaturas{
    protected Drawable Picture;
    protected String NomAsignatura;
    protected long id;

        public ListaAsignaturas(Drawable Picture, String NomAsignatura){
            super();
            this.Picture = Picture;
            this.NomAsignatura = NomAsignatura;
        }

        public ListaAsignaturas(Drawable Picture, String NomAsignatura, String CodAsignatura, String Carrera, long id){
            super();
            this.Picture = Picture;
            this.NomAsignatura = NomAsignatura;
            this.id = id;
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

        public long getId(){
            return id;
        }

        public void setId(long id){
            this.id = id;
        }
}