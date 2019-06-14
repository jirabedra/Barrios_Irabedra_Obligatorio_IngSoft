/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrios_irabedra.obligatorio.controladores;

import com.barrios_irabedra.obligatorio.dominio.*;
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
import javafx.scene.text.Text;

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
    private Text textPreguntaVF;
    @FXML
    private Button btnRespuestaVerdadera;
    @FXML
    private Button btnRespuestaFalsa;
    @FXML
    private Pane panePreguntaVF;

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
        panePreguntaVF.setVisible(false);
        panelTextoDragAndDrop.setVisible(false);
        gridPaneCaminosPreguntas.setVisible(true);
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
        int contP = cantP;
        int contador = 0;
        ArrayList<Pregunta> listaPreguntas = Sistema.getInstance().getListaTodasLasPreguntas();
        Iterator it = listaPreguntas.iterator();
        for (int i = 7; i >= 0 && (cantP >= 0) && it.hasNext(); i--) {
            this.matrizBotones[i][0].setUserData(it.next());
            activar(matrizBotones[i][0]);
            contador++;
            cantP--;
        }
        for (int i = 1; i < 8 && cantP >= 0 && it.hasNext(); i++) {
            this.matrizBotones[0][i].setUserData(it.next());
            activar(matrizBotones[0][i]);
            contador++;
            cantP--;
        }
        for (int i = 1; i < 8 && cantP >= 0 && it.hasNext(); i++) {
            this.matrizBotones[i][7].setUserData(it.next());
            activar(matrizBotones[i][7]);
            contador++;
            cantP--;
        }
        for (int i = 6; i > 1 && cantP >= 0 && it.hasNext(); i--) {
            this.matrizBotones[7][i].setUserData(it.next());
            activar(matrizBotones[7][i]);
            contador++;
            cantP--;
        }
        for (int i = 6; i > 1 && cantP >= 0 && it.hasNext(); i--) {
            this.matrizBotones[i][2].setUserData(it.next());
            activar(matrizBotones[i][2]);
            contador++;
            cantP--;
        }
        for (int i = 3; i < 6 && cantP >= 0 && it.hasNext(); i++) {
            this.matrizBotones[2][i].setUserData(it.next());
            activar(matrizBotones[2][i]);
            contador++;
            cantP--;
        }
        for (int i = 3; i < 6 && cantP >= 0 && it.hasNext(); i++) {
            this.matrizBotones[i][5].setUserData(it.next());
            activar(matrizBotones[i][5]);
            contador++;
            cantP--;
        }
        if (cantP >= 0 && it.hasNext()) {
            this.matrizBotones[5][4].setUserData(it.next());
            activar(matrizBotones[5][4]);
            contador++;
            cantP--;
        }
        if (cantP == 0) {
            desactivarElRestoDeBotones(contador);
        }
    }

    private void desactivarElRestoDeBotones(int contador) {
        for (int i = 7; i >= 0; i--) {
            if (contador == 0) {
                matrizBotones[i][0].setStyle("-fx-background-color: #696969");
                matrizBotones[i][0].setDisable(true);
            } else {
                contador--;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (contador == 0) {
                matrizBotones[0][i].setStyle("-fx-background-color: #696969");
                matrizBotones[0][i].setDisable(true);
            } else {
                contador--;
            }
        }
        for (int i = 1; i < 8; i++) {
            if (contador == 0) {
                matrizBotones[i][7].setStyle("-fx-background-color: #696969");
                matrizBotones[i][7].setDisable(true);
            } else {
                contador--;
            }
        }
        for (int i = 6; i > 1; i--) {
            if (contador == 0) {
                matrizBotones[7][i].setStyle("-fx-background-color: #696969");
                matrizBotones[7][i].setDisable(true);
            } else {
                contador--;
            }
        }
        for (int i = 6; i > 1; i--) {
            if (contador == 0) {
                matrizBotones[i][2].setStyle("-fx-background-color: #696969");
                matrizBotones[i][2].setDisable(true);
            } else {
                contador--;
            }
        }
        for (int i = 3; i < 6; i++) {
            if (contador == 0) {
                matrizBotones[2][i].setStyle("-fx-background-color: #696969");
                matrizBotones[2][i].setDisable(true);
            } else {
                contador--;
            }
        }
        for (int i = 3; i < 6; i++) {
            if (contador == 0) {
                matrizBotones[i][5].setStyle("-fx-background-color: #696969");
                matrizBotones[i][5].setDisable(true);
            } else {
                contador--;
            }
        }
        if (contador == 0) {
            matrizBotones[5][4].setStyle("-fx-background-color: #696969");
            matrizBotones[5][4].setDisable(true);
        } else {
            contador--;
        }
    }

    private void activar(final Button b) {
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Pregunta p = (Pregunta) b.getUserData();
                if (p instanceof PreguntaVF) {
                    gridPaneCaminosPreguntas.setVisible(false);
                    panePreguntaVF.setVisible(true);
                    textPreguntaVF.setText(p.getPregunta());
                    if (Sistema.getInstance().procesarRespuestaSeleccionada(p, b.getId())) {
                        b.setStyle("-fx-background-color: #01a8e2");
                    } else {
                        b.setStyle("-fx-background-color: #000f3f");
                    }
                } else if (p instanceof PreguntaCortaRespuesta) {

                } else if (p instanceof PreguntaMultipleOpcion) {

                }
            }
        });
    }
}
