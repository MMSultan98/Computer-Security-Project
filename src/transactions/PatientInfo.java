package transactions;

import java.util.HashMap;

public class PatientInfo extends Transaction {

    private String name, age, weight, height, sex;
    private HashMap<String, String> initialMeasurements;


    public PatientInfo(int doctorID, int patientID, String date, String name, String age, String weight, String height, String sex, HashMap<String,String> initialMeasurements) {
        super(doctorID, patientID, date);
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
        this.initialMeasurements = initialMeasurements;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return this.age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public HashMap<String,String> getInitialMeasurements() {
        return this.initialMeasurements;
    }

    public void setInitialMeasurements(HashMap<String,String> initialMeasurements) {
        this.initialMeasurements = initialMeasurements;
    }

}
