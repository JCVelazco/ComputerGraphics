import javax.swing.JFrame;
import javax.swing.JPanel;

class Cone{
      
      public static void main(String[] args) {
            //default values
            double radioCone = 1;
            double heightCone = 1;

            int baseSections = 1;
            int heightSections = 1;

            try{
                  radioCone = Double.parseDouble(args[0]);
                  heightCone = Double.parseDouble(args[1]);
                  baseSections = Integer.parseInt(args[2]);
                  heightSections = Integer.parseInt(args[3]);
            }catch(Exception e){
                  System.out.println("No se ingreso parametros para las medidas");
            }
            
            WireframeJApplet applet = new WireframeJApplet();
            applet.setSizes(radioCone, heightCone);
            applet.setSections(baseSections, heightSections);
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