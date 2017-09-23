import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class recursiveCirclesC {
	public static ArrayList<Circle> recursiveCircles(double x, double y, double d){ //Crée une liste de cerles différents
		if(d>1){ //Si la variable "radius" est inférieure à 1900
			Circle circle = new Circle(d); //Définit l'object cercle
			circle.setCenterX(x); //La x position du cercle
			circle.setCenterY(y);  //La y position du cercle
			circle.setFill(null); //Définit la couleur du cercle. Le cercle est transparant, donc l'écran ne soit pas noir et on puisse voir les différents cercles.
			circle.setStroke(Color.BLACK); //Définit le contour du cercle 
			ArrayList<Circle> list = recursiveCircles(x, y, d/2);  //Appelle la liste avec les autres cercles. 
			list.add(circle); //Ajoute le cerle à la liste de autres cercles
			return list; //Revoie la liste au cerle suivant
		}
		else{ //Si la variable "radius" est supérieure à 1900, retourne une liste.
			return new ArrayList<Circle>();
		}
	}
}
