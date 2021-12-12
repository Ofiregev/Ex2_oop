package api;

import classes.GraphClass;
import classes.Node;

import java.util.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
/**
 * the goal of this test class is to check the reliability of DWGraph_DS.
 * part of the testes taken from or based on the testes from the course's github.(including creating graph)
 */
class GraphClassTest {
    private DirectedWeightedGraph g;
    private static Random _rnd = null;
    GraphClassTest(){
        this.g = AlgoGraphTest.small_graph();
    }
    /**
     * check the getV function
     */

    @org.junit.jupiter.api.Test
    void getV() {
        Iterator<NodeData> i=this.g.nodeIter();
        assertTrue(i.next() instanceof NodeData);

    }
    /**
     * check the getE function
     */
    @org.junit.jupiter.api.Test
    void getE() {
        Iterator<EdgeData> i=this.g.edgeIter();
        assertTrue(i.next() instanceof EdgeData);

    }
    /**
     * check the removeNode function-removing node from the graph then check how many node are there in the graph
     */
    @org.junit.jupiter.api.Test
    void removeNode() {

        assertEquals(16,g.edgeSize());
        g.removeNode(10);
        assertEquals(10,g.nodeSize());
        assertEquals(12,g.edgeSize());
        g.removeNode(9);
        assertEquals(9,g.nodeSize());

    }
    /**
     * check the removeEdge function-removing edge from the graph then check how many edges are there in the graph
     */
    @org.junit.jupiter.api.Test
    void removeEdge() {

        assertEquals(16,g.edgeSize());
        g.removeEdge(0,1);
        assertEquals(15,g.edgeSize());
        g.removeEdge(0,1);
        assertEquals(15,g.edgeSize());
    }
    /**
     * check the nodeSize function-removing node from the graph then check how many node are there in the graph
     */
    @org.junit.jupiter.api.Test
    void nodeSize() {

        assertEquals(11,g.nodeSize());
        g.removeNode(10);
        assertEquals(10,g.nodeSize());
        g.removeNode(6);
        assertEquals(9,g.nodeSize());
    }
    /**
     * check the edgeSize function-removing edge from the graph then check how many edges are there in the graph
     */


    /**
     * check the getNode function
     */
    @org.junit.jupiter.api.Test
    void getNode() {
        DirectedWeightedGraph g2=new GraphClass();
        NodeData n=new Node();
        g2.addNode(n);
        NodeData n2=g2.getNode(n.getKey());
        assertEquals(n,n2);
    }

    /**
     * check the addNode function-add node to the graph and check if the node is found in the graph
     */
    @org.junit.jupiter.api.Test
    void addNode() {
        DirectedWeightedGraph g2=new GraphClass();
        NodeData n=new Node();
        g2.addNode(n);
        assertNotNull(g2.getNode(n.getKey()));
    }
    /**
     * check the connect function-connect two node in the graph and check how many edges in the graph
     */
    @org.junit.jupiter.api.Test
    void connect() {
        DirectedWeightedGraph g2=new GraphClass();
        NodeData n=new Node(1);
        g2.addNode(n);
        NodeData n2=new Node(2);
        g2.addNode(n2);
        g2.connect(n.getKey(), n2.getKey(), 1);
        assertEquals(1,g2.edgeSize());
        g2.connect(n2.getKey(), n.getKey(), 1);
        assertEquals(2,g2.edgeSize());


    }

    ///////////////////////////////////

    /**
     * Generate a random graph with v_size nodes and e_size edges
     * @param v_size
     * @param e_size
     * @param seed
     * @return
     */

    public static DirectedWeightedGraph graph_creator(int v_size, int e_size, int seed) {
        DirectedWeightedGraph g = new GraphClass();
        _rnd = new Random(seed);
        int[] nodes = new int[v_size];
        for (int i = 0; i < v_size; i++) {
            NodeData n = new Node(i);
            g.addNode(n);
            nodes[i]=i;
        }
        // Iterator<node_data> itr = V.iterator(); // Iterator is a more elegant and generic way, but KIS is more important

        while (g.edgeSize() < e_size) {
            int a = nextRnd(0, v_size);
            int b = nextRnd(0, v_size);
            int i = nodes[a];
            int j = nodes[b];
            double w = _rnd.nextDouble();
            g.connect(i, j, w);
        }

        return g;
    }
    private static int nextRnd(int min, int max) {
        double v = nextRnd(0.0+min, (double)max);
        int ans = (int)v;
        return ans;
    }
    private static double nextRnd(double min, double max) {
        double d = _rnd.nextDouble();
        double dx = max-min;
        double ans = d*dx+min;
        return ans;
    }
    public static DirectedWeightedGraph graph_creator(int v_size) {
        DirectedWeightedGraph g = new GraphClass();
        for (int i = 0; i < v_size; i++) {
            NodeData n = new Node(i);
            g.addNode(n);
        }
        return g;
    }
    public static DirectedWeightedGraph small_graph() {
        DirectedWeightedGraph g0 = GraphClassTest.graph_creator(11);
        g0.connect(0,1,1);
        g0.connect(0,2,2);
        g0.connect(0,3,3);

        g0.connect(1,4,0.5);
        g0.connect(1,5,1);
        g0.connect(2,4,1);
        g0.connect(3, 5,10);
        g0.connect(3,6,100);
        g0.connect(5,7,1.1);
        g0.connect(6,7,10);
        g0.connect(7,10,2);
        g0.connect(6,8,11);
        g0.connect(8,10,0.5);
        g0.connect(4,10,30);
        g0.connect(3,9,10);
        g0.connect(10,9,1);


        return g0;
    }

    /**
     * Simple method for returning an array with all the node_data of the graph,
     * Note: this should be using an Iterator<node_edge> to be fixed in Ex1
     * @param g
     * @return
     */
    private static int[] nodes(DirectedWeightedGraph g) {
        int size = g.nodeSize();
        Collection<NodeData> V = (Collection<NodeData>) g.getEdge(2,3);
        NodeData[] nodes = new NodeData[size];
        V.toArray(nodes); // O(n) operation
        int[] ans = new int[size];
        for(int i=0;i<size;i++) {ans[i] = nodes[i].getKey();}
        Arrays.sort(ans);
        return ans;
    }


}