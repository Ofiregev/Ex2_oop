package classes;


import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;

@SuppressWarnings("unchecked")

public class GraphClass implements DirectedWeightedGraph {
    private HashMap<Integer, Node> NodesHashMap;
    private HashMap<String, Edge> EdgesHashMap;
    private int mc = 0;

    public GraphClass() {
        this.NodesHashMap = new HashMap<>();
        this.EdgesHashMap = new HashMap<>();

    }

    //Copy Constructor
    public GraphClass(GraphClass c) {
        nodeIterator iter = (GraphClass.nodeIterator) c.nodeIter();
        NodesHashMap = new HashMap<>();
        EdgesHashMap = new HashMap<>();
        this.mc = c.mc;
        while (iter.hasNext()) {
            this.addNode(c.getNode(iter.next().getKey()));
            for (Object key : c.NodesHashMap.get(iter.curr.getKey()).getHashMap().keySet()) {
                this.NodesHashMap.get(iter.curr.getKey()).getHashMap().put(key, c.NodesHashMap.get(iter.curr.getKey()).getHashMap().get(key));
                this.connect(iter.curr.getKey(), (Integer) key, (Double) c.NodesHashMap.get(iter.curr.getKey()).getHashMap().get(key));

            }


        }

    }

    @Override
    public NodeData getNode(int key) {

        return this.NodesHashMap.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        String s=src +","+dest;
        if(EdgesHashMap.get(s)==null){
            return null;
        } else {
            return EdgesHashMap.get(s);
        }
    }

    public HashMap<Integer, Node> getNodesHashMap() {
        return this.NodesHashMap;
    }

    public HashMap<String, Edge> getEdgesHashMap() {
        return this.EdgesHashMap;
    }

    @Override
    public void addNode(NodeData n) {
        if (n != null) {
            this.NodesHashMap.put(n.getKey(), (Node) n);
            mc++;
        }

    }


    @Override
    public void connect(int src, int dest, double w) {
        Edge e = new Edge(src, dest, w);
        String s = src + "," + dest;
        this.NodesHashMap.get(src).getHashMap().put(dest, w);

        this.EdgesHashMap.put(s, e);

    }

    //this function checks if there is an edge between two nodes
    public boolean hasEdge(int src, int dst) {
        String s = src + "," + dst;
        if (EdgesHashMap.containsKey(s)) {
            return true;
        }
        return false;
    }

    //return the weight of the edge by its src and dst
    public double getWeightBySrc(int src, int dst) {
        if (!hasEdge(src, dst)) {
            return -1;
        }
        String s = src + "," + dst;
        return this.EdgesHashMap.get(s).getWeight();

    }

    public int[] getEdgeBySrc(int src) {
        if (this.NodesHashMap.get(src).getHashMap() == null) {
            return null;
        }
        int[] dst = new int[this.NodesHashMap.get(src).getHashMap().size()];
        int i = 0;
        for (Object key : this.NodesHashMap.get(src).getHashMap().keySet()) {
            dst[i] = (int) key;
            i++;
        }
        return dst;
    }


    @Override
    public Iterator<NodeData> nodeIter() {
        nodeIterator iterator = new nodeIterator();
        return iterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        EdgeIterator edgeIterator = new EdgeIterator();
        return edgeIterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        this.mc++;
        Node node = new Node(key, (GeoLocationClass) this.NodesHashMap.get(key).getLocation());
        Iterator<NodeData> nodeDataIterator = nodeIter();
        while (nodeDataIterator.hasNext()) {
            Node curr = (Node) nodeDataIterator.next();
            if (NodesHashMap.get(curr.getKey()).getHashMap().containsKey(key)) {
                int[] e = getEdgeBySrc(key);
                for (int i = 0; i < e.length; i++) {
                    String s = key + "," + e[i];
                    this.EdgesHashMap.remove(s);
                }
                String s = curr.getKey() + "," + key;
                this.EdgesHashMap.remove(s);
                this.NodesHashMap.get(curr.getKey()).getHashMap().remove(key);

            }
        }
        this.NodesHashMap.remove(key);
        return node;
    }


    @Override
    public EdgeData removeEdge(int src, int dest) {
        this.mc++;
        String s = src + "," + dest;
        Edge e = this.EdgesHashMap.get(s);
        this.NodesHashMap.get(src).getHashMap().remove(dest);
        this.EdgesHashMap.remove(s);
        return e;
    }

    @Override
    public int nodeSize() {
        return this.NodesHashMap.size();
    }

    @Override
    public int edgeSize() {
        return this.EdgesHashMap.size();
    }

    @Override
    public int getMC() {
        return this.mc;
    }

    private class nodeIterator implements Iterator<NodeData> {
        private int myMC;
        private Iterator<Node> iter;
        private NodeData curr;

        public nodeIterator() {
            myMC = mc;
            iter = NodesHashMap.values().iterator();
        }

        private void isValide() {
            if (myMC != mc) {
                throw new RuntimeException("the iterator has change!");
            }
        }

        @Override
        public boolean hasNext() {
            isValide();
            return iter.hasNext();
        }

        @Override
        public NodeData next() {
            isValide();
            if (!hasNext()) {
                return null;
            }
            curr = iter.next();
            return curr;
        }

        @Override
        public void remove() {
            isValide();
            ++myMC;
            iter.remove();
            removeNode(curr.getKey());
        }
    }

    private class EdgeIterator implements Iterator<EdgeData> {
        private int myMC;
        private Iterator<Edge> iter;
        private EdgeData curr;

        public EdgeIterator() {
            myMC = mc;
            iter = EdgesHashMap.values().iterator();
        }

        private void isValide() {
            if (myMC != mc) {
                throw new RuntimeException("the iterator has change!");
            }
        }

        @Override
        public boolean hasNext() {
            isValide();
            return iter.hasNext();
        }

        @Override
        public EdgeData next() {
            isValide();
            curr = iter.next();
            return curr;
        }

        @Override
        public void remove() {
            isValide();
            ++myMC;
            iter.remove();
            removeEdge(curr.getSrc(), curr.getDest());
        }
    }

}


