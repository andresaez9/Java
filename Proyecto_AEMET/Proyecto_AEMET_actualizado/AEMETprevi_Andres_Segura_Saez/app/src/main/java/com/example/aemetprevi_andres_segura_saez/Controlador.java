package com.example.aemetprevi_andres_segura_saez;

import android.webkit.WebView;
import android.widget.TextView;

public class Controlador {
    //ESTADO

    //Enlace a MainActivity
    protected MainActivity miActivity;

    //Instancia singleton
    private static Controlador miControlador;

    //COMPORTAMIENTO

    //Constructor. Privado
    private Controlador() {

    }

    //Método de acceso
    public static Controlador getInstance() {
        if (miControlador == null)
            miControlador = new Controlador();
        return miControlador;
    }

    public void getPrevision(MainActivity fromActivity, String localidad) {
        Peticion miPeticion = new Peticion();
        String url = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/" + localidad + "?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbmRyZXMuYWxjYXJhekBwb2xpdGVjbmljb21hbGFnYS5jb20iLCJqdGkiOiJjOTc3MjI0OC0xYzNiLTQ4ODAtYjYxYy04YWE5NjNmNGE0YmYiLCJpc3MiOiJBRU1FVCIsImlhdCI6MTYxNzcwNzMyOCwidXNlcklkIjoiYzk3NzIyNDgtMWMzYi00ODgwLWI2MWMtOGFhOTYzZjRhNGJmIiwicm9sZSI6IiJ9.GojMAUMYA_BPK17JnzoxQsVqPgy2y7qQkH9XUGh6qnE";
        //Enlazamos con la Activity que hace la llamada, para devolverle los datos cuando
        //los tengamos

        this.miActivity = fromActivity;

        miPeticion.getPrevision(url);
    }

    public void setRespuestaAPeticion(String respuesta, int turno) {
        Respuesta miRespuesta = new Respuesta(respuesta, turno);

        if (turno == 1) {
            //Hay que volver a pedir
            Peticion miPeticion = new Peticion();
            String url = miRespuesta.getUrl();
            //Enlazamos con la Activity que hace la llamada, para devolverle los datos cuando
            //los tengamos

            miPeticion.getPrevision(url);
        } else if (turno == 0){

            ((TextView) miActivity.findViewById(R.id.tvResultado)).setText(miRespuesta.getInforme());

            WebView myWebView = (WebView) miActivity.findViewById(R.id.webview);
            myWebView.setVerticalScrollBarEnabled(true);
            myWebView.loadUrl(miRespuesta.getUrl());
        } else {
            ((TextView) miActivity.findViewById(R.id.tvResultado)).setText(miRespuesta.getInforme());
        }

    }
}
