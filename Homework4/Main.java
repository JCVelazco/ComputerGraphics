import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Math;
import java.util.stream.*;
import java.util.Scanner;
import java.io.File; 

public class Main extends JPanel{
    
    public static int size;
    public static int[] values;
    public static String[] labels;
    
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
        float red = 0f, green = 1f, blue = 1f; //aqua
        System.out.println("Running paintComponent");
        
    }
    
    
    /*
    */
    /*
    int size = 3;
    
    int [] values = new int[] {25,50,25};
    String [] labels = new String[] {"First", "Second", "Third"};
    
    int sum = IntStream.of(values).sum();
    //System.out.println(sum);
    
    double arrPercentages [] = new double[size];
    for(int i =0; i < size; i++){
        arrPercentages[i] = (values[i]*100)/sum;
    }
    
    
    
    double angle = 0;
    double anguloFinal = 0;
    for(int i = 0; i < size; i++){
        g.setColor(new Color(red,green,blue));
        //calculo angulo
        anguloFinal = angle+(arrPercentages[i]*360)/100;
        
        System.out.println(i + ": "+angle+" , "+anguloFinal);
        
        g.fillArc(300, 200, 400, 400, (int)angle, (int)anguloFinal);
        
        angle = anguloFinal;
        green = green - 0.1f;
    }
    
    
    
    
    
    
    */
    
    
    
	public static void readInput() {
        try{
            File file = new File("input.txt"); 
            Scanner sc = new Scanner(file); 
            size = sc.nextInt();
            values = new int[size];
            labels = new String[size];
            for(int i=0; i<size; i++){
                values[i]=sc.nextInt();
            }
            
            for(int i=0; i<size; i++){
                labels[i]=sc.next();
            }
            sc.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }
	
	public static void main(String[] args)
    { 
        readInput();
		Main panel = new Main();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(800, 600);
		application.setVisible(true);
	}
}