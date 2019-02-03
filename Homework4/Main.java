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
        double anguloApetura;
        
        for(int i = 0; i < size; i++){
            randomizeColors();
            g.setColor(new Color(red,green,blue));
            //calculo angulo
            anguloApetura = ((arrPercentages[i]*360.00)/100.00);
            anguloFinal = angle+(arrPercentages[i]*360.00)/100.00;
            g.fillArc(100, 100, 360, 360, (int)angle, (int)anguloApetura);
            paintSquares(g, i);
            paintPercentages(g, angle, anguloFinal, arrPercentages, i);
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
    
    public static void paintSquares(Graphics g, int nElement){
        g.fillRect(550,(nElement*30)+100,20,20);
        g.drawString(labels[nElement], 580, (nElement*30)+110);
    }

    public static void paintPercentages(Graphics g, double initialAngle, double finalAngle, double [] arrPercentages, int nElement){
        String myPercentage = arrPercentages[nElement] + " %";
        g.setColor(new Color(1f,1f,1f));
        //check the 4 "cuadrantes"
        double middleAngle = (initialAngle+finalAngle)/2;
        System.out.println(initialAngle+" "+ finalAngle+" : "+middleAngle);//nice

        if(middleAngle >= 0 && middleAngle <= 90){
            //y-- and x++
            int yMove = (int)middleAngle;
            int xMove = 180 - yMove;
            g.drawString(myPercentage, 280+xMove, 280-yMove);
        }
        if(middleAngle > 90 && middleAngle <= 180){
            //y-- and x--
            //normalize the angle to be from 0 - 90
            middleAngle -= 90;
            int yMove = (int)middleAngle;
            int xMove = 180 - yMove;
            g.drawString(myPercentage, 280-xMove, 280-yMove);
        }
        if(middleAngle > 180 && middleAngle <= 270){
            //y++ and x--
            //normalize the angle to be from 0 - 90
            middleAngle -= 180;
            int yMove = (int)middleAngle ;
            int xMove = 180 - yMove;
            g.drawString(myPercentage, 280-xMove, 280+yMove);
        }
        if(middleAngle > 270 && middleAngle <= 360){
            //y++ and x++
            //normalize the angle to be from 0 - 90
            middleAngle -= 270;
            int yMove = (int)middleAngle;
            int xMove = 180 - yMove;
            g.drawString(myPercentage, 280+xMove, 280+yMove);
        }
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