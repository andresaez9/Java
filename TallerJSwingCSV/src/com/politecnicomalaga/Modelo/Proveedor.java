package com.politecnicomalaga.Modelo;

public class Proveedor {
    //Estado

    private String nombreEmpresa;
    private String FechAlta;
    private String DirPostal;
    private String Telefono;
    private String email;
    private String contacto;

    //CONSTRUCTOR
    public Proveedor(String nEmpresa, String FAlta, String nPostal, String nTelefono, String nEmail, String nContacto){
        this.nombreEmpresa = nEmpresa;
        this.FechAlta = FAlta;
        this.DirPostal = nPostal;
        this.Telefono = nTelefono;
        this.email = nEmail;
        this.contacto = nContacto;
    }

    public Proveedor(String sCSV){
        String[] listaParametros = sCSV.split(";");
            this.nombreEmpresa = listaParametros[0];
            this.FechAlta = listaParametros[1];
            this.DirPostal = listaParametros[2];
            this.Telefono = listaParametros[3];
            this.email = listaParametros[4];
            this.contacto = listaParametros[5];

        }
    @Override
    public String toString() {
        return  this.nombreEmpresa + ';' + this.FechAlta + ';' + this.DirPostal + ';' + this.Telefono +";"+ this.email+";"+ this.contacto;
    }
}
