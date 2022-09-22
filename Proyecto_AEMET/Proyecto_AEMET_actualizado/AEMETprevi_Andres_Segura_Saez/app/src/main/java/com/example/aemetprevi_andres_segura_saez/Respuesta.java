package com.example.aemetprevi_andres_segura_saez;

import com.google.gson.Gson;

public class Respuesta {
    //ESTADO
    protected String datos;
    protected int turno;

    //COMPORTAMIENTO
    public Respuesta(String entrada, int turno) {

        datos = entrada;
        this.turno = turno;
    }

    public String getInforme() {
        return datos;
    }

    public String getUrl() {
        ResultadoMunicipio intermedio;
        String url = "";

        //Este es mi trabajador. Traduce texto JSON a clase POJO
        Gson miGsonRespuesta = new Gson();
        if (turno == 1) {
            //Ya tengo la primera respuesta, ahora necesito coger la URL real
            //primero cogemos el JSON y lo convertimos en objeto JAVA String -> ResultadoMunicipio
            intermedio = miGsonRespuesta.fromJson(datos, ResultadoMunicipio.class);
            url = intermedio.getDatos();
        } else
            url = datos.substring(datos.indexOf("https://www.aemet.es/es/eltiempo"),datos.indexOf("language")-8);


        return url;
    }
}
