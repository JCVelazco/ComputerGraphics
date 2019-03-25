//Juan Carlos Velazco A01326707
//Carlos Parrodi A01421454
import javax.swing.JFrame;
import javax.swing.JPanel;

class Sphere{
      
      public static void main(String[] args) {
            //default values
            double radioSphere = 1;
            int yCuts = 360;
            int xzCuts = 360;

            try{
                  radioSphere = Double.parseDouble(args[0]);
                  yCuts = Integer.parseInt(args[1]);
                  xzCuts = Integer.parseInt(args[2]);
            }catch(Exception e){
                  System.out.println("No se ingreso parametros para las medidas");
            }
            
            WireframeJApplet applet = new WireframeJApplet();
            applet.setSizes(radioSphere);
            applet.setSections(yCuts, xzCuts);
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