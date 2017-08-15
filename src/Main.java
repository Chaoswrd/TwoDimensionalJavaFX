import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Slider sld = new Slider();
		
		VBox vbx = new VBox();
		ObservableList vList = vbx.getChildren();
		vList.add(sld);
		
		Pane fractalPane = new Pane();
		ObservableList list = fractalPane.getChildren();
		list.addAll(recursiveCirclesAtSides(400, 650, 350));
		
		sld.valueProperty().addListener(e ->{
			list.clear();
			list.addAll(recursiveCirclesAtSides((int) (4*sld.getValue()), 650, 350));
		});
		
		BorderPane bp = new BorderPane();
		bp.setCenter(fractalPane);
		bp.setLeft(vbx);
				
		Scene scene = new Scene(bp, 1300, 700);
		primaryStage.setTitle("Circle Recursion");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static ArrayList<Circle> recursiveCircles(int radius){ //Crée une liste de cerles différents
		if(radius<1900){ //Si la variable "radius" est inférieure à 1900
			Circle circle = new Circle(radius); //Définit l'object cercle
			circle.setCenterX(650); //La x position du cercle
			circle.setCenterY(350);  //La y position du cercle
			circle.setFill(null); //Définit la couleur du cercle. Le cercle est transparant, donc l'écran ne soit pas noir et on puisse voir les différents cercles.
			circle.setStroke(Color.BLACK); //Définit le contour du cercle 
			ArrayList<Circle> list = recursiveCircles(radius*2);  //Appelle la liste avec les autres cercles. 
			list.add(circle); //Ajoute le cerle à la liste de autres cercles
			return list; //Revoie la liste au cerle suivant
		}
		else{ //Si la variable "radius" est supérieure à 1900, retourne une liste.
			return new ArrayList<Circle>();
		}
	}
	
	public static ArrayList<Circle> recursiveCirclesAtSides(int radius, int x, int y){ //Crée une liste de cerles différents sur une ligne
		if(radius>1){ //Si la variable "radius" est inférieure à 1900
			Circle circle = new Circle(radius); //Définit l'object cercle
			circle.setCenterX(x); //La x position du cercle
			circle.setCenterY(y);  //La y position du cercle
			circle.setFill(null); //Définit la couleur du cercle. Le cercle est transparant, donc l'écran ne soit pas noir et on puisse voir les différents cercles.
			circle.setStroke(Color.BLACK); //Définit le contour du cercle 
			ArrayList<Circle> list = recursiveCirclesAtSides(radius/2, x+radius, y);  //Appelle la liste avec les autres cercles. Le cercle sur le côté droit
			ArrayList<Circle> list2 = recursiveCirclesAtSides(radius/2, x-radius, y);  //Appelle la liste avec les autres cercles. Le cercle sur le côté gauche
			list.add(circle); //Ajoute le cerle à la liste de autres cercles
			list.addAll(list2); //Ajoute les cerles de gauche à la liste de autres cercles.
			return list; //Revoie la liste au cerle suivant
		}
		else{ //Si la variable "radius" est supérieure à 1900, retourne une liste.
			return new ArrayList<Circle>();
		}
	}
	
	public static ArrayList<Circle> recursiveCirclesInTwoDimensions(int radius, int x, int y){ //Crée une liste de cerles différents
		if(radius>1){ //Si la variable "radius" est inférieure à 1900, elle termine
			Circle circle = new Circle(radius); //Définit l'object cercle
			circle.setCenterX(x); //La x position du cercle
			circle.setCenterY(y);  //La y position du cercle
			circle.setFill(null); //Définit la couleur du cercle. Le cercle est transparant, donc l'écran ne soit pas noir et on puisse voir les différents cercles.
			circle.setStroke(Color.BLACK); //Définit le contour du cercle 
			ArrayList<Circle> list = recursiveCirclesInTwoDimensions(radius/2, x+radius, y);  //Appelle la liste avec les autres cercles. Le cercle sur le côté droit
			ArrayList<Circle> list2 = recursiveCirclesInTwoDimensions(radius/2, x-radius, y);  //Appelle la liste avec les autres cercles. Le cercle sur le côté gauche
			ArrayList<Circle> list3 = recursiveCirclesInTwoDimensions(radius/2, x, y+radius);  //Appelle la liste avec les autres cercles. le cercle sur le dessus
			ArrayList<Circle> list4 = recursiveCirclesInTwoDimensions(radius/2, x, y-radius);  //Appelle la liste avec les autres cercles. le cercle en bas
			list.add(circle); //Ajoute le cercle à la liste de autres cercles
			list.addAll(list2); //Ajoute les cercles de gauche à la liste de autres cercles.
			list.addAll(list3); //Ajoute les cerles du haut à la liste de autres cercles.
			list.addAll(list4); //Ajoute les cercles en bas à la liste de autres cercles.
			return list; //Revoie la liste au cerle suivant
		}
		else{ //Si la variable "radius" est supérieure à 1900, retourne une liste.
			return new ArrayList<Circle>();
		}
	}
}
