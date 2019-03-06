package techtask.nanda.techniciantask.model;

import java.util.List;

/**
 * Created by nandana.samudera on 14/12/2018.
 */

public class TaskPengerjaanStatus {
    private List<TaskPengerjaan> result;
    private int req_status;
    private String req_msg;

    public TaskPengerjaanStatus(List<TaskPengerjaan> results, int req_status, String req_msg) {
        this.result = results;
        this.req_status = req_status;
        this.req_msg = req_msg;
    }

    public List<TaskPengerjaan> getResults() {
        return result;
    }

    public void setResults(List<TaskPengerjaan> results) {
        this.result = results;
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
