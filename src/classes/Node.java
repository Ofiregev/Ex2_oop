package classes;


import api.GeoLocation;
import api.NodeData;

import java.util.HashMap;
import java.util.Random;

public class Node implements NodeData {
    private int id;
    private GeoLocationClass location;
    private HashMap<Integer, Double> edge_hash_Map;
    private double weight; //this is for the graph algorithms


    //Default constructor
    public Node() {
        this.id = -1;
        this.location = new GeoLocationClass("0,0,0");
        this.edge_hash_Map = new HashMap<>();
        this.weight = -1;

    }

    //constructor
    public Node(int id, GeoLocationClass location) {
        this.id = id;
        this.location = location;
        this.edge_hash_Map = new HashMap<>();

    }

    //for algorithms
    public Node(int id, double weight) {
        this.id = id;
        this.weight = weight;
    }
    //for tests
    public Node(int i) {
        Random r = new Random();
        String t=String.valueOf(r.nextInt());
        this.id = i;
        this.location = new GeoLocationClass("0,0,0");
        this.edge_hash_Map = new HashMap<>();

    }


    @Override
    public int getKey() {
        return this.id;
    }

    @Override
    public GeoLocation getLocation() {
        return this.location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location = (GeoLocationClass) p;

    }


    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;

    }

    public HashMap getHashMap() {
        return this.edge_hash_Map;
    }


    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {

    }


    public String toString() {
        return "Node id:" + this.id + " Node pos:" + this.location.toString();
    }
}
