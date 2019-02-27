//Juan Carlos Velazco A01326707
//Carlos Parrodi Martínez A01421454 
import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.*;
class Point{
	public int x;
	public int y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	public void setPoints(int x, int y){
		this.x = x;
		this.y = y;
	}
}
/*
class Line{
	public Point intial;
	public Point finish;
	
	public Line(Point initial, Point finish){
		initial = this.intial;
		finish = this.finish;
	}
}*/

public class Main extends JPanel{
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		System.out.println("----------------------------");
		Point myFirstPoint2 = new Point(200, 500);
		Point mySecondPoint = new Point(600, 500);
		goFowardDrawing(g, 400, myFirstPoint2, 0);
		Point myFirstPoint = new Point(200, 500);
		drawTriangle(g, myFirstPoint, mySecondPoint, 2, 0);
	}
	
	public void drawTriangle(Graphics g, Point firstP, Point secondP, int iterations, int angle){
		//if i finish my iterations i roll back
		if(iterations == 0){System.out.println("salgo");return;}
		float red = 0f, green = 0f, blue = 1f;
		g.setColor(new Color(red,green,blue));
		Point myActualPosition = firstP;
		Point lastPosition = new Point(0,0);

		//getDistanceBToPoints
		double distanceBTwoPoints = distanceBTwoPoints(firstP, secondP);
		//lengthOftheSizeOfthenewTriangle

		double lengthOfTriangle = distanceBTwoPoints/3;
		//System.out.println("size of new side: "+lengthOfTriangle);

		//move without drawing
		lastPosition.setPoints(myActualPosition.x, myActualPosition.y);
		goFoward(g, lengthOfTriangle, myActualPosition, angle);
		System.out.printf("llamo recursivamente al metodo con valores de firstPX: %d y firstPY: %d .... aparte de lastPX: %d y lastPY: %d\n", lastPosition.x, lastPosition.y, myActualPosition.x, myActualPosition.y);
		drawTriangle(g, lastPosition, myActualPosition, iterations-1, angle);

		//paint the triangle
		lastPosition.setPoints(myActualPosition.x, myActualPosition.y);
		System.out.println(lastPosition.x);
		goFowardDrawing(g, lengthOfTriangle, myActualPosition, angle+60);
		System.out.printf("llamo recursivamente al metodo con valores de firstPX: %d y firstPY: %d .... aparte de lastPX: %d y lastPY: %d\n", lastPosition.x, lastPosition.y, myActualPosition.x, myActualPosition.y);
		drawTriangle(g, lastPosition, myActualPosition, iterations-1, angle);

		lastPosition.setPoints(myActualPosition.x, myActualPosition.y);
		System.out.println(lastPosition.x);
		goFowardDrawing(g, lengthOfTriangle, myActualPosition, angle-60);
		System.out.printf("llamo recursivamente al metodo con valores de firstPX: %d y firstPY: %d .... aparte de lastPX: %d y lastPY: %d\n", lastPosition.x, lastPosition.y, myActualPosition.x, myActualPosition.y);
		drawTriangle(g, lastPosition, myActualPosition, iterations-1, angle);

		//move at the end
		lastPosition.setPoints(myActualPosition.x, myActualPosition.y);
		System.out.println(lastPosition.x);
		goFoward(g, lengthOfTriangle, myActualPosition, angle);
		System.out.printf("llamo recursivamente al metodo con valores de firstPX: %d y firstPY: %d .... aparte de lastPX: %d y lastPY: %d\n", lastPosition.x, lastPosition.y, myActualPosition.x, myActualPosition.y);
		drawTriangle(g, lastPosition, myActualPosition, iterations-1, angle+60);




		//iterations--;
		
		
	}
	
	//Goes foward and draws a line using the last specified color
	public void goFowardDrawing (Graphics g, double pixels, Point myPoint, int degrees){
		System.out.println("imprimo raya de "+pixels);
		double finalX = (myPoint.x)+(pixels*(Math.cos(Math.toRadians(degrees))));
		double finalY = (myPoint.y)-(pixels*(Math.sin(Math.toRadians(degrees))));
		g.drawLine((int)myPoint.x, (int)myPoint.y, (int)finalX, (int)finalY);	
		myPoint.x = (int)Math.round(finalX);
		myPoint.y = (int)Math.round(finalY);
	}
	//Goes foward without drawing
	public void goFoward (Graphics g, double pixels, Point myActuaPoint, int degree){
		System.out.println("avanzdo en raya de "+pixels);
		myActuaPoint.x = (int)Math.round((myActuaPoint.x)+(pixels*(Math.cos(Math.toRadians(degree)))));
		myActuaPoint.y = (int)Math.round((myActuaPoint.y)-(pixels*(Math.sin(Math.toRadians(degree)))));
	}
	//It only changes the direction where the thing that draws "points"
	/*
	public void rotate (double angle){
		if((generalAngle+angle)>360){
			double inbetweenAngle = 360 - generalAngle;
			generalAngle = angle - inbetweenAngle;
		}else{
			generalAngle += angle;
		}
	}*/
	public double distanceBTwoPoints(Point first, Point second){
		//System.out.println("x: "+first.x+" and x2: "+second.x+" , y: "+first.y+" second y: "+second.y);
		double distance = Math.sqrt(Math.pow((first.x-second.x), 2) + Math.pow((first.y-second.y), 2));
		return distance;
	}
	
	public static void main(String args[]){
		Main panel = new Main();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(800, 800);
		application.setVisible(true);
	}
}