import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
public class Main extends JPanel{
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//gradient sky
		float red = 0f, green = 1f, blue = 1f; //aqua
		for(int r = 0; r < 1000; r ++){
			for(int c = 0; c < 1950; c++){
				g.setColor(new Color(red, green, blue));
				g.drawLine(c, r, c, r);	
			}	
			red = red + 0.001f;		
		}
		// simple grass
		red = 0f; green = 0.8f; blue = 0f; //1/2 black green
		for(int r = 800; r < 1000; r ++){
			for(int c = 0; c < 1950; c++){
				g.setColor(new Color(red, green, blue));
				g.drawLine(c, r, c, r);	
			}	
		}
		// gradient sun
		red = 1f; green = 0.4f; blue = 0f; //orange
		double xOfSun = 1500;
		double yOfSun = 80;
		for(int depth = 300; depth > 0; depth--){
			g.setColor(new Color(red,green,blue));
			g.fillOval((int)xOfSun, (int)yOfSun, depth, depth);
			xOfSun = xOfSun + 0.5;
			yOfSun =  yOfSun + 0.5;
			green = green + 0.002f;
		}
		
		//gradient house
		// simple house:
		red = 1f; green = 0.85f; blue = 0.7f; //light brown
		for(int r = 500; r < 850; r ++){
			for(int c = 50; c < 450; c++){
				g.setColor(new Color(red, green, blue));
				g.drawLine(c, r, c, r);	
			}		
			blue =  blue + 0.0005f;
		}
		// simple windows:
		red = 0f; green = 0.8f; blue = 1f; //light brown
		for(int r = 600; r < 680; r ++){
			for(int c = 100; c < 180; c++){
				g.setColor(new Color(red, green, blue));
				g.drawLine(c, r, c, r);	
				g.setColor(new Color(red, green, blue));
				g.drawLine(c+220, r, c+220, r);	
			}		
			green =  green - 0.002f;
		}
		
		// door:
		red = 0.5f; green = 0.25f; blue = 0.15f; //brown
		for(int r = 700; r < 850; r ++){
			for(int c = 200; c < 300; c++){
				g.setColor(new Color(red, green, blue));
				g.drawLine(c, r, c, r);	
			}		
			green =  green - 0.0005f;
		}
		/*
		//roof
		red = 1f; green = 0.3f; blue = 0f; //red-orange
		//first values:
		int initialX = 50, initialY = 500, finalX = 250, finalY = 400;
		//final: initialX: 450, initialY: 500, finalX: 450, finalY: 500
		
		for(int c = 0; c < 200; c ++){//the house is 400px long, so 200 will be the middle of the first line
			g.setColor(new Color(red, green, blue));
			g.drawLine(initialX, initialY, finalX, finalY);	
			
			initialX = initialX + 2;
			finalX++;
			if(c % 2 == 0)
			finalY = finalY + 1;	
			
			red =  red - 0.0005f;
		}*/
		red = 1f; green = 0.3f; blue = 0f; //red-orange
		int [] xPointsRoof = new int[] {50,450,250};
		int [] yPointsRoof = new int[] {500, 500, 400};
		g.setColor(new Color(red, green, blue));
		g.fillPolygon(xPointsRoof, yPointsRoof, 3);
		
		g.setColor(new Color(255,0,0));
		int[] xPointsBocho =  {683,665,645,631,614,605,595,584,576,571,566,563,566,559,558,550,550,544,544,552,552,562,562,572,578,582,590,604,615,625,632,639,643,648,660,663,780,780,788,790,792,795,800,808,818,831,842,848,854,860,868,877,890,895,895,906,906,900,900,894,888,885,881,877,881,880,874,868,856,848,816,797,784,770,716};
		int[] yPointsBocho =  {767,801,802,805,809,813,817,824,833,840,854,852,860,863,858,858,851,857,881,881,878,878,887,875,869,860,848,840,840,844,851,863,878,888,888,883,883,890,890,883,871,860,853,844,841,841,847,855,865,876,882,884,886,886,880,880,860,857,852,858,858,853,845,838,838,832,832,820,805,794,769,759,755,754,755};
		g.fillPolygon(xPointsBocho, yPointsBocho, 75);

		g.setColor(new Color(0,0,0));
		g.fillOval(581, 842, 58, 59);
		g.fillOval(794, 842, 58, 59);
		g.setColor(new Color(255,255,255));
		g.fillOval(809, 857, 29, 30);
		g.setColor(new Color(0,0,0));
		g.fillOval(810, 858, 27, 28);
		g.setColor(new Color(255,255,255));
		g.fillOval(814, 862, 19, 20);
		
		g.fillOval(595, 857, 29, 30);
		g.setColor(new Color(0,0,0));
		g.fillOval(596, 858, 27, 28);
		g.setColor(new Color(255,255,255));
		g.fillOval(600, 862, 19, 20);
		

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