//Juan Carlos Velazco A01326707
//Carlos Parrodi Martínez A01421454 
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.*;

public class Main extends JPanel{
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
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