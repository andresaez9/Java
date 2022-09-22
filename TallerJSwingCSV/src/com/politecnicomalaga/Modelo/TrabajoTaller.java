package com.politecnicomalaga.Modelo;

import java.util.Calendar;

public class TrabajoTaller {
    //ESTADO
    protected Vehiculo miVehiculo;
    protected String fechaEntrada;
    protected String diagnostico;
    protected String resolucion; //lo que el mecánico ha dicho que hay que hacer
    protected float hTrabPrevistas; //horas trabajadas previstas
    protected float hTrabRealizadas; //horas trabajadas realizadas

    //COMPORTAMIENTO
    //Constructor
    public TrabajoTaller(Vehiculo miVehiculo, String diagnostico, String resolucion, float hTrabPrevistas){
        this.diagnostico = diagnostico;
        this.resolucion = resolucion;
        this.hTrabPrevistas = hTrabPrevistas;
        hTrabRealizadas = 0; //ponemos 0 porque cuando se crea no hay ninguna hora realizada
        this.miVehiculo = miVehiculo;
        fechaEntrada = Calendar.getInstance().toString();
        //Se fija la hora de entrada del vehículo a la hora/día de creación del objeto
    }

    //Sobrecarga de constructor
    public TrabajoTaller(Vehiculo miVehiculo, String diagnostico, String resolucion, float hTrabPrevistas, String fechaEntrada){
        this.diagnostico = diagnostico;
        this.resolucion = resolucion;
        this.hTrabPrevistas = hTrabPrevistas;
        hTrabRealizadas = 0; //ponemos 0 porque cuando se crea no hay ninguna hora realizada
        this.miVehiculo = miVehiculo;
        this.fechaEntrada = fechaEntrada;
    }

    public TrabajoTaller(String sCSV){
        String[] listaParametros = sCSV.split(";");

        switch(listaParametros[0]){
            case "Coche" -> this.miVehiculo = new Coche(listaParametros[1],listaParametros[2],listaParametros[3],listaParametros[4]);
            case "Moto" -> this.miVehiculo = new Moto(listaParametros[1],listaParametros[2],listaParametros[3],listaParametros[4]);
            case "Furgón" -> this.miVehiculo = new Furgon(listaParametros[1],listaParametros[2],listaParametros[3],listaParametros[4]);
            case "Camión" -> this.miVehiculo = new Camion(listaParametros[1],listaParametros[2],listaParametros[3],listaParametros[4]);
        }

        this.fechaEntrada = listaParametros[5];
        this.diagnostico = listaParametros[6];
        this.resolucion = listaParametros[7];
        this.hTrabPrevistas = Float.valueOf(listaParametros[8]);
        this.hTrabRealizadas = Float.valueOf(listaParametros[9]);
    }

    public Vehiculo getMiVehiculo() {
        return miVehiculo;
    }

    public void setMiVehiculo(Vehiculo miVehiculo) {
        this.miVehiculo = miVehiculo;
    }

    public String getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(String fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public float gethTrabPrevistas() {
        return hTrabPrevistas;
    }

    public void sethTrabPrevistas(float hTrabPrevistas) {
        this.hTrabPrevistas = hTrabPrevistas;
    }

    public float gethTrabRealizadas() {
        return hTrabRealizadas;
    }

    public void sethTrabRealizadas(float hTrabRealizadas) {
        this.hTrabRealizadas = hTrabRealizadas;
    }

    @Override
    public String toString() {

        return miVehiculo.toString() + ';' +
                fechaEntrada +  ';' +
                diagnostico + ';' +
                resolucion + ';' +
                String.valueOf(hTrabPrevistas) + ';' +
                String.valueOf(hTrabRealizadas);
    }

    //Comparamos el TrabajoTaller

    public int compareTo(TrabajoTaller trabajoTaller) {
        return (trabajoTaller.getFechaEntrada() + trabajoTaller.getMiVehiculo().getMatricula()).compareTo(this.fechaEntrada+this.miVehiculo.getMatricula());
    }
}
