import java.util.List;

public class Iris {
    private List<Double> data;
    private String name;

    public Iris(List<Double> data, String name){
        this.data = data;
        this.name = name;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Iris{" +
                "data=" + data +
                ", name='" + name + '\'' +
                '}';
    }
}
