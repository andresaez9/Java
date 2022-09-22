package com.politecnicomalaga.Persistencia;

import com.politecnicomalaga.Controlador.Controlador;
import com.politecnicomalaga.Modelo.TrabajoTaller;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class ControlTrabajos {

    //ESTADO
    List<TrabajoTaller> listaTrabajos;

    //COMPORTAMIENTO
    //Constructor
    public ControlTrabajos(){
        listaTrabajos = new LinkedList<TrabajoTaller>();
    }

    //Otros métodos

    //Añade un objeto Trabajo a su lista, lo contruye pasandole un CSV
    public void addTrabajo(String sCSV){
        TrabajoTaller tt;
        tt = new TrabajoTaller(sCSV);
        listaTrabajos.add(tt);
    }

    //Añade el trabajo que se le pase como parametro a su lista
    public void addTrabajo(TrabajoTaller tt){
        listaTrabajos.add(tt);
    }


    //Elimina el trabajo de la List  comparando el CSV. Devuelve el trabajo
    public TrabajoTaller cobrar(String sCSV, String sHorasReales){
        TrabajoTaller tt;

        tt = new TrabajoTaller(sCSV);

        //Buscamos el tt en la lista
        for (int i = 0; i < listaTrabajos.size(); i++) {
            if (tt.compareTo(listaTrabajos.get(i)) == 0){
                tt.sethTrabRealizadas(Float.valueOf(sHorasReales));
                listaTrabajos.remove(i);
                return tt;
            }
        }
        return tt;
    }

    public String[] getListaTrabajosVista(){
        String[] resultado = new String[listaTrabajos.size()];

        for (int i = 0; i < listaTrabajos.size(); i++) {
            resultado[i] = listaTrabajos.get(i).toString();
        }
        return resultado;
    }

    public String getListaTrabajosVistaString(){
        String resultado = "";

        for (int i = 0; i < listaTrabajos.size(); i++) {
            resultado += listaTrabajos.get(i).toString() + "\n";
        }
        return resultado;
    }
}
