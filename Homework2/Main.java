//Juan Carlos Velazco Rossell A01326707
//Carlos Parrodi Martinez A01421454
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Math;

public class Main extends JPanel{
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);	
		double red = 1, green = 1, blue = 0;
		
		for(int r = 0; r < 500; r ++){
			red = 1;
			blue = 0;
			for(int c = 0; c < 500; c++){
				g.setColor(new Color((float)red, (float)green, (float)blue));
				g.drawLine(c, r, c, r);	
				blue = blue + 0.002;
				red = red - 0.002;
			}	
			green = green - 0.002;		
		}
		
		red = 0;
		green = 1;
		blue = 1;

		/*thinking in:
		500    0             1    0
		0      500    ==     0    1
		*/

		int rowCounter = 0;
		for(int r = 500; r > 0; r --){
			red = 0;
			for(int c = 0; c < 500; c ++){
				int difference = Math.abs(r - c); //get the asbsolut difference between both places
				green = difference/500f; //divide between 500 to get its value for 0 to 1
				g.setColor(new Color((float)red, (float)green, (float)blue));
				g.drawLine(c+500, rowCounter, c+500, rowCounter);
				red = red + 0.002;
			}	
			rowCounter++;
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