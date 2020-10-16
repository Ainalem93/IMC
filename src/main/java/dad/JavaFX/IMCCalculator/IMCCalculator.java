package dad.JavaFX.IMCCalculator;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class IMCCalculator extends Application{
	
	private Label pesoLabel; //para texto
	private Label alturaLabel; //para texto
	private Label kgLabel; //para texto
	private Label cmLabel; //para texto
	private Label imcLabel; //para texto
	private Label imcnLabel; //para texto
	private Label resultadoLabel;
	
	private DoubleProperty peso = new SimpleDoubleProperty(0); //para recoger los datos de las cajas
	private DoubleProperty altura = new SimpleDoubleProperty(0);
	private DoubleProperty resultado = new SimpleDoubleProperty(0);
	
	private TextField pesoText; //para cuadro de texto
	private TextField alturaText; //para cuadro de texto

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		pesoLabel = new Label("Peso");
		alturaLabel = new Label("Altura");
		kgLabel = new Label("kg");
		cmLabel = new Label("cm");
		imcLabel = new Label("IMC:");
		imcnLabel = new Label();
		resultadoLabel = new Label();
		
		pesoText = new TextField();
		pesoText.setMaxWidth(150); //150 es lo normal
		
		alturaText = new TextField();
		alturaText.setMaxWidth(150); //150 es lo normal
		
		HBox primera = new HBox(); //para poner los elementos de izq a der
		primera.setSpacing(15);
		primera.getChildren().addAll(pesoLabel, pesoText, kgLabel);
		primera.setAlignment(Pos.CENTER);
		
		
		HBox segunda = new HBox(); //para poner los elementos de izq a der
		segunda.setSpacing(15);
		segunda.getChildren().addAll(alturaLabel, alturaText, cmLabel);
		segunda.setAlignment(Pos.CENTER);
		
		
		HBox tercero = new HBox(); //para poner los elementos de izq a der
		tercero.setSpacing(15);
		tercero.getChildren().addAll(imcLabel, imcnLabel);
		tercero.setAlignment(Pos.CENTER);
	
		
		VBox root = new VBox();  //caja donde metemos todo
		root.setSpacing(15);
		root.getChildren().addAll(primera, segunda, tercero, resultadoLabel);
		root.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(root, 480, 320); //caja mas su tamaño
		
		primaryStage.setTitle("IMC Calculator"); //nombre de arriba de la caja
		primaryStage.setScene(scene); //mostrar caja
		primaryStage.show();
		
		Bindings.bindBidirectional(pesoText.textProperty(), peso, new NumberStringConverter());
		//para que el resultado cambie al valor introducido y convierta el "string" en numero
		
		Bindings.bindBidirectional(alturaText.textProperty(), altura, new NumberStringConverter());
		
		DoubleBinding alturaEnM = altura.divide(100); //para pasar de cm a m
		
		DoubleBinding alturaAl2 = alturaEnM.multiply(alturaEnM); //elevar al cuadrado
		
		DoubleBinding resulFinal = peso.divide(alturaAl2); //dividir todo
		
		resultado.bind(resulFinal); //para meter el dato final
		
		imcnLabel.textProperty().bind(resultado.asString());
		//para mostrar el resultado enlazandolo con el resultado final y convirtiendolo de nuevo a string
		//mostrando solo 2 decimales
		
		imcnLabel.textProperty().addListener((o, ov, nv)->{
			//para actualizar los valores cuando cambien
			
			double n = resultado.doubleValue(); 
			
			if (n < 18.5) {
				resultadoLabel.setText("Bajo Peso");
			}
			
			if (n >= 18.5 && n < 25) {
				resultadoLabel.setText("Normal");
			}
			
			if (n >= 25 && n < 30) {
				resultadoLabel.setText("Sobre peso");
			}
			
			if (n > 30) {
				resultadoLabel.setText("Obeso");
			}
		});
		
	}
	
	public static void main(String[] args) {
		launch(args); //lanza aplicacion

	}

}
