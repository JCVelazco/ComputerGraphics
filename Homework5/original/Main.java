//Juan Carlos Velazco Rossell A01326707
//Carlos Parrodi Martinez A01421454
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Math;

/*
Computer Graphics

In-Class Assignment 

Create methods in a graphical application in Java that "draw vector based instructions"

The methods to be implemented are:

Void position (int x, int y)
Void goFowardDrawing (double pixels)
Goes foward and draws a line using the last specified color
Void goFoward (double pixels)
Goes foward without drawing
Void rotate (double angle)
It only changes the direction where the thing that draws "points"

The starting angle of the application is 0 degrees, so to paint a square, you could call the following instructions:

position (200, 300);
goFowardDrawing (g,100);
rotate (90);
goFowardDrawing (g,100);
rotate (90);
goFowardDrawing (g,100);
rotate (90);
goFowardDrawing (g,100);
rotate (90);

Once you have the methods working, draw the house on page 9 of the Concepts document
(TIP: It may be a good idea for the methods to receive a reference to the "g" object of the Graphics class as a parameter)
the current position can be implemented as double class variables

Upload a ZIP file containing 4 folders with a Main.java file each one
. Folder "original" where the house is painted originally as it is defined
. Folder "a" where the first operation mentioned in page 20 of the document "Concepts" are applied.
. Folder "b" where the second operation mentioned in page 20 of the document "Concepts" are applied.
. Folder "c" where the third operation mentioned in page 20 of the document "Concepts" are applied.
*/

public class Main extends JPanel{
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);	
	}
	
	public void position (Graphics g, int x, int y){
		
	}
	//Goes foward and draws a line using the last specified color
	public void goFowardDrawing (Graphics g, double pixels){
		
	}
	//Goes foward without drawing
	public void goFoward (Graphics g, double pixels){
		
	}
	//It only changes the direction where the thing that draws "points"
	public void rotate (Graphics g, double angle){
		
	}
	
	
	public static void main(String args[]){
		Main panel = new Main();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(1000, 600);
		application.setVisible(true);
	}
}