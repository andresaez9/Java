package com.politecnicomalaga.Modelo;

public class Vehiculo {
    //ESTADO
    protected String matricula;
    protected String modelo;
    protected String propietario;
    protected String dniPropietario;
    protected String tipo;

    //COMPORTAMIENTO
    //Constructor
    public Vehiculo(String nMatricula, String nModelo, String nPropietario, String nDniPropietario){
        this.matricula = nMatricula;
        this.modelo = nModelo;
        this.propietario = nPropietario;
        this.dniPropietario = nDniPropietario;
    }

    public Vehiculo(String sCSV){
        String[] listaParametros = sCSV.split(";");
        this.matricula = listaParametros[0];
        this.modelo = listaParametros[1];
        this.propietario = listaParametros[2];
        this.dniPropietario = listaParametros[3];
    }

    //otros métodos
    //getter y setter
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getDniPropietario() {
        return dniPropietario;
    }

    public void setDniPropietario(String dniPropietario) {
        this.dniPropietario = dniPropietario;
    }

    @Override
    public String toString() {
        return  tipo + ';' + matricula + ';' + modelo + ';' + propietario + ';' + dniPropietario;
    }

    //método compare to para usar el ControladorTaller
    public int compareTo(Vehiculo vehiculo){
        return matricula.compareTo(vehiculo.getMatricula());
    }
}
