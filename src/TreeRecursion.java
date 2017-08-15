import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class TreeRecursion extends Application{

	@FXML Slider iterationsSlider;
	@FXML Slider lengthSlider;
	
	public static void main(String[] args) {
		launch(args);
	}
	double degreeChange = 50;
	double lengthChange = 0.6;

	@Override
	@FXML
	public void start(Stage primaryStage) throws Exception {
		
		double length = 300;
		double degrees = 90;
		double x = 650;
		double y = 700;
		
	    Parent vbx = FXMLLoader.load(getClass().getResource("Client.fxml"));
	    
	    
	    iterationsSlider = (Slider) vbx.lookup("#iterationsSlider");
	    iterationsSlider.setPrefWidth(1300*0.2);
	    lengthSlider = (Slider) vbx.lookup("#lengthSlider");
	    lengthSlider.setPrefWidth(1300*0.2);
		
		Pane root = new Pane();
		ObservableList list = root.getChildren();
		list.addAll(tree(length, degrees, x, y, lengthChange, degreeChange));
		
		BorderPane bp = new BorderPane();
		bp.setCenter(root);
		bp.setLeft(vbx);
	
		
		iterationsSlider.valueProperty().addListener(e -> {
			degreeChange = iterationsSlider.getValue()*3.6;
			list.clear();
			list.addAll(tree(length, degrees, x, y, lengthChange, degreeChange));
			bp.setCenter(root);
		});
		
		lengthSlider.valueProperty().addListener(e -> {
			lengthChange = lengthSlider.getValue()/150;
			list.clear();
			list.addAll(tree(length, degrees, x, y, lengthChange, degreeChange));
			bp.setCenter(root);
		});
		
		Scene scene = new Scene(bp, 1300, 700);
		primaryStage.setTitle("Tree Recursion");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static ArrayList<Line> tree(double length, double degrees, double x, double y, double lengthChange, double degreeChange){
		if(length>1){
			double xDisplacement = (x + Math.cos(Math.toRadians(degrees)) * length); 
			double yDisplacement = (y - Math.sin(Math.toRadians(degrees)) * length);
			Line line = new Line();
			line.setStartX(x);
			line.setStartY(y);
			line.setEndX(xDisplacement);
			line.setEndY(yDisplacement);
			ArrayList<Line> lines = tree(length*lengthChange, degrees+degreeChange, xDisplacement, yDisplacement, lengthChange, degreeChange);
			ArrayList<Line> lines2 = tree(length*lengthChange, degrees-degreeChange, xDisplacement, yDisplacement, lengthChange, degreeChange);
			lines.add(line);
			lines.addAll(lines2);
			return lines;
		}
		else{
			return new ArrayList<Line>();
		}
	}
	
}
