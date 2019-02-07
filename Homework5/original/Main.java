//Juan Carlos Velazco Rossell A01326707
//Carlos Parrodi Martinez A01421454
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Math;

public class Main extends JPanel{

	public static double generalAngle = 0;
	public static double generalX = 0;
	public static double generalY = 0;

	
	public void paintComponent(Graphics g){
		super.paintComponent(g);	
		position (200, 300);
		goFowardDrawing (g,100);
		rotate (90);
		goFowardDrawing (g,100);
		rotate (90);
		goFowardDrawing (g,100);
		rotate (90);
		goFowardDrawing (g,100);
		rotate (90);
	}
	
	public void position (int x, int y){
		generalX = x;
		generalY = y;
		System.out.println("My new x is: "+generalX+" and my new Y is: "+generalY);
	}
	//Goes foward and draws a line using the last specified color
	public void goFowardDrawing (Graphics g, double pixels){
		double finalX = pixels*(Math.cos(generalAngle));
		double finalY = pixels*(Math.sin(generalAngle));
		g.drawLine((int)generalX, (int)generalY, (int)finalX, (int)finalY);	
		generalX = finalX;
		generalY = finalY;
		System.out.println("My new x is: "+generalX+" and my new Y is: "+generalY);
	}
	//Goes foward without drawing
	public void goFoward (Graphics g, double pixels){
		generalX = pixels*(Math.cos(generalAngle));
		generalY = pixels*(Math.sin(generalAngle));
	}
	//It only changes the direction where the thing that draws "points"
	public void rotate (double angle){
		if((generalAngle+angle)>360){
			double inbetweenAngle = 360 - generalAngle;
			generalAngle = angle - inbetweenAngle;
		}else{
		generalAngle += angle;
		}
		System.out.println("My new angle is: "+generalAngle);
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