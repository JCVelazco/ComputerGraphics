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
		//Instructions to draw the house
		position(30, 40);
		rotate(-70);	
		goFoward(g, 200);
		rotate(-20);
		goFowardDrawing(g, 200);
		rotate(90);
		goFowardDrawing(g, 300);
		rotate(90);
		goFowardDrawing(g, 200);
		rotate(56.443);
		goFowardDrawing(g, 180);
		rotate(67.114);
		goFowardDrawing(g, 180);
		rotate(123.557);
		goFoward(g, 130);
		rotate(-67.114);
		goFowardDrawing(g, 100);
		rotate(90);
		goFowardDrawing(g, 50);
		rotate(90);
		goFowardDrawing(g, 100);
		rotate(90);
		goFowardDrawing(g, 50);
	}
	
	public void position (int x, int y){
		generalX = x;
		generalY = y;
		generalAngle=0;
	}
	//Goes foward and draws a line using the last specified color
	public void goFowardDrawing (Graphics g, double pixels){
		double finalX = (generalX)+(pixels*(Math.cos(Math.toRadians(generalAngle))));
		double finalY = (generalY)-(pixels*(Math.sin(Math.toRadians(generalAngle))));
		g.drawLine((int)generalX, (int)generalY, (int)finalX, (int)finalY);	
		generalX = Math.round(finalX);
		generalY = Math.round(finalY);

	}
	//Goes foward without drawing
	public void goFoward (Graphics g, double pixels){
		generalX = Math.round((generalX)+(pixels*(Math.cos(Math.toRadians(generalAngle)))));
		generalY = Math.round((generalY)-(pixels*(Math.sin(Math.toRadians(generalAngle)))));
	}
	//It only changes the direction where the thing that draws "points"
	public void rotate (double angle){
		if((generalAngle+angle)>360){
			double inbetweenAngle = 360 - generalAngle;
			generalAngle = angle - inbetweenAngle;
		}else{
			generalAngle += angle;
		}
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