/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrios_irabedra.obligatorio.controladores;

import com.barrios_irabedra.obligatorio.dominio.Pregunta;
import com.barrios_irabedra.obligatorio.dominio.Sistema;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Usuario
 */
public class VentanaPrincipalController implements Initializable {

    Button[][] matrizBotones = new Button[8][8];
    Button b = new Button();

    @FXML
    private Pane paneDragAndDrop;
    @FXML
    private Button btnComenzar;
    @FXML
    private Pane panelJuego;
    @FXML
    private Pane panelTextoDragAndDrop;
    @FXML
    private GridPane gridPaneCaminosPreguntas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        panelJuego.setVisible(false);
        paneDragAndDrop.setVisible(true);
        btnComenzar.setVisible(true);
    }

    @FXML
    private void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    private void handleDrop(DragEvent event) {
        List<File> files = event.getDragboard().getFiles();
        Sistema.getInstance().recibirArchivos(files);

        System.out.println(Sistema.getInstance().getListaPreguntasCortaRespuesta());
        System.out.println(Sistema.getInstance().getListaPreguntasVF());
        System.out.println(Sistema.getInstance().getListaPreguntasMultipleOpcion());
    }

    @FXML
    private void actionBtnComenzar(ActionEvent event) {
        crearMatrizBotones();
        asignoPreguntaBoton();
        paneDragAndDrop.setVisible(false);
        btnComenzar.setVisible(false);
        panelTextoDragAndDrop.setVisible(false);
        panelJuego.setVisible(true);

    }

    private void crearMatrizBotones() {
        Integer fil;
        Integer col;
        for (Node child : this.gridPaneCaminosPreguntas.getChildren()) {
            fil = this.gridPaneCaminosPreguntas.getRowIndex(child);
            col = this.gridPaneCaminosPreguntas.getColumnIndex(child);
            int f = fil == null ? 0 : fil;
            int c = col == null ? 0 : col;
            if (fil != null && col != null) {
                if (f == fil && c == col) {
                    this.matrizBotones[fil - 1][col - 1] = (Button) child;
                }
            }
        }
    }

    private void asignoPreguntaBoton() {
        int cantP = Sistema.getInstance().cantidadTotalPreguntas();
        ArrayList<Pregunta> listaPreguntas = Sistema.getInstance().getListaTodasLasPreguntas();
        for (int i = 0; cantP >= 0; i++) {
            
        }

    }
}
