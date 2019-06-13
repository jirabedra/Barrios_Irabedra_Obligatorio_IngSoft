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
import javafx.event.EventHandler;
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
    @FXML
    private Button btn70;

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
        Iterator it = listaPreguntas.iterator();
        for (int i = 7; i >= 0 && (cantP >= 0) && it.hasNext(); i--) {
            this.matrizBotones[i][0].setUserData(it.next());
            this.matrizBotones[i][0].setStyle("-fx-background-color: #000000");
            cantP--;
        }
        for (int i = 1; i < 8 && cantP >= 0 && it.hasNext(); i++) {
            this.matrizBotones[0][i].setUserData(it.next());
            this.matrizBotones[0][i].setStyle("-fx-background-color: #000000");
            cantP--;
        }
        for (int i = 1; i < 8 && cantP >= 0 && it.hasNext(); i++) {
            this.matrizBotones[i][7].setUserData(it.next());
            this.matrizBotones[i][7].setStyle("-fx-background-color: #000000");
            cantP--;
        }
        for (int i = 6; i < 1 && cantP >= 0 && it.hasNext(); i--) {
            this.matrizBotones[7][i].setUserData(it.next());
            this.matrizBotones[7][i].setStyle("-fx-background-color: #000000");
            cantP--;
        }
        for (int i = 6; i < 1 && cantP >= 0 && it.hasNext(); i--) {
            this.matrizBotones[i][2].setUserData(it.next());
            this.matrizBotones[i][2].setStyle("-fx-background-color: #000000");
            cantP--;
        }
        for (int i = 3; i < 6 && cantP >= 0 && it.hasNext(); i++) {
            this.matrizBotones[2][i].setUserData(it.next());
            this.matrizBotones[2][i].setStyle("-fx-background-color: #000000");
            cantP--;
        }
        for (int i = 3; i < 5 && cantP >= 0 && it.hasNext(); i++) {
            this.matrizBotones[i][5].setUserData(it.next());
            this.matrizBotones[i][5].setStyle("-fx-background-color: #000000");
            cantP--;
        }
        if (cantP >= 0 && it.hasNext()) {
            this.matrizBotones[4][4].setUserData(it.next());
            this.matrizBotones[4][4].setStyle("-fx-background-color: #000000");
            cantP--;
        }

        activar(matrizBotones[0][0]);
    }

    private void activar(Button b) {
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }

        });
    }
}
