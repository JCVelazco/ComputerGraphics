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
	
	public int red = 0, green = 0, blue = 0;
	public int iterations = 2;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		System.out.println("-----------------------------------");
		Point fPoint = new Point(400, 600);
		Point sPoint = new Point(400, 400);
		g.drawLine(fPoint.x, fPoint.y, sPoint.x, sPoint.y);
		drawTree(g, fPoint, sPoint, iterations, 90);

		
	}
	
	public void drawTree(Graphics g, Point firstP, Point secondP, int iterations, int angle){
		if(iterations == 0)return;

		double numOfBranches = getRandomNumberOfBranches();
		for(int i = 0; i < numOfBranches; i++){
			int angleForTheNewBranch = getRandomAngle();
			double lenghtForTheNewBranch = getRandomLength();
			double sizeOfBranch = distanceBTwoPoints(firstP, secondP);
			lenghtForTheNewBranch *= sizeOfBranch;

			System.out.println("("+firstP.x+","+firstP.y+")"+" to ("+secondP.x+","+secondP.y+")");
			Point newPoint = getPointOfStart(firstP, secondP);
			//i create this second one, because when i use goForwardDrawing the values of this points will be updated to the end of the line
			Point startOfPoint = new Point(newPoint.x, newPoint.y);
			System.out.println("selected point ("+newPoint.x+","+newPoint.y+")");

			if(i%2 == 0){
				//to the right
				//angle that i have minus the angle that i get
				goFowardDrawing(g, lenghtForTheNewBranch, newPoint, angle-angleForTheNewBranch, iterations);
				drawTree(g, startOfPoint, newPoint, iterations-1, angleForTheNewBranch);
			}else{
				//to the left
				goFowardDrawing(g, lenghtForTheNewBranch, newPoint, angle+angleForTheNewBranch, iterations);
				drawTree(g, startOfPoint, newPoint, iterations-1, angleForTheNewBranch);
			}
		}
		
	}
	
	
	//Goes foward and draws a line using the last specified color
	public void goFowardDrawing (Graphics g, double pixels, Point myPoint, int degrees, int iterations){
		double finalX = (myPoint.x)+(pixels*(Math.cos(Math.toRadians(degrees))));
		double finalY = (myPoint.y)-(pixels*(Math.sin(Math.toRadians(degrees))));
		g.drawLine((int)myPoint.x, (int)myPoint.y, (int)finalX, (int)finalY);	
		myPoint.x = (int)Math.round(finalX);
		myPoint.y = (int)Math.round(finalY);
	}
	
	public double distanceBTwoPoints(Point first, Point second){
		double distance = Math.sqrt(Math.pow((first.x-second.x), 2) + Math.pow((first.y-second.y), 2));
		return distance;
	}
	
	public double getRandomNumberOfBranches(){
		//from 2 to 5
		int min = 2, max = 6;
		return ThreadLocalRandom.current().nextInt(min, max);
	}
	
	public int getRandomAngle(){
		//from 20 to 70
		int min = 20, max = 71;
		return ThreadLocalRandom.current().nextInt(min, max);
	}
	
	public double getRandomLength(){
		//from 0.3 to 0.7
		double min = 0.3, max = 0.8;
		return ThreadLocalRandom.current().nextDouble(min, max);
	}
	
	public Point getPointOfStart(Point firstP, Point secondP){
		int greatX;
		int smallX;
		//this is needed because if i go to the left x will decrees and if i go to right it will increese
		if(firstP.x>= secondP.x){
			greatX = firstP.x;
			smallX = secondP.x;
		}else{
			smallX = firstP.x;
			greatX = secondP.x;
		}
		int pointForX = ThreadLocalRandom.current().nextInt(smallX, (greatX)+1);
		int pointForY = ThreadLocalRandom.current().nextInt(secondP.y, (firstP.y)+1);
		Point myPoint = new Point(pointForX, pointForY);
		return myPoint;
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