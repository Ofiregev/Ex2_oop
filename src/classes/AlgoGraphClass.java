package classes;


import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AlgoGraphClass implements DirectedWeightedGraphAlgorithms {
    GraphClass g;
    private HashMap<Integer, Double> dist;//save the shortest path to every node
    private HashMap<Integer, Integer> parent;//save the parent of the node
    private HashMap<Integer, Boolean> visited;//for dfs
    private PriorityQueue<Node> nodePriorityQueue;//for dijkstra
    private HashMap<Integer, Double> longestPath;
    private Set p;
    private int update;

    @Override
    public void init(DirectedWeightedGraph g) {
        this.g = (GraphClass) g;
        this.dist = new HashMap<>();
        this.parent = new HashMap<>();
        this.update = -1;
        Iterator<NodeData> iterator = this.g.nodeIter();
        while (iterator.hasNext()) {
            Node curr = (Node) iterator.next();
            dist.put(curr.getKey(), (double) Integer.MAX_VALUE);
            parent.put(curr.getKey(), -1);

        }

        this.visited = new HashMap<>();
        this.p = new HashSet<>();
        this.nodePriorityQueue = new PriorityQueue<>(new Node_comp());
        this.longestPath = new HashMap<>();

    }


    @Override
    public DirectedWeightedGraph getGraph() {

        return this.g;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return new GraphClass(this.g);
    }

    @Override
    public boolean isConnected() {
        setMyGraph(this.g);
        Iterator<NodeData> iterator = this.g.nodeIter();
        dfs(iterator.next().getKey());
        if (visited.containsValue(false)) {
            return false;
        }
        setMyGraph(reverseDfs());
        Iterator<NodeData> iterator2 = this.g.nodeIter();
        dfs(iterator2.next().getKey());
        if (visited.containsValue(false)) {
            return false;
        }

        return true;
    }

    public void dfs(int src) {
        visited.put(src, true);
        if (this.g.getEdgeBySrc(src) == null) {
            return;
        }
        int[] nei = this.g.getEdgeBySrc(src);
        for (int i = 0; i < nei.length; i++) {
            int t = nei[i];
            if (!visited.get(t)) {
                dfs(t);

            }

        }

    }

    public GraphClass reverseDfs() {
        GraphClass graphClass = new GraphClass();
        for (Node n : this.g.getNodesHashMap().values()) {
            Node v = new Node(n.getKey(), (GeoLocationClass) n.getLocation());
            graphClass.addNode(v);
        }
        for (Edge e : this.g.getEdgesHashMap().values()) {
            graphClass.connect(e.getDest(), e.getSrc(), e.getWeight());
        }
        return graphClass;
    }

    public void setMyGraph(GraphClass c) {
        this.visited = new HashMap<>();
        Iterator<NodeData> iterator = c.nodeIter();
        while (iterator.hasNext()) {
            Node curr = (Node) iterator.next();
            visited.put(curr.getKey(), false);

        }

    }


    public void Dijkstra(int src) {
        this.dist = new HashMap<>();
        this.parent = new HashMap<>();
        this.p = new HashSet<>();

        Iterator<NodeData> iterator = this.g.nodeIter();
        while (iterator.hasNext()) {
            Node curr = (Node) iterator.next();
            dist.put(curr.getKey(), (double) Integer.MAX_VALUE);
            parent.put(curr.getKey(), -1);

        }
        nodePriorityQueue.add(new Node(src, 0));
        dist.put(src, (double) 0);
        while (p.size() != dist.size()) {
            if (nodePriorityQueue.isEmpty()) {
                return;
            }
            int v = nodePriorityQueue.remove().getKey();
            if (p.contains(v)) {
                continue;
            }
            p.add(v);
            int[] nei = this.g.getEdgeBySrc(v);
            for (int i = 0; i < nei.length; i++) {
                int t = nei[i];
                relax(v, t);


            }
        }
    }

    public void relax(int src, int dest) {
        if (dist.get(dest) > dist.get(src) + this.g.getWeightBySrc(src, dest)) {
            dist.put(dest, dist.get(src) + this.g.getWeightBySrc(src, dest));
            parent.put(dest, src);
            nodePriorityQueue.add(new Node(dest, dist.get(src) + this.g.getWeightBySrc(src, dest)));

        }
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if (this.g.getNode(dest) == null || this.g.getNode(src) == null) {
            return -1;
        }
        if (update == 0||update ==-1) {
            Dijkstra(src);
        }
            return dist.get(dest);
        }


    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        Dijkstra(src);
        List<NodeData> list = new LinkedList<>();
        int i = dest;
        while ((int) parent.get(i) != -1) {
            Node n = (Node) this.g.getNode(i);
            list.add(n);
            i = (int) parent.get(n.getKey());
        }
        Node s = (Node) this.g.getNode(src);
        list.add(s);
        List<NodeData> ans = new LinkedList<>();
        int j = list.size()-1;
        while (j>=0) {
            Node n = (Node) list.get(j);
            ans.add(n);
            j--;
        }


        return ans;
    }


    @Override
    public NodeData center() {
        Iterator<NodeData> iterator = this.g.nodeIter();
        while (iterator.hasNext()) {
            Node v = (Node) iterator.next();
            longestPath.put(v.getKey(), (double) 0);
        }
        Iterator<NodeData> iterator1 = this.g.nodeIter();
        while (iterator1.hasNext()) {
            Node i = (Node) iterator1.next();
            update++;
            Iterator<NodeData> iterator2 = this.g.nodeIter();
            while (iterator2.hasNext()) {
                Node j = (Node) iterator2.next();
                double t = shortestPathDist(i.getKey(), j.getKey());
                update++;
                if (longestPath.get(i.getKey()) < t) {
                    longestPath.put(i.getKey(), t);
                }
            }
            update=-1;
        }
        Node n = (Node) this.g.getNode(returnMin(longestPath));
        return n;
    }

    public int returnMin(HashMap hashMap) {
        double minVm = Integer.MAX_VALUE;
        int indexMin = -1;
        Iterator<Map.Entry<Integer, Double>> iterator3 = hashMap.entrySet().iterator();
        while (iterator3.hasNext()) {
            Map.Entry<Integer, Double> curr = iterator3.next();
            if ((double) hashMap.get(curr.getKey()) < minVm) {
                minVm = (double) hashMap.get(curr.getKey());
                indexMin = curr.getKey();
            }
        }
        return indexMin;

    }


    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        for (NodeData n : cities) {
            Dijkstra(n.getKey());
        }
        double minValue = Double.POSITIVE_INFINITY;
        List<NodeData> path = new ArrayList<>();
        for (NodeData i : cities) {
            List<NodeData> cur = new ArrayList();
            double value;
            value = GreedyPath(i, new ArrayList<>(cities), cur);
            if (value < minValue) {
                minValue = value;
                path = cur;
            }
        }

        return path;
    }
    public double GreedyPath(NodeData i, List<NodeData> c, List<NodeData> v)
    {
        NodeData first = i;
        v.add(i);
        c.remove(i);
        NodeData index = null;
        double sum =0;

        while(!c.isEmpty())
        {
            double min = Double.POSITIVE_INFINITY;
            for (NodeData j:c) {
                Dijkstra(i.getKey());
                if(this.dist.get(j.getKey()) < min)
                {
                    min = this.dist.get(j.getKey());
                    index = j;
                }
            }
            sum+=min;
            boolean f= true;
            for (NodeData j:shortestPath(i.getKey(),index.getKey()))
            {
                if(f==true)
                {
                    f =false;
                    continue;
                }
                v.add(j);
            }
            i = index;
            c.remove(index);
        }

        v.add(first);
        Dijkstra(i.getKey());
        sum+= dist.get(first.getKey());
//        for (NodeData n : ins) {
//            System.out.print(n.getKey() + ",");
//        }
//        System.out.printf(" : " + sum);
//        System.out.println();
        return sum;
    }


    @Override
    public boolean save(String file) {
        JSONObject json = new JSONObject();
        JSONArray Nodes = new JSONArray();
        JSONArray Edges = new JSONArray();
        Iterator<NodeData> i = this.g.nodeIter();
        while (i.hasNext()) {
            Node v = (Node) i.next();
            JSONObject jnode = new JSONObject();
            jnode.put("pos", v.getLocation().toString());
            jnode.put("id", v.getKey());
            Nodes.add(jnode);

        }
        Iterator<EdgeData> j = this.g.edgeIter();
        while (j.hasNext()) {
            Edge edge = (Edge) j.next();
            JSONObject jedge = new JSONObject();
            jedge.put("src", edge.getSrc());
            jedge.put("w", edge.getWeight());
            jedge.put("dest", edge.getDest());
            Edges.add(jedge);
        }
        json.put("Edges", Edges);
        json.put("Nodes", Nodes);
        try (FileWriter fw = new FileWriter(file)) {
            fw.write(json.toJSONString());
            fw.flush();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    @Override
    public boolean load(String file) {
        {
            try {
                this.g = new GraphClass();
                Object o = new JSONParser().parse(new FileReader(file));
                JSONObject jsonObject = (JSONObject) o;
                JSONArray nodes = (JSONArray) jsonObject.get("Nodes");
                Iterator i = nodes.iterator();
                while (i.hasNext()) {
                    HashMap<String, Object> objectHashMap = (HashMap<String, Object>) i.next();
                    GeoLocationClass location = new GeoLocationClass((String) objectHashMap.get("pos"));
                    int id = Integer.parseInt(String.valueOf(objectHashMap.get("id")));
                    Node n = new Node(id, location);
                    this.g.addNode(n);
                }
                JSONArray edges = (JSONArray) jsonObject.get("Edges");
                Iterator<HashMap> j = edges.iterator();
                while (j.hasNext()) {
                    HashMap<String, Object> objectHashMap2 = (HashMap<String, Object>) j.next();
                    int src = (int) (long) objectHashMap2.get("src");
                    int dest = (int) (long) objectHashMap2.get("dest");
                    double weight = (double) objectHashMap2.get("w");
                    this.g.connect(src, dest, weight);
                }
            } catch (ParseException | IOException e) {
                return false;
            }
        }

        System.out.println("true");
        return true;
    }

    public static void main(String[] args) {
        DirectedWeightedGraph d =new GraphClass();
        AlgoGraphClass a = new AlgoGraphClass();
//        DirectedWeightedGraph f = new GraphClass();
//        Node n0 = new Node(0, new GeoLocationClass("1,1,0"));
//        Node n1 = new Node(1, new GeoLocationClass("0,0,0"));
//        Node n2 = new Node(2, new GeoLocationClass("0,1,0"));
//
//
//        f.addNode(n0);
//        f.addNode(n1);
//        f.addNode(n2);
//        f.connect(n0.getKey(), n1.getKey(), 2);
//        f.connect(n1.getKey(), n0.getKey(), 4);
//
//        f.connect(n1.getKey(), n2.getKey(), 3);
//
//        f.connect(n2.getKey(), n1.getKey(), 6);
//
//        f.connect(n0.getKey(), n2.getKey(), 5);
//
//        f.connect(n2.getKey(), n0.getKey(), 2);


        List<NodeData> L =new ArrayList<>();
        L.add(a.g.getNode(1));
        L.add(a.g.getNode(2));
        L.add(a.g.getNode(3));
        System.out.println(a.tsp(L).toString());
        System.out.println(a.center());

    }
}


