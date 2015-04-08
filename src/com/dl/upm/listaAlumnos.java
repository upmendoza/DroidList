package com.dl.upm;

import android.graphics.drawable.Drawable;

/**
 * Created by DELL on 04/12/2014.
 */
public class listaAlumnos {
    protected Drawable imagen;
    protected String nombreAlumno;
    protected String matriculaAlumno;
    protected String correoAlumno;
    protected long id;

    public listaAlumnos(Drawable Picture, String nombre, String matricula, String correo){
        super();
        this.imagen = Picture;
        this.nombreAlumno = nombre;
        this.correoAlumno = correo;
        this.matriculaAlumno = matricula;
    }

    public listaAlumnos(Drawable Picture, String nombre, String matricula, String correo, long id){
        super();
        this.imagen = Picture;
        this.nombreAlumno = nombre;
        this.correoAlumno = correo;
        this.matriculaAlumno = matricula;
        this.id = id;
    }

    public Drawable getPicture(){
        return imagen;
    }

    public void setPicture(Drawable Picture){
        this.imagen = Picture;
    }

    public String getNombre(){
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombre){
        this.nombreAlumno = nombre;
    }

    public String getMatriculaAlumno(){
        return matriculaAlumno;
    }

    public void getMatriculaAlumno(String matricula){
        this.matriculaAlumno = matricula;
    }

    public String getCorreoAlumno(){
        return correoAlumno;
    }

    public void setCorreoAlumno(String correo){
        this.correoAlumno = correo;
    }

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }
}
