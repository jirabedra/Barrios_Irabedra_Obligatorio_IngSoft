package com.barrios_irabedra.obligatorio.dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Usuario
 */
public class Sistema {

    boolean comienzoPreguntaMultipleOpcion = false;

    public static Sistema getInstanciaSistema() {
        return instanciaSistema;
    }

    private List<PreguntaCortaRespuesta> listaPreguntasCortaRespuesta;
    private List<PreguntaMultipleOpcion> listaPreguntasMultipleOpcion;
    private List<PreguntaVF> listaPreguntasVF;
    private static Sistema instanciaSistema;

    private Sistema() {
        this.listaPreguntasCortaRespuesta = new ArrayList<>();
        this.listaPreguntasMultipleOpcion = new ArrayList<>();
        this.listaPreguntasVF = new ArrayList<>();
    }

    public static Sistema getInstance() {
        if (instanciaSistema == null) {
            instanciaSistema = new Sistema();
        }
        return instanciaSistema;
    }

    public void recibirArchivos(List<File> files) {
        filtroTXT(files);
        cargoArchivos(files);
    }

    /**
     * Este metodo elimina de la lista de archivos recibidos los
     * que no son txt
     *
     * @param lista es la lista de archivos cargados por
     * dragAndDrop
     */
    private void filtroTXT(List<File> lista) {
        Iterator<File> it = lista.iterator();
        while (it.hasNext()) {
            File archivo = it.next();
            if (!checkTXT(archivo)) {
                lista.remove(archivo);
            }
        }
    }

    /**
     *
     * @param unArchivo es el archivo a evaluar
     * @return true si y solo si es un .txt
     */
    private Boolean checkTXT(File unArchivo) {
        String nombre = unArchivo.getName().toUpperCase();
        return nombre.endsWith(".TXT");
    }

    /**
     * Metodo que prepara los archivos para ser procesadas sus
     * preguntas
     *
     * @param lista es la lista de archivos txt que sera cargada
     * al sistema
     */
    private void cargoArchivos(List<File> lista) {
        Iterator<File> it = lista.iterator();
        while (it.hasNext()) {
            cargarPreguntas(filtrarArchivo(it.next()));
        }

    }

