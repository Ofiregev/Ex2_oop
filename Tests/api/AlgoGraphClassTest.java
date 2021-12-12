package api;

import classes.AlgoGraphClass;
import classes.GraphClass;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlgoGraphTest {


    @Test
    void getGraph() {
        DirectedWeightedGraph g0 = GraphClassTest.graph_creator(10, 30, 1);
        DirectedWeightedGraphAlgorithms ag0 = new AlgoGraphClass();
        ag0.init(g0);
        assertEquals(g0, ag0.getGraph());
    }

    @Test
    void copy() {
        DirectedWeightedGraph g0 = GraphClassTest.graph_creator(10, 30, 1);
        DirectedWeightedGraphAlgorithms ag0 = (DirectedWeightedGraphAlgorithms) new AlgoGraphClass();
        ag0.init(g0);
        DirectedWeightedGraph g1 = ag0.copy();
        assertEquals(g0.nodeSize(), g1.nodeSize());
        assertEquals(g0.edgeSize(), g1.edgeSize());
        g1.removeNode(0);
        assertNotEquals(g0.nodeSize(), g1.nodeSize());
    }

    @Test
    void isConnected() {
        DirectedWeightedGraph g0 = small_graph();
        DirectedWeightedGraphAlgorithms ag0 = new AlgoGraphClass();
        ag0.init(g0);
        assertTrue(ag0.isConnected());
        makeconnected(ag0.getGraph());
        assertTrue(ag0.isConnected());
        g0.removeNode(0);
        g0.removeNode(1);
        g0.removeNode(5);
        ag0.init(g0);
        assertFalse(ag0.isConnected());
    }

    @Test
    void shortestPathDist() {
        DirectedWeightedGraph g0 = small_graph();
        DirectedWeightedGraphAlgorithms ag0 = new AlgoGraphClass();
        ag0.init(g0);
        double d = ag0.shortestPathDist(6, 10);
        assertEquals(11.5, d);
        g0.removeNode(1);
        d = ag0.shortestPathDist(0, 4);
        assertEquals(3, d);
        d = ag0.shortestPathDist(2, 0);
        assertEquals(Integer.MAX_VALUE, d);
    }

    @Test
    void shortestPath() {

        DirectedWeightedGraph g0 = small_graph();
        DirectedWeightedGraphAlgorithms ag0 = new AlgoGraphClass();
        ag0.init(g0);
        List<NodeData> sp = ag0.shortestPath(0, 10);
        List<NodeData> check = new ArrayList<>();
        check.add(g0.getNode(0));
        check.add(g0.getNode(1));
        check.add(g0.getNode(5));
        check.add(g0.getNode(7));
        check.add(g0.getNode(10));
        assertEquals(sp, check);


    }

    @Test
    void save() {
        DirectedWeightedGraph g0 = GraphClassTest.graph_creator(10, 30, 1);
        DirectedWeightedGraph ga = new GraphClass((GraphClass) g0);
        DirectedWeightedGraphAlgorithms ag0 = new AlgoGraphClass();
        System.out.println(g0.toString());
        System.out.println(ga.toString());
        ag0.init(g0);
        String str = "graph.json";
        ag0.save(str);
        boolean bo = ag0.save(str);
        assertTrue(bo);
    }


    @Test
    void save_load() {
        DirectedWeightedGraph g0 = GraphClassTest.graph_creator(10, 30, 1);
        DirectedWeightedGraph ga = new GraphClass((GraphClass) g0);
        DirectedWeightedGraphAlgorithms ag0 = new AlgoGraphClass();
        ag0.init(g0);
        String str = "C:\\Users\\avi44\\OneDrive\\מסמכים\\GitHub\\Ex2_oop\\saved1.json";
        ag0.save(str);
        boolean bo = ag0.save(str);
        assertTrue(bo);
        boolean b = ag0.load(str);
        assertTrue(b);


    }

    /**
     * create a small graph
     */
    public static DirectedWeightedGraph small_graph() {
        DirectedWeightedGraph g0 = GraphClassTest.graph_creator(11);
        g0.connect(0, 1, 1);
        g0.connect(0, 2, 2);
        g0.connect(0, 3, 3);

        g0.connect(1, 4, 0.5);
        g0.connect(1, 5, 1);
        g0.connect(2, 4, 1);
        g0.connect(3, 5, 10);
        g0.connect(3, 6, 100);
        g0.connect(5, 7, 1.1);
        g0.connect(6, 7, 10);
        g0.connect(7, 10, 2);
        g0.connect(6, 8, 11);
        g0.connect(8, 10, 0.5);
        g0.connect(4, 10, 30);
        g0.connect(3, 9, 10);
        g0.connect(10, 9, 1);


        return g0;
    }

    private void makeconnected(DirectedWeightedGraph g) {
        Iterator<NodeData> i = g.nodeIter();
        NodeData n = i.next();
        while (i.hasNext()) {
            NodeData t = i.next();
            g.connect(n.getKey(), t.getKey(), 1);
            g.connect(t.getKey(), n.getKey(), 1);
        }
    }

}