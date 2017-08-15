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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SpikeRecursion extends Application{

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
		
		double length = 1200;
		double x = 40;
		double y = 400;
		
	    /*Parent vbx = FXMLLoader.load(getClass().getResource("Client.fxml"));
	    
	    
	    iterationsSlider = (Slider) vbx.lookup("#iterationsSlider");
	    iterationsSlider.setPrefWidth(1300*0.2);
	    lengthSlider = (Slider) vbx.lookup("#lengthSlider");
	    lengthSlider.setPrefWidth(1300*0.2);*/
		
		Pane root = new Pane();
		ObservableList list = root.getChildren();
		list.addAll(spikes(x, y, length, 0, 5));
		
		BorderPane bp = new BorderPane();
		bp.setCenter(root);
		//bp.setLeft(vbx);
	
		
		/*iterationsSlider.valueProperty().addListener(e -> {
			degreeChange = iterationsSlider.getValue()*3.6;
			list.clear();
			list.addAll(spikes(x, y, length));
			bp.setCenter(root);
		});
		
		lengthSlider.valueProperty().addListener(e -> {
			lengthChange = lengthSlider.getValue()/150;
			list.clear();
			list.addAll(spikes(x, y, length));
			bp.setCenter(root);
		});*/
		
		Scene scene = new Scene(bp, 1280, 800);
		primaryStage.setTitle("SpikeRecursion");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private ArrayList<Line> spikes(double x, double y, double length, double degrees, int iterations){
		double length3 = length/3;
		Double[][] doob = {
				rotation(length3, degrees),
				rotation(length3, degrees-60),
				rotation(length3, degrees+60),
				rotation(length3, degrees)
		};
		Double[] lastXY = {x,y};
		if(iterations>1){
			ArrayList<Line> lines = new ArrayList<Line>();
			for(int i=0; i<4; i++){
				if(i==1)
					lines.addAll(spikes(lastXY[0],lastXY[1],length3,degrees-60,iterations-1));
				else if(i==2)
					lines.addAll(spikes(lastXY[0],lastXY[1],length3,degrees+60,iterations-1));
				else
					lines.addAll(spikes(lastXY[0],lastXY[1],length3,degrees,iterations-1));
				lastXY[0]=doob[i][0]+lastXY[0];
				lastXY[1]=doob[i][1]+lastXY[1];
			}
	
			return lines;
		}
		else{
			ArrayList<Line> lines = new ArrayList<Line>();
			
			for(int i=0; i<4; i++){
				Line line = new Line();
				line.setStartX(lastXY[0]);
				line.setStartY(lastXY[1]);
				Double[] r = rotation(100, -60);
				line.setEndX(doob[i][0]+lastXY[0]);
				line.setEndY(doob[i][1]+lastXY[1]);
				lastXY[0]=doob[i][0]+lastXY[0];
				lastXY[1]=doob[i][1]+lastXY[1];
				line.setFill(Color.BLACK);
				lines.add(line);
			}
			return lines;
		}
	}
	
	private Double[] rotation(double length, double degrees) {
		Double[] xy = new Double[2];
		double radian = Math.toRadians(degrees);
		xy[0] = length*Math.cos(radian); //x value
		xy[1] = length*Math.sin(radian); //y value
		return xy;
	}

}
