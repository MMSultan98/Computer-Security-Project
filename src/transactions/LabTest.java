package transactions;

import java.util.HashMap;

public class LabTest extends Transaction {

    private String testName;
    private HashMap<String, String> results;


    public LabTest(int doctorID, int patientID, String date, String testName, HashMap<String,String> results) {
        super(doctorID, patientID, date);
        this.testName = testName;
        this.results = results;
    }

    public String getTestName() {
        return this.testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public HashMap<String,String> getResults() {
        return this.results;
    }

    public void setResults(HashMap<String,String> results) {
        this.results = results;
    }

}
