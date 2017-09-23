import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class recursiveCirclesInTwoDimensions {
	public static ArrayList<Circle> recursiveCirclesInTwoDimensions(double x, double y, double d){ //Crée une liste de cerles différents
		if(d>1){ //Si la variable "radius" est inférieure à 1900, elle termine
			Circle circle = new Circle(d); //Définit l'object cercle
			circle.setCenterX(x); //La x position du cercle
			circle.setCenterY(y);  //La y position du cercle
			circle.setFill(null); //Définit la couleur du cercle. Le cercle est transparant, donc l'écran ne soit pas noir et on puisse voir les différents cercles.
			circle.setStroke(Color.BLACK); //Définit le contour du cercle 
			ArrayList<Circle> list = recursiveCirclesInTwoDimensions(x+d, y, d/2);  //Appelle la liste avec les autres cercles. Le cercle sur le côté droit
			ArrayList<Circle> list2 = recursiveCirclesInTwoDimensions(x-d, y, d/2);  //Appelle la liste avec les autres cercles. Le cercle sur le côté gauche
			ArrayList<Circle> list3 = recursiveCirclesInTwoDimensions(x, y+d, d/2);  //Appelle la liste avec les autres cercles. le cercle sur le dessus
			ArrayList<Circle> list4 = recursiveCirclesInTwoDimensions(x, y-d, d/2);  //Appelle la liste avec les autres cercles. le cercle en bas
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
