import javax.swing.JFrame;
import javax.swing.JPanel;

class Box{
      
      public static void main(String[] args) {
            //default values
            double xSize = 1;
            double ySize = 1;
            double zSize = 1;

            int xSections = 1;
            int ySections = 1;
            int zSections = 1;

            try{
                  xSize = Double.parseDouble(args[0]);
                  ySize = Double.parseDouble(args[1]);
                  zSize = Double.parseDouble(args[2]);
                  xSections = Integer.parseInt(args[3]);
                  ySections = Integer.parseInt(args[4]);
                  zSections = Integer.parseInt(args[5]);
            }catch(Exception e){
                  System.out.println("No se ingreso parametros para las medidas");
            }
            
            WireframeJApplet applet = new WireframeJApplet();
            applet.setSizes(xSize, ySize, zSize);
            applet.setSections(xSections, ySections, zSections);
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