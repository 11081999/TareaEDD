import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.geometry.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.io.Serializable;

public class LoadScene extends Scene implements Serializable{
	private Main main;
	private GridPane root = new GridPane();
	private VBox vbox1 = new VBox();
	private VBox vbox2 = new VBox();

	public LoadScene(Main main){
		super(new GridPane());
		this.main = main;

		root = (GridPane)super.getRoot();
		root.getStylesheets().add("styles.css");
		vbox1.getStyleClass().add("vbox1");
		vbox2.getStyleClass().add("vbox2");

		Label title = new Label("FORMULARIO");
		title.getStyleClass().add("titleLabel");
		Label LabelNnombre= new Label("Nombre: ");
		LabelNnombre.getStyleClass().add("titleLabel");
		Label LabelMatricula= new Label("Matricula: ");
		LabelMatricula.getStyleClass().add("titleLabel");
		Label LabelCalificacion= new Label("Calificacion: ");
		LabelCalificacion.getStyleClass().add("titleLabel");


		TextField txtFnombre= new TextField("Nombre ");
		TextField txtFmatricula= new TextField();
		TextField txtFcalificacion= new TextField();

		Label LabelResultado = new Label("Nombre, Matricula, calificacion ");
		//resultado.getStyleClass().add("titleLabel");

		Button BttnRegistrar = new Button("registrar");
		BttnRegistrar.getStyleClass().add("registerButton");
		BttnRegistrar.setMinHeight(50);
		BttnRegistrar.setMinWidth(100);
		BttnRegistrar.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e){

				//resultado.setText(nombre.getText());
				imprimirRegistro(LabelResultado, txtFnombre, txtFmatricula, txtFcalificacion);


			}
		});

		//root.setPadding(new Insets(150));
    root.setHgap(25);
    root.setVgap(10);

		//Elementos de la Vbox1
    root.add(title,0,0);
		root.add(LabelNnombre,0,0);
		root.add(txtFnombre,0,0);
		root.add(LabelMatricula,0,0);
		root.add(txtFmatricula,0,0);
		root.add(LabelCalificacion,0,0);
		root.add(txtFcalificacion,0,0);
		root.add(BttnRegistrar,0,0);

		//Elementos de la Vbox2
		root.add(LabelResultado,1,0);

		//Añadir los elementos a la Vbox1 (tienen que estar ordenados)
		vbox1.getChildren().addAll(title, LabelNnombre, txtFnombre, LabelMatricula,
		txtFmatricula, LabelCalificacion, txtFcalificacion, BttnRegistrar);
		//Añadir los elementos a la Vbox2
		vbox2.getChildren().addAll(LabelResultado);

		root.add(vbox1,0,0);
		root.add(vbox2,1,0);

		vbox1.setAlignment(Pos.CENTER);
		vbox2.setAlignment(Pos.CENTER);
		root.setAlignment(Pos.CENTER);
		super.setRoot(root);
	}

	public void imprimirRegistro(Label resultado, TextField nombre, TextField matricula, TextField calificacion){
		resultado.setText(nombre.getText()+", "+matricula.getText()+", "+calificacion.getText());
	}
}
