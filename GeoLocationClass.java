package api;

public class GeoLocationClass implements GeoLocation {
    private String location;
    private String[] s;
    private double x, y, z;


    //constructor
    public GeoLocationClass(String location) {
        this.location = location;
        this.s = location.split(",");
    }

    @Override
    public double x() {
        return Double.parseDouble(s[0]);
    }

    @Override
    public double y() {
        return Double.parseDouble(s[1]);
    }

    @Override
    public double z() {
        return Double.parseDouble(s[2]);
    }

    @Override
    public double distance(GeoLocation g) {
        return Math.sqrt(Math.pow(this.x - g.x(), 2) + Math.pow(this.y - g.y(), 2) + Math.pow(this.z - g.z(), 2));
    }
    public String toString(){
        return this.location;
    }
}
