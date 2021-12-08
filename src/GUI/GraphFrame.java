package GUI;
import api.AlgoGraphClass;
import api.GraphClass;
import api.GraphClass;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Locale;

public class GraphFrame extends JFrame  implements ActionListener{
    GraphPanel c;
    AlgoGraphClass a;

    public GraphFrame(GraphClass c) throws IOException{

        this.c =new GraphPanel(c);
        this.a = new AlgoGraphClass();
        this.a.init(c);

        this.add(this.c);
        this.setTitle("My Graph");
        this.setResizable(true);
        this.pack();
        this.setVisible(true);
        this.setLocale(null);



        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        JMenu algo = new JMenu("Algorithms");
        menuBar.add(algo);

        JMenu graph = new JMenu("remove node");
        menuBar.add(graph);

        JMenu center = new JMenu("get center");
        algo.add(center);
        ActionEvent e =new ActionEvent("get center",1,"get center");
        MenuItem item1 = new MenuItem("get center");
        item1.addActionListener(this);
        MenuItem item2 = new MenuItem("remove node");
        item2.addActionListener(this);



        this.addMouseListener(this);



    }

    private void addMouseListener(GraphFrame graphFrame) {
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("get center")) {
            JLabel label = new JLabel(this.a.center().toString());
            label.setLocation(500,10);
            this.add(label);
            repaint();
            System.out.println(this.a.center());
        } else if (str.equals("remove node")) {
            a.getGraph().removeNode(1);
            repaint();
            System.out.println("okay");
        }
    }
}
