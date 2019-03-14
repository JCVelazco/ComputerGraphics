//Juan Carlos Velazco A01326707
//Carlos Parrodi A01421454
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

class Point3D {
   public double x, y, z, newz;
   public Point3D( double x, double y, double z) {
      this.x = x;  
      this.y = y;  
      this.z = z;
      this.newz = z;
   }

   public void setNewZ(double newz){
      this.newz = newz;
   }
}

class Edge {
   public int a, b;
   public Edge( int A, int B ) {
      a = A;  b = B;
   }
}

class Face implements Comparable<Face>{
   public double avg;
   public int a, b, c, d;
   public Color color;
   public Face(int a, int b, int c, int d){
      this.a = a;
      this.b = b;
      this.c = c;
      this.d = d;
   }
   public void setAvg(double avg){
      this.avg = avg;
   }
   public void setColor(int red, int green, int blue){
      this.color = new Color(red,green,blue);
   }

   @Override
   public int compareTo(Face face) {
       return (int)(this.avg - face.avg);
   }
}

public class WireframeJApplet extends JApplet 
implements KeyListener, FocusListener, MouseListener {
   
   int width, height;
   // int mx, my;  // the most recently recorded mouse coordinates
   
   int azimuth = 0, elevation = 0;
   //rotation y, and x
   
   ArrayList<Point3D> vertices;
   ArrayList<Edge> edges;
   ArrayList<Face> faces;
   
   
   boolean focussed = false;   // True when this applet has input focus.
   
   DisplayPanel canvas;
   
   //Color alpha = new Color(0.5, 0.5, 0.5, 0.2);
   
   public void init() {
      
      vertices = new ArrayList<Point3D>();
      edges = new ArrayList<Edge>();
      faces = new ArrayList<Face>();
      
      //all the base
      vertices.add(new Point3D( -1.5, -1, 0.5 ));//1
      vertices.add(new Point3D( -1.5, -1, -0.5 )); //2
      vertices.add(new Point3D( -0.5, -1, -0.5 )); //3
      vertices.add(new Point3D( -0.5, -1, 0.5 ));//4
      vertices.add(new Point3D( 0.5, -1, 0.5 )); //5
      vertices.add(new Point3D( 0.5, -1, -0.5 )); //6
      vertices.add(new Point3D( 1.5, -1, -0.5 )); //7
      vertices.add(new Point3D( 1.5, -1, 0.5 ));//8
      
      //upper base
      vertices.add(new Point3D( -1.5,0, 0.5 ));//9
      vertices.add(new Point3D( -1.5, 0, -0.5 )); //10
      vertices.add(new Point3D( -0.5, 0, -0.5 )); //11
      vertices.add(new Point3D( -0.5, 0, 0.5 ));//12
      vertices.add(new Point3D( 0.5, 0, 0.5 )); //13
      vertices.add(new Point3D( 0.5, 0, -0.5 )); //14
      vertices.add(new Point3D( 1.5, 0, -0.5 )); //15
      vertices.add(new Point3D( 1.5, 0,0.5 ));//16
      
      //upper upper base
      vertices.add(new Point3D( 0.5, 1, 0.5 )); //17
      vertices.add(new Point3D( 0.5, 1, -0.5 )); //18
      vertices.add(new Point3D( 1.5, 1, -0.5 )); //19
      vertices.add(new Point3D( 1.5, 1,0.5 ));//20
      
      
      //makes the main edges of the box, according to the vertices
      edges = new ArrayList<Edge>();
      //firstFace
      edges.add(new Edge( 0, 1 ));
      edges.add(new Edge( 1, 2 ));
      edges.add(new Edge( 2, 3 ));
      edges.add(new Edge( 3, 0 ));
      faces.add(new Face(0, 1, 2, 3));
      faces.get(0).setColor(255, 0, 0);
      //secondFace
      edges.add(new Edge( 2, 3 ));
      edges.add(new Edge( 3, 4 ));
      edges.add(new Edge( 4, 5 ));
      edges.add(new Edge( 5, 2 ));
      faces.add(new Face(2, 3, 4, 5));
      faces.get(1).setColor(0, 255, 0);
      //thirdFace
      edges.add(new Edge( 4, 5 ));
      edges.add(new Edge( 5, 6 ));
      edges.add(new Edge( 6, 7 ));
      edges.add(new Edge( 7, 4 ));
      faces.add(new Face(4, 5, 6, 7));
      faces.get(2).setColor(0, 0, 255);
      
      //fourFace (left side)
      edges.add(new Edge( 0, 1 ));
      edges.add(new Edge( 1, 9 ));
      edges.add(new Edge( 9, 8 ));
      edges.add(new Edge( 8, 0 ));
      faces.add(new Face(0, 1, 9, 8 ));
      faces.get(3).setColor(0, 255, 255);
      //fiveFace (rs)
      edges.add(new Edge( 7, 6 ));
      edges.add(new Edge( 6, 14 ));
      edges.add(new Edge( 14, 15 ));
      edges.add(new Edge( 15, 7 ));
      faces.add(new Face(7, 6, 14, 15));
      faces.get(4).setColor(255, 0, 255);
      //sixFace
      edges.add(new Edge( 0, 8 ));
      edges.add(new Edge( 8, 11 ));
      edges.add(new Edge( 11, 3 ));
      edges.add(new Edge( 3, 0 ));
      faces.add(new Face(0, 8, 11, 3));
      faces.get(5).setColor(100, 100, 255);
      //sevenFace
      edges.add(new Edge( 3, 11 ));
      edges.add(new Edge( 11, 12 ));
      edges.add(new Edge( 12, 4 ));
      edges.add(new Edge( 4, 3 ));
      faces.add(new Face(3, 11, 12, 4));
      faces.get(6).setColor(255, 50, 255);
      //8Face
      edges.add(new Edge( 4, 12 ));
      edges.add(new Edge( 12, 15 ));
      edges.add(new Edge( 15, 7 ));
      edges.add(new Edge( 7, 4 ));
      faces.add(new Face(4, 12, 15, 7));
      faces.get(7).setColor(100, 0, 255);
      //9Face
      edges.add(new Edge( 1, 9 ));
      edges.add(new Edge( 9, 10 ));
      edges.add(new Edge( 10, 2 ));
      edges.add(new Edge( 2, 1));
      faces.add(new Face(1, 9, 10, 2));
      faces.get(8).setColor(20, 200, 255);
      //10Face
      edges.add(new Edge( 2, 10 ));
      edges.add(new Edge( 10, 13 ));
      edges.add(new Edge( 13, 5 ));
      edges.add(new Edge( 5, 2 ));
      faces.add(new Face(2, 10, 13, 5));
      faces.get(9).setColor(255, 0, 100);
      //11Face
      edges.add(new Edge( 5, 13 ));
      edges.add(new Edge( 13, 14 ));
      edges.add(new Edge( 14, 6 ));
      edges.add(new Edge( 6, 5 ));
      faces.add(new Face(5, 13, 14, 6));
      faces.get(10).setColor(255, 60, 100);
      //12Face
      edges.add(new Edge( 8, 9 ));
      edges.add(new Edge( 9, 10 ));
      edges.add(new Edge( 10, 11 ));
      edges.add(new Edge( 11, 8 ));
      faces.add(new Face(8, 9, 10, 11));
      faces.get(11).setColor(255, 100, 0);
      //13Face
      edges.add(new Edge( 11, 10 ));
      edges.add(new Edge( 10, 13 ));
      edges.add(new Edge( 13, 12 ));
      edges.add(new Edge( 12, 11 ));
      faces.add(new Face(11, 10, 13, 12));
      faces.get(12).setColor(255, 0, 0);
      //14Face
      edges.add(new Edge( 12, 13 ));
      edges.add(new Edge( 13, 17 ));
      edges.add(new Edge( 17, 16 ));
      edges.add(new Edge( 16, 12 ));
      faces.add(new Face(12, 13, 17, 16));
      faces.get(13).setColor(0, 0, 200);
      //15Face
      edges.add(new Edge( 15, 19 ));
      edges.add(new Edge( 19, 18 ));
      edges.add(new Edge( 18, 14 ));
      edges.add(new Edge( 14, 15 ));
      faces.add(new Face(15, 19, 18, 14));
      faces.get(14).setColor(0, 200, 100);
      //16Face
      edges.add(new Edge( 12, 16 ));
      edges.add(new Edge( 16, 19 ));
      edges.add(new Edge( 19, 15 ));
      edges.add(new Edge( 15, 12 ));
      faces.add(new Face(12, 16, 19, 15));
      faces.get(15).setColor(2, 40, 200);
      //17Face
      edges.add(new Edge( 13, 17 ));
      edges.add(new Edge( 17, 18 ));
      edges.add(new Edge( 18, 14 ));
      edges.add(new Edge( 14, 13 ));
      faces.add(new Face(13, 17, 18, 14));
      faces.get(16).setColor(0, 255, 100);
      //17Face
      edges.add(new Edge( 16, 17 ));
      edges.add(new Edge( 17, 18 ));
      edges.add(new Edge( 18, 19 ));
      edges.add(new Edge( 19, 16 ));
      faces.add(new Face(16, 17, 18, 19));
      faces.get(17).setColor(255, 255, 255);
      
      canvas = new DisplayPanel();  // Create drawing surface and 
      setContentPane(canvas);       //    install it as the applet's content pane.
      
      canvas.addFocusListener(this);   // Set up the applet to listen for events
      canvas.addKeyListener(this);     //                          from the canvas.
      canvas.addMouseListener(this);
      
   } // end init();
   
   public void getAvg(){
      double avg;
      for(Face face: faces){
         avg = ((vertices.get(face.a).newz)+(vertices.get(face.b).newz)+(vertices.get(face.c).newz)+(vertices.get(face.d).newz))/4;
         face.setAvg(avg);
      }

      Collections.sort(faces, new Comparator<Face>() {
         @Override
         public int compare(Face f1, Face f2) {
             return Double.compare(f1.avg, f2.avg);
         }
     });

	   for(Face face: faces){
			System.out.println(face.avg);
	   }
      
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
            float nearToObj = 1f;  // distance from near plane to center of object
            
            //point3D -> point2D
            for ( j = 0; j < vertices.size(); ++j ) {
               double x0 = vertices.get(j).x;
               double y0 = vertices.get(j).y;
               double z0 = vertices.get(j).z;
               
               // compute an orthographic projection
               double x1 = cosT*x0 + sinT*z0;
               double y1 = -sinTsinP*x0 + cosP*y0 + cosTsinP*z0;
               double z1 = cosTcosP*z0 - sinTcosP*x0 - sinP*y0;
               vertices.get(j).setNewZ(z1);
               getAvg();
               
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
            
            for ( j = faces.size()-1; j > -1; --j ) {
               g.setColor(faces.get(j).color);
               int[] xPoints = {points[ (faces.get(j).a) ].x,points[ (faces.get(j).b) ].x,points[ (faces.get(j).c) ].x, points[ (faces.get(j).d) ].x};
               int[] yPoints = {points[ (faces.get(j).a) ].y,points[ (faces.get(j).b) ].y,points[ (faces.get(j).c) ].y, points[ (faces.get(j).d) ].y};
               g.fillPolygon(xPoints, yPoints, 4);
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
