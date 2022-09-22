package com.politecnicomalaga.Controlador;


import com.politecnicomalaga.Persistencia.ControlProveedores;
import com.politecnicomalaga.Persistencia.ControlTrabajos;
import com.politecnicomalaga.Vista.MainFrame;

import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Controlador {
    //ESTADO //////////////////////////////////////////////////////////////////////////////////

    // Lista de los tipos de vehiculos para el taller
    protected String[] tiposVehiculo = {"Coche", "Moto", "Furgón", "Camión"};

    private static final String ficheroPorCobrar = "porCobrar.csv"; //Tiene el List trabajos por Cobrar
    private static final String ficheroCobrado = "cobrado.csv";     //Tiene el List trabajos cobrados
    private static final String ficheroProveedores = "proveedores.csv";


    //Variable String para erroes
    protected String mensajeError;

    //El truco
    public static Controlador singleton;

    //Instanciamos las clases ControlTrabajos
    ControlTrabajos miControlTrabajosPorCobrar;
    ControlTrabajos miControlTrabajosCobrados;
    ControlProveedores miControlProveedores;

    //CONTRUCTOR //////////////////////////////////////////////////////////////////////////////////

    public Controlador(){
        //this.trabajosACobrar = new ArrayList<TrabajoTaller>();
        //this.trabajosRealizados = new ArrayList<TrabajoTaller>();
        miControlTrabajosPorCobrar = new ControlTrabajos();
        miControlTrabajosCobrados = new ControlTrabajos();
        miControlProveedores = new ControlProveedores();
        cargarTrabajosPorCobrar();
        cargarTrabajosCobrados();
        cargarProveedores();
        mensajeError = "";
    }

    //METODOS //////////////////////////////////////////////////////////////////////////////////

    //El trucamen
    public static Controlador getSingleton(){
        if(singleton == null){
            singleton = new Controlador();
        }
        return singleton;
    }

    //Mete los trabajos PorCobrar en el List al abrir el programa
    private void cargarTrabajosPorCobrar() {
        String sCSV = "";
        FileReader fr;
        Scanner sc;
        byte lines = 0;

        try {
            fr = new FileReader(ficheroPorCobrar);
            sc = new Scanner(fr);

            while (sc.hasNextLine()) {
                sCSV = sc.nextLine();
                miControlTrabajosPorCobrar.addTrabajo(sCSV);
                lines++;
            }

            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Mete los trabajos Cobrados en el List al abrir el programa
    private void cargarTrabajosCobrados() {
        String sCSV = "";
        FileReader fr;
        Scanner sc;
        byte lines = 0;

        try {
            fr = new FileReader(ficheroCobrado);
            sc = new Scanner(fr);

            while (sc.hasNextLine()) {
                sCSV = sc.nextLine();
                miControlTrabajosCobrados.addTrabajo(sCSV);
                lines++;
            }

            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Añade los proveedores del fichero a la Lista de Proveedores cuando se abre el programa
    private void cargarProveedores() {
        String sCSV = "";
        FileReader fr;
        Scanner sc;
        byte lines = 0;

        try {
            fr = new FileReader(ficheroProveedores);
            sc = new Scanner(fr);

            while (sc.hasNextLine()) {
                sCSV = sc.nextLine();
                miControlProveedores.add(sCSV);
                lines++;
            }

            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void grabarTrabajoTaller(String sCSV, String sFichero){
        //abrimos una escritura al fichero
        FileWriter fw = null;
        try {
            fw = new FileWriter(sFichero,true);
            //adicionamos al final la línea tal cual y un "\n"
            fw.write(sCSV + "\n");
            fw.flush();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (fw != null){
                try{
                    fw.close();
                } catch (IOException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    //Vacia el fichero y escribe los elementos del List actuales (Parametro String = (String) elementos de la LIsta)
    public void actualizarTrabajoTaller(String sCSV, String sFichero){
        //abrimos una escritura al fichero
        FileWriter fw = null;
        try {
            fw = new FileWriter(sFichero,false);
            //adicionamos al final la línea tal cual y un "\n"
            fw.write(sCSV);
            fw.flush();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if (fw != null){
                try{
                    fw.close();
                } catch (IOException e){
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    //Añade un Trabajo por cobrar a la List y al fichero
    public void altaTrabajo(String sCSV){
        miControlTrabajosPorCobrar.addTrabajo(sCSV);

        grabarTrabajoTaller(sCSV,ficheroPorCobrar);
    }



    public void cobrarTrabajo(String sCSV, String horasReales){
        miControlTrabajosCobrados.addTrabajo(miControlTrabajosPorCobrar.cobrar(sCSV,horasReales)); //Añade el trabajo a Cobrados y lo elimina de Cobrar
        grabarTrabajoTaller(sCSV, ficheroCobrado); //Añade el CSV del nuevo Trabajo a Cobrado
        actualizarTrabajoTaller(miControlTrabajosPorCobrar.getListaTrabajosVistaString(), ficheroPorCobrar); //Actualiza el fichero (quita el trabajo borrado)
    }

    public void altaProveedor(String sCSV){
        miControlProveedores.add(sCSV);
        grabarTrabajoTaller(sCSV, ficheroProveedores);
    }

    //METODOS PARA CARGAR JLIST ///////////////////////////////////////////////////////////////////////////////////////

    //Carga los elementos de las JList con los elementos del List de miControlTrabajosPorCobrar
    public void cargarListaCobrar(JList listaVer, JLabel label){
        int i;
        //Rellena un Array con los datos de la lista TrabajosPorCobrar (Se llama despues de borrar un trabajo)
        String[] lista = getTrabajosACobrar();
        DefaultListModel<String> myModel = new DefaultListModel<>();    //Crea un modelo

        //Recorre el array y añade cada elemento al model
        for (i =0;i<lista.length;i++) {
            myModel.addElement(lista[i]);
        }

        //Actualiza la listaCobros con el nuevo Modelo
        listaVer.setModel(myModel);

        label.setText("Trabajos por cobrar: " + i);
    }

    //Carga los elementos de las JList con los elementos del List de miControlTrabajosCobrados
    public void cargarListaCobrados(JList listaVer, JLabel label){
        int i;
        //Rellena un Array con los datos de la lista TrabajosPorCobrar (Se llama despues de borrar un trabajo)
        String[] lista = getTrabajosRealizados();
        DefaultListModel<String> myModel = new DefaultListModel<>();    //Crea un modelo

        //Recorre el array y añade cada elemento al model
        for (i =0;i<lista.length;i++) {
            myModel.addElement(lista[i]);
        }

        //Actualiza la listaCobros con el nuevo Modelo
        listaVer.setModel(myModel);

        label.setText("Trabajos cobrados: " + i);
    }

    //Carga los elementos de las JList con los elementos del List de miControlProveedores
    public void cargarListaProveedores(JList listaVer, JLabel label){
        int i;
        //Rellena un Array con los datos de la lista TrabajosPorCobrar (Se llama despues de borrar un trabajo)
        String[] lista = getProveedores();
        DefaultListModel<String> myModel = new DefaultListModel<>();    //Crea un modelo

        //Recorre el array y añade cada elemento al model
        for (i =0;i<lista.length;i++) {
            myModel.addElement(lista[i]);
        }

        //Actualiza la listaCobros con el nuevo Modelo
        listaVer.setModel(myModel);

        label.setText("Total proveedores disponibles: " + i);
    }


    //Getter de la lista
    public String[] getTrabajosACobrar() {
        return miControlTrabajosPorCobrar.getListaTrabajosVista();
    }

    // llamamos al arrayList para coger el contador
    public String[] getTrabajosRealizados() {
        return miControlTrabajosCobrados.getListaTrabajosVista();
    }

    public String[] getProveedores(){
        return miControlProveedores.getListaProveedoresVista();
    }

    public String[] getTiposVehiculo(){
        return tiposVehiculo;
    }

}
