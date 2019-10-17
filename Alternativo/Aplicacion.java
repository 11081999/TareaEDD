import java.util.LinkedList;

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
    private LinkedList<Estudiante> listaLigada = new LinkedList<>();

    public void start(Stage stage) throws Exception {
        BorderPane panePrincipal = new BorderPane();
        FlowPane paneContenido = new FlowPane();
        panePrincipal.setCenter(paneContenido);

        /*******************
         * Campos de texto *
         * ****************/

        GridPane paneFormulario = new GridPane();

        Label lblNombre = new Label("Nombre:");
        txtNombre = new TextField();
        paneFormulario.add(lblNombre, 0, 0);
        paneFormulario.add(txtNombre, 1, 0);

        Label lblMatricula = new Label("Matrícula:");
        txtMatricula = new TextField();
        paneFormulario.add(lblMatricula, 0, 1);
        paneFormulario.add(txtMatricula, 1, 1);

        Label lblCalificacion = new Label("Calificación:");
        txtCalificacion = new TextField();
        paneFormulario.add(lblCalificacion, 0, 2);
        paneFormulario.add(txtCalificacion, 1, 2);

        /*******************
         *     Botones     *
         * ****************/

        Button bttnAnadir = new Button("Añadir");
        paneFormulario.add(bttnAnadir, 0, 3);
        bttnAnadir.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                anadirEstudiante();
            }
        });

        Button bttnEliminar = new Button("Eliminar");
        paneFormulario.add(bttnEliminar, 1, 3);
        bttnEliminar.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                eliminarEstudiante();
            }
        });

        Button bttnOrdenar = new Button("Ordenar");
        paneFormulario.add(bttnOrdenar, 0, 4);
        bttnOrdenar.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                ordenarEstudiantes();
            }
        });

        paneFormulario.setHgap(10);
        paneFormulario.setVgap(25);
        paneContenido.getChildren().add(paneFormulario);

        /*******************
         *      Lista      *
         * ****************/

        lista = FXCollections.observableArrayList();
        lvEstudiante = new ListView<>(lista);
        paneContenido.getChildren().add(lvEstudiante);

        Scene scene = new Scene(panePrincipal);
        stage.setScene(scene);
        stage.setHeight(450);
        stage.setWidth(500);
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

        listaLigada.add(e);

        lista.setAll(listaLigada);
        txtNombre.setText("");
        txtMatricula.setText("");
        txtCalificacion.setText("");

        /*System.out.println("Observable List: "+lista);
        System.out.println("Linked List:     "+listaLigada.toString());*/
    }

    private void eliminarEstudiante() {
        Estudiante e = lvEstudiante.getSelectionModel().getSelectedItem();

        listaLigada.remove(e);

        lista.setAll(listaLigada);

        /*System.out.println("Observable List: "+lista);
        System.out.println("Linked List:     "+listaLigada.toString());*/
    }

    private void ordenarEstudiantes() {
        quickSort(0, listaLigada.size()-1);

        lista.setAll(listaLigada);

        /*System.out.println("Observable List: "+lista);
        System.out.println("Linked List:     "+listaLigada.toString());*/
    }

    /*******************
     *    Quick sort   *
     * ****************/

    private void intercambiar(int indice1, int indice2) {
        Estudiante temp1 = listaLigada.get(indice1);
        Estudiante temp2 = listaLigada.get(indice2);
        listaLigada.set(indice1, temp2);
        listaLigada.set(indice2, temp1);
    }

    private void quickSort(int izquierda, int derecha) {
        Estudiante pivote = listaLigada.get((derecha + izquierda) / 2);
        int i = izquierda;
        int j = derecha;
        while (i <= j) {
            while (listaLigada.get(i).getCalificacion() < pivote.getCalificacion()) {
                i++;
            }
            while (listaLigada.get(j).getCalificacion() > pivote.getCalificacion()) {
                j--;
            }
            if (i <= j) {
                if (i != j) {
                    intercambiar(i, j);
                }
                i++;
                j--;
            }
        }
        if (izquierda < j) {
            quickSort(izquierda, j);
        }
        if (i < derecha) {
            quickSort(i, derecha);
        }
    }


    public static void main(String[] args) {
        launch();
    }
}