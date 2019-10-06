import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static List<Iris> dataTrain;
    static List<Iris>  dataTest;
    static List<Iris> userData;
    static List<Double> Data;
    static List<String> storeIrisName;
    static int k = 0;



    public static void main(String[] args) {
        dataTrain = new ArrayList<>();
        dataTest = new ArrayList<>();
        userData = new ArrayList<>();
        storeIrisName = new ArrayList<>();
        Data = new ArrayList<>();

        getInput();
        File train= new File("src/train.txt");
        File test = new File("src/test.txt");
        readFile(train, dataTrain);
        readFile(test, dataTest);

        setDecisionToNull();
        lookDistance(dataTest, dataTrain);
        lookDistance(userData, dataTrain);

        System.out.println(dataTrain.size());
        System.out.println("accurancy :" + getAccurancy());
        System.out.println("Result : " + userData.get(0).getName());

    }

    public static void getInput(){
        Scanner scanner =  new Scanner(System.in);
        System.out.println("Insert k-number");
        k = scanner.nextInt();
        for(int i = 0; i < 4; i++){
            System.out.println("Insert the Iris data");
            double atrribute = scanner.nextDouble();
            Data.add(atrribute);
        }

        userData.add(new Iris(Data, ""));

    }

    public static void readFile(File file, List<Iris> list) {
        try {
            BufferedReader input = new BufferedReader(new FileReader(file));
            String line;

            while( (line = input.readLine()) != null) {
                inputParse(line, list);

            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void inputParse(String line, List<Iris> list) {
        List <Double> listofValue = null;
        String [] output = line.split(",");
        String name = null;
        listofValue = new ArrayList<>();
        for(int i = 0; i< output.length; i++) {
            if(!output[i].contains("Iris")) {
                listofValue.add(new Double(Double.parseDouble(output[i])));
            }
            else {
                name = output[i];
            }
        }
        list.add(new Iris(listofValue, name));
    }


    public static Double substract(Double test, Double train) {
        return test-train;
    }

    public static void lookDistance(List<Iris> Test, List<Iris> Train) {
        List<Knn> listdistance = null;

        double result = 0;
        for(int i = 0; i < Test.size(); i++) {
            //open the list of iris test
            // i = 0 - 30;
            listdistance = new ArrayList<>();
            for(int n = 0; n < Train.size(); n++) {
                //open list of iris train
                // n = 0 - 120
                for (int j = 0; j < Train.get(n).getData().size(); j++) {
                    //open list of double
                    result += Math.pow(substract(Test.get(i).getData().get(j), Train.get(n).getData().get(j)), 2);
                }
                result = Math.sqrt(result);
                listdistance.add(new Knn(Train.get(n).getName(), result));
            }
            sortingbyK(listdistance, Test.get(i));
        }
    }

    public static void setDecisionToNull(){
        for(Iris i : dataTest){
            storeIrisName.add(i.getName());
            i.setName("");
        }
    }
    public static void sortingbyK(List<Knn> list, Iris iris){
        List<Knn> result = list.stream()
                .sorted()
                .limit(k)
                .collect(Collectors.toList());
        int countIrVi = 0;
        int countIrVer = 0;
        int countIrSer = 0;
        for(Knn i : result){
            if(i.getIris().equals("Iris-virginica") ){
                countIrVi++;
            }
            if(i.getIris().equals("Iris-versicolor") ){
                countIrVer++;
            }
            if(i.getIris().equals("Iris-setosa") ){
                countIrSer++;
            }
        }

        if(countIrVi > countIrVer && countIrVi > countIrSer){
            iris.setName("Iris-virginica");
        }
        else if(countIrVer > countIrVi && countIrVer > countIrSer){
            iris.setName("Iris-versicolor");
        }
        else {
            iris.setName("Iris-setosa");
        }

    }

    public static double getAccurancy(){
        double count = 0;

        for(int i = 0; i < dataTest.size(); i++){
            if(dataTest.get(i).getName().equals(storeIrisName.get(i))){
                count++;
            }
        }

        return count/dataTest.size();
    }
    public  static void showList (List<Iris> list) {
        for(Iris iris : list) {
            System.out.println(iris);
        }
    }

}
