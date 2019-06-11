/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrios_irabedra.obligatorio.dominio;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author usuario
 */
public class SistemaTest {

    public SistemaTest() {
        assertNotNull(new Sistema());
    }

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Invocado antes de comenzar las pruebas en Sistema");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Invocado al finalizar todas las pruebas en Sistema");
    }

    @Before
    public void setUp() {
        System.out.println("Invocado al comenzar una prueba.");
    }

    @After
    public void tearDown() {
        System.out.println("Invocado al finalizar una prueba.");
    }

    /**
     * Test of getInstanciaSistema method, of class Sistema.
     ////////////////////////////////////////////////////////////
    @Test
    public void testGetInstanciaSistema() {
        System.out.println("getInstanciaSistema");
        Sistema result = new Sistema();
        result = Sistema.getInstanciaSistema();
        assertNotNull(result);
    }

    /**
     * Test of getInstance method, of class Sistema.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        Sistema result = Sistema.getInstance();
        assertNotNull(result);
    }

    /**
     * Test of recibirArchivos method, of class Sistema.
     /////////////////////////////////////////////////
    @Test
    public void testRecibirArchivos() {
        System.out.println("recibirArchivos");
        List<File> files = null;
        Sistema instance = null;
        instance.recibirArchivos(files);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of filtrarArchivo method, of class Sistema.
     ////////////////////////////////////////////////////////
    @Test
    public void testFiltrarArchivo() {
        System.out.println("filtrarArchivo");
        File unArchivo = null;
        Sistema instance = null;
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.filtrarArchivo(unArchivo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of esLineaConContenido method, of class Sistema.
     */
    
    @Test
    public void testEsLineaConContenidoVacia() {
        System.out.println("esLineaConContenido");
        String linea = "";
        Sistema instance = new Sistema();
        Boolean expResult = false;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEsLineaConContenidoBarra() {
        System.out.println("esLineaConContenido");
        String linea = "/";
        Sistema instance = new Sistema();
        Boolean expResult = false;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEsLineaConContenidoDosPuntos() {
        System.out.println("esLineaConContenido");
        String linea = ":";
        Sistema instance = new Sistema();
        Boolean expResult = true;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEsLineaConContenidoIgual() {
        System.out.println("esLineaConContenido");
        String linea = "=";
        Sistema instance = new Sistema();
        Boolean expResult = true;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEsLineaConContenidoVirgulilla() {
        System.out.println("esLineaConContenido");
        String linea = "~";
        Sistema instance = new Sistema();
        Boolean expResult = true;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEsLineaConContenidoNumeral() {
        System.out.println("esLineaConContenido");
        String linea = "#";
        Sistema instance = new Sistema();
        Boolean expResult = true;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEsLineaConContenidosSignoDePreguntaInvertido() {
        System.out.println("esLineaConContenido");
        String linea = "¿";
        Sistema instance = new Sistema();
        Boolean expResult = true;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEsLineaConContenidoCorcheteCerrado() {
        System.out.println("esLineaConContenido");
        String linea = "}";
        Sistema instance = new Sistema();
        Boolean expResult = true;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testEsLineaConContenidoDefault() {
        System.out.println("esLineaConContenido");
        String linea = "♂♥♂ UwU";
        Sistema instance = new Sistema();
        Boolean expResult = false;
        Boolean result = instance.esLineaConContenido(linea);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of cargarPreguntas method, of class Sistema.
     */
    @Test
    public void testCargarPreguntasNulas() {
        System.out.println("cargarPreguntas");
        ArrayList<String> textoFiltrado = null;
        assertEquals(textoFiltrado, null);
    }
    /*
    @Test
    public void testCargarPreguntasConContenidoEnElArray() {
        System.out.println("cargarPreguntas");
        ArrayList<String> textoFiltrado = new ArrayList<>();
        textoFiltrado.add("Lolis");
        textoFiltrado.add("7u7");
        textoFiltrado.add("UwU");
        Sistema instance = new Sistema();
        instance.cargarPreguntas(textoFiltrado);
    }

    /**
     * Test of procesarLinea method, of class Sistema.
     /
    @Test
    public void testProcesarLinea() {
        System.out.println("procesarLinea");
        String unaLinea = "";
        ArrayList<String> lineasPreguntaMultipleOpcion = null;
        boolean comienzoPreguntaMultipleOpcion = false;
        Sistema instance = null;
        boolean expResult = false;
        boolean result = instance.procesarLinea(unaLinea, lineasPreguntaMultipleOpcion, comienzoPreguntaMultipleOpcion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ocurrenciasSubtring method, of class Sistema.
     /
    @Test
    public void testOcurrenciasSubtring() {
        System.out.println("ocurrenciasSubtring");
        String unString = "";
        String unSubstring = "";
        Sistema instance = null;
        int expResult = 0;
        int result = instance.ocurrenciasSubtring(unString, unSubstring);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListaPreguntasCortaRespuesta method, of class
     * Sistema.
     /
    @Test
    public void testGetListaPreguntasCortaRespuesta() {
        System.out.println("getListaPreguntasCortaRespuesta");
        Sistema instance = null;
        List<PreguntaCortaRespuesta> expResult = null;
        List<PreguntaCortaRespuesta> result = instance.getListaPreguntasCortaRespuesta();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListaPreguntasMultipleOpcion method, of class
     * Sistema.
     /
    @Test
    public void testGetListaPreguntasMultipleOpcion() {
        System.out.println("getListaPreguntasMultipleOpcion");
        Sistema instance = null;
        List<PreguntaMultipleOpcion> expResult = null;
        List<PreguntaMultipleOpcion> result = instance.getListaPreguntasMultipleOpcion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListaPreguntasVF method, of class Sistema.
     /
    @Test
    public void testGetListaPreguntasVF() {
        System.out.println("getListaPreguntasVF");
        Sistema instance = null;
        List<PreguntaVF> expResult = null;
        List<PreguntaVF> result = instance.getListaPreguntasVF();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of veracidadRespuesta method, of class Sistema.
     /
    @Test
    public void testVeracidadRespuesta() {
        System.out.println("veracidadRespuesta");
        Pregunta p = null;
        String respuesta = "";
        boolean valorDeVerdad = false;
        Sistema instance = null;
        boolean expResult = false;
        boolean result = instance.veracidadRespuesta(p, respuesta, valorDeVerdad);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    * */

}
