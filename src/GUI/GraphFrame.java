package GUI;

import api.AlgoGraphClass;
import api.DirectedWeightedGraph;
import api.GraphClass;
import api.NodeData;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class GraphFrame extends JFrame implements ActionListener {
    GraphPanel c;
    AlgoGraphClass a;
    JMenuBar menuBar;
    JMenu GraphMenu;
    JMenu algorithms;
    JMenuItem loadGraph;
    JMenuItem saveGraph;
    JMenuItem Sp;
    JMenuItem SpL;
    JMenuItem center;
    JMenuItem isConectted;
    JMenuItem removeNode;
    JMenuItem addNode;

    public GraphFrame(GraphClass c) throws IOException {
        this.c = new GraphPanel(c);
        this.a = new AlgoGraphClass();
        this.a.init(c);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLayout(new FlowLayout());
        this.setTitle("My Graph");

        menuBar = new JMenuBar();

        GraphMenu = new JMenu("Graph setting");
        algorithms = new JMenu("Algorithms");
        menuBar.add(GraphMenu);
        menuBar.add(algorithms);

        loadGraph = new JMenuItem("load graph");
        saveGraph = new JMenuItem("save graph");

        center = new JMenuItem("get center");
        Sp = new JMenuItem("shortest path");
        SpL = new JMenuItem("shortest path list");
        isConectted = new JMenuItem("check connection");
        removeNode = new JMenuItem("remove node");
        addNode = new JMenuItem("add node");


        loadGraph.addActionListener(this);
        saveGraph.addActionListener(this);
        center.addActionListener(this);
        Sp.addActionListener(this);
        SpL.addActionListener(this);
        isConectted.addActionListener(this);
        removeNode.addActionListener(this);
        addNode.addActionListener(this);

        GraphMenu.add(loadGraph);
        GraphMenu.add(saveGraph);
        GraphMenu.add(removeNode);
        GraphMenu.add(addNode);
        algorithms.add(center);
        algorithms.add(Sp);
        algorithms.add(SpL);
        algorithms.add(isConectted);



        this.setJMenuBar(menuBar);


        this.setVisible(true);



    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadGraph) {
            System.out.println("yayy");
            this.add(this.c);
            revalidate();
        }
        //check this
        if (e.getSource() == saveGraph) {
            String s = JOptionPane.showInputDialog("Please enter an address");
            try {
                this.a.save(s);
                JOptionPane.showMessageDialog(null, "Saved", "save", JOptionPane.PLAIN_MESSAGE);

            } catch (Exception exception) {
                throw exception;
            }
        }
        if (e.getSource() == center) {
            JOptionPane.showMessageDialog(null, "The center of the graph is:" + this.a.center(), "Center", JOptionPane.PLAIN_MESSAGE);


        }
        if (e.getSource() == Sp) {
            String ans1 = JOptionPane.showInputDialog("please enter the source");
            String ans2 = JOptionPane.showInputDialog("please enter the destination");
            JOptionPane.showMessageDialog(null, "The shortest path is:" + this.a.shortestPathDist(Integer.parseInt(ans1), Integer.parseInt(ans2)), "Shortest Path", JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == SpL) {
            String ans1 = JOptionPane.showInputDialog("please enter the source");
            String ans2 = JOptionPane.showInputDialog("please enter the destination");
            String s="source node";
            int i=0;
            List<NodeData> list =this.a.shortestPath(Integer.parseInt(ans1),Integer.parseInt(ans2));
            while (i<list.size()) {
                s = s +"-->" +list.get(i).getKey();
                i++;
            }
            JOptionPane.showMessageDialog(null, "The shortest path is:" + s, "Shortest Path List", JOptionPane.PLAIN_MESSAGE);
        }
        if (e.getSource() == isConectted) {
            JOptionPane.showMessageDialog(null, this.a.isConnected(), "Is Connected", JOptionPane.PLAIN_MESSAGE);

        }
        if ((e.getSource() == removeNode)){
            String remove = JOptionPane.showInputDialog("please enter the id of the node");

            this.a.getGraph().removeNode(Integer.parseInt(remove));
            repaint();
            this.add(c);
            repaint();

        }
        if(e.getSource() == addNode){
            try {
                GraphPanel w =new GraphPanel(new GraphClass());
                String remove = JOptionPane.showInputDialog("please enter the id of the node");
                String s1 = JOptionPane.showInputDialog("please enter the location of the node -x");
                String s2 = JOptionPane.showInputDialog("please enter the location of the node -y");
                w.addNode(Integer.parseInt(remove),Integer.parseInt(s1),Integer.parseInt(s2));
                this.add(w);
                revalidate();
                System.out.println("done");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }


        }


    }
}
