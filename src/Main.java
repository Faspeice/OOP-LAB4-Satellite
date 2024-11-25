import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Спутник");
        frame.setPreferredSize(new Dimension(800, 600));


        final JPanel panel = new JPanel() {
            private static final int WIDTH = 800;
            private static final int HEIGHT = 600;
            private static final int PLANET_RADIUS = 120;
            private static final int SATELLITE_RADIUS = 10;
            private static final int ORBIT_WIDTH = 400;
            private static final int ORBIT_HEIGHT = 100;
            private double angle = 0;

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


                int planetX = WIDTH / 2;
                int planetY = HEIGHT / 2;

                g2d.setColor(Color.BLUE);
                g2d.fillOval(planetX - PLANET_RADIUS, planetY - PLANET_RADIUS, 2 * PLANET_RADIUS, 2 * PLANET_RADIUS);


                double satelliteX = planetX + ORBIT_WIDTH / 2 * Math.cos(angle);
                double satelliteY = planetY + ORBIT_HEIGHT / 2 * Math.sin(angle);


                if (!isHidden(planetX, planetY, PLANET_RADIUS, satelliteX, satelliteY, SATELLITE_RADIUS)) {

                    g2d.setColor(Color.RED);
                    g2d.fillOval((int) satelliteX - SATELLITE_RADIUS, (int) satelliteY - SATELLITE_RADIUS, 2 * SATELLITE_RADIUS, 2 * SATELLITE_RADIUS);
                }


                angle += 0.01;
            }

            private boolean isHidden(int planetX, int planetY, int planetRadius, double satelliteX, double satelliteY, int satelliteRadius) {

                double distance = Math.sqrt(Math.pow(satelliteX - planetX, 2) + Math.pow(satelliteY - planetY, 2));
                if (distance <= planetRadius + satelliteRadius) {

                    if (satelliteY < planetY) {
                        return true;
                    }
                }
                return false;
            }
        };

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });

        timer.start();
    }
}