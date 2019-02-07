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
	public static float red = 0f, green = 0f, blue = 0f;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);	
		position (200, 300);//360 and (200,300)
		goFowardDrawing (g,100);//(300,300) right
		rotate (90);//90
		goFowardDrawing (g,100);//(300,200) up
		rotate (90);//180
		goFowardDrawing (g,100);//(200,200) left
		rotate (90);//270
		goFowardDrawing (g,100);//(200,300) down
		rotate (90);//360
	}
	
	public void position (int x, int y){
		generalX = x;
		generalY = y;
		//debug
		System.out.println("My new x is: "+generalX+" and my new Y is: "+generalY);
	}
	//Goes foward and draws a line using the last specified color
	public void goFowardDrawing (Graphics g, double pixels){
		double finalX = (generalX)+(pixels*(Math.cos(Math.toRadians(generalAngle))));
		double finalY = (generalY)-(pixels*(Math.sin(Math.toRadians(generalAngle))));
		g.drawLine((int)generalX, (int)generalY, (int)finalX, (int)finalY);	
		generalX = Math.round(finalX);
		generalY = Math.round(finalY);
		//debug
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
		//debug
		//System.out.println("My new angle is: "+generalAngle);
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