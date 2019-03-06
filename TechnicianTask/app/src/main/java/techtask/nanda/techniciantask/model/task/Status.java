package techtask.nanda.techniciantask.model.task;

import java.util.ArrayList;

/**
 * Created by ASUS on 09/12/2018.
 */

public class Status {
    private ArrayList<Result> results;
    private int req_status;
    private String req_msg;

    public Status(ArrayList<Result> results, int req_status, String req_msg) {
        this.results = results;
        this.req_status = req_status;
        this.req_msg = req_msg;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public void setResults(ArrayList<Result> results) {
        this.results = results;
    }

    public int getReq_status() {
        return req_status;
    }

    public void setReq_status(int req_status) {
        this.req_status = req_status;
    }

    public String getReq_msg() {
        return req_msg;
    }

    public void setReq_msg(String req_msg) {
        this.req_msg = req_msg;
    }
}
