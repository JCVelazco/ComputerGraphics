//Juan Carlos Velazco A01326707
//Carlos Parrodi Martínez A01421454 
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
public class Main extends JPanel{
	
	public float red = 0.5f, green = 0.25f, blue = 0.1f;
	public int iterations = 5;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		red = 0.5f; green = 0.25f; blue = 0.1f;
		g.setColor(new Color(red, green, blue));
		Point fPoint = new Point(400, 600);
		Point sPoint = new Point(400, 200);
		g.drawLine(fPoint.x, fPoint.y, sPoint.x, sPoint.y);
		drawTree(g, fPoint, sPoint, iterations, 90);
		
		
	}
	
	public void drawTree(Graphics g, Point firstP, Point secondP, int iterations, int angle){
		if(iterations == 0)return;
		
		double numOfBranches = getRandomNumberOfBranches();
		for(int i = 0; i < numOfBranches; i++){
			int angleForTheNewBranch = getRandomAngle();
			double sizeOfBranch = distanceBTwoPoints(firstP, secondP);
			double lenghtForTheNewBranch = sizeOfBranch/2;
			Point newPoint = getPointOfStart(firstP, sizeOfBranch, angle);
			//i create this second one, because when i use goForwardDrawing the values of this points will be updated to the end of the line
			Point startOfPoint = new Point(newPoint.x, newPoint.y);
			
			if(i%2 == 0){
				//to the right
				//angle that i have minus the angle that i get
				goFowardDrawing(g, lenghtForTheNewBranch, newPoint, angle-angleForTheNewBranch, iterations);
				drawTree(g, startOfPoint, newPoint, iterations-1, angle-angleForTheNewBranch);
			}else{
				//to the left
				goFowardDrawing(g, lenghtForTheNewBranch, newPoint, angle+angleForTheNewBranch, iterations);
				drawTree(g, startOfPoint, newPoint, iterations-1, angle+angleForTheNewBranch);
			}
		}
		
	}
	
	
	//Goes foward and draws a line using the last specified color
	public void goFowardDrawing (Graphics g, double pixels, Point myPoint, int degrees, int iterations){
		double finalX = (myPoint.x)+(pixels*(Math.cos(Math.toRadians(degrees))));
		double finalY = (myPoint.y)-(pixels*(Math.sin(Math.toRadians(degrees))));
		
		//sorry for the hardcoded, i was bored
		//define color
		if(iterations == 5){
			red = .4f;
			green = .35f;
		}
		if(iterations == 4){
			red = .3f;
			green = .50f;
		}
		if(iterations == 3){
			red = .2f;
			green = .65f;
		}
		if(iterations == 2){
			red = .1f;
			green = .80f;
		}
		if(iterations == 1){
			red = 0f;
			green = 1f;
		}
		
		g.setColor(new Color(red, green, blue));
		
		
		g.drawLine((int)myPoint.x, (int)myPoint.y, (int)finalX, (int)finalY);	
		myPoint.x = (int)Math.round(finalX);
		myPoint.y = (int)Math.round(finalY);
	}
	
	public void goFoward (double pixels, Point myActuaPoint, int degree){
		myActuaPoint.x = (int)Math.round((myActuaPoint.x)+(pixels*(Math.cos(Math.toRadians(degree)))));
		myActuaPoint.y = (int)Math.round((myActuaPoint.y)-(pixels*(Math.sin(Math.toRadians(degree)))));
	}
	
	public double distanceBTwoPoints(Point first, Point second){
		double distance = Math.sqrt(Math.pow((first.x-second.x), 2) + Math.pow((first.y-second.y), 2));
		return distance;
	}
	
	public double getRandomNumberOfBranches(){
		//from 3 to 8
		int min = 3, max = 7;
		return ThreadLocalRandom.current().nextInt(min, max);
	}
	
	public int getRandomAngle(){
		//from 20 to 70
		int min = 20, max = 71;
		return ThreadLocalRandom.current().nextInt(min, max);
	}
	
	public Point getPointOfStart(Point firstP, double length, int angle){
		double min = 0.3, max = 1;
		double lengthToNewPoint = ThreadLocalRandom.current().nextDouble(min, max);
		lengthToNewPoint *= length;
		Point myNewPoint = new Point(firstP.x,firstP.y);
		goFoward(lengthToNewPoint, myNewPoint, angle);
		return myNewPoint;
		
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