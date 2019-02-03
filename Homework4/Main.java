import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Math;
import java.util.Scanner;
import java.io.File; 
import java.util.Random;

public class Main extends JPanel{
    
    //we make them public variables because they will be used in the paintComponent
    public static double size;
    public static double[] values;
    public static String[] labels;
    
    //colors
    public static float red, green, blue;
    
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintPieChart(g);
        paintBarGraph(g);  
    }
    
    public static void paintPieChart(Graphics g){
        double sum = 0;
        
        for(int i = 0 ; i < size ; i++)
        sum += values[i];
        
        double arrPercentages [] = new double[(int)size];
        for(int i =0; i < size; i++){
            arrPercentages[i] = (values[i]*100)/sum;
        }
        
        double angle = 0;
        double anguloFinal = 0;
        
        for(int i = 0; i < size; i++){
            randomizeColors();
            g.setColor(new Color(red,green,blue));
            //calculo angulo
            anguloFinal = angle+(arrPercentages[i]*360.00)/100.00;
            
            g.fillArc(100, 100, 400, 400, (int)angle, (int)((arrPercentages[i]*360.00)/100.00));
            
            angle = anguloFinal;
        }
        
    }
    
    public static void paintBarGraph(Graphics g){
        
    }
    
    public static void randomizeColors(){
        red = (float)Math.random();
        green = (float)Math.random();
        blue = (float)Math.random();
    }
    
    
    
	public static void readInput() {
        try{
            /*the format is:
            n (number of elements)
            (n values)
            (n labels)
            */
            File file = new File("input.txt"); 
            Scanner sc = new Scanner(file); 
            size = sc.nextInt();
            values = new double[(int)size];
            labels = new String[(int)size];
            for(int i=0; i<size; i++){
                values[i]=sc.nextInt();
            }
            //using next() isntead of nextLine, because we dont want to read the first \n
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
        //try to find a input.txt and read it
        readInput();
		Main panel = new Main();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(800, 600);
		application.setVisible(true);
	}
}