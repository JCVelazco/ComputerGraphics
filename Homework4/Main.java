import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Math;
import java.util.Scanner;
import java.io.File; 
public class Main extends JPanel{
    
    //we make them public variables because they will be used in the paintComponent
    public static double size;
    public static double[] values;
    public static String[] labels;
    
    //colors
    public static float red, green, blue;

    //angles
    public static double angle;
    public static double anguloFinal;

    public static double barLength;
    public static double xBarPosition = 700;
    
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintGraphs(g); 
    }
    
    public static void paintGraphs(Graphics g){
        double sum = 0;
        xBarPosition = 700;
        for(int i = 0 ; i < size ; i++)
        sum += values[i];
        
        double arrPercentages [] = new double[(int)size];
        for(int i =0; i < size; i++){
            arrPercentages[i] = (values[i]*100)/sum;
        }

        //the total length is 600px, we ill not use the 50px at each corner
        int totalXSpace = 600;
        totalXSpace = totalXSpace - 25 - (25)*(values.length);
        barLength = totalXSpace/values.length;
        
        for(int i = 0; i < size; i++){
            paintSquareForBarGraph(g);
            randomizeColors();
            g.setColor(new Color(red,green,blue));
            paintPieGraph(g, arrPercentages, i);
            paintBarGraph(g, arrPercentages, i);
            paintSquares(g, i);
            //paintPercentages(g, angle, anguloFinal, arrPercentages, i);
            angle = anguloFinal;
        }
        
    }

    public static void paintPieGraph(Graphics g, double [] arrPercentages, int nElement){
        double anguloApetura = ((arrPercentages[nElement]*360.00)/100.00);
        anguloFinal = angle+(arrPercentages[nElement]*360.00)/100.00;
        g.fillArc(100, 100, 360, 360, (int)angle, (int)anguloApetura);
        angle = anguloFinal;
    }

    
    public static void paintBarGraph(Graphics g, double [] arrPercentages, int nElement){
        xBarPosition += 25;
        //calculate height of the bar
        double heightBar = 300-((300*arrPercentages[nElement])/100);
        System.out.println(arrPercentages[nElement]+" "+(300-heightBar));
        int finalX = (int)(xBarPosition+barLength);
        int [] xArray = new int []{(int)xBarPosition, (int)xBarPosition, finalX, finalX};
        int [] yArray = new int []{(int)heightBar, 400, 400, (int)heightBar};
        g.fillPolygon(xArray, yArray, 4);
        xBarPosition += barLength;
    }

    public static void paintSquareForBarGraph(Graphics g){
        g.setColor(new Color(0.7f, 0.7f, 0.7f));
        g.drawPolygon(new int[] {700,700,1300,1300}, new int[] {100, 400, 400,100}, 4);
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
        /* NOT TOTALLY WORKING
        String myPercentage = arrPercentages[nElement] + " %";
        g.setColor(new Color(1f,1f,1f));
        //check the 4 "cuadrantes"
        double middleAngle = (initialAngle+finalAngle)/2;

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
            middleAngle = 90 - middleAngle;

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
        }*/
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