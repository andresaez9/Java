package com.example.aemetprevi_andres_segura_saez;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Peticion {
    //ESTADO

    //protected String localidad;
    static protected int turno = 0;

    //COMPORTAMIENTO
    public Peticion() {

    }

    public void getPrevision(String URL) {
        OkHttpClient cliente = new OkHttpClient();
        final int nuevoTurno = (Peticion.turno+1)%2;
        Peticion.turno = nuevoTurno;

        //construimos la peticion
        Request peticion = new Request.Builder()
                .url(URL)
                .get()
                .addHeader("cache-control", "no-cache")
                .build();


        //realizamos la llamada al server, pero en otro thread (con enqueue)
        Call llamada = cliente.newCall(peticion);
        llamada.enqueue(new Callback() {
            public void onResponse(Call call, Response respuestaServer)
                    throws IOException {
                //TENEMOS RESPUESTAS!!!!!
                String respuesta = respuestaServer.body().string();
                // Create a handler that associated with Looper of the main thread
                Handler manejador = new Handler(Looper.getMainLooper());

// Send a task to the MessageQueue of the main thread
                manejador.post(new Runnable() {
                    @Override
                    public void run() {
                        // Code will be executed on the main thread
                        Controlador miControlador = Controlador.getInstance();
                        miControlador.setRespuestaAPeticion(respuesta,nuevoTurno);
                    }
                });


            }

            public void onFailure(Call call, IOException e) {
                String respuesta = e.getMessage();
                Handler manejador = new Handler(Looper.getMainLooper());

// Send a task to the MessageQueue of the main thread
                manejador.post(new Runnable() {
                    @Override
                    public void run() {
                        // Code will be executed on the main thread
                        Controlador miControlador = Controlador.getInstance();
                        miControlador.setRespuestaAPeticion(respuesta,-1);
                    }
                });
            }
        });
    }
}
