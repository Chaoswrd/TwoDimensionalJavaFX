import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application{

	@FXML Slider iterationsSlider;
	@FXML Slider lengthSlider;
	@FXML ChoiceBox<String> choiceBox;
	String algo = "recursiveCircles";
	
	double degreeChange = 1;
	double lengthChange = 5*degreeChange;
	
	public static void main(String[] args) {
		launch(args);
	}

	@FXML
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		double length = 200; //Default length
		double x = 350; //X of recursive function
		double y = 350; //Y -||-
		
		Parent vbx = FXMLLoader.load(getClass().getResource("Client.fxml"));
	    
	    iterationsSlider = (Slider) vbx.lookup("#iterationsSlider");
	    iterationsSlider.setPrefWidth(1300*0.2);
	    
	    lengthSlider = (Slider) vbx.lookup("#lengthSlider");
	    lengthSlider.setPrefWidth(1300*0.2);
	    
	    choiceBox = ((ChoiceBox<String>) vbx.lookup("#choiceBox"));
	    //Sets the list of algorithms
	    choiceBox.setItems(FXCollections.observableArrayList("recursiveCircles", "recursiveCirclesAtSides", "recursiveCirclesInTwoDimensions"));
		
		Pane fractalPane = new Pane();
		ObservableList list = fractalPane.getChildren();
		list.addAll(recursiveCirclesC.recursiveCircles(x, y, length));
		
		BorderPane bp = new BorderPane();
		bp.setCenter(fractalPane);
		bp.setLeft(vbx);
		
		//Works as a multiplier on the length
		iterationsSlider.valueProperty().addListener(e -> {
			degreeChange = iterationsSlider.getValue();
			lengthChange = 5*degreeChange;
			list.clear();
			list.addAll(getFunc(algo, x, y, lengthChange*length));
			bp.setCenter(fractalPane);
		});
		//Changes length of the recursive pattern
		lengthSlider.valueProperty().addListener(e -> {
			lengthChange = lengthSlider.getValue()/50;
			list.clear();
			list.addAll(getFunc(algo, x, y, lengthChange*length));
			bp.setCenter(fractalPane);
		});
		//Sets the algorithm
		choiceBox.valueProperty().addListener(e -> {
			algo = choiceBox.getValue().toString();
			list.clear();
			list.addAll(getFunc(algo, x, y, length));
			bp.setCenter(fractalPane);
		});
				
		Scene scene = new Scene(bp, 1300, 700);
		primaryStage.setTitle("Circle Recursion");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//Checks which algorithm is set ands calls that specific algorithm
	private static ArrayList<Circle> getFunc(String id, double x, double y, double length){
		switch(id){
			case "recursiveCircles":
				return recursiveCirclesC.recursiveCircles(x, y, length);
			case "recursiveCirclesAtSides":
				return recursiveCirclesAtSidesC.recursiveCirclesAtSides(x, y, length);
			case "recursiveCirclesInTwoDimensions":
				return recursiveCirclesInTwoDimensions.recursiveCirclesInTwoDimensions(x, y, length);
		}
		return null;
	}
}
