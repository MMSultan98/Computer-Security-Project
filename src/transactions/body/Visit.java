package transactions.body;

import java.util.HashMap;
import transactions.TransactionBody;

public class Visit extends TransactionBody {
    
    private String reason, diagnosis;
    private HashMap<String, String> measurements, prescription;


    public Visit(String reason, String diagnosis, HashMap<String,String> measurements, HashMap<String,String> prescription) {
        super();
        this.reason = reason;
        this.diagnosis = diagnosis;
        this.measurements = measurements;
        this.prescription = prescription;
    }


    public String getReason() {
        return this.reason;
    }

    public String getDiagnosis() {
        return this.diagnosis;
    }

    public HashMap<String,String> getMeasurements() {
        return this.measurements;
    }

    public HashMap<String,String> getPrescription() {
        return this.prescription;
    }
    
}
