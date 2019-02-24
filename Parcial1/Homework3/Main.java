//Carlos Parrodi Martínez A01421454 Juan Carlos Velazco A01326707
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.*;

public class Main extends JPanel{
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		drawSky(g);
		drawGrass(g);
		drawSun(g);
		drawHouse(g);
		drawBocho(g);
		drawDog(g);
		drawTree(g);
	}

	public void drawSky(Graphics g){
		//Gradient Sky
		float red = 0f, green = 0.8f, blue = 1f;
		for(int r = 0; r < 1000; r ++){
			for(int c = 0; c < 1950; c++){
				g.setColor(new Color(red, green, blue));
				g.drawLine(c, r, c, r);	
			}	
			red = red + 0.001f;		
		}
	}

	public void drawGrass(Graphics g){
		//Simple colored grass
		float red = 0f, green = 0.6f, blue = 0.2f; 
		for(int r = 800; r < 1000; r ++){
			for(int c = 0; c < 1950; c++){
				g.setColor(new Color(red, green, blue));
				g.drawLine(c, r, c, r);	
			}	
		}
	}

	public void drawSun(Graphics g){
		//Gradient sun
		float red = 0.8f, green = 0.4f, blue = 0f;
		double xOfSun = 1500;
		double yOfSun = 80;
		for(int depth = 300; depth > 0; depth--){
			g.setColor(new Color(red,green,blue));
			g.fillOval((int)xOfSun, (int)yOfSun, depth, depth);
			xOfSun = xOfSun + 0.5;
			yOfSun =  yOfSun + 0.5;
			green = green + 0.002f;
		}
		
	}

	public void drawHouse(Graphics g){
		//Body of House with gradient
		float red = 1f, green = 0.85f, blue = 0.3f; 
		for(int r = 500; r < 850; r ++){
			for(int c = 50; c < 450; c++){
				g.setColor(new Color(red, green, blue));
				g.drawLine(c, r, c, r);	
			}		
			blue =  blue + 0.0005f;
		}
		//Windows of House with gradient
		red = 0f; green = 0.8f; blue = 1f;
		for(int r = 600; r < 680; r ++){
			for(int c = 100; c < 180; c++){
				g.setColor(new Color(red, green, blue));
				g.drawLine(c, r, c, r);	
				g.setColor(new Color(red, green, blue));
				g.drawLine(c+220, r, c+220, r);	
			}		
			green =  green - 0.002f;
		}
		
		//Door of the House
		red = 0.5f; green = 0.25f; blue = 0.15f;
		for(int r = 700; r < 850; r ++){
			for(int c = 200; c < 300; c++){
				g.setColor(new Color(red, green, blue));
				g.drawLine(c, r, c, r);	
			}		
			green =  green - 0.0005f;
		}
		
		//Roof of the House
		red = 1f; green = 0.3f; blue = 0f;
		int [] xPointsRoof = new int[] {50,450,250};
		int [] yPointsRoof = new int[] {500, 500, 400};
		g.setColor(new Color(red, green, blue));

		g.fillPolygon(xPointsRoof, yPointsRoof, 3);	
		g.fillPolygon(xPointsRoof, yPointsRoof, 3);
	}

	public void drawBocho(Graphics g){

		//Car body with mapped points
		g.setColor(new Color(255,0,0));
		int[] xPointsBocho =  {683,665,645,631,614,605,595,584,576,571,566,563,566,559,558,550,550,544,544,552,552,562,562,572,578,582,590,604,615,625,632,639,643,648,660,663,780,780,788,790,792,795,800,808,818,831,842,848,854,860,868,877,890,895,895,906,906,900,900,894,888,885,881,877,881,880,874,868,856,848,816,797,784,770,716};
		int[] yPointsBocho =  {767,801,802,805,809,813,817,824,833,840,854,852,860,863,858,858,851,857,881,881,878,878,887,875,869,860,848,840,840,844,851,863,878,888,888,883,883,890,890,883,871,860,853,844,841,841,847,855,865,876,882,884,886,886,880,880,860,857,852,858,858,853,845,838,838,832,832,820,805,794,769,759,755,754,755};
		g.fillPolygon(xPointsBocho, yPointsBocho, 75);
		g.setColor(new Color(0,0,0));
		g.drawPolygon(xPointsBocho, yPointsBocho, 75);

		//Wheels of the car with different colors for rims
		g.setColor(new Color(0,0,0));
		g.fillOval(581, 842, 58, 59);
		g.fillOval(794, 842, 58, 59);
		g.setColor(new Color(255,255,255));
		g.fillOval(595, 857, 29, 30);
		g.fillOval(809, 857, 29, 30);
		g.setColor(new Color(0,0,0));
		g.fillOval(810, 858, 27, 28);
		g.fillOval(596, 858, 27, 28);
		g.setColor(new Color(255,255,255));
		g.fillOval(814, 862, 19, 20);
		g.fillOval(600, 862, 19, 20);
		
		
		
		//Car details (windows, outlines, etc.)
		int[] xPointsBochoWindow = {744,716,699,691,684,674,743};
		int[] yPointsBochoWindow = {771,772,776,780,784,803,803};
		g.setColor(new Color(0,0,0));
		g.drawLine(666, 875, 666, 800);
		g.drawLine(667, 875, 667, 800);
		g.drawLine(750, 875, 750, 768);
		g.drawLine(751, 875, 751, 768);
		for(int i=0; i<6;i++){
			g.drawLine(666,875+i,780,875+i);
		}
		g.drawLine(666,806,800,806);
		g.drawLine(666,807,800,807);
		g.drawArc(804, 759, 50, 40, 136, 180);
		g.drawArc(662, 765, 130, 116, 70, 90);
		g.drawArc(662, 766, 130, 116, 70, 90);
		g.drawArc(716, 772, 80, 62, 0, 90);
		g.drawLine(743, 815,  735, 815);
		g.drawPolygon(xPointsBochoWindow, yPointsBochoWindow, 7);
		g.setColor(new Color(255,255,255));
		g.fillArc(804, 759, 50, 40, 136, 180);
		g.fillArc(716, 772, 80, 62, 0, 90);
		g.fillPolygon(xPointsBochoWindow, yPointsBochoWindow, 7);
	}

	public void drawDog(Graphics g){
		//Dog body with mapped points
		int[] xPointsDog = {348,345,343,341,341,356,350,350,360,369,385,385,400,395,395,402,402,414,416,415,409,404,403,399,396,394,390,387,376};
		int[] yPointsDog = {851,861,874,880,895,895,890,883,870,870,875,895,895,888,872,861,846,846,842,837,835,828,819,826,818,830,834,844,849};
		g.setColor(new Color(215,157,81));
		g.fillPolygon(xPointsDog, yPointsDog,29);
		//Dog tail
		g.drawArc(335, 815, 40, 40, 180, 70);
		g.drawArc(335, 814, 40, 40, 180, 70);
		g.drawArc(335, 813, 40, 40, 180, 70);
		//Dog eye and nose
		g.setColor(new Color(0,0,0));
		g.fillOval(403, 831, 4, 4);
		g.fillOval(412, 835, 4, 4);
	}
	
	public void drawTree(Graphics g){
		//Tree with random generated leaves with random generated colors and sizes

		//Trunk of the tree
		g.setColor(new Color(88,62,33));
		g.fillRect(1300, 700, 60, 200);
		//Random variables for later use
		int raugmentx, raugmenty,rheight, rwidth,rred,rgreen,rblue;
		Random rand = new Random();
		//Generate random number of leaves that will go on the tree
		int leafnumber = rand.nextInt(50)+20;
		//This oval will cover the top of the trunk in case none of the random leaves cover it(cosmetic purpose)
		g.setColor(new Color(129,218,7));
		g.fillOval(1280, 680, 100, 50);

		//Cicle which will generate random sized and colored leaves in different positions
		for(int i=0;i<leafnumber;i++){
			rred = rand.nextInt(80)+50;
			rgreen = rand.nextInt(114)+86;
			rblue = rand.nextInt(4)+3;
			raugmentx = rand.nextInt(200)-100;
			raugmenty = rand.nextInt(250)-200;
			rheight = rand.nextInt(70)+30;
			rwidth = rand.nextInt(70)+30;
			g.setColor(new Color(rred,rgreen,rblue));
			g.fillOval(1280+raugmentx, 680+raugmenty, rheight, rwidth);
		}
	}
	public static void main(String args[]){
		Main panel = new Main();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(2000, 1000);
		application.setVisible(true);
	}
}