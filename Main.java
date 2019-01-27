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
		//house:
		red = 1f; green = 0.85f; blue = 0.7f; //light brown
		for(int r = 400; r < 850; r ++){
			for(int c = 50; c < 450; c++){
				g.setColor(new Color(red, green, blue));
				g.drawLine(c, r, c, r);	
			}		
			blue =  blue + 0.0005f;
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