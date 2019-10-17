package aplicacion;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Aplicacion extends Application{
    private TextField txtNombre, txtMatricula, txtCalificacion;
    private ObservableList<Estudiante> lista;
    private ListView<Estudiante> lvEstudiante;

    public void start(Stage stage) throws Exception {
        BorderPane panePrincipal = new BorderPane();
        FlowPane paneContenido = new FlowPane();
        panePrincipal.setCenter(paneContenido);

        /*******************
         * Campos de texto *
         * ****************/

        GridPane paneContenidomulario = new GridPane();

        Label lblNombre = new Label("Nombre:");
        txtNombre = new TextField();
        paneContenidomulario.add(lblNombre, 0, 0);
        paneContenidomulario.add(txtNombre, 1, 0);

        Label lblMatricula = new Label("Matrícula:");
        txtMatricula = new TextField();
        paneContenidomulario.add(lblMatricula, 0, 1);
        paneContenidomulario.add(txtMatricula, 1, 1);

        Label lblCalificacion = new Label("Calificación:");
        txtCalificacion = new TextField();
        paneContenidomulario.add(lblCalificacion, 0, 2);
        paneContenidomulario.add(txtCalificacion, 1, 2);

        paneContenido.getChildren().add(paneContenidomulario);

        /*******************
         *     Botones     *
         * ****************/

        Button bttnAñadir = new Button("Añadir");
        paneContenido.getChildren().add(bttnAñadir);
        bttnAñadir.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                anadirEstudiante();
            }
        });

        Button bttnEliminar = new Button("Eliminar");
        paneContenido.getChildren().add(bttnEliminar);
        bttnEliminar.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                eliminarEstudiante();
            }
        });

        Button bttnOrdenar = new Button("Ordenar");
        paneContenido.getChildren().add(bttnOrdenar);

        /*******************
         *      Lista      *
         * ****************/

        lista = FXCollections.observableArrayList();
        lvEstudiante = new ListView<>(lista);
        paneContenido.getChildren().add(lvEstudiante);

        Scene scene = new Scene(panePrincipal);
        stage.setScene(scene);
        stage.setHeight(600);
        stage.setWidth(600);
        stage.show();
    }

    /*******************
     *    Funciones    *
     * ****************/

    private void anadirEstudiante() {
        String nombre = txtNombre.getText();
        String matricula = txtMatricula.getText();
        double calificacion = Double.parseDouble(txtCalificacion.getText());

        Estudiante e = new Estudiante(nombre, matricula, calificacion);

        lista.add(e);
        txtNombre.setText("");
        txtMatricula.setText("");
        txtCalificacion.setText("");

        System.out.println(lista);
    }

    private void eliminarEstudiante() {
        Estudiante e = lvEstudiante.getSelectionModel().getSelectedItem();

        lista.remove(e);

        System.out.println(lista);
    }


    public static void main(String[] args) {
        launch();
    }
}