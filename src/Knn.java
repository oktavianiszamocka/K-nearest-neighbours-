public class Knn implements Comparable<Knn>{
    private String iris;
    private double distance;

    public Knn(String iris, double distance){
        this.iris = iris;
        this.distance = distance;
    }

    public String getIris() {
        return iris;
    }

    public void setIris(String iris) {
        this.iris = iris;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Knn{" +
                "iris='" + iris + '\'' +
                ", distance=" + distance +
                '}';
    }

    @Override
    public int compareTo(Knn o) {
        if(getDistance() > o.getDistance() )
            return 1;
        if(getDistance() == o.getDistance())
            return 0;
        else
            return -1;
    }
}
