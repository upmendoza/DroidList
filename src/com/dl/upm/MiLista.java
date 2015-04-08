package com.dl.upm;

import android.app.Application;

/**
 * Created by IntelliJ IDEA.
 * User: Ulises
 * Date: 20/04/12
 * Time: 10:26
 * To change this template use File | Settings | File Templates.
 */
public class MiLista extends Application {
    private String vEstado = "";

    public String getvEstado() {
        return vEstado;
    }

    public void setvEstado(String Valor) {
        this.vEstado = Valor;
    }
}
