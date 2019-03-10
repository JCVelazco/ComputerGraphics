//Juan Carlos Velazco A01326707
//Carlos Parrodi A01421454
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Point3D {
   public double x, y, z;
   public Point3D( double x, double y, double z ) {
      this.x = x;  
      this.y = y;  
      this.z = z;
   }
}

class Edge {
   public int a, b;
   public Edge( int A, int B ) {
      a = A;  b = B;
   }
}

public class WireframeJApplet extends JApplet 
                  implements KeyListener, FocusListener, MouseListener {
                      
   int width, height;
   // int mx, my;  // the most recently recorded mouse coordinates

   int azimuth = 35, elevation = 30;
   //rotation y, and x
   
   ArrayList<Point3D> vertices;
   ArrayList<Edge> edges;

   boolean focussed = false;   // True when this applet has input focus.
   
   DisplayPanel canvas;
   //hardcoded values for x and z, y will be the -+ySize max o min value
   double xSize = 6;
   double ySize = 1;
   double zSize = 6;

   public void init() {

      //Primeros cuatro puntos del plano 2x2
      Point3D a = new Point3D( -xSize/2, getRandom(-ySize, ySize), zSize/2 ); 
      Point3D b = new Point3D( -xSize/2, getRandom(-ySize, ySize), -zSize/2 );
      Point3D c = new Point3D( xSize/2, getRandom(-ySize, ySize), -zSize/2 ); 
      Point3D d = new Point3D( xSize/2, getRandom(-ySize, ySize), zSize/2 );
      
      vertices = new ArrayList<Point3D>(); 
      edges = new ArrayList<Edge>();

      Point3D[][] matrix = {{a,d},
                            {b,c}};

      int iterations = 6;
      //Aquí comienza el procedimiento of creating the new matrix
      Point3D[][] finalMatrix = squaredDiamond(matrix, iterations);
      //Una vez teniendo la matriz completa, se manda a formato vertices/edges
      setEdges(finalMatrix);

      canvas = new DisplayPanel();  // Create drawing surface and 
      setContentPane(canvas);       //    install it as the applet's content pane.
   
      canvas.addFocusListener(this);   // Set up the applet to listen for events
      canvas.addKeyListener(this);     //                          from the canvas.
      canvas.addMouseListener(this);
      
   } // end init();

   //Este método controla los pasos del procedimiento
   public Point3D[][] squaredDiamond(Point3D[][] matrix, int iterations){
      Point3D[][] actualMatrix = matrix;
      Point3D[][] newMatrix;

      for(int i=0; i<iterations; i++){
      newMatrix = placeInitialValues(actualMatrix); 
      newMatrix = getCenters(newMatrix,i+1);
      newMatrix = getAdyacent(newMatrix,i+1);
      actualMatrix = newMatrix;
      }
      return actualMatrix;
   }

   //Se crea matriz 2*n-1 y se ingresan los valores de la matriz pasada en sus respectivos lugares
   public Point3D[][] placeInitialValues(Point3D[][] actualMatrix){
      Point3D[][] newMatrix = new Point3D[(actualMatrix[0].length*2)-1][(actualMatrix[0].length*2)-1];
      for(int i=0; i<actualMatrix[0].length;i++){
         for(int j=0; j<actualMatrix[0].length;j++){
            newMatrix[i*2][j*2] = actualMatrix[i][j]; 
         }
      }
      return newMatrix;
   }
   
   //Se calculan los centros de la matriz
   public Point3D[][] getCenters(Point3D[][] matrix, int times){
      for(int i=0; i<matrix[0].length/2;i++){
         for(int j=0; j<matrix[0].length/2;j++){
            int newi = (i*2)+1, newj= (j*2)+1;
            double averagex = (matrix[newi-1][newj-1].x + matrix[newi+1][newj+1].x)/2;
            double averagez = (matrix[newi-1][newj-1].z + matrix[newi+1][newj+1].z)/2;
            //we calculate 4 with 4 values because all the y's that surround it are totally different
            double averagey = (matrix[newi-1][newj-1].y + matrix[newi+1][newj+1].y + matrix[newi+1][newj-1].y + matrix[newi-1][newj+1].y)/4;
            //Aquí se calcula el delta y times es el smoother
            averagey += (getRandom(-1, 1))/times;
            matrix[newi][newj] = new Point3D(averagex,averagey,averagez); 
         }
      }

      return matrix;
   }

   //Se calculan todos los espacios adyacentes restantes
   public Point3D[][] getAdyacent(Point3D[][] matrix, int times){
      double x,averagey,z;
      for(int i=0; i<matrix[0].length;i++){
         for(int j=0; j<matrix[0].length;j++){
            //if there is no value...
            if(matrix[i][j] == null){
               //if i am in the first row (i can't i-1)
               if(i==0){
                  x = matrix[i+1][j].x;
                  z = matrix[i][j+1].z;
                  averagey = (matrix[i][j+1].y+matrix[i][j-1].y+matrix[i+1][j].y)/3;
               //if i am in the first col (i can't j-1)
               }else if(j==0){
                  x = matrix[i+1][j].x;
                  z = matrix[i][j+1].z;
                  averagey = (matrix[i][j+1].y+matrix[i-1][j].y+matrix[i+1][j].y)/3;
               //if i am in the last row (i can't i+1)
               }else if(i==matrix[0].length-1){
                  x = matrix[i-1][j].x;
                  z = matrix[i][j+1].z;
                  averagey = (matrix[i][j+1].y+matrix[i][j-1].y+matrix[i-1][j].y)/3;
               //if i am in the last col (i can't j+1)
               }else if(j==matrix[0].length-1){
                  x = matrix[i+1][j].x;
                  z = matrix[i][j-1].z;
                  averagey = (matrix[i][j-1].y+matrix[i-1][j].y+matrix[i+1][j].y)/3;
               //middle cases
               }else{
                  x = matrix[i+1][j].x;
                  z = matrix[i][j+1].z;
                  averagey = (matrix[i][j+1].y+matrix[i][j-1].y+matrix[i+1][j].y+matrix[i-1][j].y)/4;
               }
               //times as smother
               averagey += getRandom(-1,1)/times;
               matrix[i][j]= new Point3D(x, averagey, z);
            }
         }
      }
      return matrix;
   }
   //Aquí se ingresan los vertices y se crean los edges para enviar a dibujar
   public void setEdges(Point3D[][] matrix){
      //add all the points of the matrix in the array
      for(int i=0; i<matrix[0].length;i++){
         for(int j=0; j<matrix[0].length;j++){
            vertices.add(matrix[i][j]);
         }
      }
      int count = (int)Math.sqrt(vertices.size());
      
      for(int k=0; k<vertices.size(); k++){
         if(k-count>= 0 ){
            edges.add(new Edge(k-count,k ));
         }
         if(k-1>=0 && k%count !=0){
            edges.add(new Edge(k-1, k));
         }
         if(k+1<vertices.size() && (k+1)%count !=0 ){
            edges.add(new Edge(k, k+1));
         }
         if(k+count<vertices.size()){
            edges.add(new Edge(k, k+count));
         }
      }
   }

   //Método para debugear que imprime la matriz
   public void printMatrix(Point3D[][] matrix){
      for(int i=0; i<matrix[0].length;i++){
         for(int j=0; j<matrix[0].length;j++){
            System.out.print(matrix[i][j].x + "  ");
         }
         System.out.println();
      }
   }

   //Devuelve un valor random
   public static double getRandom(double min, double max){
		return ThreadLocalRandom.current().nextDouble(min, max);
   } 

   
   class DisplayPanel extends JPanel {
      public void paintComponent(Graphics g) {
         super.paintComponent(g);  
         if (focussed) 
            g.setColor(Color.cyan);
         else
            g.setColor(Color.lightGray);

         int width = getSize().width;  // Width of the applet.
         int height = getSize().height; // Height of the applet.
         g.drawRect(0,0,width-1,height-1);
         g.drawRect(1,1,width-3,height-3);
         g.drawRect(2,2,width-5,height-5);

         if (!focussed) {
            g.setColor(Color.magenta);
            g.drawString("Click to activate",100,120);
            g.drawString("Use arrow keys to change azimuth and elevation",100,150);
            g.drawString("Landscape by JC and Parrodi",100,180);
         }
         else {
            double theta = Math.toRadians(azimuth);
            double phi = Math.toRadians(elevation);
            
            float cosT = (float)Math.cos( theta ), sinT = (float)Math.sin( theta );
            float cosP = (float)Math.cos( phi ), sinP = (float)Math.sin( phi );
            
            float cosTcosP = cosT*cosP, cosTsinP = cosT*sinP,
                  sinTcosP = sinT*cosP, sinTsinP = sinT*sinP;

            // project vertices onto the 2D viewport
            Point[] points;
            points = new Point[ vertices.size() ];
            int j;
            int scaleFactor = width/8;
            float near = 3;  // distance from eye to near plane
            float nearToObj = 3f;  // distance from near plane to center of object

            //point3D -> point2D
            for ( j = 0; j < vertices.size(); ++j ) {
               double x0 = vertices.get(j).x;
               double y0 = vertices.get(j).y;
               double z0 = vertices.get(j).z;

               // compute an orthographic projection
               double x1 = cosT*x0 + sinT*z0;
               double y1 = -sinTsinP*x0 + cosP*y0 + cosTsinP*z0;
               double z1 = cosTcosP*z0 - sinTcosP*x0 - sinP*y0;

               // now adjust things to get a perspective projection
               x1 = x1*near/(z1+near+nearToObj);
               y1 = y1*near/(z1+near+nearToObj);

               // the 0.5 is to round off when converting to int
               points[j] = new Point(
                  (int)(width/2 + scaleFactor*x1 + 0.5),
                  (int)(height/2 - scaleFactor*y1 + 0.5)
               );
            }

            // draw the wireframe
            g.setColor( Color.black );
            g.fillRect( 0, 0, width, height );
            g.setColor( Color.white );
            

            for ( j = 0; j < edges.size(); ++j ) {

               //Aquí se calculan los colores dependiendo de la altura en 'y', tomando en cuenta que el centro de 'y' es 400
               /*if((points[ (edges.get(j).a) ].y + points[ (edges.get(j).b) ].y)/2 < 350)
               g.setColor( Color.GREEN );
            else if((points[ (edges.get(j).a) ].y + points[ (edges.get(j).b) ].y)/2 > 450)
               g.setColor( Color.BLUE );
            else 
               g.setColor(new Color(101, 67, 33));*/

               g.drawLine(
                  points[ (edges.get(j).a) ].x, points[ (edges.get(j).a) ].y,
                  points[ (edges.get(j).b) ].x, points[ (edges.get(j).b) ].y
               );
            }

         } 
      }  // end paintComponent()    
    } // end nested class DisplayPanel 

   // ------------------- Event handling methods ----------------------
   
   public void focusGained(FocusEvent evt) {
      focussed = true;
      canvas.repaint();
   }
   
   public void focusLost(FocusEvent evt) {
      focussed = false;
      canvas.repaint(); 
   }
      
   public void keyTyped(KeyEvent evt) {  
   }  // end keyTyped()
      
   public void keyPressed(KeyEvent evt) { 
       
      int key = evt.getKeyCode();  // keyboard code for the key that was pressed
      
      if (key == KeyEvent.VK_LEFT) {
         azimuth += 5;
         canvas.repaint();         
      }
      else if (key == KeyEvent.VK_RIGHT) {
         azimuth -= 5;
         canvas.repaint();         
      }
      else if (key == KeyEvent.VK_UP) {
         elevation -= 5;
         canvas.repaint();         
      }
      else if (key == KeyEvent.VK_DOWN) {
         elevation += 5;         
         canvas.repaint();
      }

   }  // end keyPressed()

   public void keyReleased(KeyEvent evt) { 
      // empty method, required by the KeyListener Interface
   }
   
   public void mousePressed(MouseEvent evt) {      
      canvas.requestFocus();
   }      
   
   public void mouseEntered(MouseEvent evt) { }  // Required by the
   public void mouseExited(MouseEvent evt) { }   //    MouseListener
   public void mouseReleased(MouseEvent evt) { } //       interface.
   public void mouseClicked(MouseEvent evt) { }
   public void mouseDragged( MouseEvent e ) { }
} // end class 
