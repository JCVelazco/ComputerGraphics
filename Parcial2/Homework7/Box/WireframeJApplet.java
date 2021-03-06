//Juan Carlos Velazco A01326707
//Carlos Parrodi A01421454
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.Arrays;

class Point3D {
   public double x, y, z;
   public Point3D( double x, double y, double z ) {
      this.x = x;  
      this.y = y;  
      this.z = z;
   }
}

class Face {
   public Point3D a,b,c,d;
   public Face(Point3D a, Point3D b, Point3D c, Point3D d) {
      this.a = a;  
      this.b = b;  
      this.c = c;
      this.d = d;
   }
}

class FaceAv implements Comparable<FaceAv> {
   public int index;
   public double avg;
   public FaceAv(int index, double avg) {
      this.index = index;  
      this.avg = avg;  
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
   Point3D a = new Point3D(1,2,3);
   Point3D b = new Point3D(4,5,6);
   Point3D c = new Point3D(7,8,9);
   Point3D d = new Point3D(10,11,12);
   Face myface = new Face(a,b,c,d);
   double myavg = zAvg(myface);
   ArrayList<Point3D> vertices;
   ArrayList<Edge> edges;

   boolean focussed = false;   // True when this applet has input focus.
   
   DisplayPanel canvas;
   
   double xSize;
   double ySize;
   double zSize;
   
   int xSections;
   int ySections;
   int zSections;

   public void setSizes(double xSize, double ySize, double zSize){
      this.xSize = xSize;
      this.ySize = ySize;
      this.zSize = zSize;
   }

   public void setSections(int xSections, int ySections, int zSections){
      this.xSections = xSections;
      this.ySections = ySections;
      this.zSections = zSections;
   }

   public void init() {
      ArrayList<FaceAv> thisavg = new ArrayList<FaceAv>();
      thisavg.add(new FaceAv(1,2.5)); 
      thisavg.add(new FaceAv(2,0.3));
      thisavg.add(new FaceAv(3,5.7));
      Collections.sort(thisavg);
      for(FaceAv face : thisavg){
         System.out.println(face);
      }

      vertices = new ArrayList<Point3D>();
      //origin
      vertices.add(new Point3D( -xSize/2, -ySize/2, -zSize/2 )); //(point: first face, left down)
      vertices.add(new Point3D( -xSize/2, -ySize/2,  zSize/2 )); //(point: face behind, left down)
      vertices.add(new Point3D( -xSize/2,  ySize/2, -zSize/2 )); //(point: first face, left top)
      vertices.add(new Point3D( -xSize/2,  ySize/2,  zSize/2 ));////(point: face behind, left top)
      //same that the ones above but with move in xSize:
      vertices.add(new Point3D(  xSize/2, -ySize/2, -zSize/2 ));
      vertices.add(new Point3D(  xSize/2, -ySize/2,  zSize/2 ));
      vertices.add(new Point3D(  xSize/2,  ySize/2, -zSize/2 ));
      vertices.add(new Point3D(  xSize/2,  ySize/2,  zSize/2 ));

      //makes the main edges of the box, according to the vertices
      edges = new ArrayList<Edge>();
      edges.add(new Edge( 0, 1 ));
      edges.add(new Edge( 0, 2 ));
      edges.add(new Edge( 0, 4 ));
      edges.add(new Edge( 1, 3 ));
      edges.add(new Edge( 1, 5 ));
      edges.add(new Edge( 2, 3 ));
      edges.add(new Edge( 2, 6 ));
      edges.add(new Edge( 3, 7 ));
      edges.add(new Edge( 4, 5 ));
      edges.add(new Edge( 4, 6 ));
      edges.add(new Edge( 5, 7 ));
      edges.add(new Edge( 6, 7 ));
      
      addLayersForX();
      addLayersForY();
      addLayersForZ();
      
      canvas = new DisplayPanel();  // Create drawing surface and 
      setContentPane(canvas);       //    install it as the applet's content pane.
   
      canvas.addFocusListener(this);   // Set up the applet to listen for events
      canvas.addKeyListener(this);     //                          from the canvas.
      canvas.addMouseListener(this);
      
   } // end init();

   public void addLayersForX(){
      if(xSections == 1)return;
      double distanceForEachSection = xSize/xSections;
      for(int i = 1; i < xSections; i++){
         Point3D newPoint0 = new Point3D((vertices.get(0).x)+distanceForEachSection*i, vertices.get(0).y, vertices.get(0).z);
         Point3D newPoint1 = new Point3D((vertices.get(1).x)+distanceForEachSection*i, vertices.get(1).y, vertices.get(1).z);
         Point3D newPoint2 = new Point3D((vertices.get(3).x)+distanceForEachSection*i, vertices.get(3).y, vertices.get(3).z);
         Point3D newPoint3 = new Point3D((vertices.get(2).x)+distanceForEachSection*i, vertices.get(2).y, vertices.get(2).z);
         vertices.add(newPoint0); vertices.add(newPoint1); vertices.add(newPoint2); vertices.add(newPoint3);
         //get first index of this new subdivision:
         int indexOfStart = vertices.indexOf(newPoint0);
         Edge edge0To1 = new Edge(indexOfStart, indexOfStart+1);
         Edge edge1To3 = new Edge(indexOfStart+1, indexOfStart+2);
         Edge edge3To2 = new Edge(indexOfStart+2, indexOfStart+3);
         Edge edge2To0 = new Edge(indexOfStart+3, indexOfStart);
         edges.add(edge0To1);edges.add(edge1To3);edges.add(edge3To2);edges.add(edge2To0);
      }
   }

   public void addLayersForY(){
      if(ySections == 1)return;
      double distanceForEachSection = ySize/ySections;
      for(int i = 1; i < ySections; i++){
         Point3D newPoint0 = new Point3D(vertices.get(0).x, (vertices.get(0).y)+distanceForEachSection*i, vertices.get(0).z);
         Point3D newPoint1 = new Point3D(vertices.get(1).x, (vertices.get(1).y)+distanceForEachSection*i, vertices.get(1).z);
         Point3D newPoint2 = new Point3D(vertices.get(5).x, (vertices.get(5).y)+distanceForEachSection*i, vertices.get(5).z);
         Point3D newPoint3 = new Point3D(vertices.get(4).x, (vertices.get(4).y)+distanceForEachSection*i, vertices.get(4).z);
         vertices.add(newPoint0); vertices.add(newPoint1); vertices.add(newPoint2); vertices.add(newPoint3);
         //get first index of this new subdivision:
         int indexOfStart = vertices.indexOf(newPoint0);
         Edge edge0To1 = new Edge(indexOfStart, indexOfStart+1);
         Edge edge1To3 = new Edge(indexOfStart+1, indexOfStart+2);
         Edge edge3To2 = new Edge(indexOfStart+2, indexOfStart+3);
         Edge edge2To0 = new Edge(indexOfStart+3, indexOfStart);
         edges.add(edge0To1);edges.add(edge1To3);edges.add(edge3To2);edges.add(edge2To0);
      }
   }

   public void addLayersForZ(){
      if(zSections == 1)return;
      double distanceForEachSection = zSize/zSections;
      for(int i = 1; i < zSections; i++){
         Point3D newPoint0 = new Point3D(vertices.get(0).x, (vertices.get(0).y), (vertices.get(0).z)+distanceForEachSection*i);
         Point3D newPoint1 = new Point3D(vertices.get(2).x, (vertices.get(2).y), (vertices.get(2).z)+distanceForEachSection*i);
         Point3D newPoint2 = new Point3D(vertices.get(6).x, (vertices.get(6).y), (vertices.get(6).z)+distanceForEachSection*i);
         Point3D newPoint3 = new Point3D(vertices.get(4).x, (vertices.get(4).y), (vertices.get(4).z)+distanceForEachSection*i);
         vertices.add(newPoint0); vertices.add(newPoint1); vertices.add(newPoint2); vertices.add(newPoint3);
         //get first index of this new subdivision:
         int indexOfStart = vertices.indexOf(newPoint0);
         Edge edge0To1 = new Edge(indexOfStart, indexOfStart+1);
         Edge edge1To3 = new Edge(indexOfStart+1, indexOfStart+2);
         Edge edge3To2 = new Edge(indexOfStart+2, indexOfStart+3);
         Edge edge2To0 = new Edge(indexOfStart+3, indexOfStart);
         edges.add(edge0To1);edges.add(edge1To3);edges.add(edge3To2);edges.add(edge2To0);
      }
   }

   public double zAvg(Face face){
      Point3D a = face.a;
      Point3D b = face.b;
      Point3D c = face.c;
      Point3D d = face.d;
      double avg = (a.z + b.z + c.z + d.z)/4;
      System.out.println(avg);
      return avg;
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
