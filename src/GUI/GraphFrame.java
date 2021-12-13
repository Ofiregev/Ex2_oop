
package GUI;

import api.*;
import classes.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GraphFrame extends JFrame implements ActionListener {
    GraphPanel c;
    AlgoGraphClass a;
    JMenuBar menuBar;
    JMenu GraphMenu;
    JMenu algorithms;
    JMenuItem loadGraph;
    JMenuItem newFile;
    JMenuItem saveGraph;
    JMenuItem Sp;
    JMenuItem SpL;
    JMenuItem center;
    JMenuItem isConectted;
    JMenuItem removeNode;
    JMenuItem remove;
    JMenuItem addNode;
    JMenuItem addEdge;
    JMenuItem removeEdge;
    JMenuItem nodeSize;
    JMenuItem edgeSize;
    JMenuItem tsp;
    JMenuItem getNode;
    JMenuItem getEdge;
    JButton button;
    GraphPanel w;

    ImageIcon loadIcon;
    ImageIcon exitIcon;
    ImageIcon saveIcon;
    ImageIcon GraphIcon;
    ImageIcon DGraphIcon;
    ImageIcon shortIcon;
    ImageIcon addGraphIcon;

    int i, j = 0;

    int upd;

    public GraphFrame(GraphClass c) throws IOException {
        this.c = new GraphPanel(c);
        this.a = new AlgoGraphClass();
        this.a.init(c);
        this.upd = 0;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        this.setLayout(new FlowLayout());
        this.menuBar = new JMenuBar();
        this.loadIcon = new ImageIcon();

        this.exitIcon = new ImageIcon("./ImageIcon/EXIT.png");
        this.loadIcon = new ImageIcon("./ImageIcon/img.png");
        this.saveIcon = new ImageIcon("./ImageIcon/save.png_.jpg");
        this.GraphIcon = new ImageIcon("./ImageIcon/Graph.png");
        this.DGraphIcon = new ImageIcon("./ImageIcon/D.png");
        this.shortIcon = new ImageIcon("./ImageIcon/shortestPath.jpg");
        this.addGraphIcon = new ImageIcon("./ImageIcon/add.png");
        this.loadIcon = scaleImageIcon(loadIcon, 30, 30);
        this.saveIcon = scaleImageIcon(saveIcon, 30, 30);
        this.exitIcon = scaleImageIcon(exitIcon, 30, 30);
        this.GraphIcon = scaleImageIcon(GraphIcon, 40, 40);

        this.DGraphIcon = scaleImageIcon(DGraphIcon, 40, 40);
        this.shortIcon = scaleImageIcon(shortIcon, 30, 30);
        this.addGraphIcon = scaleImageIcon(addGraphIcon, 40, 50);
        this.setTitle("My Graph");

        menuBar = new JMenuBar();
        GraphMenu = new JMenu("Graph setting");
        algorithms = new JMenu("Algorithms");
        menuBar.add(GraphMenu);
        menuBar.add(algorithms);

        loadGraph = new JMenuItem("load graph");
        newFile = new JMenuItem("Open new graph");
        saveGraph = new JMenuItem("save graph");

        center = new JMenuItem("get center");
        Sp = new JMenuItem("shortest path");
        SpL = new JMenuItem("shortest path list");
        isConectted = new JMenuItem("check connection");
        tsp = new JMenuItem("tsp");

        remove = new JMenuItem("remove");
        /*remove --> removeNode,removeEdge */

        removeNode = new JMenuItem("remove node");
        removeEdge = new JMenuItem("remove edge");
        getNode = new JMenuItem("get node data");
        getEdge = new JMenuItem("get edge data");


        addNode = new JMenuItem("add node");
        addEdge = new JMenuItem("addEdge");
        nodeSize = new JMenuItem("get nodes size");
        edgeSize = new JMenuItem("get edges size");


        loadGraph.addActionListener(this);
        newFile.addActionListener(this);
        saveGraph.addActionListener(this);
        center.addActionListener(this);
        Sp.addActionListener(this);
        SpL.addActionListener(this);
        isConectted.addActionListener(this);
        removeNode.addActionListener(this);
        /* */
        remove.addActionListener(this);
        addNode.addActionListener(this);
        addEdge.addActionListener(this);
        removeEdge.addActionListener(this);
        nodeSize.addActionListener(this);
        edgeSize.addActionListener(this);
        tsp.addActionListener(this);
        getEdge.addActionListener(this);
        getNode.addActionListener(this);
        newFile.addActionListener(this);

        this.loadGraph.setIcon(this.loadIcon);
        this.saveGraph.setIcon(this.saveIcon);
        this.removeEdge.setIcon(this.DGraphIcon);
        this.removeNode.setIcon(this.DGraphIcon);
        this.addNode.setIcon(this.addGraphIcon);
        this.addEdge.setIcon(this.addGraphIcon);
        this.getEdge.setIcon(this.loadIcon);
        this.getNode.setIcon(this.loadIcon);


        GraphMenu.add(loadGraph);
        GraphMenu.add(newFile);
        GraphMenu.add(saveGraph);
        GraphMenu.add(removeNode);
        GraphMenu.add(removeEdge);

        GraphMenu.add(getNode);
        GraphMenu.add(getEdge);

        GraphMenu.add(addNode);
        GraphMenu.add(addEdge);
        GraphMenu.add(nodeSize);
        GraphMenu.add(edgeSize);
        algorithms.add(center);
        algorithms.add(Sp);
        algorithms.add(SpL);
        algorithms.add(isConectted);
        algorithms.add(tsp);

        GraphMenu.setFont(new Font(Font.SERIF, Font.PLAIN, 16));
        algorithms.setFont(new Font(Font.SERIF, Font.PLAIN, 16));


        this.setJMenuBar(menuBar);


        this.setVisible(true);


    }

    private ImageIcon scaleImageIcon(ImageIcon imageIcon, int width, int height) {
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    public void openF() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.button = new JButton("Select File");
        this.button.addActionListener(this);
        this.add(this.button, BorderLayout.EAST);
        //this.pack();
        this.setVisible(true);
    }

    public void l_G(GraphPanel c) {

        this.add(this.c);
        System.out.println("loaded");
        System.out.println(this.c.graphClass.edgeSize());
        repaint();
        revalidate();
        repaint();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loadGraph) {
            if (j > 0) {
                this.removeAll();
                System.out.println("j" + j + "and loadG:" + this.c.graphClass.nodeSize());
                this.add(this.c);
                System.out.println("j" + j + "and loadG:" + this.c.graphClass.nodeSize());
                repaint();
            } else {
                // removeAll();
                l_G(this.c);
            }

        } else if (e.getSource() == newFile) {
//            String s = JOptionPane.showInputDialog("Please enter an address");
//            this.remove(this.c);
//            DirectedWeightedGraph temp =this.c.graphClass;
//            AlgoGraphClass algoGraphClass = new AlgoGraphClass();
//            algoGraphClass.load(s);
//            algoGraphClass.init(temp);
//            this.a.load(s);
//            this.a.init(temp);
//            System.out.println(temp.edgeSize());
           String s;
            System.out.println("hello");
            do {
               s = JOptionPane.showInputDialog("Please enter a valid address");
                //this.remove(this.c);
               //GraphClass temp =this.c.graphClass;
             //this.a.load(s);
                System.out.println(s);
                //this.add(c);
            } while (!this.a.load(s));
            System.out.println(s);
            //this.a.load(s);
            //this.a.init(temp);
            this.remove(c);
            try {
                this.c = new GraphPanel((GraphClass) this.a.getGraph());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            this.add(c);
            //l_G(this.c);
            //c.repaint();
            this.repaint();
            this.revalidate();


//
//                openF();
//                JFileChooser fileChooser = new JFileChooser();
//                int response = fileChooser.showSaveDialog(newFile);
//                if (response == JFileChooser.APPROVE_OPTION) {
//                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
//                    System.out.println(file);
//                    this.add(this.c);


        }


        // revalidate();
        //  }
        else if (e.getSource() == saveGraph) {
            String s = JOptionPane.showInputDialog("Please enter an address");
            try {
                this.a.save(s);
                JOptionPane.showMessageDialog(null, "Saved", "save", JOptionPane.PLAIN_MESSAGE);

            } catch (Exception exception) {
                throw exception;
            }
        } else if (e.getSource() == center) {
            JOptionPane.showMessageDialog(null, "The center of the graph is:" + this.a.center(), "Center", JOptionPane.PLAIN_MESSAGE);


        } else if (e.getSource() == Sp) {
            String ans1 = JOptionPane.showInputDialog("please enter the source");
            String ans2 = JOptionPane.showInputDialog("please enter the destination");
            JOptionPane.showMessageDialog(null, "The shortest path is:" + this.a.shortestPathDist(Integer.parseInt(ans1), Integer.parseInt(ans2)), "Shortest Path", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == SpL) {
            String ans1 = JOptionPane.showInputDialog("please enter the source");
            String ans2 = JOptionPane.showInputDialog("please enter the destination");
            String s = "source node";
            int i = 0;
            List<NodeData> list = this.a.shortestPath(Integer.parseInt(ans1), Integer.parseInt(ans2));
            while (i < list.size()) {
                s = s + "-->" + list.get(i).getKey();
                i++;
            }
            JOptionPane.showMessageDialog(null, "The shortest path is:" + s, "Shortest Path List", JOptionPane.PLAIN_MESSAGE);
        } else if (e.getSource() == isConectted) {

            JOptionPane.showMessageDialog(null, this.a.isConnected(), "Is Connected", JOptionPane.PLAIN_MESSAGE);

        } else if ((e.getSource() == removeNode)) {
            String remove = JOptionPane.showInputDialog("please enter the id of the node");

            this.a.getGraph().removeNode(Integer.parseInt(remove));
            repaint();
            this.add(c);
            repaint();

        } else if (e.getSource() == addNode) {

            this.remove(this.c);
            GraphClass temp = this.c.graphClass;
            //this.add(w);

            String r = JOptionPane.showInputDialog("please enter the id of the node");
            String s1 = JOptionPane.showInputDialog("please enter the location of the node -x");
            String s2 = JOptionPane.showInputDialog("please enter the location of the node -y");
            String bla = s1 + "," + s2 + ",0.0";
            temp.addNode(new Node(Integer.parseInt(r), new GeoLocationClass(bla)));
            try {
                this.c = new GraphPanel(temp);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            this.add(c);
            this.repaint();
            this.revalidate();
            System.out.println("done");


        } else if (e.getSource() == addEdge) {

            this.remove(this.c);
            GraphClass temp = this.c.graphClass;
            //this.add(w);
            String src = JOptionPane.showInputDialog("please enter the location of the src");
            String w = JOptionPane.showInputDialog("please enter the id of the w");
            String dest = JOptionPane.showInputDialog("please enter the location of the dest");
            String bla = w + "," + dest + ",0.0";
            temp.connect(Integer.parseInt(src), Integer.parseInt(dest), Integer.parseInt(w));
            //temp.addNode(new Node(Integer.parseInt(src), new GeoLocationClass(bla)));
            try {
                this.remove(c);
                this.c = new GraphPanel(temp);
                this.add(c);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            //this.add(c);
            this.repaint();
            this.revalidate();
            System.out.println("done");


        } else if (e.getSource() == removeEdge) {
            String remove = JOptionPane.showInputDialog("please enter the edge (like this : '1,8' )");
            String[] s = remove.split(",");
            this.a.getGraph().removeEdge(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
            repaint();
            this.add(c);
            repaint();
        } else if (e.getSource() == nodeSize) {
            String message = "The Size Of The Nodes In The Graph is: " + a.getGraph().nodeSize();
            JOptionPane.showMessageDialog(new JFrame(), message, "Size Of Nodes", JOptionPane.DEFAULT_OPTION);
        } else if (e.getSource() == edgeSize) {
            String message = "The Size Of The Edges In The Graph is: " + a.getGraph().edgeSize();
            JOptionPane.showMessageDialog(new JFrame(), message, "Size Of Edges", JOptionPane.DEFAULT_OPTION);
        } else if (e.getSource() == tsp) {
            String list = JOptionPane.showInputDialog("please enter list of nodes (like this : '1,8,4')");
            String[] s = list.split(",");
            List<NodeData> l = new ArrayList<>();
            int i = 0;
            while (i < s.length) {
                l.add(a.getGraph().getNode(Integer.parseInt(s[i])));
                i++;
            }

            List<NodeData> w = a.tsp(l);
            String d = "";
            int j = 0;
            while (j < w.size()) {
                d = d + "-->" + w.get(j).getKey();
                j++;
            }
            JOptionPane.showMessageDialog(null, d, "tsp", JOptionPane.DEFAULT_OPTION);


        } else if ((e.getSource() == getNode)) {
            String node = JOptionPane.showInputDialog("please enter the id of the node");
            String s = this.a.getGraph().getNode(Integer.parseInt(node)).toString();
            JOptionPane.showMessageDialog(null, s, "get node", JOptionPane.DEFAULT_OPTION);
        } else if ((e.getSource() == getEdge)) {
            String edge = JOptionPane.showInputDialog("please enter the edge (like this : ' 1,8 ')");
            String[] w = edge.split(",");
            Edge q = (Edge) this.a.getGraph().getEdge(Integer.parseInt(w[0]), Integer.parseInt(w[1]));
            this.repaint();
            this.revalidate();
            j = 0;
            while (q == null && j < 3) {
                edge = JOptionPane.showInputDialog("Try again,this is the experience " + j + " please enter a valid edge (like this : '1,8')" + "You have " + (3 - j));
                w = edge.split(",");
                q = (Edge) this.a.getGraph().getEdge(Integer.parseInt(w[0]), Integer.parseInt(w[1]));
                j++;
            }
            String s = q.toString();
            JOptionPane.showMessageDialog(null, s, "get Edge", JOptionPane.DEFAULT_OPTION);


        }
        e.setSource(null);
    }
}


//package GUI;
//
//import api.*;
//
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;


//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class GraphFrame extends JFrame implements ActionListener {
//    GraphPanel c;
//    AlgoGraphClass a;
//    JMenuBar menuBar;
//    JMenu GraphMenu;
//    JMenu algorithms;
//    JMenuItem loadGraph;
//    JMenuItem saveGraph;
//    JMenuItem Sp;
//    JMenuItem SpL;
//    JMenuItem center;
//    JMenuItem isConectted;
//    JMenuItem removeNode;
//    JMenuItem addNode;
//    JMenuItem removeEdge;
//    JMenuItem nodeSize;
//    JMenuItem edgeSize;
//    JMenuItem tsp;
//    JMenuItem getNode;
//    JMenuItem getEdge;
//
//    int upd;
//
//    public GraphFrame(GraphClass c) throws IOException {
//        this.c = new GraphPanel(c);
//        this.a = new AlgoGraphClass();
//        this.a.init(c);
//        this.upd = 0;
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//        this.setLayout(new FlowLayout());
//        this.setTitle("My Graph");
//
//        menuBar = new JMenuBar();
//        GraphMenu = new JMenu("Graph setting");
//        algorithms = new JMenu("Algorithms");
//        menuBar.add(GraphMenu);
//        menuBar.add(algorithms);
//
//        loadGraph = new JMenuItem("load graph");
//        saveGraph = new JMenuItem("save graph");
//
//        center = new JMenuItem("get center");
//        Sp = new JMenuItem("shortest path");
//        SpL = new JMenuItem("shortest path list");
//        isConectted = new JMenuItem("check connection");
//        tsp = new JMenuItem("tsp");
//
//
//        removeNode = new JMenuItem("remove node");
//        removeEdge = new JMenuItem("remove edge");
//        getNode = new JMenuItem("get node data");
//        getEdge = new JMenuItem("get edge data");
//
//
//        addNode = new JMenuItem("add node");
//        nodeSize = new JMenuItem("get nodes size");
//        edgeSize = new JMenuItem("get edges size");
//
//
//        loadGraph.addActionListener(this);
//        saveGraph.addActionListener(this);
//        center.addActionListener(this);
//        Sp.addActionListener(this);
//        SpL.addActionListener(this);
//        isConectted.addActionListener(this);
//        removeNode.addActionListener(this);
//        addNode.addActionListener(this);
//        removeEdge.addActionListener(this);
//        nodeSize.addActionListener(this);
//        edgeSize.addActionListener(this);
//        tsp.addActionListener(this);
//        getEdge.addActionListener(this);
//        getNode.addActionListener(this);
//
//        GraphMenu.add(loadGraph);
//        GraphMenu.add(saveGraph);
//        GraphMenu.add(removeNode);
//        GraphMenu.add(removeEdge);
//        GraphMenu.add(getNode);
//        GraphMenu.add(getEdge);
//
//        GraphMenu.add(addNode);
//        GraphMenu.add(nodeSize);
//        GraphMenu.add(edgeSize);
//        algorithms.add(center);
//        algorithms.add(Sp);
//        algorithms.add(SpL);
//        algorithms.add(isConectted);
//        algorithms.add(tsp);
//
//        GraphMenu.setFont( new Font(Font.SERIF, Font.PLAIN,  16));
//        algorithms.setFont( new Font(Font.SERIF, Font.PLAIN,  16));
//
//
//        this.setJMenuBar(menuBar);
//
//
//        this.setVisible(true);
//
//
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == loadGraph) {
//            this.add(this.c);
//            revalidate();
//        }
//        else if (e.getSource() == saveGraph) {
//            String s = JOptionPane.showInputDialog("Please enter an address");
//            try {
//                this.a.save(s);
//                JOptionPane.showMessageDialog(null, "Saved", "save", JOptionPane.PLAIN_MESSAGE);
//
//            } catch (Exception exception) {
//                throw exception;
//            }
//        } else if (e.getSource() == center) {
//            JOptionPane.showMessageDialog(null, "The center of the graph is:" + this.a.center(), "Center", JOptionPane.PLAIN_MESSAGE);
//
//
//        } else if (e.getSource() == Sp) {
//            String ans1 = JOptionPane.showInputDialog("please enter the source");
//            String ans2 = JOptionPane.showInputDialog("please enter the destination");
//            JOptionPane.showMessageDialog(null, "The shortest path is:" + this.a.shortestPathDist(Integer.parseInt(ans1), Integer.parseInt(ans2)), "Shortest Path", JOptionPane.PLAIN_MESSAGE);
//        } else if (e.getSource() == SpL) {
//            String ans1 = JOptionPane.showInputDialog("please enter the source");
//            String ans2 = JOptionPane.showInputDialog("please enter the destination");
//            String s = "source node";
//            int i = 0;
//            List<NodeData> list = this.a.shortestPath(Integer.parseInt(ans1), Integer.parseInt(ans2));
//            while (i < list.size()) {
//                s = s + "-->" + list.get(i).getKey();
//                i++;
//            }
//            JOptionPane.showMessageDialog(null, "The shortest path is:" + s, "Shortest Path List", JOptionPane.PLAIN_MESSAGE);
//        } else if (e.getSource() == isConectted) {
//            JOptionPane.showMessageDialog(null, this.a.isConnected(), "Is Connected", JOptionPane.PLAIN_MESSAGE);
//
//        } else if ((e.getSource() == removeNode)) {
//            String remove = JOptionPane.showInputDialog("please enter the id of the node");
//
//            this.a.getGraph().removeNode(Integer.parseInt(remove));
//            repaint();
//            this.add(c);
//            repaint();
//
//        } else if (e.getSource() == addNode) {
//            try {
//                GraphPanel w = new GraphPanel(new GraphClass());
//                this.add(w);
//                String r = JOptionPane.showInputDialog("please enter the id of the node");
//                String s1 = JOptionPane.showInputDialog("please enter the location of the node -x");
//                String s2 = JOptionPane.showInputDialog("please enter the location of the node -y");
//                w.addNode(Integer.parseInt(r), Integer.parseInt(s1), Integer.parseInt(s2));
//                repaint();
//                this.add(w);
//                repaint();
//                System.out.println("done");
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//
//
//        } else if (e.getSource() == removeEdge) {
//            String remove = JOptionPane.showInputDialog("please enter the edge (like this : '1,8')");
//            String[] s = remove.split(",");
//            this.a.getGraph().removeEdge(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
//            repaint();
//            this.add(c);
//            repaint();
//        } else if (e.getSource() == nodeSize) {
//            String message = "The Size Of The Nodes In The Graph is: " + a.getGraph().nodeSize();
//            JOptionPane.showMessageDialog(new JFrame(), message, "Size Of Nodes", JOptionPane.DEFAULT_OPTION);
//        } else if (e.getSource() == edgeSize) {
//            String message = "The Size Of The Edges In The Graph is: " + a.getGraph().edgeSize();
//            JOptionPane.showMessageDialog(new JFrame(), message, "Size Of Edges", JOptionPane.DEFAULT_OPTION);
//        } else if (e.getSource() == tsp) {
//            String list = JOptionPane.showInputDialog("please enter list of nodes (like this : '1,8,4')");
//            String[] s = list.split(",");
//            List<NodeData> l = new ArrayList<>();
//            int i = 0;
//            while (i < s.length) {
//                l.add(a.getGraph().getNode(Integer.parseInt(s[i])));
//                i++;
//            }
//
//            List<NodeData> w = a.tsp(l);
//            String d = "";
//            int j = 0;
//            while (j < w.size()) {
//                d = d + "-->" + w.get(j).getKey();
//                j++;
//            }
//            JOptionPane.showMessageDialog(null, d, "tsp", JOptionPane.DEFAULT_OPTION);
//
//
//        } else if ((e.getSource() == getNode)) {
//            String node = JOptionPane.showInputDialog("please enter the id of the node");
//            String s = this.a.getGraph().getNode(Integer.parseInt(node)).toString();
//            JOptionPane.showMessageDialog(null, s, "get node", JOptionPane.DEFAULT_OPTION);
//        } else if ((e.getSource() == getEdge)) {
//            String edge = JOptionPane.showInputDialog("please enter the edge (like this : '1,8')");
//            String[] w = edge.split(",");
//            Edge q = (Edge) this.a.getGraph().getEdge(Integer.parseInt(w[0]), Integer.parseInt(w[1]));
//            while (q == null) {
//                edge = JOptionPane.showInputDialog("please enter a valid edge (like this : '1,8')");
//                w = edge.split(",");
//                q = (Edge) this.a.getGraph().getEdge(Integer.parseInt(w[0]), Integer.parseInt(w[1]));
//            }
//            String s = q.toString();
//            JOptionPane.showMessageDialog(null, s, "get Edge", JOptionPane.DEFAULT_OPTION);
//
//
//        }
//    }
//}
