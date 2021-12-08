package GUI;

import api.*;


import org.w3c.dom.traversal.NodeIterator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;


public class GraphPanel extends JPanel {
    GraphClass c;
    AlgoGraphClass a;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private double UX;
    private double UY;
    private Dimension screenSize;


    GraphPanel(GraphClass c) throws IOException {
        this.screenSize =Toolkit.getDefaultToolkit().getScreenSize();

        this.setPreferredSize(screenSize);
        this.setBackground(new Color(0x9FADBA));
        this.setFocusable(true);
        this.c = c;
        new AlgoGraphClass().init(c);
        System.out.println("start");
        setLimits();

        UX = screenSize.getWidth() / Math.abs(maxX - minX) * 0.975;
        UY = screenSize.getHeight() / Math.abs(maxY - minY) * 0.9;


    }
    private void setLimits(){
        Iterator<NodeData> n = this.c.nodeIter();
        NodeData node;
        if(n.hasNext())
        {
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

    public void draw(Graphics g) {
        g.setFont(new Font("david", Font.BOLD, 14));
        g.drawString("Ofir Regev", 1200, 30);
        g.drawString(String.valueOf("MC: "+this.c.getMC()),1200,50);


        Iterator<NodeData> iter = this.c.nodeIter();
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
        Iterator<EdgeData> iter2 = this.c.edgeIter();
        while (iter2.hasNext()) {
            EdgeData edge = iter2.next();

            double srcX = this.c.getNode(edge.getSrc()).getLocation().x();
            srcX = ((srcX - minX) * UX) + 12;
            double srcY = this.c.getNode(edge.getSrc()).getLocation().y();
            srcY = ((srcY - minY) * UY) + 12;

            double destX = this.c.getNode(edge.getDest()).getLocation().x();
            destX = ((destX - minX) * UX) + 12;
            double destY = this.c.getNode(edge.getDest()).getLocation().y();
            destY = ((destY - minY) * UY) + 12;

            g.setColor(Color.GRAY);
            //g.drawLine((int)srcX, (int)srcY, (int)destX, (int)destY);
            g.drawLine((int) srcX, (int) srcY, (int) destX, (int) destY);
            //System.out.println(edge);
        }
    }




    }






