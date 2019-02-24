//Juan Carlos Velazco Rossell A01326707
//Carlos Parrodi Martinez A01421454
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.lang.Math;
import java.util.Scanner;
import java.io.File; 
import java.text.DecimalFormat;
public class GraphHW extends JPanel{
    
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
    public static double xBarPosition;
    public static DecimalFormat df=new DecimalFormat("0.0");
    
	public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintGraphs(g); 
    }
    
    public static void paintGraphs(Graphics g){
        double sum = 0;
        xBarPosition = 380;
        angle = 0;
        for(int i = 0 ; i < size ; i++)
        sum += values[i];
        
        double arrPercentages [] = new double[(int)size];
        for(int i =0; i < size; i++){
            arrPercentages[i] = (values[i]*100)/sum;
        }

        //the total length is 560px, we ill not use the 50px at each corner
        int totalXSpace = 500;
        totalXSpace = totalXSpace - 5 - (5)*(values.length);
        barLength = totalXSpace/values.length;
        
        for(int i = 0; i < size; i++){
            paintSquareForBarGraph(g);
            randomizeColors();
            g.setColor(new Color(red,green,blue));
            paintPieGraph(g, arrPercentages, i);
            paintBarGraph(g, arrPercentages, i);
            paintSquares(g, i);
            angle = anguloFinal;
        }
        
    }

    public static void paintPieGraph(Graphics g, double [] arrPercentages, int nElement){
        double anguloApetura = ((arrPercentages[nElement]*360.00)/100.00);
        anguloFinal = angle+(arrPercentages[nElement]*360.00)/100.00;
        g.fillArc(10, 40, 360, 360, (int)angle, (int)anguloApetura);
        //putting the % in the pie chart:
        String percentageMessage = df.format(arrPercentages[nElement])+"%";
        double angleTotal = (angle + (angle+anguloApetura))/2;
        double moveOnX = 90*(Math.cos(Math.toRadians(angleTotal)));
        double moveOnY = 90*(Math.sin(Math.toRadians(angleTotal)));
        //add the coordenates where the radio of the circle is:
        Color rememberColor = new Color(red, green, blue);
        moveOnX = 180 + Math.round(moveOnX);
        moveOnY = 220 - Math.round(moveOnY);
        g.setColor(new Color(0,0,0));
        g.drawString(percentageMessage, (int)(moveOnX), (int)(moveOnY));
        g.setColor(rememberColor);
        angle = anguloFinal;
    }

    
    public static void paintBarGraph(Graphics g, double [] arrPercentages, int nElement){
        xBarPosition += 5;
        //calculate height of the bar (400 start of the bar)
        double heightBar = 380-((300*arrPercentages[nElement])/100);
        String percentageMessage = df.format(arrPercentages[nElement])+"%";
        int finalX = (int)(xBarPosition+barLength);
        int [] xArray = new int []{(int)xBarPosition, (int)xBarPosition, finalX, finalX};
        int [] yArray = new int []{(int)heightBar, 400, 400, (int)heightBar};
        g.fillPolygon(xArray, yArray, 4);
        g.drawString(percentageMessage, (int)(xBarPosition+(barLength/2.5)), (int)(heightBar-10));
        xBarPosition += barLength;
    }

    public static void paintSquareForBarGraph(Graphics g){
        g.setColor(new Color(0.7f, 0.7f, 0.7f));
        g.drawPolygon(new int[] {380,380,880,880}, new int[] {50, 400, 400,50}, 4);
    }
    
    public static void randomizeColors(){
        red = (float)Math.random();
        green = (float)Math.random();
        blue = (float)Math.random();
    }
    
    public static void paintSquares(Graphics g, int nElement){
        g.fillRect(890,(nElement*30)+20,20,20);
        g.drawString(labels[nElement], 920, (nElement*30)+35);
    }
    
    //for manually adding the txt
	public static void readInput() {
        try{
            /*the format is:
            n (number of elements)
            (n values)
            (n labels)
            */
            Scanner sc = new Scanner(System.in); 
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
		GraphHW panel = new GraphHW();
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.add(panel);
		application.setSize(1000, 500);
		application.setVisible(true);
	}
}