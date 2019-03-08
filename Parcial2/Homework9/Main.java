//Juan Carlos Velazco A01326707
//Carlos Parrodi A01421454
import javax.swing.JFrame;
import javax.swing.JPanel;

class Main{
      
      public static void main(String[] args) {
            WireframeJApplet applet = new WireframeJApplet();
            applet.init();
            final JFrame frame = new JFrame("Wireframe Viewer");
            frame.setContentPane(applet.getContentPane());
            frame.setJMenuBar(applet.getJMenuBar());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocation(100, 100);
            frame.setVisible(true);
            applet.start();
      }
}