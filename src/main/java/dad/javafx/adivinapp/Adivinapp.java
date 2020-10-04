package dad.javafx.adivinapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Adivinapp extends Application {

	private Label requestLabel;
	private TextField numField;
	private Button comprobarButton;
	private int numAleatorio = (int) (Math.random() * (100 -1)) + 1;
	int nIntentos = 0;
	Alert acierto = new Alert(AlertType.INFORMATION);
	Alert error = new Alert(AlertType.ERROR);
	Alert nMenor = new Alert(AlertType.WARNING);
	Alert nMayor = new Alert(AlertType.WARNING);
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//Texto
		requestLabel = new Label("Introduce un n�mero del 1 al 100");
		requestLabel.setWrapText(true);// En caso de que haya demasiado texto se esriba para debajo en vez de tener que
		// agrandar la pantalla
		
		//Caja de texto
		numField = new TextField();
		numField.setPromptText("Introduce un n�mero");
		numField.setMaxWidth(150);
		
		//Bot�n
		comprobarButton = new Button("Comprobar");
		comprobarButton.setDefaultButton(true);//Bot�n por defecto al pulsar enter
		//Acci�n del bot�n con lambda
		comprobarButton.setOnAction(e -> onComprobarButtonAction(e));
		
		//"Caja"
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(requestLabel, numField, comprobarButton);
		
		//Meto la caja en la escena y esta la muestro
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("AdivinApp");
		primaryStage.show();

	}
	
	public void onComprobarButtonAction(ActionEvent e) {
		//Se les a�ade texto a las ventanas emergentes
		nMenor.setTitle("�Fallaste!");
		nMenor.setHeaderText("Has fallado muchacho");
		
		nMayor.setTitle("�Fallaste!");
		nMayor.setHeaderText("Has fallado muchacho");
		
		acierto.setTitle("�Ag�ita chaval");
		acierto.setHeaderText("Esa es");
		
		
		
		try {
			int num = Integer.parseInt(numField.getText());
			
			if (num == numAleatorio) {
				nIntentos++;
				acierto.setContentText(
						"S�lo has necesitado " + nIntentos + " intento/os" + "\n" + "Vuelve a jugar y hazlo mejor");
				acierto.showAndWait();
				nIntentos = 0;
				numAleatorio = (int) (Math.random() * (100 -1)) + 1;
				numField.setText("");

			} else if (num < 1 || num > 100) {
				error.setContentText("El n�mero introducido no es v�lido.");
				error.showAndWait();
				nIntentos++;
				numField.setText("");
			} else if (numAleatorio < num) {
				nMenor.setContentText("El n�mero es menor que " + num + "\n" + "Vuelve a intentarlo");
				nMenor.showAndWait();
				nIntentos++;
				numField.setText("");
			} else {
				nMayor.setContentText("El n�mero es mayor que " + num + "\n" + "Vuelve a intentarlo");
				nMayor.showAndWait();
				nIntentos++;
				numField.setText("");
			}
			
			
			
		}catch (NumberFormatException a) {
			error.setTitle("Error");
			error.setHeaderText("�Qu� est�s introduciendo muchacho?");
			error.setContentText("El n�mero que introduciste no me sirve pa nada");
			error.showAndWait();
		}
		
		
	}

	public static void main(String[] args) {
		launch(args);

	}

}
