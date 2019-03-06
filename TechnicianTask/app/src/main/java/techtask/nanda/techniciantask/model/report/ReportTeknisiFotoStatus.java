package techtask.nanda.techniciantask.model.report;

import java.util.List;

import techtask.nanda.techniciantask.model.Message;
import techtask.nanda.techniciantask.model.TeamLeader;

/**
 * Created by ASUS on 03/02/2019.
 */

public class ReportTeknisiFotoStatus {
    private Message result;
    private int req_status;
    private String req_msg;

    public Message getResult() {
        return result;
    }

    public void setResult(Message result) {
        this.result = result;
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
