//Juan Carlos Velazco A01326707
//Carlos Parrodi A01421454
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

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
   
   double radioSphere;
   double heightSphere;

   int meridianos;
   int paralelos;
   
   public void setSizes(double radioSphere, double heightSphere){
      this.heightSphere = heightSphere;
      this.radioSphere = radioSphere;
   }
   
   public void setSections(int meridianos, int paralelos){
      this.meridianos = meridianos;
      this.paralelos = paralelos;
   }
   
   public void init() {
      
      vertices = new ArrayList<Point3D>();
      edges = new ArrayList<Edge>();
      
      Point3D starPoint = new Point3D(0, heightCone, 0);
      vertices.add(starPoint);//[0] my topPoint, the next 360 will be my center:
      //insertPointsForBase(360);
      
      canvas = new DisplayPanel();  // Create drawing surface and 
      setContentPane(canvas);       //    install it as the applet's content pane.
      
      canvas.addFocusListener(this);   // Set up the applet to listen for events
      canvas.addKeyListener(this);     //                          from the canvas.
      canvas.addMouseListener(this);
      
   } // end init();
   /*
   public void insertPointsForBase(int numbPoints){
      double angleOfCone = Math.atan(heightCone/radioCone);
      angleOfCone = Math.toDegrees(angleOfCone);
      double currentHeight = heightCone;
      double newRadio;
      double heightForEachSection = heightCone/heightSections;

      for(int i = 0; i < heightSections; i++){
         currentHeight = heightCone - i*heightForEachSection;
         newRadio = currentHeight/Math.tan(Math.toRadians(angleOfCone));

         for(int j = 1; j <= numbPoints; j++){
            vertices.add(new Point3D(newRadio*Math.cos(Math.toRadians(j)), i*heightForEachSection, newRadio*Math.sin(Math.toRadians(j))));
            if(j != 1){
               edges.add(new Edge(((360*i)+j), (360*i)+j+1));
            }
         }
      }
   }*/
   
   
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
            g.drawString("If you didn't insert values for length of X, Y or Z. Or the divisions that you want, this values will be 1 as default.",100,180);
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
            float nearToObj = 0.5f;  // distance from near plane to center of object
            
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
