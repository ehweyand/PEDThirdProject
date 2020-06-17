package main;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Plot extends JFrame {
    public Plot() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        PlotComponent pcomp = new PlotComponent(500, 500);
        add(pcomp);
        pcomp.addPoint(100, 100);
        pcomp.addPoint(300, 200);
        pcomp.addPoint(400, 300);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> new Plot());
    }
}

class PlotComponent extends JComponent {
    private ArrayList<Point2D> points = new ArrayList<Point2D>();

    public PlotComponent(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

    public void addPoint(double x, double y) {
        points.add(new Point2D.Double(x, y));
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.BLUE);

        for (Point2D p : points) {
            Shape point = new Ellipse2D.Double(p.getX(), p.getY(), 10, 10);
            g2d.draw(point);
        }
    }
}
