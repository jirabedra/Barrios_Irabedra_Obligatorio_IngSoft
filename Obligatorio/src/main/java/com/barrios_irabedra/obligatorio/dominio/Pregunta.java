package com.barrios_irabedra.obligatorio.dominio;

// @author Juan Barrios, Juan Irabedra

import java.util.HashMap;

public abstract class Pregunta {

    private String pregunta;
    private int tiempo;
    private HashMap<String, Boolean> respuestas;

    //Acceso y modificacion
    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public void agregarRespuesta(String respuesta, Boolean valorVerdad){
        this.respuestas.put(respuesta, valorVerdad);
    }
    
    public String getRespuestas(){
        return this.respuestas.toString();
    }
    
    //Constructor con params
    public Pregunta(String nombre, int tiempo, HashMap<String, Boolean> respuestas) {
        this.pregunta = nombre;
        this.tiempo = tiempo;
        this.respuestas = new HashMap<>();
    }

    //Constructor sin params.
    public Pregunta() {
        this.respuestas = new HashMap<>();  
    }

    @Override
    public String toString() {
        return pregunta + ". \n" + "Tienes " + tiempo + " segundos para responder. \n"
                + "Y sus respuestas eran " + respuestas;
    }
}
