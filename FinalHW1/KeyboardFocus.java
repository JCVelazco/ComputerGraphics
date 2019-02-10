import java.awt.*;
import java.awt.event.*;
import java.sql.Array;

import javax.swing.*;

public class KeyboardFocus extends JApplet 
implements KeyListener, FocusListener, MouseListener {
    // (Note:  MouseListener is implemented only so that
    //         the applet can request the input focus when
    //         the user clicks on it.)
    
    boolean focussed = false;   // True when this applet has input focus.
    
    DisplayPanel canvas;  // The drawing surface on which the applet draws,
    // belonging to a nested class DisplayPanel, which
    // is defined below.
    
    //matrix for translation
    public static double translationMatrix [][] = new double[][]{{1,0,0}, {0,1,0}, {0,0,1}};
    //matrix for rotation
    public static double rotationMatrix [][] = new double[][]{{1,1,0}, {1,1,0}, {0,0,1}};
    //matrix for scaling
    public static double scalingMatrix [][] = new double[][]{{0,0,0}, {0,0,0}, {0,0,1}};
    
    public static double generalArray[] = new double[]{0,0,1};
    
    public static double generalAngle = 0;
    
    public static double stateOfScaling = 1;
    
    // SUPERMAN
    public static double[] xPointsSuperman = {510,510,509,509,510,510,511,511,510,510,509,509,508,507,507,505,505,506,506,505,505,504,503,502,501,501,500,500,501,501,497,496,495,494,494,492,491,490,490,492,493,493,492,491,490,489,489,488,488,489,488,487,486,485,484,483,482,481,480,479,479,478,477,476,476,477,477,478,478,479,479,480,481,481,482,484,484,485,485,486,487,489,490,491,492,493,494,495,496,496,497,498,499,500,502,502,503,504,505,505,506,507,511,511,512,518,519,519,518,518,517,517,516,516,515,515,514,514,513,513,512,512,511,511,510,509,508,508,508,509,510,510,511,511,512,512,513,513,514,514,513,513,512,512,511};
    public static double[] yPointsSuperman = {245,246,247,249,250,251,252,255,256,262,263,265,266,267,269,269,267,266,264,263,262,261,260,260,261,263,263,266,267,270,270,271,271,272,274,274,275,275,271,271,270,269,268,268,269,269,271,272,280,281,282,283,284,284,285,286,287,288,289,290,291,292,293,294,299,300,303,303,301,301,300,299,298,297,296,296,304,305,307,308,309,309,310,311,312,312,313,313,314,315,316,317,318,319,319,328,329,330,328,321,320,318,318,321,322,322,321,316,315,311,311,308,307,306,305,304,303,302,301,299,298,295,294,291,290,289,288,283,283,281,280,278,277,275,274,270,269,261,261,255,254,248,247,246,245};
    
    public static double xSupermanHeart = 500;
    public static double ySupermanHeart = 290;
    public void init() {
        // Initialize the applet; set it up to receive keyboard
        // and focus events.  Place the square in the middle of
        // Then, set up the drawing surface.
        
        setSize(1000,600); 
        /*
        squareTop = getSize().height / 2 - SQUARE_SIZE / 2;
        squareLeft = getSize().width / 2 - SQUARE_SIZE / 2;
        
        squareTop = getSize().height / 2 - SQUARE_SIZE / 2;
        squareLeft = getSize().width / 2 - SQUARE_SIZE / 2;
        */
        
        canvas = new DisplayPanel();  // Create drawing surface and 
        setContentPane(canvas);       //    install it as the applet's content pane.
        
        canvas.setBackground(Color.white);  // Set the background color of the canvas.
        
        canvas.addFocusListener(this);   // Set up the applet to listen for events
        canvas.addKeyListener(this);     //                          from the canvas.
        canvas.addMouseListener(this);
        
    } // end init();
    
    
    class DisplayPanel extends JPanel {
        // An object belonging to this nested class is used as
        // the content pane of the applet.  It displays the
        // moving square on a white background with a border
        // that changes color depending on whether this 
        // component has the input focus or not.
        
        public void paintComponent(Graphics g) {
            
            super.paintComponent(g);  // Fills the panel with its
            // background color, which is white.
            
            /* Draw a 3-pixel border, colored cyan if the applet has the
            keyboard focus, or in light gray if it does not. */
            
            if (focussed) 
            g.setColor(Color.green);
            else
            g.setColor(Color.lightGray);
            
            
            
            int width = getSize().width;  // Width of the applet.
            int height = getSize().height; // Height of the applet.
            g.drawRect(0,0,width-1,height-1);
            g.drawRect(1,1,width-3,height-3);
            g.drawRect(2,2,width-5,height-5);
            
            /* Draw superman. */
            drawSuperman(g);
            
            /* If the applet does not have input focus, print a message. */
            
            if (!focussed) {
                g.setColor(Color.magenta);
                g.drawString("Click to activate",7,20);
            }
            
        }  // end paintComponent()
        
    } // end nested class DisplayPanel 
    
    
    
    // ------------------- Event handling methods ----------------------
    
    
	public void drawSuperman(Graphics g){
        //convert the doubkle points to int
        int[] xintArray = new int[xPointsSuperman.length];
        int[] yintArray = new int[yPointsSuperman.length];
        
        for (int i=0; i< xintArray.length; ++i){
            xintArray[i] = (int) xPointsSuperman[i];
            yintArray[i] = (int) yPointsSuperman[i];
        }
		//Dog body with mapped points
		g.setColor(new Color(255,0,0));
        g.fillPolygon(xintArray, yintArray,144);
        g.setColor(new Color(0,0,0));
	}
    
    //transaletes on +1 all the coordenates of an element (not totally sure)
    public void translation(double moveX, double moveY){
        double resultMatrix[] = new double[3];
        //add dx and dy
        translationMatrix[0][2] = moveX;
        translationMatrix[1][2] = moveY;
        for( int i = 0; i < xPointsSuperman.length; i++){
            //add actual x and y
            generalArray[0] = xPointsSuperman[i];
            generalArray[1] = yPointsSuperman[i];
            for(int row = 0; row < 3; row++){
                double sum = 0;
                for(int col = 0; col < 3; col++){
                    sum = sum + (translationMatrix[row][col]*generalArray[col]);
                }
                resultMatrix[row] =  sum; 
            }
            xPointsSuperman[i] = resultMatrix[0];
            yPointsSuperman[i] = resultMatrix[1];
        } 
    }
    
    //rotates
    /*
    public void rotation(double sumOrRest){
        
        double resultMatrix[] = new double[3];
        rotationMatrix[0][0] = Math.cos(Math.toRadians(generalAngle));
        rotationMatrix[0][1] = -Math.sin(Math.toRadians(generalAngle));
        rotationMatrix[1][0] = Math.sin(Math.toRadians(generalAngle));
        rotationMatrix[1][1] = Math.cos(Math.toRadians(generalAngle));
        for(int i = 0; i < xPointsSuperman.length; i++){
            //add actual x and y
            generalArray[0] = xPointsSuperman[i];
            generalArray[1] = yPointsSuperman[i];
            
            for(int row = 0; row < 3; row++){
                double sum = 0;
                for(int col = 0; col < 3; col++){
                    sum = sum + (rotationMatrix[row][col]*generalArray[col]);
                }
                resultMatrix[row] =  sum; 
            }
            xPointsSuperman[i] = (int)resultMatrix[0];
            yPointsSuperman[i] = (int)resultMatrix[1];
        }
        
    }*/
    
    //scales
    public void escalation(double sumOrRest){
        
        stateOfScaling += sumOrRest;
        System.out.println("Debug scaleL "+stateOfScaling);
        double resultMatrix[] = new double[3];
        scalingMatrix[0][0] = stateOfScaling;
        scalingMatrix[1][1] = stateOfScaling;   
        for(int i = 0; i < xPointsSuperman.length; i++){         
            //add actual x and y
            generalArray[0] = xPointsSuperman[i] - xSupermanHeart;
            generalArray[1] = yPointsSuperman[i] - ySupermanHeart;
            
            for(int row = 0; row < 3; row++){
                double sum = 0;
                for(int col = 0; col < 3; col++){
                    sum = sum + (scalingMatrix[row][col]*generalArray[col]);
                }
                resultMatrix[row] =  sum; 
            }
            xPointsSuperman[i] = resultMatrix[0]+xSupermanHeart;
            yPointsSuperman[i] = resultMatrix[1]+ySupermanHeart;
        }
        canvas.repaint();
        
    }
    
    public void debugPrintMatrix(double[][] matrix){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                System.out.printf("%d", (int)matrix[i][j]);
            }
            System.out.println();
        }
    }
    
    public void focusGained(FocusEvent evt) {
        // The applet now has the input focus.
        focussed = true;
        canvas.repaint();  // redraw with cyan border
    }
    
    
    public void focusLost(FocusEvent evt) {
        // The applet has now lost the input focus.
        focussed = false;
        canvas.repaint();  // redraw without cyan border
    }
    
    
    public void keyTyped(KeyEvent evt) {
        // The user has typed a character, while the
        // applet has the input focus.  If it is one
        // of the keys that represents a color, change
        // the color of the square and redraw the applet.
        
        char ch = evt.getKeyChar();  // The character typed.
        //rotation ++
        if (ch == 'E' || ch == 'e') {
            generalAngle++;
            // rotation(1);
            canvas.repaint();
        }
        //rotation --
        else if (ch == 'D' || ch == 'd') {
            generalAngle--;
            //rotation(-1);
            canvas.repaint();
        }
        //scalation ++
        else if (ch == 'R' || ch == 'r') {
            if(stateOfScaling < 2){
                System.out.println("Debug scalation++");
                escalation(0.1);
                canvas.repaint();
            }
        }
        //scalation --
        else if (ch == 'F' || ch == 'f') {
            if(stateOfScaling > -1){
                System.out.println("Debug scalatio--");
                escalation(-0.1);
                canvas.repaint();
            }
        }
        
    }  // end keyTyped()
    
    
    public void keyPressed(KeyEvent evt) { 
        // Called when the user has pressed a key, which can be
        // a special key such as an arrow key.  If the key pressed
        // was one of the arrow keys, move the square (but make sure
        // that it doesn't move off the edge, allowing for a 
        // 3-pixel border all around the applet).
        
        int key = evt.getKeyCode();  // keyboard code for the key that was pressed
        
        if (key == KeyEvent.VK_LEFT) {
            translation(-1,0);
            canvas.repaint();
        }
        else if (key == KeyEvent.VK_RIGHT) {
            translation(1,0);
            canvas.repaint();
        }
        else if (key == KeyEvent.VK_UP) {
            translation(0,-1);
            canvas.repaint();
        }
        else if (key == KeyEvent.VK_DOWN) {
            translation(0,1);
            canvas.repaint();
        }
        
    }  // end keyPressed()
    
    
    public void keyReleased(KeyEvent evt) { 
        // empty method, required by the KeyListener Interface
    }
    
    
    public void mousePressed(MouseEvent evt) {
        // Request that the input focus be given to the
        // canvas when the user clicks on the applet.
        canvas.requestFocus();
    }   
    
    
    public void mouseEntered(MouseEvent evt) { }  // Required by the
    public void mouseExited(MouseEvent evt) { }   //    MouseListener
    public void mouseReleased(MouseEvent evt) { } //       interface.
    public void mouseClicked(MouseEvent evt) { }
    
    
    
    
} // end class KeyboardFocus