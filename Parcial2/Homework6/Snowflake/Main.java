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
		int arrX[] = new int[]{200,500,350};
		int arrY[] = new int[]{500,500,240};
		
		g.drawPolygon(arrX, arrY, 3);

		int iterations = 5;
		
		for(int i = 0; i < 3; i++){
			if(i == 2){
				Point myFirstPoint = new Point(arrX[0], arrY[0]);
				Point mySecondPoint = new Point(arrX[2], arrY[2]);
				drawTriangle(g, myFirstPoint, mySecondPoint, iterations, 60);
			}
			else if(i == 1){
				Point myFirstPoint = new Point(arrX[2], arrY[2]);
				Point mySecondPoint = new Point(arrX[1], arrY[1]);
				drawTriangle(g, myFirstPoint, mySecondPoint, iterations, 300);
			}
			else if(i == 0){
				Point myFirstPoint = new Point(arrX[1], arrY[1]);
				Point mySecondPoint = new Point(arrX[0], arrY[0]);
				drawTriangle(g, myFirstPoint, mySecondPoint, iterations, 180);
			}
		}
		
		
	}
	
	public void drawTriangle(Graphics g, Point firstP, Point secondP, int iterations, int angle){
		//if i finish my iterations i roll back
		if(iterations == 0)return;
		float red = 0f, green = 0f, blue = 1f;
		g.setColor(new Color(red,green,blue));
		Point myActualPosition = firstP;
		Point lastPosition = new Point(0,0);
		
		//getDistanceBToPoints
		double distanceBTwoPoints = distanceBTwoPoints(firstP, secondP);
		//lengthOftheSizeOfthenewTriangle
		
		double lengthOfTriangle = distanceBTwoPoints/3;
		//System.out.println("size of new side: "+lengthOfTriangle);
		
		//move to the trianngle and check
		lastPosition.setPoints(myActualPosition.x, myActualPosition.y);
		goFoward(g, lengthOfTriangle, myActualPosition, angle);
		drawTriangle(g, lastPosition, myActualPosition, iterations-1, angle);
		
		//paint the triangle to the top and check
		lastPosition.setPoints(myActualPosition.x, myActualPosition.y);
		goFowardDrawing(g, lengthOfTriangle, myActualPosition, angle+60);
		drawTriangle(g, lastPosition, myActualPosition, iterations-1, angle+60);
		//paint to the down and check
		lastPosition.setPoints(myActualPosition.x, myActualPosition.y);
		goFowardDrawing(g, lengthOfTriangle, myActualPosition, angle-60);
		drawTriangle(g, lastPosition, myActualPosition, iterations-1, angle-60);
		
		//move at the end and check
		lastPosition.setPoints(myActualPosition.x, myActualPosition.y);
		goFoward(g, lengthOfTriangle, myActualPosition, angle);
		drawTriangle(g, lastPosition, myActualPosition, iterations-1, angle);
		
		
		
	}
	
	//Goes foward and draws a line using the last specified color
	public void goFowardDrawing (Graphics g, double pixels, Point myPoint, int degrees){
		double finalX = (myPoint.x)+(pixels*(Math.cos(Math.toRadians(degrees))));
		double finalY = (myPoint.y)-(pixels*(Math.sin(Math.toRadians(degrees))));
		g.drawLine((int)myPoint.x, (int)myPoint.y, (int)finalX, (int)finalY);	
		myPoint.x = (int)Math.round(finalX);
		myPoint.y = (int)Math.round(finalY);
	}
	//Goes foward without drawing
	public void goFoward (Graphics g, double pixels, Point myActuaPoint, int degree){
		myActuaPoint.x = (int)Math.round((myActuaPoint.x)+(pixels*(Math.cos(Math.toRadians(degree)))));
		myActuaPoint.y = (int)Math.round((myActuaPoint.y)-(pixels*(Math.sin(Math.toRadians(degree)))));
	}
	
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