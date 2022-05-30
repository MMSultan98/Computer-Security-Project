package transactions;

import java.util.HashMap;

public class Visit extends Transaction {
    
    private String reason, diagnosis;
    private HashMap<String, String> measurements, prescription;


    public Visit(int doctorID, int patientID, String date, String reason, String diagnosis, HashMap<String,String> measurements, HashMap<String,String> prescription) {
        super(doctorID, patientID, date);
        this.reason = reason;
        this.diagnosis = diagnosis;
        this.measurements = measurements;
        this.prescription = prescription;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDiagnosis() {
        return this.diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public HashMap<String,String> getMeasurements() {
        return this.measurements;
    }

    public void setMeasurements(HashMap<String,String> measurements) {
        this.measurements = measurements;
    }

    public HashMap<String,String> getPrescription() {
        return this.prescription;
    }

    public void setPrescription(HashMap<String,String> prescription) {
        this.prescription = prescription;
    }    

}
