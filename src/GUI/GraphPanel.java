package GUI;

import api.*;
import classes.AlgoGraphClass;
import classes.GeoLocationClass;
import classes.GraphClass;
import classes.Node;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Iterator;


public class GraphPanel extends JPanel {
    GraphClass graphClass;
    AlgoGraphClass a;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private double UX;
    private double UY;
    private Dimension screenSize;


    GraphPanel(GraphClass graphClass) throws IOException {
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setPreferredSize(screenSize);
        this.setBackground(new Color(0x9FADBA));
        this.setFocusable(true);
        this.graphClass = graphClass;
        new AlgoGraphClass().init(graphClass);
        System.out.println("start");
        setLimits();

        UX = screenSize.getWidth() / Math.abs(maxX - minX) * 0.90;
        UY = screenSize.getHeight() / Math.abs(maxY - minY) * 0.8;


    }

    private void setLimits() {
        Iterator<NodeData> n = this.graphClass.nodeIter();
        NodeData node;
        if (n.hasNext()) {
            node = n.next();
            minX = node.getLocation().x();
            minY = node.getLocation().y();
            maxX = node.getLocation().x();
            maxY = node.getLocation().y();
        }
        while (n.hasNext()) {
            node = n.next();
            minX = Math.min(minX, node.getLocation().x());
            minY = Math.min(minY, node.getLocation().y());
            maxX = Math.max(maxX, node.getLocation().x());
            maxY = Math.max(maxY, node.getLocation().y());
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g)
    {
        g.setFont(new Font("david", Font.BOLD, 14));
        g.drawString("Ofir Regev & Ofek Saadon ", 1100, 30);
        g.drawString(String.valueOf("MC: " + this.graphClass.getMC()), 1200, 50);
        Iterator<EdgeData> iter2 = this.graphClass.edgeIter();
        while (iter2.hasNext())
        {

            EdgeData edge = iter2.next();

            double srcX = this.graphClass.getNode(edge.getSrc()).getLocation().x();
            srcX = ((srcX - minX) * UX) + 12;
            double srcY = this.graphClass.getNode(edge.getSrc()).getLocation().y();
            srcY = ((srcY - minY) * UY) + 12;

            double destX = this.graphClass.getNode(edge.getDest()).getLocation().x();
            destX = ((destX - minX) * UX) + 12;
            double destY = this.graphClass.getNode(edge.getDest()).getLocation().y();
            destY = ((destY - minY) * UY) + 12;

            g.setColor(Color.GRAY);
            g.drawLine((int) srcX, (int) srcY, (int) destX, (int) destY);
            drawArrowLine(g, (int) srcX, (int) srcY, (int) destX, (int) destY, 30, 7);

        }
        Iterator<NodeData> iter = this.graphClass.nodeIter();
        while (iter.hasNext()) {
            NodeData N = iter.next();
            // draw the node
            int x = (int) ((N.getLocation().x() - minX) * UX);
            int y = (int) ((N.getLocation().y() - minY) * UY);
            g.setColor(Color.PINK);
            g.fillOval(x, y, 24, 24);
            g.setColor(Color.WHITE);
            g.drawString("" + N.getKey(), x + 8, y + 15);
        }

    }

    private void drawArrowLine(Graphics g, int x1, int y1, int x2, int y2, int d, int h) {
        int dx = x2 - x1, dy = y2 - y1;
        double D = Math.sqrt(dx * dx + dy * dy);
        double xm = D - d, xn = xm, ym = h, yn = -h, x;
        double sin = dy / D, cos = dx / D;

        x = xm * cos - ym * sin + x1;
        ym = xm * sin + ym * cos + y1;
        xm = x;

        x = xn * cos - yn * sin + x1;
        yn = xn * sin + yn * cos + y1;
        xn = x;

        int[] xpoints = {x2, (int) xm, (int) xn};
        int[] ypoints = {y2, (int) ym, (int) yn};

        g.drawLine(x1, y1, x2, y2);
        g.fillPolygon(xpoints, ypoints, 3);
    }

    public void addNode(int key, int x, int y) {
        double newX = (x - 12) / UX + minX;
        double newY = (y - 12) / UY + minY;
        System.out.println("x" + x + "y=" + y);
        this.graphClass.addNode(new Node(key, new GeoLocationClass(newX + "," + newY + ",0")));
        repaint();
        revalidate();
    }
}











