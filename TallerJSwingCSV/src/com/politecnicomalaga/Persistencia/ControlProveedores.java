package com.politecnicomalaga.Persistencia;

import com.politecnicomalaga.Modelo.Proveedor;
import com.politecnicomalaga.Modelo.TrabajoTaller;

import java.util.LinkedList;
import java.util.List;

public class ControlProveedores {
    //ESTADO

    private List<Proveedor> listaProveedores;

    //CONSTRUCTOR
    public ControlProveedores(){
        listaProveedores = new LinkedList<Proveedor>();
    }

    //METODOS

    //Añadir Trabajo a la lista. Recibe CSV para objeto Proveedor y lo añade a su Lista.
    public void add(String sCSV){
        Proveedor proveedor;

        proveedor = new Proveedor(sCSV);
        listaProveedores.add(proveedor);
    }

    public String[] getListaProveedoresVista(){
        String[] resultado = new String[listaProveedores.size()];

        for (int i = 0; i < listaProveedores.size(); i++) {
            resultado[i] = listaProveedores.get(i).toString();
        }
        return resultado;
    }
}
