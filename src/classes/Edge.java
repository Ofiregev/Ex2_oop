package classes;

import api.EdgeData;

public class Edge implements EdgeData {
    private int src;
    private int dst;
    private double weight;

    //Default constructor
    public Edge(){
        this.src = -1;
        this.dst = -1;
        this.weight = 0;
    }
    //Constructor
    public Edge(int src, int dst, double weight){
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    @Override
    public int getSrc() {
        return this.src;
    }

    @Override
    public int getDest() {
        return this.dst;
    }

    @Override
    public double getWeight() {
        return this.weight;
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
    public String toString(){
        return "Edge:" +"("+this.src+"," +this.dst+")"+ " weight: " + this.weight;
    }
}