    public ArrayList<String> filtrarArchivo(File unArchivo) {
        ArrayList<String> ret = new ArrayList<>();
        try {
            //BufferedReader lector = new BufferedReader(new FileReader(unArchivo.getName()));
            Scanner sc = new Scanner(unArchivo);
            String linea = "";
            while (sc.hasNext()) {
                linea = sc.nextLine();
                if (this.esLineaConContenido(linea)) {
                    ret.add(linea);
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.print("No se ha encontrado archivo con nombre: " + unArchivo.getName());
            ex.printStackTrace();
        }
        return ret;
    }

    /**
     * true sii no es vacia, comentada o inesperada
     *
     * @param linea es la linea leida
     */
    public Boolean esLineaConContenido(String linea) {
        if (linea.trim().length() == 0) {
            return false;
        } else {
            linea = linea.trim();
            char primerCaracter = linea.charAt(0);
            switch (primerCaracter) {
                case '/':
                    return false;
                case ':':
                    return true;
                case '=':
                    return true;
                case '~':
                    return true;
                case '#':
                    return true;
                case '¿':
                    return true;
                case '}':
                    return true;
                default:
                    return false;
            }
        }
    }

    /**
     * abre el archivo indicado por el d&d y lo delega otro
     * metodo para procesar
     *
     * @param unArchivo es el archivo con las preguntas
     */
    public void cargarPreguntas(ArrayList<String> textoFiltrado) {
        Iterator<String> it = textoFiltrado.iterator();
        //  boolean comienzoPreguntaMultipleOpcion = false;
        ArrayList<String> lineasPreguntaMultipleOpcion = new ArrayList<>();
        while (it.hasNext()) {
            procesarLinea(it.next(), lineasPreguntaMultipleOpcion);
        }

    }

    public boolean procesarLinea(String unaLinea, ArrayList<String> lineasPreguntaMultipleOpcion) {
        if (!comienzoPreguntaMultipleOpcion) {
            switch (ocurrenciasSubtring(unaLinea, "::")) {
                case 0:
                    procesarLineaCortaRespuesta(unaLinea);
                    break;
                case 1:
                    comienzoPreguntaMultipleOpcion = true;
                    break;
                case 2:
                    procesarLineaVF(unaLinea);
                    break;
                default:
                    System.err.println("Algo salio muy mal");
                    break;
            }
        }

        if (unaLinea.equals("}")) {
            comienzoPreguntaMultipleOpcion = false;
            procesarLineaMultipleOpcion(lineasPreguntaMultipleOpcion);
            lineasPreguntaMultipleOpcion.clear();
        }

        if (comienzoPreguntaMultipleOpcion) {
            lineasPreguntaMultipleOpcion.add(unaLinea);
        }

        return comienzoPreguntaMultipleOpcion;
    }

    public int ocurrenciasSubtring(String unString, String unSubstring) {
        int contador = 0;
        String copia = unString;
        while (copia.contains(unSubstring)) {
            copia = copia.replaceFirst(unSubstring, "");
            contador++;
        }
        return contador;
    }

    private void procesarLineaCortaRespuesta(String unaLinea) {
        Pregunta p = new PreguntaCortaRespuesta();
        StringTokenizer tk = new StringTokenizer(unaLinea, "{");
        p.setPregunta(tk.nextToken());
        String segundoToken = tk.nextToken();
        segundoToken.replaceAll("}", "");
        guardoRespuestasCortas(p, segundoToken);
        this.listaPreguntasCortaRespuesta.add((PreguntaCortaRespuesta) p);
    }

    private void guardoRespuestasCortas(Pregunta unaPregunta, String respuestasTotales) {
        StringTokenizer tk = new StringTokenizer(respuestasTotales, "=");
        while (tk.hasMoreTokens()) {
            String respuesta = tk.nextToken().trim();
            unaPregunta.agregarRespuesta(respuesta, true);
        }

    }

    private void guardoRespuestasVF(Pregunta unaPregunta, String respuesta) {
        if (respuesta.contains("F")) {
            unaPregunta.agregarRespuesta(respuesta, true);
        } else if (respuesta.contains("T") || respuesta.contains("V")) {
            unaPregunta.agregarRespuesta(respuesta, false);
        } else {
            System.err.println("No hay ni Verdadero ni Falso");
        }
    }

    private void procesarLineaVF(String unaLinea) {
        Pregunta p = new PreguntaVF();
        StringTokenizer tk = new StringTokenizer(unaLinea, "::");
        tk.nextElement(); //no me importa el titulo (primer tok)
        String segundoToken = tk.nextToken();
        tk = new StringTokenizer(segundoToken, "{");
        p.setPregunta(tk.nextToken());
        segundoToken = tk.nextToken();
        segundoToken = segundoToken.toUpperCase().replace("}", "");
        guardoRespuestasVF(p, segundoToken);
        this.listaPreguntasVF.add((PreguntaVF) p);
    }

    private void procesarLineaMultipleOpcion(ArrayList<String> lineasPreguntaMultipleOpcion) {
        PreguntaMultipleOpcion p = new PreguntaMultipleOpcion();
        Iterator<String> it = lineasPreguntaMultipleOpcion.iterator();
        it.next();//El primero es el titulo de la pregunta no se usa).
        String pregunta = it.next();//La segunda linea es la pregunta.
        limpiarToken(pregunta);
        p.setPregunta(pregunta);
        String respuesta = "";
        while (it.hasNext()) {
            respuesta = it.next();
            switch (respuesta.charAt(0)) {
                case '=':
                    respuesta = respuesta.replaceFirst("=", "");
                    p.agregarRespuesta(respuesta, true);
                    break;
                case '~':
                    respuesta = respuesta.replaceFirst("~", "");
                    p.agregarRespuesta(respuesta, false);
                    break;
                case '#':
                    respuesta = respuesta.replaceFirst("#", "");
                    p.agregarComentarioRespuesta(respuesta, "");
                    break;
            }
        }
        this.listaPreguntasMultipleOpcion.add(p);
    }

    private void limpiarToken(String respuestas) {
        respuestas = respuestas.replaceFirst("::", "");
        //respuestas = respuestas.replaceFirst("{", "");
        respuestas = respuestas.trim();
    }

    public List<PreguntaCortaRespuesta> getListaPreguntasCortaRespuesta() {
        return listaPreguntasCortaRespuesta;
    }

    public List<PreguntaMultipleOpcion> getListaPreguntasMultipleOpcion() {
        return listaPreguntasMultipleOpcion;
    }

    public List<PreguntaVF> getListaPreguntasVF() {
        return listaPreguntasVF;
    }

}
