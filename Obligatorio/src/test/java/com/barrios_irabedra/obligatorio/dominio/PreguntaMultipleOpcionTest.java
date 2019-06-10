/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrios_irabedra.obligatorio.dominio;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class PreguntaMultipleOpcionTest {
    
    public PreguntaMultipleOpcionTest() {
        assertNotNull(new PreguntaMultipleOpcion());
    }
    
     @BeforeClass
    public static void setUpClass() {
        System.out.println("Invocado antes de comenzar las pruebas en Pregunta"
                + "MultipleOpcion.");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Invocado al finalizar todas las pruebas en Pregunta"
                + "MultipleOpcion.");
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
     * Test of agregarComentarioRespuesta method, of class PreguntaMultipleOpcion.
     */
    @Test
    public void testAgregarComentarioRespuesta() {
        System.out.println("agregarComentarioRespuesta");
        String unaRespuesta = "Blanco";
        String unComentario = "De hecho, el caballo blanco de San Martín era blanco.";
        PreguntaMultipleOpcion instance = new PreguntaMultipleOpcion();
        instance.agregarComentarioRespuesta(unaRespuesta, unComentario);
        assertTrue(instance.getComentario(unaRespuesta).equals(unComentario));
    }

    /**
     * Test of getComentario method, of class PreguntaMultipleOpcion.
     */
    /*@Test
    public void testGetComentario() {
        System.out.println("getComentario");
        String unaRespuesta = "";
        PreguntaMultipleOpcion instance = new PreguntaMultipleOpcion();
        String expResult = "";
        String result = instance.getComentario(unaRespuesta);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of toString method, of class PreguntaMultipleOpcion.
     */
    /*@Test
    public void testToString() {
        System.out.println("toString");
        PreguntaMultipleOpcion instance = new PreguntaMultipleOpcion();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    
}
